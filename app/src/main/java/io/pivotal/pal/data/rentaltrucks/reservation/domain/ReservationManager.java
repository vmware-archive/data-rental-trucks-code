package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import io.pivotal.pal.data.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltrucks.event.ReservationInitializedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;

@Component
public class ReservationManager {

    private final ReservationRepository reservationRepository;
    private final AsyncEventPublisher<ReservationInitializedEvent> eventPublisher;

    public ReservationManager(ReservationRepository reservationRepository,
                              AsyncEventPublisher<ReservationInitializedEvent> eventPublisher) {
        this.reservationRepository = reservationRepository;
        this.eventPublisher = eventPublisher;
    }

    public Reservation createReservation(ReservationRequest reservationRequest) {

        // could be validation here

        // new up a reservation with status=requested
        Reservation reservation = new Reservation(
                reservationRequest.getConfirmationNumber(),
                "REQUESTED",
                reservationRequest.getPickupDate(),
                reservationRequest.getDropoffDate(),
                reservationRequest.getCustomerName()
        );
        reservationRepository.save(reservation);

        // emit reservation initialized event
        ReservationInitializedEvent event = new ReservationInitializedEvent(
                reservationRequest.getConfirmationNumber(),
                reservationRequest.getPickupDate(),
                reservationRequest.getDropoffDate()
        );
        eventPublisher.publish(event);

        return reservation;
    }

    public void finalizeReservation(Reservation reservation) {
        // ideally assigns a confirmation number but too bad we did this already
        reservation.finalizeConfirmation();
        reservationRepository.save(reservation);

        // what logic actually belongs in reservation?
        // what else:  update some value or history object based on date of finalize event
    }

    public void finalizeReservation(String confirmationNumber) {
        Reservation reservation = reservationRepository.findOne(confirmationNumber);

        finalizeReservation(reservation);
    }

    public Reservation complete(Reservation reservation) {
        reservation.complete();
        return reservationRepository.save(reservation);
    }

    public Reservation complete(String confirmationNumber) {
        Reservation reservation = reservationRepository.findOne(confirmationNumber);
        return complete(reservation);
    }

    public void failReservation(Reservation reservation) {
        reservation.failed();
        reservationRepository.save(reservation);
    }

    public void cancelReservation(Reservation reservation) {
        reservation.cancel();
        reservationRepository.save(reservation);
    }

    public Collection<Reservation> findReservationsBetween(LocalDate pickupDate, LocalDate dropoffDate) {
        return reservationRepository.findAllByStartDateBeforeAndEndDateAfter(dropoffDate, pickupDate);
    }

    public Iterable<Reservation> listReservations() {
        return reservationRepository.findAll();
    }
}
