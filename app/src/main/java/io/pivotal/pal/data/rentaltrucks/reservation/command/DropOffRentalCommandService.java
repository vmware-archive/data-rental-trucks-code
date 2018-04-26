package io.pivotal.pal.data.rentaltrucks.reservation.command;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltrucks.event.TruckDroppedOffEvent;
import org.springframework.stereotype.Service;

@Service
public class DropOffRentalCommandService {

    private final AsyncEventPublisher<TruckDroppedOffEvent> eventPublisher;

    public DropOffRentalCommandService(AsyncEventPublisher<TruckDroppedOffEvent> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void dropOffRental(DropOffRentalCommandDto commandDto) {

        // TODO: Do we need the truck VIN from the command DTO or can we derive it here?

        // emit truck dropped off event
        TruckDroppedOffEvent event =
                new TruckDroppedOffEvent(commandDto.getConfirmationNumber(), commandDto.getTruckVin(), commandDto.getDropOffMileage());
        eventPublisher.publish(event);
    }

}
