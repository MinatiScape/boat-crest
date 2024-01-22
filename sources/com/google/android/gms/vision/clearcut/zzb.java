package com.google.android.gms.vision.clearcut;

import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public final class zzb {
    public final Object b = new Object();
    @GuardedBy("lock")
    public long c = Long.MIN_VALUE;

    /* renamed from: a  reason: collision with root package name */
    public final long f10190a = Math.round(30000.0d);

    public zzb(double d) {
    }

    public final boolean tryAcquire() {
        synchronized (this.b) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.c + this.f10190a > currentTimeMillis) {
                return false;
            }
            this.c = currentTimeMillis;
            return true;
        }
    }
}
