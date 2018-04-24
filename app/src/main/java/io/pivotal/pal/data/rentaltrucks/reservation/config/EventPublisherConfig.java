package io.pivotal.pal.data.rentaltrucks.reservation.config;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.framework.event.DefaultAsyncEventPublisher;
import io.pivotal.pal.data.rentaltrucks.event.ReservationRequestedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventPublisherConfig {

    @Bean
    public AsyncEventPublisher<ReservationRequestedEvent> reservationRequestedEventPublisher() {
        return new DefaultAsyncEventPublisher<>("reservation-requested");
    }
}
