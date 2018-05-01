package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.stereotype.Component;

@Component("reservationTruckManager")
public class TruckManager {

    private final TruckRepository truckRepository;

    public TruckManager(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public Truck withdrawTruckFromYard(String truckVin) {
        Truck truck = truckRepository.findOne(truckVin);
        truck.withdrawFromYard();
        return truckRepository.save(truck);
    }

    public Truck returnTruckToYard(String truckVin, Integer dropOffMileage) {
        Truck truck = truckRepository.findOne(truckVin);
        truck.returnToYard(dropOffMileage);
        return truckRepository.save(truck);
    }

    public Iterable<Truck> findTrucksByStatus(String status) {
        return truckRepository.findAllByStatus("AVAILABLE");
    }

    public Truck returnTruckToYardFromMaintenance(String truckVin) {
        Truck truck = truckRepository.findOne(truckVin);
        truck.returnToYardFromMaintenance();
        return truckRepository.save(truck);
    }

    public Truck withdrawTruckFromYardToSendToMaintenance(String truckVin) {
        Truck truck = truckRepository.findOne(truckVin);
        truck.withdrawFromYardToSendToMaintenance();
        return truckRepository.save(truck);
    }

    public Iterable<Truck> listAvailableTrucks() {
        return truckRepository.findAllByStatus("AVAILABLE");
    }
}
