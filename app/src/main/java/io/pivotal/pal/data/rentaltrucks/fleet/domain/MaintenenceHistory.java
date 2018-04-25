package io.pivotal.pal.data.rentaltrucks.fleet.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "MaintenanceHistory")
@Table(name = "maintenance_history")
public class MaintenenceHistory {

    @Id
    @Column(name = "id")
    private final String id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "truck_id")
    private Truck truck;

    public MaintenenceHistory(String id, LocalDate startDate, LocalDate endDate, String comments, Truck truck) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.comments = comments;
        this.truck = truck;
    }

    private MaintenenceHistory() {
        this.id = null;
        this.startDate = null;
        this.endDate = null;
        this.comments = null;
        this.truck = null;
    }

    public String getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaintenenceHistory that = (MaintenenceHistory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(comments, that.comments) &&
                Objects.equals(truck, that.truck);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, startDate, endDate, comments, truck);
    }

    @Override
    public String toString() {
        return "MaintenenceHistory{" +
                "id='" + id + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", comments='" + comments + '\'' +
                ", truck=" + truck +
                '}';
    }
}
