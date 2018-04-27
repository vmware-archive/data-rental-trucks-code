package io.pivotal.pal.data.rentaltrucks.fleet.config;

import io.pivotal.pal.data.framework.event.AsyncEventSubscriberAdapter;
import io.pivotal.pal.data.rentaltrucks.event.TruckDroppedOffEvent;
import io.pivotal.pal.data.rentaltrucks.event.TruckEnteredMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.event.TruckPickedUpEvent;
import io.pivotal.pal.data.rentaltrucks.event.TruckReturnedFromMaintenanceEvent;
import io.pivotal.pal.data.rentaltrucks.fleet.handler.ReturnTruckToYardHandler;
import io.pivotal.pal.data.rentaltrucks.fleet.handler.TruckEnteredMaintenanceHandler;
import io.pivotal.pal.data.rentaltrucks.fleet.handler.TruckReturnedFromMaintenanceHandler;
import io.pivotal.pal.data.rentaltrucks.fleet.handler.WithdrawTruckFromYardHandler;
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

    @Bean
    public AsyncEventSubscriberAdapter<TruckReturnedFromMaintenanceEvent> truckReturnedFromMaintenanceSubscriberAdapter(TruckReturnedFromMaintenanceHandler handler) {
        return new AsyncEventSubscriberAdapter<>("truck-returned-from-maintenance", handler);
    }
}
