package io.pivotal.pal.data.rentaltrucks.fleet.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckEnteredMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckService;
import org.springframework.stereotype.Component;

@Component
public class TruckEnteredMaintenanceHandler implements AsyncEventHandler<TruckEnteredMaintenanceEvent> {

    private final TruckService truckService;

    public TruckEnteredMaintenanceHandler(TruckService truckService) {
        this.truckService = truckService;
    }

    @Override
    public void onEvent(TruckEnteredMaintenanceEvent data) {
        truckService.moveTruckToMaintenance(data.getTruckVin(), data.getStartDate());
    }
}
