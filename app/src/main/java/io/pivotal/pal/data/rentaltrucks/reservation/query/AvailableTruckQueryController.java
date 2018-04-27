package io.pivotal.pal.data.rentaltrucks.reservation.query;

import io.pivotal.pal.data.rentaltrucks.reservation.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.TruckRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailableTruckQueryController {

    private final TruckRepository truckRepository;

    public AvailableTruckQueryController(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @GetMapping("/available-trucks")
    public Iterable<Truck> listTrucks() {
        return truckRepository.findAllByStatus("AVAILABLE");
    }
}
