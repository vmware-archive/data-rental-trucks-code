package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

import static java.util.Arrays.asList;

@Component
class ReservationDatabasePopulator {

    private final ReservationRepository reservationRepository;
    private final TruckRepository truckRepository;

    public ReservationDatabasePopulator(ReservationRepository reservationRepository, TruckRepository truckRepository) {
        this.reservationRepository = reservationRepository;
        this.truckRepository = truckRepository;
    }

    @PostConstruct
    public void populateDatabase() {
        Reservation reservation1 = new Reservation(
                "100",
                "FINALIZED",
                LocalDate.of(2018, 01, 01),
                LocalDate.of(2018, 01, 02),
                "cust 1"
        );
        Reservation reservation2 = new Reservation(
                "101",
                "FINALIZED",
                LocalDate.of(2018, 01, 02),
                LocalDate.of(2018, 01, 03),
                "cust 2"
        );
        Reservation reservation3 = new Reservation(
                "102",
                "FINALIZED",
                LocalDate.of(2018, 01, 01),
                LocalDate.of(2018, 01, 03),
                "cust 3"
        );

        reservationRepository.save(asList(reservation1, reservation2, reservation3));

        Truck truck1 = new Truck("001", "PICKED_UP", 10000);
        Truck truck2 = new Truck("002", "PICKED_UP", 10000);
        Truck truck3 = new Truck("003", "AVAILABLE", 10000);
        Truck truck4 = new Truck("004", "AVAILABLE", 10000);

        truckRepository.save(asList(truck1, truck2, truck3, truck4));
    }
}
