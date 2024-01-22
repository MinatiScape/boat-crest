package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
/* loaded from: classes10.dex */
public final class n3 {

    /* renamed from: a  reason: collision with root package name */
    public final Clock f10127a;
    public long b;

    public n3(Clock clock) {
        Preconditions.checkNotNull(clock);
        this.f10127a = clock;
    }

    public final void a() {
        this.b = 0L;
    }

    public final void b() {
        this.b = this.f10127a.elapsedRealtime();
    }

    public final boolean c(long j) {
        return this.b == 0 || this.f10127a.elapsedRealtime() - this.b >= 3600000;
    }
}
