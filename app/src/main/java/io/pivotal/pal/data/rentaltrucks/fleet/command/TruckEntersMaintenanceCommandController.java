package io.pivotal.pal.data.rentaltrucks.fleet.command;

import io.pivotal.pal.data.framework.event.SyncEventHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TruckEntersMaintenanceCommandController {

    private final SyncEventHandler<TruckEntersMaintenanceCommandDto, Void> commandHandler;

    public TruckEntersMaintenanceCommandController(SyncEventHandler<TruckEntersMaintenanceCommandDto, Void> commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/enters-to-maintenance")
    public ResponseEntity<Void> entersTruckToMaintenance(@RequestBody TruckEntersMaintenanceCommandDto commandDto,
                                                         UriComponentsBuilder uriComponentsBuilder) {

        commandHandler.onEvent(commandDto);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("truckVin", commandDto.getTruckVin());

        URI locationUri = uriComponentsBuilder
                .path("/trucks/{truckVin}")
                .buildAndExpand(uriVariables)
                .toUri();

        return ResponseEntity.created(locationUri).build();
    }
}
