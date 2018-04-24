package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import java.time.LocalDate;
import java.util.Objects;

public class ReservationRequest {

    private final LocalDate pickupDate;
    private final LocalDate dropoffDate;
    private final String customerName;

    public ReservationRequest(LocalDate pickupDate, LocalDate dropoffDate, String customerName) {
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.customerName = customerName;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public LocalDate getDropoffDate() {
        return dropoffDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationRequest that = (ReservationRequest) o;
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
        return "ReservationRequest{" +
                "pickupDate='" + pickupDate + '\'' +
                ", dropoffDate='" + dropoffDate + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }

}
