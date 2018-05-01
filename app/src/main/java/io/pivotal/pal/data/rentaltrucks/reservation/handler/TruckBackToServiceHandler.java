package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckReturnedFromMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckManager;
import org.springframework.stereotype.Component;

@Component
public class TruckBackToServiceHandler implements AsyncEventHandler<TruckReturnedFromMaintenanceEvent> {

    private final TruckManager truckManager;

    public TruckBackToServiceHandler(TruckManager truckManager) {
        this.truckManager = truckManager;
    }

    @Override
    public void onEvent(TruckReturnedFromMaintenanceEvent data) {

        // update the truck status to AVAILABLE
        truckManager.returnTruckToYardFromMaintenance(data.getTruckVin());
    }
}
