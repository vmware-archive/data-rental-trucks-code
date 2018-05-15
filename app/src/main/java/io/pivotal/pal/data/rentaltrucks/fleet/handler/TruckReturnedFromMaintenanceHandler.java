package io.pivotal.pal.data.rentaltrucks.fleet.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckReturnedFromMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckService;
import org.springframework.stereotype.Component;

@Component
public class TruckReturnedFromMaintenanceHandler implements AsyncEventHandler<TruckReturnedFromMaintenanceEvent> {

    private final TruckService truckService;

    public TruckReturnedFromMaintenanceHandler(TruckService truckService) {
        this.truckService = truckService;
    }

    @Override
    public void onEvent(TruckReturnedFromMaintenanceEvent event) {
        truckService.returnTruckFromMaintenance(event.getTruckVin(), event.getEndDate());
    }
}
