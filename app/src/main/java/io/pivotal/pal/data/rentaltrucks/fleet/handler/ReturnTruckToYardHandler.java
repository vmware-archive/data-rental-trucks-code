package io.pivotal.pal.data.rentaltrucks.fleet.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckDroppedOffEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckManager;
import org.springframework.stereotype.Component;

@Component
public class ReturnTruckToYardHandler implements AsyncEventHandler<TruckDroppedOffEvent> {

    private final TruckManager truckManager;

    public ReturnTruckToYardHandler(TruckManager truckManager) {
        this.truckManager = truckManager;
    }

    @Override
    public void onEvent(TruckDroppedOffEvent data) {

        // update status and mileage
        truckManager.returnTruckToYard(data.getTruckVin(), data.getDropOffMileage());
    }
}
