package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckPickedUpEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.*;
import org.springframework.stereotype.Component;

@Component
public class CompletePickupEventHandler implements AsyncEventHandler<TruckPickedUpEvent> {

    private final RentalManager rentalManager;
    private final ReservationRepository reservationRepository;
    private final TruckRepository truckRepository;

    public CompletePickupEventHandler(RentalManager rentalManager, ReservationRepository reservationRepository, TruckRepository truckRepository) {
        this.rentalManager = rentalManager;
        this.reservationRepository = reservationRepository;
        this.truckRepository = truckRepository;
    }

    @Override
    public void onEvent(TruckPickedUpEvent data) {
        Reservation reservation = reservationRepository.findOne(data.getConfirmationNumber());

        Truck truck = truckRepository.findOne(data.getTruckId());

        Rental rental = rentalManager.pickupReservedTruck(reservation, truck);
        // do something with the rental like send another event ??
    }
}
