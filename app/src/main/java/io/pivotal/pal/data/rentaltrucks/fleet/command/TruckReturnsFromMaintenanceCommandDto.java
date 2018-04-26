package io.pivotal.pal.data.rentaltrucks.fleet.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class TruckReturnsFromMaintenanceCommandDto {

    private final String truckVin;
    private final LocalDate endDate;

    @JsonCreator
    public TruckReturnsFromMaintenanceCommandDto(@JsonProperty("truckVin") String truckVin,
                                                 @JsonProperty("endDate") LocalDate endDate) {
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
        TruckReturnsFromMaintenanceCommandDto that = (TruckReturnsFromMaintenanceCommandDto) o;
        return Objects.equals(truckVin, that.truckVin) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(truckVin, endDate);
    }

    @Override
    public String toString() {
        return "TruckReturnsFromMaintenanceCommandDto{" +
                "truckVin='" + truckVin + '\'' +
                ", endDate=" + endDate +
                '}';
    }
}
