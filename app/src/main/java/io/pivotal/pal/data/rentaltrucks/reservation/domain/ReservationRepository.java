package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface ReservationRepository extends CrudRepository<Reservation, String> {

    Collection<Reservation> findAllByStartDateBeforeAndEndDateAfter(LocalDate startDate, LocalDate endDate);

    Collection<Reservation> findAllByStatusAndStartDateBeforeAndEndDateAfter(String status, LocalDate startDate, LocalDate endDate);
}
