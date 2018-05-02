package io.pivotal.pal.data.rentaltrucks.fleet.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckDroppedOffEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckService;
import org.springframework.stereotype.Component;

@Component
public class ReturnTruckToYardHandler implements AsyncEventHandler<TruckDroppedOffEvent> {

    private final TruckService truckService;

    public ReturnTruckToYardHandler(TruckService truckService) {
        this.truckService = truckService;
    }

    @Override
    public void onEvent(TruckDroppedOffEvent data) {

        // update status and mileage
        truckService.returnTruckToYard(data.getTruckVin(), data.getDropOffMileage());
    }
}
