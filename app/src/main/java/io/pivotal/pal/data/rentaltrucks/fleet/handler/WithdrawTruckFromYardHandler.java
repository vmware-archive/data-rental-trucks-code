package io.pivotal.pal.data.rentaltrucks.fleet.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckPickedUpEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckService;
import org.springframework.stereotype.Component;

@Component
public class WithdrawTruckFromYardHandler implements AsyncEventHandler<TruckPickedUpEvent> {

    private final TruckService truckService;

    public WithdrawTruckFromYardHandler(TruckService truckService) {
        this.truckService = truckService;
    }

    @Override
    public void onEvent(TruckPickedUpEvent event) {
        truckService.withdrawTruckFromyard(event.getTruckVin());
    }
}
