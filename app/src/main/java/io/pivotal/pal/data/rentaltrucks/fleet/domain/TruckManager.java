package io.pivotal.pal.data.rentaltrucks.fleet.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("fleetTruckManager")
public class TruckManager {

    private final TruckRepository truckRepository;

    public TruckManager(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public Truck returnTruckToYard(String truckVin, Integer dropOffMileage) {
        Truck truck = truckRepository.findOne(truckVin);
        truck.returnToYard(dropOffMileage);
        return truckRepository.save(truck);
    }

    public void moveTruckToMaintenance(String truckVin, LocalDate startDate) {
        // rehydrate truck using truck vin
        Truck truck = truckRepository.findOne(truckVin);

        // move truck to in maintenance and log the activity in history table
        truck.moveToMaintenance(startDate);
        truckRepository.save(truck);
    }

    public Truck returnTruckFromMaintenance(String truckVin, LocalDate endDate) {
        // return the truck to the yard
        Truck truck = truckRepository.findOne(truckVin);
        truck.returnFromMaintenance(endDate);
        return truckRepository.save(truck);
    }

    public Truck withdrawTruckFromyard(String truckVin) {
        // update the fleet truck status
        Truck truck = truckRepository.findOne(truckVin);
        truck.withdrawFromYard();
        return truckRepository.save(truck);
    }
}
