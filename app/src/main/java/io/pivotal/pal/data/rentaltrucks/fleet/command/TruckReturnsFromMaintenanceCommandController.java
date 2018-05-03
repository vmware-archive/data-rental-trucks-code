package io.pivotal.pal.data.rentaltrucks.fleet.command;

import io.pivotal.pal.data.framework.event.SyncEventHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class TruckReturnsFromMaintenanceCommandController {

    private final SyncEventHandler<TruckReturnsFromMaintenanceCommandDto, Void> commandHandler;

    public TruckReturnsFromMaintenanceCommandController(SyncEventHandler<TruckReturnsFromMaintenanceCommandDto, Void> commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/returns-from-maintenance")
    public ResponseEntity<Void> returnFromMaintenance(@RequestBody TruckReturnsFromMaintenanceCommandDto commandDto) {

        commandHandler.onEvent(commandDto);

        return ResponseEntity.created(URI.create("stubbed")).build(); // FIXME
    }
}
