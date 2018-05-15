package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckReturnedFromMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckService;
import org.springframework.stereotype.Component;

@Component
public class TruckBackToServiceHandler implements AsyncEventHandler<TruckReturnedFromMaintenanceEvent> {

    private final TruckService truckService;

    public TruckBackToServiceHandler(TruckService truckService) {
        this.truckService = truckService;
    }

    @Override
    public void onEvent(TruckReturnedFromMaintenanceEvent event) {

        // update the truck status to AVAILABLE
        truckService.returnTruckToYardFromMaintenance(event.getTruckVin());
    }
}
