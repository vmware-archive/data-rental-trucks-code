package io.pivotal.pal.data.rentaltrucks.event;

import java.util.Objects;

public class TruckDroppedOffEvent {

    private final String confirmationNumber;
    private final Integer dropOffMileage;

    public TruckDroppedOffEvent(String confirmationNumber,
                                Integer dropOffMileage) {
        this.confirmationNumber = confirmationNumber;
        this.dropOffMileage = dropOffMileage;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public Integer getDropOffMileage() {
        return dropOffMileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckDroppedOffEvent that = (TruckDroppedOffEvent) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(dropOffMileage, that.dropOffMileage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber, dropOffMileage);
    }

    @Override
    public String toString() {
        return "TruckDroppedOffEvent{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", dropOffMileage=" + dropOffMileage +
                '}';
    }

}
