package io.pivotal.pal.data.rentaltrucks.fleet.config;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.framework.event.DefaultAsyncEventPublisher;
import io.pivotal.pal.data.rentaltrucks.event.TruckEnteredMaintenanceEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("fleetEventPublisherConfig")
public class EventPublisherConfig {

    @Bean
    public AsyncEventPublisher<TruckEnteredMaintenanceEvent> truckEnteredMaintenanceEventPublisher() {
        return new DefaultAsyncEventPublisher<>("truck-entered-maintenance");
    }
}
