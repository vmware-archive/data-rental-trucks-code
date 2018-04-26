package io.pivotal.pal.data.rentaltrucks.fleet.command;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltrucks.event.TruckEnteredMaintenanceEvent;
import org.springframework.stereotype.Service;

@Service
public class TruckEntersMaintenanceCommandService {

    private final AsyncEventPublisher<TruckEnteredMaintenanceEvent> eventPublisher;

    public TruckEntersMaintenanceCommandService(AsyncEventPublisher<TruckEnteredMaintenanceEvent> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void enterTheTruck(TruckEntersMaintenanceCommandDto commandDto) {

        // emit event for truck entered maintenance
        TruckEnteredMaintenanceEvent event =
                new TruckEnteredMaintenanceEvent(commandDto.getTruckVin(), commandDto.getStartDate());
        eventPublisher.publish(event);
    }
}
