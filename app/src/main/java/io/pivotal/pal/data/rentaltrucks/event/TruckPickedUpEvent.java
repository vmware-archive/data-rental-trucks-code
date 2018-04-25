package io.pivotal.pal.data.rentaltrucks.event;

import java.util.Objects;

public class TruckPickedUpEvent {

    private final String confirmationNumber;
    private final String truckId;

    // add required truck specs here in future

    public TruckPickedUpEvent(String confirmationNumber, String truckId) {
        this.confirmationNumber = confirmationNumber;
        this.truckId = truckId;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getTruckId() {
        return truckId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckPickedUpEvent that = (TruckPickedUpEvent) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(truckId, that.truckId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(confirmationNumber, truckId);
    }

    @Override
    public String toString() {
        return "TruckPickedUpEvent{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", truckId='" + truckId + '\'' +
                '}';
    }
}
