package io.pivotal.pal.data.rentaltrucks.fleet.command;

import java.time.LocalDate;
import java.util.Objects;

public class TruckReturnedFromMaintenanceEvent {

    private final String truckVin;
    private final LocalDate endDate;

    public TruckReturnedFromMaintenanceEvent(String truckVin, LocalDate endDate) {
        this.truckVin = truckVin;
        this.endDate = endDate;
    }

    public String getTruckVin() {
        return truckVin;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckReturnedFromMaintenanceEvent that = (TruckReturnedFromMaintenanceEvent) o;
        return Objects.equals(truckVin, that.truckVin) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(truckVin, endDate);
    }

    @Override
    public String toString() {
        return "TruckReturnedFromMaintenanceEvent{" +
                "truckVin='" + truckVin + '\'' +
                ", endDate=" + endDate +
                '}';
    }
}
