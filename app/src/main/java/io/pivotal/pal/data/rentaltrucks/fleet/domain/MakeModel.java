package io.pivotal.pal.data.rentaltrucks.fleet.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class MakeModel {

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    public MakeModel(String make, String model) {
        this.make = make;
        this.model = model;
    }

    MakeModel() {
        // default constructor
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MakeModel makeModel = (MakeModel) o;
        return Objects.equals(make, makeModel.make) &&
                Objects.equals(model, makeModel.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model);
    }

    @Override
    public String toString() {
        return "MakeModel{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
