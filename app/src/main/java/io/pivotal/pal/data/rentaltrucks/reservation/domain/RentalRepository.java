package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.data.repository.CrudRepository;

interface RentalRepository extends CrudRepository<Rental, String> {
}
