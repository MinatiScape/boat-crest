package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class d0 extends r0 {
    public final /* synthetic */ String l;
    public final /* synthetic */ String m;
    public final /* synthetic */ boolean n;
    public final /* synthetic */ zzbz o;
    public final /* synthetic */ zzee p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d0(zzee zzeeVar, String str, String str2, boolean z, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.p = zzeeVar;
        this.l = str;
        this.m = str2;
        this.n = z;
        this.o = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.p.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).getUserProperties(this.l, this.m, this.n, this.o);
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void b() {
        this.o.zzd(null);
    }
}
