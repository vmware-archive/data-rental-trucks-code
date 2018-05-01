package io.pivotal.pal.data.rentaltrucks.fleet.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckEnteredMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckManager;
import org.springframework.stereotype.Component;

@Component
public class TruckEnteredMaintenanceHandler implements AsyncEventHandler<TruckEnteredMaintenanceEvent> {

    private final TruckManager truckManager;

    public TruckEnteredMaintenanceHandler(TruckManager truckManager) {
        this.truckManager = truckManager;
    }

    @Override
    public void onEvent(TruckEnteredMaintenanceEvent data) {
        truckManager.moveTruckToMaintenance(data.getTruckVin(), data.getStartDate());
    }
}
