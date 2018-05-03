package io.pivotal.pal.data.rentaltrucks.fleet.command;

import io.pivotal.pal.data.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.framework.event.SyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckReturnedFromMaintenanceEvent;
import org.springframework.stereotype.Component;

@Component
public class TruckReturnsFromMaintenanceCommandHandler implements SyncEventHandler<TruckReturnsFromMaintenanceCommandDto, Void> {

    private final AsyncEventPublisher<TruckReturnedFromMaintenanceEvent> eventPublisher;

    public TruckReturnsFromMaintenanceCommandHandler(AsyncEventPublisher<TruckReturnedFromMaintenanceEvent> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Void onEvent(TruckReturnsFromMaintenanceCommandDto commandDto) {

        // publish truck returned from maintenance event
        TruckReturnedFromMaintenanceEvent event =
                new TruckReturnedFromMaintenanceEvent(commandDto.getTruckVin(), commandDto.getEndDate());
        eventPublisher.publish(event);

        return null;
    }
}
