package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.stereotype.Component;

@Component
public class ReservationManager {

    private final ReservationRepository repository;
    private final ConfirmationNumberFactory factory;

    public ReservationManager(ReservationRepository repository, ConfirmationNumberFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public String requestReservation(ReservationRequest reservationRequest) {

        // generate a confirmation number
        String confirmationNumber = factory.make();

        // new up a res
        Reservation reservation = new Reservation(
                confirmationNumber,
                "REQUESTED",
                reservationRequest.getPickupDate(),
                reservationRequest.getDropoffDate(),
                reservationRequest.getCustomerName()
        );

        // persist ???
        repository.save(reservation);

        return confirmationNumber;
    }

}
