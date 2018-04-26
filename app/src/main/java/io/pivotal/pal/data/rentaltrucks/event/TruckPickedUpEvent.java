package io.pivotal.pal.data.rentaltrucks.event;

import java.util.Objects;

public class TruckPickedUpEvent {

    private final String confirmationNumber;
    private final String truckVin;

    public TruckPickedUpEvent(String confirmationNumber, String truckVin) {
        this.confirmationNumber = confirmationNumber;
        this.truckVin = truckVin;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getTruckVin() {
        return truckVin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckPickedUpEvent that = (TruckPickedUpEvent) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(truckVin, that.truckVin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(confirmationNumber, truckVin);
    }

    @Override
    public String toString() {
        return "TruckPickedUpEvent{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", truckVin='" + truckVin + '\'' +
                '}';
    }
}
