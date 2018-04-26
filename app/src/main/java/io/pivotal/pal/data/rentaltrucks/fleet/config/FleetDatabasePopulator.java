package io.pivotal.pal.data.rentaltrucks.fleet.config;

import io.pivotal.pal.data.rentaltrucks.fleet.domain.MakeModel;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.Truck;
import io.pivotal.pal.data.rentaltrucks.fleet.domain.TruckRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static java.util.Arrays.asList;

@Component
public class FleetDatabasePopulator {

    private final TruckRepository truckRepository;

    public FleetDatabasePopulator(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @PostConstruct
    public void populateDatabase() {
        Truck truck1 = new Truck("001", "RENTED", 10000, new MakeModel("Ford", "F350"));
        Truck truck2 = new Truck("002", "RENTED", 10000, new MakeModel("Chevy", "Truck"));
        Truck truck3 = new Truck("003", "AVAILABLE", 10000, new MakeModel("Dodge", "BadTrucks"));

        truckRepository.save(asList(truck1, truck2, truck3));
    }
}
