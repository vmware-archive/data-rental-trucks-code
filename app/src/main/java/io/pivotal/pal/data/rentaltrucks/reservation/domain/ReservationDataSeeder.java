package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class ReservationDataSeeder {

    private final ReservationRepository repository;

    public ReservationDataSeeder(ReservationRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void postConstruct() {
        Reservation reservation = new Reservation(
                "some-confirmation-number",
                "some-status",
                LocalDate.of(2018, 01, 01),
                LocalDate.of(2018, 01, 10),
                "some-customer-name"
        );

        repository.save(reservation);
    }
}
