package io.pivotal.pal.data.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AggregateRoot {


//    private readonly List<IEvent> changes;
//    protected Dictionary<Type, Action<IEvent>> eventAppliers;

    private final List<Object> changes;
    protected final Map<Class<?>, Consumer<Object>> eventAppliers;

    protected AggregateRoot() {
        this.changes = new ArrayList<>();
        this.eventAppliers = new HashMap<>();

        this.registerAppliers();
    }

    protected abstract void registerAppliers();

    protected <T> void registerApplier(Consumer<T> applier, Class<T> clazz) {
        this.eventAppliers.put(clazz, x -> applier.accept((T) x));
    }

    protected <T> void applyChanges(T event) {
        Class<?> eventClass = event.getClass();
        if (!this.eventAppliers.containsKey(eventClass)) {
            throw new IllegalStateException("no event apply method registered");
        }
        this.eventAppliers.get(eventClass).accept(event);
        this.changes.add(event);
    }


}
