package io.pivotal.pal.data.rentaltrucks.fleet.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

@Component
class FleetDatabasePopulator {

    private final TruckRepository truckRepository;

    public FleetDatabasePopulator(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @PostConstruct
    public void populateDatabase() {
        Truck truck1 = new Truck("001", "OUT_OF_YARD", 10000, new MakeModel("Ford", "F350"));
        MaintenenceHistory maintenenceHistory = new MaintenenceHistory("fix-1", LocalDate.of(2017, 12, 31), "", truck1);
        List<MaintenenceHistory> truck1MaintenenceHistories = Collections.singletonList(maintenenceHistory);
        truck1.getMaintenanceHistories().addAll(truck1MaintenenceHistories);

        Truck truck2 = new Truck("002", "OUT_OF_YARD", 10000, new MakeModel("Chevy", "Truck"));
        Truck truck3 = new Truck("003", "AVAILABLE", 10000, new MakeModel("Dodge", "BadTrucks"));
        Truck truck4 = new Truck("004", "AVAILABLE", 10000, new MakeModel("Mack", "Truck"));

        truckRepository.save(asList(truck1, truck2, truck3, truck4));
    }
}
