package io.pivotal.pal.data.rentaltrucks.reservation.command;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PickUpRentalCommandController {

    private final PickUpRentalCommandHandler commandHandler;

    public PickUpRentalCommandController(PickUpRentalCommandHandler commandService) {
        commandHandler = commandService;
    }

    @PostMapping("/rentals")
    public ResponseEntity<Void> pickUp(@RequestBody PickUpRentalCommandDto commandDto,
                                       UriComponentsBuilder uriComponentsBuilder) {
        commandHandler.pickUpTruck(commandDto);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("confirmationNumber", commandDto.getConfirmationNumber());

        URI locationUri = uriComponentsBuilder
                .path("/rentals/{confirmationNumber}")
                .buildAndExpand(uriVariables)
                .toUri();

        return ResponseEntity.accepted()
                .location(locationUri)
                .build();
    }

}
