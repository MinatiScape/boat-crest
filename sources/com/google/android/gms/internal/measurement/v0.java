package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* loaded from: classes8.dex */
public final class v0 extends r0 {
    public final /* synthetic */ Activity l;
    public final /* synthetic */ b1 m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v0(b1 b1Var, Activity activity) {
        super(b1Var.h, true);
        this.m = b1Var;
        this.l = activity;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.m.h.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).onActivityStarted(ObjectWrapper.wrap(this.l), this.i);
    }
}
