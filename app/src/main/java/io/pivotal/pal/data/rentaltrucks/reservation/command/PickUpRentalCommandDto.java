package io.pivotal.pal.data.rentaltrucks.reservation.command;

import java.util.Objects;

public class PickUpRentalCommandDto {

    private final String confirmationNumber;
    private final String truckId;

    public PickUpRentalCommandDto(String confirmationNumber, String truckId) {
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
        PickUpRentalCommandDto that = (PickUpRentalCommandDto) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(truckId, that.truckId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(confirmationNumber, truckId);
    }

    @Override
    public String toString() {
        return "PickUpRentalCommandDto{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", truckId='" + truckId + '\'' +
                '}';
    }
}
