package io.pivotal.pal.data.rentaltrucks.reservation.config;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventSubscriberAdapter;
import io.pivotal.pal.data.rentaltrucks.event.*;
import io.pivotal.pal.data.rentaltrucks.reservation.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberAdapterConfig {

    @Bean
    public AsyncEventSubscriberAdapter<ReservationRequestedEvent> saveReservationSubscriberAdapter(SaveReservationEventHandler handler) {
        return new AsyncEventSubscriberAdapter<>("reservation-requested", handler);
    }

    @Bean
    public AsyncEventSubscriberAdapter<ReservationInitializedEvent> truckAvailabilitySubscriberAdapter(TruckAvailabilityEventHandler handler) {
        return new AsyncEventSubscriberAdapter<>("reservation-initialized", handler);
    }

    @Bean
    public AsyncEventSubscriberAdapter<TruckAvailableEvent> finalizeReservationSubscriberAdapter(FinalizeConfirmationEventHandler handler) {
        return new AsyncEventSubscriberAdapter<>("truck-available", handler);
    }

    @Bean
    public AsyncEventSubscriberAdapter<TruckPickedUpEvent> completePickUpEventSubscriber(CompletePickupEventHandler handler) {
        return new AsyncEventSubscriberAdapter<>("truck-picked-up", handler);
    }

    @Bean
    public AsyncEventSubscriberAdapter<TruckDroppedOffEvent> completeDropOffEventSubscriber(CompleteDropOffEventHandler handler) {
        return new AsyncEventSubscriberAdapter<>("truck-dropped-off", handler);
    }
}
