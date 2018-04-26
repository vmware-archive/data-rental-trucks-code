package io.pivotal.pal.data.rentaltrucks.fleet.command;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class TruckReturnsFromMaintenanceCommandController {

    private final TruckReturnsFromMaintenanceCommandService service;

    public TruckReturnsFromMaintenanceCommandController(TruckReturnsFromMaintenanceCommandService service) {
        this.service = service;
    }

    @PostMapping("/returns-from-maintenance")
    public ResponseEntity<Void> returnFromMaintenance(@RequestBody TruckReturnsFromMaintenanceCommandDto commandDto) {

        service.returnFromMaintenance(commandDto);

        return ResponseEntity.created(URI.create("stubbed")).build(); // FIXME
    }
}
