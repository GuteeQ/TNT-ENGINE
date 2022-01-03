/*
    Autor: .GuTeK <developer@tntnetwork.pl>
    Projekt: TNT-ENGINE
    Zasoby: 6/2500
    Data: 03.01.2022
    Kontakt Discord: .GuTeK#0001
    Kontakt e-mail: developer@tntnetwork.pl
    Strony internetowe: tntnetwork.pl / tnt-engine.ploo
    ⓒ 2021 by .GuTeK | ALL RIGHTS RESERVED |
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
}
