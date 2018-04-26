package io.pivotal.pal.data.rentaltrucks.reservation.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class RequestReservationCommandDto {

    private final String pickupDate;
    private final String dropoffDate;
    private final String customerName;

    @JsonCreator
    public RequestReservationCommandDto(@JsonProperty("pickupDate") String pickupDate,
                                        @JsonProperty("dropoffDate") String dropoffDate,
                                        @JsonProperty("customerName") String customerName) {
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.customerName = customerName;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public String getDropoffDate() {
        return dropoffDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestReservationCommandDto that = (RequestReservationCommandDto) o;
        return Objects.equals(pickupDate, that.pickupDate) &&
                Objects.equals(dropoffDate, that.dropoffDate) &&
                Objects.equals(customerName, that.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickupDate, dropoffDate, customerName);
    }

    @Override
    public String toString() {
        return "RequestReservationCommandDto{" +
                "pickupDate='" + pickupDate + '\'' +
                ", dropoffDate='" + dropoffDate + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
