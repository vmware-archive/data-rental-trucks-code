package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckDroppedOffEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Rental;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.RentalService;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CompleteDropOffEventHandler implements AsyncEventHandler<TruckDroppedOffEvent> {

    private static final Logger logger = LoggerFactory.getLogger(CompleteDropOffEventHandler.class);

    private final RentalService rentalService;
    private final TruckService truckService;

    public CompleteDropOffEventHandler(RentalService rentalService,
                                       TruckService truckService) {
        this.rentalService = rentalService;
        this.truckService = truckService;
    }

    @Override
    public void onEvent(TruckDroppedOffEvent data) {
        logger.info("completing dropoff for event={}", data);

        // TODO: revisit consistency between the following operations

        // update rental to RETURNED, record the dropOffMileage
        Rental rental = rentalService.dropoffRental(data.getConfirmationNumber(), data.getDropOffMileage());
        logger.info("saved updated rental={}", rental);

        // update truck status to AVAILABLE, mileage to dropOffMileage
        Truck truck = truckService.returnTruckToYard(rental.getTruckVin(), data.getDropOffMileage());
        logger.info("saved updated truck={}", truck);
    }
}
