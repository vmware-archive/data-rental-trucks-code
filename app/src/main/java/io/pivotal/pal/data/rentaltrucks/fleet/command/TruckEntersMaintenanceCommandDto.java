package io.pivotal.pal.data.rentaltrucks.fleet.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class TruckEntersMaintenanceCommandDto {

    private final String truckVin;
    private final LocalDate startDate;

    @JsonCreator
    public TruckEntersMaintenanceCommandDto(@JsonProperty("truckVin") String truckVin,
                                            @JsonProperty("startDate") LocalDate startDate) {
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
        TruckEntersMaintenanceCommandDto that = (TruckEntersMaintenanceCommandDto) o;
        return Objects.equals(truckVin, that.truckVin) &&
                Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(truckVin, startDate);
    }

    @Override
    public String toString() {
        return "TruckEntersMaintenanceCommandDto{" +
                "truckVin='" + truckVin + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
