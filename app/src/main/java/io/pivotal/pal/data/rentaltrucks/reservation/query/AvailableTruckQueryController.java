package io.pivotal.pal.data.rentaltrucks.reservation.query;

import io.pivotal.pal.data.rentaltrucks.reservation.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailableTruckQueryController {

    private final TruckService truckService;

    public AvailableTruckQueryController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping("/available-trucks")
    public Iterable<Truck> listTrucks() {
        return truckService.listAvailableTrucks();
    }
}
