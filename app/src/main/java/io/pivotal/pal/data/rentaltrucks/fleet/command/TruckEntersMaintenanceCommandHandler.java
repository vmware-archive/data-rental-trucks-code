package io.pivotal.pal.data.rentaltrucks.fleet.command;

import io.pivotal.pal.data.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.framework.event.SyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckEnteredMaintenanceEvent;
import org.springframework.stereotype.Component;

@Component
public class TruckEntersMaintenanceCommandHandler implements SyncEventHandler<TruckEntersMaintenanceCommandDto, Void> {

    private final AsyncEventPublisher<TruckEnteredMaintenanceEvent> eventPublisher;

    public TruckEntersMaintenanceCommandHandler(AsyncEventPublisher<TruckEnteredMaintenanceEvent> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Void onEvent(TruckEntersMaintenanceCommandDto commandDto) {
        // emit event for truck entered maintenance
        TruckEnteredMaintenanceEvent event =
                new TruckEnteredMaintenanceEvent(commandDto.getTruckVin(), commandDto.getStartDate());
        eventPublisher.publish(event);

        return null;
    }
}
