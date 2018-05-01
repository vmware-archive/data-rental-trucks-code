package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RentalManager {

    private final RentalRepository rentalRepository;

    public RentalManager(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public Rental pickupReservedTruck(Reservation reservation, Truck truck) {
        Rental rental = new Rental(
                reservation.getConfirmationNumber(),
                "PICKED_UP",
                truck.getVin(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getCustomerName(),
                truck.getMileage()
        );
        Rental savedRental = rentalRepository.save(rental);

        return savedRental;
    }

    public void dropoffRental(Rental rental, LocalDate dropoffDate, Integer dropoffMileage) {
        rental.dropoff(dropoffDate, dropoffMileage);
        rentalRepository.save(rental);

        // send dropoff receipt
    }

    public Rental dropoffRental(String confirmationNumber, Integer dropOffMileage) {
        Rental rental = rentalRepository.findOne(confirmationNumber);
        rental.dropoff(rental.getScheduledDropoffDate(), dropOffMileage);

        return rentalRepository.save(rental);
    }

    public Rental findByConfirmationNumber(String confirmationNumber) {
        return rentalRepository.findOne(confirmationNumber);
    }
}
