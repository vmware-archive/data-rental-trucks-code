package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.ReservationInitializedEvent;
import org.springframework.stereotype.Component;

@Component
public class TruckAvailabilityEventSubscriber implements AsyncEventHandler<ReservationInitializedEvent> {

    @Override
    public void onEvent(ReservationInitializedEvent data) {
        // check the availaility of trucks on the desired date range
        // could be counter type table in HSQL keyed on reserveDate=trucksOnHand

        // if available, emit reservation available event

        // if not, emit reservation not available event
    }
}
