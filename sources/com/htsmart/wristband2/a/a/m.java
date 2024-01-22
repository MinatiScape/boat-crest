package com.htsmart.wristband2.a.a;

import com.htsmart.wristband2.utils.WristbandLog;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public class m {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicBoolean f11935a = new AtomicBoolean(false);

    public synchronized void a() throws InterruptedException {
        while (!this.f11935a.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                if (!this.f11935a.get()) {
                    WristbandLog.w(e, "Queue's awaitRelease() has been interrupted abruptly while it wasn't released by the release() method.", new Object[0]);
                }
            }
        }
    }

    public synchronized boolean b() {
        return this.f11935a.get();
    }

    public synchronized void c() {
        if (this.f11935a.compareAndSet(false, true)) {
            notify();
        }
    }
}
