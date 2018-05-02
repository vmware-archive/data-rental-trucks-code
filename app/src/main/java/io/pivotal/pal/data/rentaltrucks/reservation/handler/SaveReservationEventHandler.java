package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.ReservationRequestedEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationService;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationRequest;
import org.springframework.stereotype.Component;

@Component
public class SaveReservationEventHandler implements AsyncEventHandler<ReservationRequestedEvent> {

    private final ReservationService reservationService;

    public SaveReservationEventHandler(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public void onEvent(ReservationRequestedEvent data) {

        ReservationRequest reservationRequest = new ReservationRequest(
                data.getPickupDate(),
                data.getDropoffDate(),
                data.getCustomerName(),
                data.getConfirmationNumber()
        );

        reservationService.createReservation(reservationRequest);
    }
}
