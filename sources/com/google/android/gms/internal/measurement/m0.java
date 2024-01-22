package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class m0 extends r0 {
    public final /* synthetic */ s0 l;
    public final /* synthetic */ zzee m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m0(zzee zzeeVar, s0 s0Var) {
        super(zzeeVar, true);
        this.m = zzeeVar;
        this.l = s0Var;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.m.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setEventInterceptor(this.l);
    }
}
