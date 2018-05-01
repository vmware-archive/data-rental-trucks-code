package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("reservationTruckRepository")
interface TruckRepository extends CrudRepository<Truck, String> {
    Iterable<Truck> findAllByStatus(String status);
}
