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
        // rehydrate a reservation from confirmation number
        Reservation reservation = reservationRepository.findOne(data.getConfirmationNumber());

        // TODO: revisit consistency between the following operations:
        // - update status on reservation
        // - update status on truck
        // - creating the rental

        // update truck to RENTED status
        Truck truck = truckRepository.findOne(data.getTruckVin());
        truck.setStatus("RENTED");
        truckRepository.save(truck);

        // update reservation to COMPLETED status
        reservation.setStatus("COMPLETED");
        reservationRepository.save(reservation);

        // create rental in PICKED_UP status
        Rental rental = rentalManager.pickupReservedTruck(reservation, truck);
    }
}
