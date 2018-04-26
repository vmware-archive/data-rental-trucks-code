package io.pivotal.pal.data.rentaltrucks.event;

import java.time.LocalDate;
import java.util.Objects;

public class TruckEnteredMaintenanceEvent {

    private final String truckVin;
    private final LocalDate startDate;

    public TruckEnteredMaintenanceEvent(String truckVin, LocalDate startDate) {
        this.truckVin = truckVin;
        this.startDate = startDate;
    }

    public String getTruckVin() {
        return truckVin;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckEnteredMaintenanceEvent that = (TruckEnteredMaintenanceEvent) o;
        return Objects.equals(truckVin, that.truckVin) &&
                Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(truckVin, startDate);
    }

    @Override
    public String toString() {
        return "TruckEnteredMaintenanceEvent{" +
                "truckVin='" + truckVin + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
