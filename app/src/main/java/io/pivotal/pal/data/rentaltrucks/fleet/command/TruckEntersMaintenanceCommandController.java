package io.pivotal.pal.data.rentaltrucks.fleet.command;

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

    private final TruckEntersMaintenanceCommandService service;

    public TruckEntersMaintenanceCommandController(TruckEntersMaintenanceCommandService service) {
        this.service = service;
    }

    @PostMapping("/enters-to-maintenance")
    public ResponseEntity<Void> entersTruckToMaintenance(@RequestBody TruckEntersMaintenanceCommandDto commandDto,
                                                         UriComponentsBuilder uriComponentsBuilder) {

        service.enterTheTruck(commandDto);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("truckVin", commandDto.getTruckVin());

        URI locationUri = uriComponentsBuilder
                .path("/trucks/{truckVin}")
                .buildAndExpand(uriVariables)
                .toUri();

        return ResponseEntity.created(locationUri).build();
    }
}
