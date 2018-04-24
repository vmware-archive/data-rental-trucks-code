package io.pivotal.pal.data.rentaltrucks.reservation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RequestReservationCommandController {

    private final RequestReservationCommandService service;

    public RequestReservationCommandController(RequestReservationCommandService service) {
        this.service = service;
    }

    @PostMapping("/reservations-requests")
    public ResponseEntity<Void> rentTruck(@RequestBody RequestReservationCommandDto commandDto,
                                          UriComponentsBuilder uriComponentsBuilder) {

        String confirmationNumber = service.rentTruck(commandDto);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("confirmationNumber", confirmationNumber);

        URI locationUri = uriComponentsBuilder
                .path("/reservations/{confirmationNumber}")
                .buildAndExpand(uriVariables)
                .toUri();

        return ResponseEntity.accepted()
                .location(locationUri)
                .build();
    }
}
