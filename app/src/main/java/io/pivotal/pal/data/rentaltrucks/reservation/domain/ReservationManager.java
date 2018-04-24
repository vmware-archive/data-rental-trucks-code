package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.stereotype.Component;

@Component
public class ReservationManager {

    // consider changing to:
    // createReservation (copied from ollie)
    // - saves to repository

    public Reservation requestReservation(ReservationRequest reservationRequest) {

        // could be validation here
        // or does reservation manager check for inventory here?

        // initializes with status=requested

        // new up a reservation
        Reservation reservation = new Reservation(
                reservationRequest.getConfirmationNumber(),
                "REQUESTED",
                reservationRequest.getPickupDate(),
                reservationRequest.getDropoffDate(),
                reservationRequest.getCustomerName()
        );


        // emit reservation initialized event

        return reservation;
    }

    public void confirmReservation(Reservation reservation) {
        // advances status to confirmed
        // ideally assigns a confirmation number but too bad
        //
    }
}
