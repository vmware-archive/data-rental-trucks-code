package io.pivotal.pal.data.rentaltrucks.fleet.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckDroppedOffEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckRepository;
import org.springframework.stereotype.Component;

@Component
public class ReturnTruckToYardHandler implements AsyncEventHandler<TruckDroppedOffEvent> {

    private final TruckRepository truckRepository;

    public ReturnTruckToYardHandler(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public void onEvent(TruckDroppedOffEvent data) {

        // update status and mileage
        Truck truck = truckRepository.findOne(data.getTruckVin());
        truck.returnToYard(data.getDropOffMileage());
        truckRepository.save(truck);
    }
}
