package io.pivotal.pal.data.rentaltrucks.fleet.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "truck", schema = "fleet")
public class Truck {

    @Id
    @Column(name = "vin")
    private final String vin;

    @Column(name = "status")
    private String status;

    @Column(name = "mileage")
    private Integer mileage;

    @OneToMany(
            mappedBy = "truck",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<MaintenenceHistory> maintenanceHistories;

    public Truck(String vin, String status, Integer mileage, Set<MaintenenceHistory> maintenenceHistories) {
        this.vin = vin;
        this.status = status;
        this.mileage = mileage;
        this.maintenanceHistories = maintenenceHistories;
    }

    private Truck() {
        this.vin = null;
        this.status = null;
        this.mileage = null;
        this.maintenanceHistories = null;
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

    public void setMaintenanceHistories(Set<MaintenenceHistory> maintenanceHistories) {
        this.maintenanceHistories = maintenanceHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return Objects.equals(vin, truck.vin) &&
                Objects.equals(status, truck.status) &&
                Objects.equals(mileage, truck.mileage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vin, status, mileage);
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
