package io.pivotal.pal.data.rentaltrucks.fleet.config;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventSubscriberAdapter;
import io.pivotal.pal.data.rentaltrucks.event.*;
import io.pivotal.pal.data.rentaltrucks.fleet.handler.ReturnTruckToYardHandler;
import io.pivotal.pal.data.rentaltrucks.fleet.handler.WithdrawTruckFromYardHandler;
import io.pivotal.pal.data.rentaltrucks.reservation.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("fleetSubscriberAdapterConfig")
public class SubscriberAdapterConfig {

    @Bean
    public AsyncEventSubscriberAdapter<TruckDroppedOffEvent> returnTruckToYardSubscriberAdapter(ReturnTruckToYardHandler handler) {
        return new AsyncEventSubscriberAdapter<>("truck-dropped-off", handler);
    }

    @Bean
    public AsyncEventSubscriberAdapter<TruckPickedUpEvent> withdrawTruckFromYardSubscriberAdapter(WithdrawTruckFromYardHandler handler) {
        return new AsyncEventSubscriberAdapter<>("truck-picked-up", handler);
    }

    @Bean
    public AsyncEventSubscriberAdapter<TruckEnteredMaintenanceEvent> truckEnteredMaintenanceSubscriberAdapter(TruckEnteredMaintenanceHandler handler) {
        return new AsyncEventSubscriberAdapter<>("truck-entered-maintenance", handler);
    }
}
