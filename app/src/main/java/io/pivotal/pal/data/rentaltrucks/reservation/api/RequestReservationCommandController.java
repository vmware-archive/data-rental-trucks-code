package io.pivotal.pal.data.rentaltrucks.reservation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestReservationCommandController {

    private final RequestReservationCommandService service;

    public RequestReservationCommandController(RequestReservationCommandService service) {
        this.service = service;
    }

    @PostMapping("/reservations-requests")
    public ResponseEntity<Void> rentTruck(@RequestBody RequestReservationCommandDto commandDto) {

        service.rentTruck(commandDto);

        return ResponseEntity.ok().build(); // NOT OK!
    }
}
