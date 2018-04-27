package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckEnteredMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckRepository;
import org.springframework.stereotype.Component;

@Component
public class TruckOutOfServiceHandler implements AsyncEventHandler<TruckEnteredMaintenanceEvent> {

    private final TruckRepository truckRepository;

    public TruckOutOfServiceHandler(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public void onEvent(TruckEnteredMaintenanceEvent data) {

        // update the truck status to OUT_OF_SERVICE
        Truck truck = truckRepository.findOne(data.getTruckVin());
        truck.withdrawFromYardToSendToMaintenance();
        truckRepository.save(truck);
    }
}
