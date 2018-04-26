package io.pivotal.pal.data.rentaltrucks.reservation.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DropOffRentalCommandDto {

    private final String confirmationNumber;
    private final String truckVin;
    private final Integer dropOffMileage;

    @JsonCreator
    public DropOffRentalCommandDto(@JsonProperty("confirmationNumber") String confirmationNumber,
                                   @JsonProperty("truckVin") String truckVin,
                                   @JsonProperty("dropOffMileage") Integer dropOffMileage) {
        this.confirmationNumber = confirmationNumber;
        this.truckVin = truckVin;
        this.dropOffMileage = dropOffMileage;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getTruckVin() {
        return truckVin;
    }

    public Integer getDropOffMileage() {
        return dropOffMileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DropOffRentalCommandDto that = (DropOffRentalCommandDto) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(truckVin, that.truckVin) &&
                Objects.equals(dropOffMileage, that.dropOffMileage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber, truckVin, dropOffMileage);
    }

    @Override
    public String toString() {
        return "DropOffRentalCommandDto{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", truckVin='" + truckVin + '\'' +
                ", dropOffMileage=" + dropOffMileage +
                '}';
    }

}
