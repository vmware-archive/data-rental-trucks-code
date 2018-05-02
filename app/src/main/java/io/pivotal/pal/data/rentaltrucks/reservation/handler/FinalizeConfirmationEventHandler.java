package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckAvailableEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FinalizeConfirmationEventHandler implements AsyncEventHandler<TruckAvailableEvent> {

    private static final Logger logger = LoggerFactory.getLogger(FinalizeConfirmationEventHandler.class);

    private final ReservationService reservationService;

    public FinalizeConfirmationEventHandler(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public void onEvent(TruckAvailableEvent data) {
        reservationService.finalizeReservation(data.getConfirmationNumber());

        // send an email
    }
}
