package io.pivotal.pal.data.rentaltrucks.fleet.handler;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckEnteredMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckRepository;
import org.springframework.stereotype.Component;

@Component
public class TruckEnteredMaintenanceHandler implements AsyncEventHandler<TruckEnteredMaintenanceEvent> {

    private final TruckRepository truckRepository;

    public TruckEnteredMaintenanceHandler(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public void onEvent(TruckEnteredMaintenanceEvent data) {
        // rehydrate truck using truck vin
        Truck truck = truckRepository.findOne(data.getTruckVin());

        // move truck to in maintenance and log the activity in history table
        truck.moveToMaintenance(data.getStartDate());
        truckRepository.save(truck);
    }
}
