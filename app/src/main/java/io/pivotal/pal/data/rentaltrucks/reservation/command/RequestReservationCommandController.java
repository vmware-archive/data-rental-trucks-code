package io.pivotal.pal.data.rentaltrucks.reservation.command;

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
public class RequestReservationCommandController {

    private final SyncEventHandler<RequestReservationCommandDto, String> commandHandler;

    public RequestReservationCommandController(SyncEventHandler<RequestReservationCommandDto, String> commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/reservations-requests")
    public ResponseEntity<Void> rentTruck(@RequestBody RequestReservationCommandDto commandDto,
                                          UriComponentsBuilder uriComponentsBuilder) {

        String confirmationNumber = commandHandler.onEvent(commandDto);

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
