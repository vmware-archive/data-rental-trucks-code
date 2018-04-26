package io.pivotal.pal.data.rentaltrucks.fleet.command;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class TruckReturnsFromMaintenanceCommandService {

    private final AsyncEventPublisher<TruckReturnedFromMaintenanceEvent> eventPublisher;

    public TruckReturnsFromMaintenanceCommandService(AsyncEventPublisher<TruckReturnedFromMaintenanceEvent> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void returnFromMaintenance(TruckReturnsFromMaintenanceCommandDto commandDto) {

        // publish truck returned from maintenance event
        TruckReturnedFromMaintenanceEvent event =
                new TruckReturnedFromMaintenanceEvent(commandDto.getTruckVin(), commandDto.getEndDate());
        eventPublisher.publish(event);
    }
}
