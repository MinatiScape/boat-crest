package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* loaded from: classes8.dex */
public final class z0 extends r0 {
    public final /* synthetic */ Activity l;
    public final /* synthetic */ zzbz m;
    public final /* synthetic */ b1 n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z0(b1 b1Var, Activity activity, zzbz zzbzVar) {
        super(b1Var.h, true);
        this.n = b1Var;
        this.l = activity;
        this.m = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.n.h.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).onActivitySaveInstanceState(ObjectWrapper.wrap(this.l), this.m, this.i);
    }
}
