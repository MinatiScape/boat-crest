package com.polidea.rxandroidble2.internal.serialization;

import com.polidea.rxandroidble2.internal.RxBleLog;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public class c implements QueueReleaseInterface, QueueAwaitReleaseInterface {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicBoolean f13497a = new AtomicBoolean(false);

    @Override // com.polidea.rxandroidble2.internal.serialization.QueueAwaitReleaseInterface
    public synchronized void awaitRelease() throws InterruptedException {
        while (!this.f13497a.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                if (!this.f13497a.get()) {
                    RxBleLog.w(e, "Queue's awaitRelease() has been interrupted abruptly while it wasn't released by the release() method.", new Object[0]);
                }
            }
        }
    }

    @Override // com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface
    public synchronized void release() {
        if (this.f13497a.compareAndSet(false, true)) {
            notify();
        }
    }
}
