package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltrucks.event.ReservationInitializedEvent;
import io.pivotal.pal.data.rentaltrucks.event.TruckAvailableEvent;
import io.pivotal.pal.data.rentaltrucks.event.TruckNotAvailableEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Reservation;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationRepository;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toList;

@Component
public class TruckAvailabilityEventHandler implements AsyncEventHandler<ReservationInitializedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(TruckAvailabilityEventHandler.class);

    private final AsyncEventPublisher<TruckAvailableEvent> truckAvailableEventPublisher;
    private final AsyncEventPublisher<TruckNotAvailableEvent> truckNotAvailableEventPublisher;
    private final ReservationRepository reservationRepository;
    private final TruckRepository truckRepository;

    public TruckAvailabilityEventHandler(AsyncEventPublisher<TruckAvailableEvent> truckAvailableEventPublisher,
                                         AsyncEventPublisher<TruckNotAvailableEvent> truckNotAvailableEventPublisher,
                                         ReservationRepository reservationRepository,
                                         TruckRepository truckRepository) {
        this.truckAvailableEventPublisher = truckAvailableEventPublisher;
        this.truckNotAvailableEventPublisher = truckNotAvailableEventPublisher;
        this.reservationRepository = reservationRepository;
        this.truckRepository = truckRepository;
    }

    @Override
    public void onEvent(ReservationInitializedEvent data) {
        logger.info("handling event: {}", data);

        // check the availaility of trucks on the desired date range
        // TODO: add predicate to filter reservations by status=FINALIZED
        Collection<Reservation> reservations =
                reservationRepository.findAllByStartDateBeforeAndEndDateAfter(data.getDropoffDate(), data.getPickupDate());
        logger.info("found reservations in window: {}", reservations);

        // for each day of our reservation initialized event, is there at least one truck available on each day?
        List<LocalDate> dateRange = DateRange
                .between(data.getPickupDate(), data.getDropoffDate())
                .collect(toList());
        logger.info("looking at date range: {}", dateRange);

        for (LocalDate day : dateRange) {
            logger.info("looking at day: {}", day);

            long truckReservedCount = reservations.stream()
                    .filter(r ->
                            day.isAfter(r.getStartDate().minus(1, DAYS)) &&
                                    day.isBefore(r.getEndDate().plus(1, DAYS)))
                    .count();
            logger.info("trucksReserved={}", truckReservedCount);

            Iterable<Truck> trucks = truckRepository.findAll();
            long truckCount = StreamSupport.stream(trucks.spliterator(), false).count();
            logger.info("truckCount={}", truckCount);

            // if no trucks available, short-circuit and abort
            if (truckReservedCount >= truckCount) {
                // emit reservation not available event
                logger.info("sending truckNotAvailable event");
                TruckNotAvailableEvent event =
                        new TruckNotAvailableEvent(data.getConfirmationNumber());
                truckNotAvailableEventPublisher.publish(event);

                return;
            }
        }

        // if available, emit reservation available event
        logger.info("sending truckAvailable event");
        TruckAvailableEvent event =
                new TruckAvailableEvent(data.getConfirmationNumber());
        truckAvailableEventPublisher.publish(event);
    }

    private static class DateRange implements Iterable<LocalDate> {

        private final LocalDate startDate;
        private final LocalDate endDate;

        DateRange(LocalDate startDate, LocalDate endDate) {
            //check that range is valid (null, start < end)
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public static Stream<LocalDate> between(LocalDate startDate, LocalDate endDate) {
            return new DateRange(startDate, endDate).stream();
        }

        @Override
        public Iterator<LocalDate> iterator() {
            return stream().iterator();
        }

        Stream<LocalDate> stream() {
            return Stream.iterate(startDate, d -> d.plusDays(1))
                    .limit(DAYS.between(startDate, endDate) + 1);
        }
    }

    static class StreamUtils {

        public static <T> Stream<T> asStream(Iterator<T> sourceIterator) {
            return asStream(sourceIterator, false);
        }

        public static <T> Stream<T> asStream(Iterator<T> sourceIterator, boolean parallel) {
            Iterable<T> iterable = () -> sourceIterator;
            return StreamSupport.stream(iterable.spliterator(), parallel);
        }
    }
}
