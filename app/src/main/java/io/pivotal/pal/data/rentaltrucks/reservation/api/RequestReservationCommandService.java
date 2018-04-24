package io.pivotal.pal.data.rentaltrucks.reservation.api;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltrucks.event.ReservationRequestedEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ConfirmationNumberFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RequestReservationCommandService {

    private final AsyncEventPublisher<ReservationRequestedEvent> eventPublisher;
    private final ConfirmationNumberFactory factory;

    public RequestReservationCommandService(AsyncEventPublisher<ReservationRequestedEvent> eventPublisher,
                                            ConfirmationNumberFactory factory) {
        this.eventPublisher = eventPublisher;
        this.factory = factory;
    }

    public String rentTruck(RequestReservationCommandDto commandDto) {

        // generate confirmation number
        String confirmationNumber = factory.make();

        // emit the ReservationRequested event
        ReservationRequestedEvent event = new ReservationRequestedEvent(
                LocalDate.parse(commandDto.getPickupDate()),
                LocalDate.parse(commandDto.getDropoffDate()),
                commandDto.getCustomerName(),
                confirmationNumber
        );
        eventPublisher.publish(event);

        return confirmationNumber;
    }
}
