package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckPickedUpEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.*;
import org.springframework.stereotype.Component;

@Component
public class CompletePickupEventHandler implements AsyncEventHandler<TruckPickedUpEvent> {

    private final ReservationService reservationService;
    private final RentalService rentalService;
    private final TruckService truckService;

    public CompletePickupEventHandler(ReservationService reservationService,
                                      RentalService rentalService,
                                      TruckService truckService) {
        this.reservationService = reservationService;
        this.rentalService = rentalService;
        this.truckService = truckService;
    }

    @Override
    public void onEvent(TruckPickedUpEvent event) {
        // TODO: revisit consistency between the following operations

        // update truck to RENTED status
        Truck truck = truckService.withdrawTruckFromYard(event.getTruckVin());

        // update reservation to COMPLETED status
        Reservation reservation = reservationService.complete(event.getConfirmationNumber());

        // create rental in PICKED_UP status
        rentalService.pickupReservedTruck(reservation, truck);
    }
}
