package io.pivotal.pal.data.rentaltrucks.reservation.query;

import io.pivotal.pal.data.rentaltrucks.reservation.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailableTruckQueryController {

    private final TruckManager truckManager;

    public AvailableTruckQueryController(TruckManager truckManager) {
        this.truckManager = truckManager;
    }

    @GetMapping("/available-trucks")
    public Iterable<Truck> listTrucks() {
        return truckManager.listAvailableTrucks();
    }
}
