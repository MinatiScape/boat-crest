package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class c0 extends r0 {
    public final /* synthetic */ zzbz l;
    public final /* synthetic */ zzee m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(zzee zzeeVar, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.m = zzeeVar;
        this.l = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.m.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).getCurrentScreenClass(this.l);
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void b() {
        this.l.zzd(null);
    }
}
