package io.pivotal.pal.data.rentaltrucks.fleet.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("fleetTruckRepository")
public interface TruckRepository extends CrudRepository<Truck, String> {
}
