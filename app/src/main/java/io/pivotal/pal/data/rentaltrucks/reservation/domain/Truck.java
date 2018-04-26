package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "truck", schema = "reservation")
public class Truck {

    @Id
    @Column(name = "vin")
    private final String vin;

    @Column(name = "status")
    private String status;

    @Column(name = "mileage")
    private Integer mileage;

    public Truck(String vin, String status, Integer mileage) {
        this.vin = vin;
        this.status = status;
        this.mileage = mileage;
    }

    private Truck() {
        this.vin = null;
        this.status = null;
        this.mileage = null;
    }

    public String getVin() {
        return vin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return Objects.equals(vin, truck.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    @Override
    public String toString() {
        return "Truck{" +
                "vin='" + vin + '\'' +
                ", status='" + status + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
