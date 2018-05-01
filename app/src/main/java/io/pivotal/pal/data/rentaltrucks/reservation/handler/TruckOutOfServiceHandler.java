package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckEnteredMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckManager;
import org.springframework.stereotype.Component;

@Component
public class TruckOutOfServiceHandler implements AsyncEventHandler<TruckEnteredMaintenanceEvent> {

    private final TruckManager truckManager;

    public TruckOutOfServiceHandler(TruckManager truckManager) {
        this.truckManager = truckManager;
    }

    @Override
    public void onEvent(TruckEnteredMaintenanceEvent data) {

        // update the truck status to OUT_OF_SERVICE
        truckManager.withdrawTruckFromYardToSendToMaintenance(data.getTruckVin());
    }
}
