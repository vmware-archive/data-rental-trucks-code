package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckReturnedFromMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckRepository;
import org.springframework.stereotype.Component;

@Component
public class TruckBackToServiceHandler implements AsyncEventHandler<TruckReturnedFromMaintenanceEvent> {

    private final TruckRepository truckRepository;

    public TruckBackToServiceHandler(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public void onEvent(TruckReturnedFromMaintenanceEvent data) {

        // update the truck status to AVAILABLE
        Truck truck = truckRepository.findOne(data.getTruckVin());
        truck.returnToYardFromMaintenance();
        truckRepository.save(truck);
    }
}
