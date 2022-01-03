/*
    Autor: .GuTeK <developer@tntnetwork.pl>
    Projekt: TNT-ENGINE
    Zasoby: 3/2500
    Data: 03.01.2022
    Kontakt Discord: .GuTeK#0001
    Kontakt e-mail: developer@tntnetwork.pl
    Strony internetowe: tntnetwork.pl / market.tntnetwork.pl
    ⓒ 2021 by .GuTeK | ALL RIGHTS RESERVED |
*/

package com.GuTeK.common.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
@Documented
@GwtCompatible
public @interface GwtIncompatible {
    String value() default "";
}
