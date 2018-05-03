package io.pivotal.pal.data.rentaltrucks.reservation.domain;

import io.pivotal.pal.data.framework.AggregateRoot;

public class ReservationRoot extends AggregateRoot {

    private String status;

    public static ReservationRoot create() {
        return new ReservationRoot();
    }

    ReservationRoot() {
        super();
        applyChanges(new ReservationCreated());
    }

    @Override
    protected void registerAppliers() {
        registerApplier(this::apply, ReservationCreated.class);
        registerApplier(this::apply, ReservationConfirmed.class);
    }

    private void apply(ReservationCreated event) {
        this.status = "CREATED";
    }

    public void confirm() {
        applyChanges(new ReservationConfirmed());
    }

    private void apply(ReservationConfirmed event) {
        this.status = "CONFIRMED";
    }


    public static class ReservationCreated {
    }

    public static class ReservationConfirmed {
    }

}
