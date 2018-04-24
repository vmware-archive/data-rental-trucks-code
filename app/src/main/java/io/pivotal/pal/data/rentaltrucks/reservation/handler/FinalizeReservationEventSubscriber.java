package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckAvailableEvent;
import org.springframework.stereotype.Component;

@Component
public class FinalizeReservationEventSubscriber implements AsyncEventHandler<TruckAvailableEvent> {

    @Override
    public void onEvent(TruckAvailableEvent data) {
        // update the trucks on hand counts

        // update reservation status=confirmed

        // send an email
    }
}
