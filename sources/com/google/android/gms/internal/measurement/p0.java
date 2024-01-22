package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class p0 extends r0 {
    public final /* synthetic */ Long l;
    public final /* synthetic */ String m;
    public final /* synthetic */ String n;
    public final /* synthetic */ Bundle o;
    public final /* synthetic */ boolean p;
    public final /* synthetic */ boolean q;
    public final /* synthetic */ zzee r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p0(zzee zzeeVar, Long l, String str, String str2, Bundle bundle, boolean z, boolean z2) {
        super(zzeeVar, true);
        this.r = zzeeVar;
        this.l = l;
        this.m = str;
        this.n = str2;
        this.o = bundle;
        this.p = z;
        this.q = z2;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        Long l = this.l;
        long longValue = l == null ? this.h : l.longValue();
        zzccVar = this.r.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).logEvent(this.m, this.n, this.o, this.p, this.q, longValue);
    }
}
