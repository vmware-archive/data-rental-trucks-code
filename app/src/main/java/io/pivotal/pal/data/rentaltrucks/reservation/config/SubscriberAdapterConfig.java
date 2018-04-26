package io.pivotal.pal.data.rentaltrucks.reservation.config;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventSubscriberAdapter;
import io.pivotal.pal.data.rentaltrucks.event.*;
import io.pivotal.pal.data.rentaltrucks.reservation.handler.CompletePickupSubscriber;
import io.pivotal.pal.data.rentaltrucks.reservation.handler.FinalizeConfirmationEventSubscriber;
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
    public AsyncEventSubscriberAdapter<TruckAvailableEvent> finalizeReservationSubscriberAdapter(FinalizeConfirmationEventSubscriber subscriber) {
        return new AsyncEventSubscriberAdapter<>("truck-available", subscriber);
    }

    @Bean
    public AsyncEventSubscriberAdapter<TruckPickedUpEvent> completePickUpEventSubscriber(CompletePickupSubscriber subscriber) {
        return new AsyncEventSubscriberAdapter<>("truck-pick-up", subscriber);
    }
}
