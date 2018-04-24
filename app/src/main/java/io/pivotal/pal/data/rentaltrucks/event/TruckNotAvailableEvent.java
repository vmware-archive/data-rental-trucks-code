package io.pivotal.pal.data.rentaltrucks.event;

import java.util.Objects;

public class TruckNotAvailableEvent {

    private final String confirmationNumber;

    // TODO: Does this need more information?

    public TruckNotAvailableEvent(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckNotAvailableEvent that = (TruckNotAvailableEvent) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber);
    }

    @Override
    public String toString() {
        return "TruckNotAvailableEvent{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                '}';
    }
}
