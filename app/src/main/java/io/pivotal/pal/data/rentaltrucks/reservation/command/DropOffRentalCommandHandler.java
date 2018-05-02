package io.pivotal.pal.data.rentaltrucks.reservation.command;

import io.pivotal.pal.data.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltrucks.event.TruckDroppedOffEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Rental;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.RentalManager;
import org.springframework.stereotype.Component;

@Component
public class DropOffRentalCommandHandler {

    private final RentalManager rentalManager;
    private final AsyncEventPublisher<TruckDroppedOffEvent> eventPublisher;

    public DropOffRentalCommandHandler(RentalManager rentalManager,
                                       AsyncEventPublisher<TruckDroppedOffEvent> eventPublisher) {
        this.rentalManager = rentalManager;
        this.eventPublisher = eventPublisher;
    }

    public void dropOffRental(DropOffRentalCommandDto commandDto) {
        // find truck vin by looking up rental
        Rental rental = rentalManager.findByConfirmationNumber(commandDto.getConfirmationNumber());

        // emit truck dropped off event
        TruckDroppedOffEvent event =
                new TruckDroppedOffEvent(commandDto.getConfirmationNumber(), rental.getTruckVin(), commandDto.getDropOffMileage());
        eventPublisher.publish(event);
    }
}
