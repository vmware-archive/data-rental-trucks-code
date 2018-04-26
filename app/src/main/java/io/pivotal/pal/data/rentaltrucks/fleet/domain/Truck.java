package io.pivotal.pal.data.rentaltrucks.fleet.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

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

    private MakeModel makeModel;

    @OneToMany(
            mappedBy = "truck",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<MaintenenceHistory> maintenanceHistories = new LinkedHashSet<>();

    // TODO: What about a rentalCounter "number of rentals" (since last maintenance?)

    public Truck(String vin, String status, Integer mileage, MakeModel makeModel) {
        this.vin = vin;
        this.status = status;
        this.mileage = mileage;
        this.makeModel = makeModel;
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

    public MakeModel getMakeModel() {
        return makeModel;
    }

    public Set<MaintenenceHistory> getMaintenanceHistories() {
        return maintenanceHistories;
    }

    ////////////////////////

    public void withdrawFromYard() {
        status = "OUT_OF_YARD";
    }

    public void returnToYard(Integer dropOffMileage) {
        status = "AVAILABLE";
        mileage = dropOffMileage;
    }

    public void moveToMaintenance(LocalDate startDate) {
        // update truck status=IN_MAINTENANCE
        status = "IN_MAINTENANCE";

        // create entry in maintenance_history with start info
        MaintenenceHistory maintenenceHistory =
                new MaintenenceHistory(UUID.randomUUID().toString(), startDate, "stubbed-comments", this);
        maintenanceHistories.add(maintenenceHistory);
    }

    public void returnFromMaintenance(LocalDate endDate) {
        // update the status on truck to AVAILABLE
        status = "AVAILABLE";

        // update the histories accordingly
        maintenanceHistories.forEach(mh -> mh.setEndDate(endDate)); // FIXME needs more thought
    }

    ////////////////////////

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
                ", makeModel=" + makeModel +
                ", maintenanceHistories=" + maintenanceHistories +
                '}';
    }
}
