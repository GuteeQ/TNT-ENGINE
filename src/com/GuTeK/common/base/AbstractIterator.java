/*
    Autor: .GuTeK <developer@tntnetwork.pl>
    Projekt: TNT-ENGINE
    Zasoby: 7/2500
    Data: 05.01.2022
    Kontakt Discord: .GuTeK#0001
    Kontakt e-mail: developer@tntnetwork.pl
    Strony internetowe: tntnetwork.pl / tnt-engine.ploo
    ⓒ 2022 by .GuTeK | ALL RIGHTS RESERVED |
*/

package com.GuTeK.common.base;

import com.GuTeK.common.annotations.GwtCompatible;

import java.util.Iterator;
import java.util.NoSuchElementException;

@GwtCompatible
abstract class AbstractIterator<T> implements Iterator<T> {
    private State state = State.NOT_READY;

    private T next;

    protected abstract T computeNext();

    private enum State {
        READY, NOT_READY, DONE, FAILED;
    }

    @Nullable
    @CanIgnoreReturnValue
    protected final T endOfData() {
        this.state = State.DONE;
        return null;
    }

    public final boolean hasNext() {
        Preconditions.checkState((this.state != State.FAILED));
        switch (this.state) {
            case READY:
                return true;
            case DONE:
                return false;
        }
        return tryToComputeNext();
    }

    private boolean tryToComputeNext() {
        this.state = State.FAILED;
        this.next = computeNext();
        if (this.state != State.DONE) {
            this.state = State.READY;
            return true;
        }
        return false;
    }

    public final T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        this.state = State.NOT_READY;
        T result = this.next;
        this.next = null;
        return result;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
