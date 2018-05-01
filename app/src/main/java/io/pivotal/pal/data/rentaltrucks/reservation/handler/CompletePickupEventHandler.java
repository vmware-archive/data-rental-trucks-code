package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckPickedUpEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.*;
import org.springframework.stereotype.Component;

@Component
public class CompletePickupEventHandler implements AsyncEventHandler<TruckPickedUpEvent> {

    private final ReservationManager reservationManager;
    private final RentalManager rentalManager;
    private final TruckManager truckManager;

    public CompletePickupEventHandler(ReservationManager reservationManager,
                                      RentalManager rentalManager,
                                      TruckManager truckManager) {
        this.reservationManager = reservationManager;
        this.rentalManager = rentalManager;
        this.truckManager = truckManager;
    }

    @Override
    public void onEvent(TruckPickedUpEvent data) {
        // TODO: revisit consistency between the following operations

        // update truck to RENTED status
        Truck truck = truckManager.withdrawTruckFromYard(data.getTruckVin());

        // update reservation to COMPLETED status
        Reservation reservation = reservationManager.complete(data.getConfirmationNumber());

        // create rental in PICKED_UP status
        rentalManager.pickupReservedTruck(reservation, truck);
    }
}
