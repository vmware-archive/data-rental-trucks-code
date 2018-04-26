package io.pivotal.pal.data.rentaltrucks.reservation.config;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventSubscriberAdapter;
import io.pivotal.pal.data.rentaltrucks.event.*;
import io.pivotal.pal.data.rentaltrucks.reservation.handler.CompletePickupEventHandler;
import io.pivotal.pal.data.rentaltrucks.reservation.handler.FinalizeConfirmationEventHandler;
import io.pivotal.pal.data.rentaltrucks.reservation.handler.SaveReservationEventHandler;
import io.pivotal.pal.data.rentaltrucks.reservation.handler.TruckAvailabilityEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberAdapterConfig {

    @Bean
    public AsyncEventSubscriberAdapter<ReservationRequestedEvent> saveReservationSubscriberAdapter(SaveReservationEventHandler subscriber) {
        return new AsyncEventSubscriberAdapter<>("reservation-requested", subscriber);
    }

    @Bean
    public AsyncEventSubscriberAdapter<ReservationInitializedEvent> truckAvailabilitySubscriberAdapter(TruckAvailabilityEventHandler subscriber) {
        return new AsyncEventSubscriberAdapter<>("reservation-initialized", subscriber);
    }

    @Bean
    public AsyncEventSubscriberAdapter<TruckAvailableEvent> finalizeReservationSubscriberAdapter(FinalizeConfirmationEventHandler subscriber) {
        return new AsyncEventSubscriberAdapter<>("truck-available", subscriber);
    }

    @Bean
    public AsyncEventSubscriberAdapter<TruckPickedUpEvent> completePickUpEventSubscriber(CompletePickupEventHandler subscriber) {
        return new AsyncEventSubscriberAdapter<>("truck-pick-up", subscriber);
    }
}
