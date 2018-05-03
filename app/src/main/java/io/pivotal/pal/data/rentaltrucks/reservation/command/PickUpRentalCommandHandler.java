package io.pivotal.pal.data.rentaltrucks.reservation.command;

import io.pivotal.pal.data.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.framework.event.SyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckPickedUpEvent;
import org.springframework.stereotype.Component;

@Component
public class PickUpRentalCommandHandler implements SyncEventHandler<PickUpRentalCommandDto, Void> {

    private final AsyncEventPublisher<TruckPickedUpEvent> eventPublisher;

    public PickUpRentalCommandHandler(AsyncEventPublisher<TruckPickedUpEvent> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void pickUpTruck(PickUpRentalCommandDto commandDto) {

        // query db for truck by truckId
        // put other truck attributes into event ??
        //  - pickupMileage frmo truck
        //  - pickupDate from res, customername from res

        TruckPickedUpEvent event = new TruckPickedUpEvent(commandDto.getConfirmationNumber(), commandDto.getTruckVin());
        eventPublisher.publish(event);
    }

    @Override
    public Void onEvent(PickUpRentalCommandDto commandDto) {

        // query db for truck by truckId
        // put other truck attributes into event ??
        //  - pickupMileage frmo truck
        //  - pickupDate from res, customername from res

        TruckPickedUpEvent event = new TruckPickedUpEvent(commandDto.getConfirmationNumber(), commandDto.getTruckVin());
        eventPublisher.publish(event);

        return null;
    }
}
