package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class j0 extends r0 {
    public final /* synthetic */ zzbz l;
    public final /* synthetic */ int m;
    public final /* synthetic */ zzee n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j0(zzee zzeeVar, zzbz zzbzVar, int i) {
        super(zzeeVar, true);
        this.n = zzeeVar;
        this.l = zzbzVar;
        this.m = i;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.n.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).getTestFlag(this.l, this.m);
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void b() {
        this.l.zzd(null);
    }
}
