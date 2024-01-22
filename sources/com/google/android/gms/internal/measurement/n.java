package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class n extends r0 {
    public final /* synthetic */ String l;
    public final /* synthetic */ String m;
    public final /* synthetic */ zzbz n;
    public final /* synthetic */ zzee o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(zzee zzeeVar, String str, String str2, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.o = zzeeVar;
        this.l = str;
        this.m = str2;
        this.n = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.o.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).getConditionalUserProperties(this.l, this.m, this.n);
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void b() {
        this.n.zzd(null);
    }
}
