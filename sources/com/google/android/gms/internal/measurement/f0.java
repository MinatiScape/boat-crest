package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class f0 extends r0 {
    public final /* synthetic */ Bundle l;
    public final /* synthetic */ zzbz m;
    public final /* synthetic */ zzee n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f0(zzee zzeeVar, Bundle bundle, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.n = zzeeVar;
        this.l = bundle;
        this.m = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.n.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).performAction(this.l, this.m, this.h);
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void b() {
        this.m.zzd(null);
    }
}
