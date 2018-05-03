package io.pivotal.pal.data.rentaltrucks.reservation.command;

import io.pivotal.pal.data.framework.event.SyncEventHandler;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ConfirmationNumberFactory;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.Reservation;
import io.pivotal.pal.data.rentaltrucks.reservation.domain.ReservationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreateReservationCommandHandler implements SyncEventHandler<CreateReservationCommandDto, String> {

    private final ReservationRepository reservationRepository;
    private final ConfirmationNumberFactory factory;

    public CreateReservationCommandHandler(ReservationRepository reservationRepository,
                                           ConfirmationNumberFactory factory) {
        this.reservationRepository = reservationRepository;
        this.factory = factory;
    }

    @Override
    public String onEvent(CreateReservationCommandDto commandDto) {
        // generate confirmation number
        String confirmationNumber = factory.make();  // move inside of the domain

        // create a new reservation via static initializer (which emits teh event)
        Reservation reservation = Reservation.create(
                LocalDate.parse(commandDto.getPickupDate()),
                LocalDate.parse(commandDto.getDropoffDate()),
                commandDto.getCustomerName(),
                confirmationNumber
        );
        reservationRepository.save(reservation);

        // event fires and mutates the entity to status=CREATED
        // who saves now

//        // emit the ReservationRequested event
//        ReservationRequestedEvent event = new ReservationRequestedEvent(
//                LocalDate.parse(commandDto.getPickupDate()),
//                LocalDate.parse(commandDto.getDropoffDate()),
//                commandDto.getCustomerName(),
//                confirmationNumber
//        );
//        eventPublisher.publish(event);

        return confirmationNumber;
    }
}
