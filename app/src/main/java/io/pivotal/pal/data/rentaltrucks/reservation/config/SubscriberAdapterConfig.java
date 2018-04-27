package io.pivotal.pal.data.rentaltrucks.reservation.config;

import io.pivotal.pal.data.framework.event.AsyncEventSubscriberAdapter;
import io.pivotal.pal.data.rentaltrucks.event.*;
import io.pivotal.pal.data.rentaltrucks.reservation.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("reservationSubscriberAdapterConfig")
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

    @Bean
    public AsyncEventSubscriberAdapter<TruckReturnedFromMaintenanceEvent> truckBackToServiceEventSubscriber(TruckBackToServiceHandler handler) {
        return new AsyncEventSubscriberAdapter<>("truck-returned-from-maintenance", handler);
    }

    @Bean
    public AsyncEventSubscriberAdapter<TruckEnteredMaintenanceEvent> truckOutOfServiceEventSubscriber(TruckOutOfServiceHandler handler) {
        return new AsyncEventSubscriberAdapter<>("truck-entered-maintenance", handler);
    }
}
