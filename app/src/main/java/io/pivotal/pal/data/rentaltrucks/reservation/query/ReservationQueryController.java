package io.pivotal.pal.data.rentaltrucks.reservation.query;

import io.pivotal.pal.data.rentaltrucks.reservation.domain.Reservation;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationQueryController {

    private final ReservationRepository repository;

    public ReservationQueryController(ReservationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/reservations")
    public Iterable<Reservation> listReservations() {
        return repository.findAll();
    }
}
