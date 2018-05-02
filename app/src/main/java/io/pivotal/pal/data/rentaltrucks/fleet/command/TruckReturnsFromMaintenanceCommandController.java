package io.pivotal.pal.data.rentaltrucks.fleet.command;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class TruckReturnsFromMaintenanceCommandController {

    private final TruckReturnsFromMaintenanceCommandHandler commandHandler;

    public TruckReturnsFromMaintenanceCommandController(TruckReturnsFromMaintenanceCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/returns-from-maintenance")
    public ResponseEntity<Void> returnFromMaintenance(@RequestBody TruckReturnsFromMaintenanceCommandDto commandDto) {

        commandHandler.returnFromMaintenance(commandDto);

        return ResponseEntity.created(URI.create("stubbed")).build(); // FIXME
    }
}
