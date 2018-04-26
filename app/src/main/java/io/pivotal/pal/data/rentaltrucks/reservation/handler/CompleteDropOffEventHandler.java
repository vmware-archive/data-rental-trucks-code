package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckDroppedOffEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Rental;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.RentalRepository;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CompleteDropOffEventHandler implements AsyncEventHandler<TruckDroppedOffEvent> {

    private static final Logger logger = LoggerFactory.getLogger(CompleteDropOffEventHandler.class);

    private final RentalRepository rentalRepository;
    private final TruckRepository truckRepository;

    public CompleteDropOffEventHandler(RentalRepository rentalRepository, TruckRepository truckRepository) {
        this.rentalRepository = rentalRepository;
        this.truckRepository = truckRepository;
    }

    @Override
    public void onEvent(TruckDroppedOffEvent data) {
        logger.info("completing dropoff for event={}", data);

        // TODO: revisit consistency between the following operations

        // update rental to RETURNED, record the dropOffMileage
        Rental rental = rentalRepository.findOne(data.getConfirmationNumber());
        rental.dropoff(rental.getScheduledDropoffDate(), data.getDropOffMileage());
        rentalRepository.save(rental);

        logger.info("saved updated rental={}", rental);

        // update truck status to AVAILABLE, mileage to dropOffMileage
        Truck truck = truckRepository.findOne(rental.getTruckVin());
        truck.returnToYard(data.getDropOffMileage());
        truckRepository.save(truck);

        logger.info("saved updated truck={}", truck);
    }
}
