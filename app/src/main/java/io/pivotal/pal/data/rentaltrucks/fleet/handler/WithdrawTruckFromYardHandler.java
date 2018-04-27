package io.pivotal.pal.data.rentaltrucks.fleet.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckPickedUpEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckRepository;
import org.springframework.stereotype.Component;

@Component
public class WithdrawTruckFromYardHandler implements AsyncEventHandler<TruckPickedUpEvent> {

    private final TruckRepository truckRepository;

    public WithdrawTruckFromYardHandler(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public void onEvent(TruckPickedUpEvent data) {
        // update the fleet truck status
        Truck truck = truckRepository.findOne(data.getTruckVin());
        truck.withdrawFromYard();
        truckRepository.save(truck);
    }
}
