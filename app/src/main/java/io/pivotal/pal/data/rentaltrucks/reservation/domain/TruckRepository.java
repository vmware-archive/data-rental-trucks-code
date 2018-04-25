package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.data.repository.CrudRepository;

public interface TruckRepository extends CrudRepository<Truck, String> {
    Iterable<Truck> findAllByStatus(String status);
}
