package io.pivotal.pal.data.rentaltrucks.reservation.handler;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.event.TruckAvailableEvent;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Reservation;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FinalizeConfirmationEventHandler implements AsyncEventHandler<TruckAvailableEvent> {

    private static final Logger logger = LoggerFactory.getLogger(FinalizeConfirmationEventHandler.class);

    private final ReservationRepository repository;

    public FinalizeConfirmationEventHandler(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onEvent(TruckAvailableEvent data) {
        // update the trucks on hand counts (does not apply yet)

        // rehydrate a reservation from the event
        Reservation reservation = repository.findOne(data.getConfirmationNumber());

        // finalize the confirmation for the reservation
        reservation.finalizeConfirmation();

        // persist
        repository.save(reservation);

        // send an email
    }
}
