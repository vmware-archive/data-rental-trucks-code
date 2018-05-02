package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckEnteredMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckService;
import org.springframework.stereotype.Component;

@Component
public class TruckOutOfServiceHandler implements AsyncEventHandler<TruckEnteredMaintenanceEvent> {

    private final TruckService truckService;

    public TruckOutOfServiceHandler(TruckService truckService) {
        this.truckService = truckService;
    }

    @Override
    public void onEvent(TruckEnteredMaintenanceEvent data) {

        // update the truck status to OUT_OF_SERVICE
        truckService.withdrawTruckFromYardToSendToMaintenance(data.getTruckVin());
    }
}
