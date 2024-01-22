package com.polidea.rxandroidble2.internal.serialization;

import io.reactivex.internal.schedulers.NonBlockingThread;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes12.dex */
public class RxBleThreadFactory extends AtomicLong implements ThreadFactory {

    /* loaded from: classes12.dex */
    public static final class a extends Thread implements NonBlockingThread {
        public a(Runnable runnable, String str) {
            super(runnable, str);
        }
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        a aVar = new a(runnable, "RxBleThread-" + incrementAndGet());
        aVar.setPriority(5);
        aVar.setDaemon(true);
        return aVar;
    }
}
