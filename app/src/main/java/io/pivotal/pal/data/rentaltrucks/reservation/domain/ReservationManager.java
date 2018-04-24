package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.stereotype.Component;

@Component
public class ReservationManager {

    public Reservation requestReservation(ReservationRequest reservationRequest) {

        // could be validation here
        // or does reservation manager check for inventory here?

        // new up a reservation
        Reservation reservation = new Reservation(
                reservationRequest.getConfirmationNumber(),
                "REQUESTED",
                reservationRequest.getPickupDate(),
                reservationRequest.getDropoffDate(),
                reservationRequest.getCustomerName()
        );

        return reservation;
    }
}
