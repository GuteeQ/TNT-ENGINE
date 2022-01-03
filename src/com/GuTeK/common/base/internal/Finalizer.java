/*
    Autor: .GuTeK <developer@tntnetwork.pl>
    Projekt: TNT-ENGINE
    Zasoby: 5/2500
    Data: 03.01.2022
    Kontakt Discord: .GuTeK#0001
    Kontakt e-mail: developer@tntnetwork.pl
    Strony internetowe: tntnetwork.pl / tnt-engine.ploo
    ⓒ 2021 by .GuTeK | ALL RIGHTS RESERVED |
*/

package com.GuTeK.common.base.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.logging.Level;
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
        try {
            if (inheritableThreadLocals != null)
                inheritableThreadLocals.set(thread, (Object)null);
        } catch (Throwable t) {
            logger.log(Level.INFO, "Failed to clear thread local values inherited by reference finalizer thread", t);
        }
        thread.start();
    }

    private static final Field inheritableThreadLocals = getInheritableThreadLocalsField();

    private Finalizer(Class<T> finalizableReferenceClass, ReferenceQueue<Object> queue, PhantomReference<Object> frqReference) {
        this.queue = queue;
        this.finalizableReferenceClassReference = new WeakReference<>(finalizableReferenceClass);
        this.frqReference = frqReference;
    }

    public void run() {
        while (true) {
            try {
                do {

                } while (cleanUp(this.queue.remove()));
                break;
            } catch (InterruptedException interruptedException) {}
        }
    }

    private boolean cleanUp(Reference<?> reference) {
        Method finalizerReferentMethod = getFinalizerReferentMethod();
        if (finalizerReferentMethod == null)
            return false;
        while (true) {
            reference.clear();
            if (reference == this.frqReference)
                return false;
            try {
                finalizerReferentMethod.invoke(reference, new Object[0]);
            } catch (Throwable t) {
                logger.log(Level.SEVERE, "Error cleaning up after reference.", t);
            }
            if ((reference = this.queue.poll()) == null)
                return true;
        }
    }

    @Nullable
    private Method getFinalizerReferentMethod() {
        
    }
}
