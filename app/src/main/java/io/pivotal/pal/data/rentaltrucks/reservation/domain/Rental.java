package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "rental", schema = "reservation")
public class Rental {

    @Id
    @Column(name = "confirmation_number")
    private String confirmationNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "truck_id")
    private String truckId;

    @Column(name = "pickup_date")
    private LocalDate pickupDate;

    @Column(name = "scheduled_dropoff_date")
    private LocalDate scheduledDropoffDate;

    @Column(name = "dropoff_date")
    private LocalDate dropoffDate;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "pickup_mileage")
    private Integer pickupMileage;

    @Column(name = "dropoff_mileage")
    private Integer dropoffMileage;

    // TODO: Could we have an Optional<DropoffMetadata> to not set at construction or something?

    public Rental(String confirmationNumber,
                  String status,
                  String truckId,
                  LocalDate pickupDate,
                  LocalDate scheduledDropoffDate,
                  String customerName,
                  Integer pickupMileage) {
        this.confirmationNumber = confirmationNumber;
        this.status = status;
        this.truckId = truckId;
        this.pickupDate = pickupDate;
        this.scheduledDropoffDate = scheduledDropoffDate;
        this.customerName = customerName;
        this.pickupMileage = pickupMileage;
    }

    Rental() {
        // default constructor
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public LocalDate getScheduledDropoffDate() {
        return scheduledDropoffDate;
    }

    public LocalDate getDropoffDate() {
        return dropoffDate;
    }

    public void setDropoffDate(LocalDate dropoffDate) {
        this.dropoffDate = dropoffDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getPickupMileage() {
        return pickupMileage;
    }

    public Integer getDropoffMileage() {
        return dropoffMileage;
    }

    public void setDropoffMileage(Integer dropoffMileage) {
        this.dropoffMileage = dropoffMileage;
    }

    ////////////////////////

    public void dropoff(LocalDate dropoffDate, Integer dropoffMileage) {
        this.status = "DROPPED_OFF";
        this.dropoffDate = dropoffDate;
        this.dropoffMileage = dropoffMileage;
    }

    ////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Objects.equals(confirmationNumber, rental.confirmationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", status='" + status + '\'' +
                ", truckId='" + truckId + '\'' +
                ", pickupDate=" + pickupDate +
                ", scheduledDropoffDate=" + scheduledDropoffDate +
                ", dropoffDate=" + dropoffDate +
                ", customerName='" + customerName + '\'' +
                ", pickupMileage=" + pickupMileage +
                ", dropoffMileage=" + dropoffMileage +
                '}';
    }
}
