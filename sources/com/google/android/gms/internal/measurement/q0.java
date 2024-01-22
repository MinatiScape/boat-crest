package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* loaded from: classes8.dex */
public final class q0 extends r0 {
    public final /* synthetic */ String l;
    public final /* synthetic */ String m;
    public final /* synthetic */ Object n;
    public final /* synthetic */ boolean o;
    public final /* synthetic */ zzee p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q0(zzee zzeeVar, String str, String str2, Object obj, boolean z) {
        super(zzeeVar, true);
        this.p = zzeeVar;
        this.l = str;
        this.m = str2;
        this.n = obj;
        this.o = z;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.p.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setUserProperty(this.l, this.m, ObjectWrapper.wrap(this.n), this.o, this.h);
    }
}
