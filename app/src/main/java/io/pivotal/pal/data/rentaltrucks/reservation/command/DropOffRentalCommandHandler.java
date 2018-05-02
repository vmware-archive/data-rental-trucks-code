package io.pivotal.pal.data.rentaltrucks.reservation.command;

import io.pivotal.pal.data.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltrucks.event.TruckDroppedOffEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Rental;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.RentalService;
import org.springframework.stereotype.Component;

@Component
public class DropOffRentalCommandHandler {

    private final RentalService rentalService;
    private final AsyncEventPublisher<TruckDroppedOffEvent> eventPublisher;

    public DropOffRentalCommandHandler(RentalService rentalService,
                                       AsyncEventPublisher<TruckDroppedOffEvent> eventPublisher) {
        this.rentalService = rentalService;
        this.eventPublisher = eventPublisher;
    }

    public void dropOffRental(DropOffRentalCommandDto commandDto) {
        // find truck vin by looking up rental
        Rental rental = rentalService.findByConfirmationNumber(commandDto.getConfirmationNumber());

        // emit truck dropped off event
        TruckDroppedOffEvent event =
                new TruckDroppedOffEvent(commandDto.getConfirmationNumber(), rental.getTruckVin(), commandDto.getDropOffMileage());
        eventPublisher.publish(event);
    }
}
