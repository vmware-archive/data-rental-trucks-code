package io.pivotal.pal.data.rentaltrucks.reservation.query;

import io.pivotal.pal.data.rentaltrucks.reservation.domain.Reservation;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationQueryController {

    private final ReservationManager reservationManager;

    public ReservationQueryController(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    @GetMapping("/reservations")
    public Iterable<Reservation> listReservations() {
        return reservationManager.listReservations();
    }
}
