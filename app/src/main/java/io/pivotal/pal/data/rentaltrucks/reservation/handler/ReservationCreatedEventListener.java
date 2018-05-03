package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.rentaltrucks.reservation.domain.Reservation;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationRepository;
import org.springframework.stereotype.Component;

@Component
public class ReservationCreatedEventListener {

    private final ReservationRepository reservationRepository;

    public ReservationCreatedEventListener(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void handleEvent(Reservation.ReservationCreatedDomainEvent event) {
        // fetch aggregate from repository
        Reservation reservation = reservationRepository.findOne(event.getReservation().getConfirmationNumber());

        // invoke handler method on aggregate
//        reservation.handle

        // save to repository

    }

}
