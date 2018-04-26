package io.pivotal.pal.data.rentaltrucks.reservation.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PickUpRentalCommandDto {

    private final String confirmationNumber;
    private final String truckVin;

    @JsonCreator
    public PickUpRentalCommandDto(@JsonProperty("confirmationNumber") String confirmationNumber,
                                  @JsonProperty("truckVin") String truckVin) {
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
        PickUpRentalCommandDto that = (PickUpRentalCommandDto) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(truckVin, that.truckVin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber, truckVin);
    }

    @Override
    public String toString() {
        return "PickUpRentalCommandDto{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", truckVin='" + truckVin + '\'' +
                '}';
    }
}
