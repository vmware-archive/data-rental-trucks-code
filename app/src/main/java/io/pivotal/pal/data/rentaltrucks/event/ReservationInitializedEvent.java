package io.pivotal.pal.data.rentaltrucks.event;

import java.time.LocalDate;
import java.util.Objects;

public class ReservationInitializedEvent {

    private final String confirmationNumber;
    private final LocalDate pickupDate;
    private final LocalDate dropoffDate;

    public ReservationInitializedEvent(String confirmationNumber, LocalDate pickupDate, LocalDate dropoffDate) {
        this.confirmationNumber = confirmationNumber;
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public LocalDate getDropoffDate() {
        return dropoffDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationInitializedEvent that = (ReservationInitializedEvent) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(pickupDate, that.pickupDate) &&
                Objects.equals(dropoffDate, that.dropoffDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber, pickupDate, dropoffDate);
    }

    @Override
    public String toString() {
        return "ReservationInitializedEvent{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", pickupDate=" + pickupDate +
                ", dropoffDate=" + dropoffDate +
                '}';
    }
}
