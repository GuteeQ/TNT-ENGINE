/*
    Autor: .GuTeK <developer@tntnetwork.pl>
    Projekt: TNT-ENGINE
    Zasoby: 6/2500
    Data: 03.01.2022
    Kontakt Discord: .GuTeK#0001
    Kontakt e-mail: developer@tntnetwork.pl
    Strony internetowe: tntnetwork.pl / tnt-engine.ploo
    ⓒ 2022 by .GuTeK | ALL RIGHTS RESERVED |
*/

package com.GuTeK.common.base;

import com.GuTeK.common.annotations.GwtCompatible;

@GwtCompatible
final class Absent<T> extends Optional<T> {
    static final Absent<Object> INSTANCE = new Absent();

    private static final long serialVersionUID = 0L;

    static <T> Optional<T> withType() {
        return INSTANCE;
    }

    public boolean isPresent() {
        return false;
    }

    public T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    public T or(T defaultValue) {
        return Preconditions.checkNotNull(defaultValue, "use Optional.orNull() instead of Optional.or(null)");
    }

    public Optional<T> or(Optional<? extends T> secondChoice) {
        return (Optional<T>)Preconditions.<Optional<? extends T>>checkNotNull(secondChoice);
    }

    public T or(Supplier<? extends T> supplier) {
        return Preconditions.checkNotNull(supplier
                .get(), "use Optional.orNull() instead of a Supplier that returns null");
    }

    @Nullable
    public T orNull() {
        return null;
    }

    public Set<T> asSet() {
        return Collections.emptySet();
    }

    public <V> Optional<V> transform(Function<? super T, V> function) {
        Preconditions.checkNotNull(function);
        return Optional.absent();
    }

    public boolean equals(@Nullable Object object) {
        return (object == this);
    }

    public int hashCode() {
        return 2040732332;
    }

    public String toString() {
        return "Optional.absent()";
    }

    private Object readResolve() {
        return INSTANCE;
    }
}