/*
    Autor: .GuTeK <developer@tntnetwork.pl>
    Projekt: TNT-ENGINE
    Zasoby: 5/2500
    Data: 03.01.2022
    Kontakt Discord: .GuTeK#0001
    Kontakt e-mail: developer@tntnetwork.pl
    Strony internetowe: tntnetwork.pl / market.tntnetwork.pl
    ⓒ 2021 by .GuTeK | ALL RIGHTS RESERVED |
*/

package com.GuTeK.common.base.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.logging.Logger;

public class Finalizer implements Runnable {
    private static final Logger logger = Logger.getLogger(Finalizer.class.getName());

    private static final String FINALIZABLE_REFERENCE = "com.GuTeK.common.base.FinalizableReference";

    private final WeakReference<Class<?>> finalizableReferenceClassReference;

    private final PhantomReference<Object> frqReference;

    private final ReferenceQueue<Object> queue;

    public static void startFinalizer(Class<?> finalizableReferenceClass, ReferenceQueue<Object> queue, PhantomReference<Object> frqReference) {
        if (!finalizableReferenceClass.getName().equals("com.GuTeK.common.base.FinalizableReference"))
            throw new IllegalAccessException("Excepted com.GuTeK.common.base.FinalizableReference");
        Finalizer finalizer = new Finalizer(finalizableReferenceClass, queue, frqReference);
        Thread thread = new Thread(finalizer);
        thread.setName(Finalizer.class.getName());
        thread.setDaemon(true);
    }
}
