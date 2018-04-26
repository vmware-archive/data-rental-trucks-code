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
public class DropOffRentalCommandController {

    private final DropOffRentalCommandService service;

    public DropOffRentalCommandController(DropOffRentalCommandService service) {
        this.service = service;
    }

    @PostMapping("/rental-dropoffs")
    public ResponseEntity<Void> dropOffRental(@RequestBody DropOffRentalCommandDto commandDto,
                                              UriComponentsBuilder uriComponentsBuilder) {
        service.dropOffRental(commandDto);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("confirmationNumber", commandDto.getConfirmationNumber());

        URI locationUri = uriComponentsBuilder
                .path("/rentals/{confirmationNumber}")
                .buildAndExpand(uriVariables)
                .toUri();

        return ResponseEntity.created(locationUri)
                .build();
    }
}
