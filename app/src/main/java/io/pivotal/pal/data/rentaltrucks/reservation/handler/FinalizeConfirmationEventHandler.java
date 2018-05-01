package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckAvailableEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FinalizeConfirmationEventHandler implements AsyncEventHandler<TruckAvailableEvent> {

    private static final Logger logger = LoggerFactory.getLogger(FinalizeConfirmationEventHandler.class);

    private final ReservationManager reservationManager;

    public FinalizeConfirmationEventHandler(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    @Override
    public void onEvent(TruckAvailableEvent data) {
        reservationManager.finalizeReservation(data.getConfirmationNumber());

        // send an email
    }
}
