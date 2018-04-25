package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RentalManager {

    private final RentalRepository rentalRepository;

    public RentalManager(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    // returns confirmationNumber to identify Rental
    public String convertToRental(Reservation reservation, Integer pickupMileage) {
        Rental rental = new Rental(
                reservation.getConfirmationNumber(),
                "PICKED_UP",
                reservation.getStartDate(),
                reservation.getEndDate(),
                null,
                reservation.getCustomerName(),
                pickupMileage,
                null
        );
        rentalRepository.save(rental);

        return reservation.getConfirmationNumber();
    }

    public void dropoffRental(Rental rental, LocalDate dropoffDate, Integer dropoffMileage) {
        rental.dropoff(dropoffDate, dropoffMileage);
        rentalRepository.save(rental);

        // send dropoff receipt
    }
}
