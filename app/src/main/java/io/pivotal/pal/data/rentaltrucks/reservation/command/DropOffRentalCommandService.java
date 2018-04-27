package io.pivotal.pal.data.rentaltrucks.reservation.command;

import io.pivotal.pal.data.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltrucks.event.TruckDroppedOffEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Rental;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.RentalRepository;
import org.springframework.stereotype.Service;

@Service
public class DropOffRentalCommandService {

    private final RentalRepository rentalRepository;
    private final AsyncEventPublisher<TruckDroppedOffEvent> eventPublisher;

    public DropOffRentalCommandService(RentalRepository rentalRepository,
                                       AsyncEventPublisher<TruckDroppedOffEvent> eventPublisher) {
        this.rentalRepository = rentalRepository;
        this.eventPublisher = eventPublisher;
    }

    public void dropOffRental(DropOffRentalCommandDto commandDto) {

        // find truck vin by looking up rental
        Rental rental = rentalRepository.findOne(commandDto.getConfirmationNumber());

        // emit truck dropped off event
        TruckDroppedOffEvent event =
                new TruckDroppedOffEvent(commandDto.getConfirmationNumber(), rental.getTruckVin(), commandDto.getDropOffMileage());
        eventPublisher.publish(event);
    }

}
