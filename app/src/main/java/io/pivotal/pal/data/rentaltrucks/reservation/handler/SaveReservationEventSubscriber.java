package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.ReservationRequestedEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Reservation;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationManager;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationRepository;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationRequest;
import org.springframework.stereotype.Component;

@Component
public class SaveReservationEventSubscriber implements AsyncEventHandler<ReservationRequestedEvent> {

    private final ReservationManager reservationManager;
    private final ReservationRepository repository;

    public SaveReservationEventSubscriber(ReservationManager reservationManager, ReservationRepository repository) {
        this.reservationManager = reservationManager;
        this.repository = repository;
    }

    @Override
    public void onEvent(ReservationRequestedEvent data) {

        ReservationRequest reservationRequest = new ReservationRequest(
                data.getPickupDate(),
                data.getDropoffDate(),
                data.getCustomerName(),
                data.getConfirmationNumber()
        );

        Reservation reservation = reservationManager.requestReservation(reservationRequest);

        repository.save(reservation);
    }
}
