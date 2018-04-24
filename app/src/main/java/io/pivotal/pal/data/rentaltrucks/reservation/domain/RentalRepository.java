package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.data.repository.CrudRepository;

public interface RentalRepository extends CrudRepository<Rental, String> {
}
