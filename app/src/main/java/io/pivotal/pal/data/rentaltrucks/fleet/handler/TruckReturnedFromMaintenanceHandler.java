package io.pivotal.pal.data.rentaltrucks.fleet.handler;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.fleet.command.TruckReturnedFromMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckRepository;
import org.springframework.stereotype.Component;

@Component
public class TruckReturnedFromMaintenanceHandler implements AsyncEventHandler<TruckReturnedFromMaintenanceEvent> {

    private final TruckRepository truckRepository;

    public TruckReturnedFromMaintenanceHandler(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public void onEvent(TruckReturnedFromMaintenanceEvent data) {

        // return the truck to the yard
        Truck truck = truckRepository.findOne(data.getTruckVin());
        truck.returnFromMaintenance(data.getEndDate());
        truckRepository.save(truck);
    }
}
