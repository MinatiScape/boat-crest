package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class h0 extends r0 {
    public final /* synthetic */ String l;
    public final /* synthetic */ zzbz m;
    public final /* synthetic */ zzee n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h0(zzee zzeeVar, String str, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.n = zzeeVar;
        this.l = str;
        this.m = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.n.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).getMaxUserProperties(this.l, this.m);
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void b() {
        this.m.zzd(null);
    }
}
