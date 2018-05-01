package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckDroppedOffEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Rental;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.RentalManager;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CompleteDropOffEventHandler implements AsyncEventHandler<TruckDroppedOffEvent> {

    private static final Logger logger = LoggerFactory.getLogger(CompleteDropOffEventHandler.class);

    private final RentalManager rentalManager;
    private final TruckManager truckManager;

    public CompleteDropOffEventHandler(RentalManager rentalManager,
                                       TruckManager truckManager) {
        this.rentalManager = rentalManager;
        this.truckManager = truckManager;
    }

    @Override
    public void onEvent(TruckDroppedOffEvent data) {
        logger.info("completing dropoff for event={}", data);

        // TODO: revisit consistency between the following operations

        // update rental to RETURNED, record the dropOffMileage
        Rental rental = rentalManager.dropoffRental(data.getConfirmationNumber(), data.getDropOffMileage());
        logger.info("saved updated rental={}", rental);

        // update truck status to AVAILABLE, mileage to dropOffMileage
        Truck truck = truckManager.returnTruckToYard(rental.getTruckVin(), data.getDropOffMileage());
        logger.info("saved updated truck={}", truck);
    }
}
