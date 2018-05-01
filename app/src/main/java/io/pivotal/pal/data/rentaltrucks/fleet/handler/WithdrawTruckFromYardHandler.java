package io.pivotal.pal.data.rentaltrucks.fleet.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckPickedUpEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckManager;
import org.springframework.stereotype.Component;

@Component
public class WithdrawTruckFromYardHandler implements AsyncEventHandler<TruckPickedUpEvent> {

    private final TruckManager truckManager;

    public WithdrawTruckFromYardHandler(TruckManager truckManager) {
        this.truckManager = truckManager;
    }

    @Override
    public void onEvent(TruckPickedUpEvent data) {
        truckManager.withdrawTruckFromyard(data.getTruckVin());
    }
}
