package io.pivotal.pal.data.rentaltrucks.fleet.domain;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "fleetTruck")
@Table(name = "truck", schema = "fleet")
public class Truck {

    @Id
    @Column(name = "vin")
    private String vin;

    @Column(name = "status")
    private String status;

    @Column(name = "mileage")
    private Integer mileage;

    @OneToMany(
            mappedBy = "truck",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<MaintenenceHistory> maintenanceHistories = new LinkedHashSet<>();

    public Truck(String vin, String status, Integer mileage) {
        this.vin = vin;
        this.status = status;
        this.mileage = mileage;
    }

    Truck() {
        // default constructor
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

    public Set<MaintenenceHistory> getMaintenanceHistories() {
        return maintenanceHistories;
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
                ", maintenanceHistories=" + maintenanceHistories +
                '}';
    }
}
