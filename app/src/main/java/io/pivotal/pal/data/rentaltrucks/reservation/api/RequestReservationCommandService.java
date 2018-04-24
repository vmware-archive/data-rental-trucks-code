package io.pivotal.pal.data.rentaltrucks.reservation.api;

import io.pivotal.pal.data.rentaltrucks.event.ReservationRequestedEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationManager;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RequestReservationCommandService {

    private final ReservationManager reservationManager;

    public RequestReservationCommandService(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }


    // FIXME: does this return value still make sense if res mgr is generating confirmation #
    public String rentTruck(RequestReservationCommandDto commandDto) {
        // thought: do i synchronously insert record(s) into the database;  so far, no

        ReservationRequest reservationRequest = new ReservationRequest(
                LocalDate.parse(commandDto.getPickupDate()),
                LocalDate.parse(commandDto.getDropoffDate()),
                commandDto.getCustomerName()
        );
        String confirmationNumber = reservationManager.requestReservation(reservationRequest);

        // emit the ReservationRequested event
        ReservationRequestedEvent event = new ReservationRequestedEvent();

        return confirmationNumber;
    }
}
