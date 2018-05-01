package io.pivotal.pal.data.rentaltrucks.fleet.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckReturnedFromMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckManager;
import org.springframework.stereotype.Component;

@Component
public class TruckReturnedFromMaintenanceHandler implements AsyncEventHandler<TruckReturnedFromMaintenanceEvent> {

    private final TruckManager truckManager;

    public TruckReturnedFromMaintenanceHandler(TruckManager truckManager) {
        this.truckManager = truckManager;
    }

    @Override
    public void onEvent(TruckReturnedFromMaintenanceEvent data) {
        truckManager.returnTruckFromMaintenance(data.getTruckVin(), data.getEndDate());
    }
}
