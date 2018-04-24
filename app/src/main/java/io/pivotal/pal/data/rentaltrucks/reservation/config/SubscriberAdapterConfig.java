package io.pivotal.pal.data.rentaltrucks.reservation.config;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventSubscriberAdapter;
import io.pivotal.pal.data.rentaltrucks.event.ReservationInitializedEvent;
import io.pivotal.pal.data.rentaltrucks.event.ReservationRequestedEvent;
import io.pivotal.pal.data.rentaltrucks.event.TruckAvailableEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.handler.FinalizeReservationEventSubscriber;
import io.pivotal.pal.data.rentaltrucks.reservation.handler.SaveReservationEventSubscriber;
import io.pivotal.pal.data.rentaltrucks.reservation.handler.TruckAvailabilityEventSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberAdapterConfig {

    @Bean
    public AsyncEventSubscriberAdapter<ReservationRequestedEvent> saveReservationSubscriberAdapter(SaveReservationEventSubscriber subscriber) {
        return new AsyncEventSubscriberAdapter<>("reservation-requested", subscriber);
    }

    @Bean
    public AsyncEventSubscriberAdapter<ReservationInitializedEvent> truckAvailabilitySubscriberAdapter(TruckAvailabilityEventSubscriber subscriber) {
        return new AsyncEventSubscriberAdapter<>("reservation-initialized", subscriber);
    }

    @Bean
    public AsyncEventSubscriberAdapter<TruckAvailableEvent> finalizeReservationSubscriberAdapter(FinalizeReservationEventSubscriber subscriber) {
        return new AsyncEventSubscriberAdapter<>("truck-available", subscriber);
    }
}
