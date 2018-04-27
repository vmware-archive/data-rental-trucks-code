package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.ReservationRequestedEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationManager;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationRequest;
import org.springframework.stereotype.Component;

@Component
public class SaveReservationEventHandler implements AsyncEventHandler<ReservationRequestedEvent> {

    private final ReservationManager reservationManager;

    public SaveReservationEventHandler(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    @Override
    public void onEvent(ReservationRequestedEvent data) {

        ReservationRequest reservationRequest = new ReservationRequest(
                data.getPickupDate(),
                data.getDropoffDate(),
                data.getCustomerName(),
                data.getConfirmationNumber()
        );

        reservationManager.createReservation(reservationRequest);
    }
}
