package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class r extends r0 {
    public final /* synthetic */ Bundle l;
    public final /* synthetic */ zzee m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(zzee zzeeVar, Bundle bundle) {
        super(zzeeVar, true);
        this.m = zzeeVar;
        this.l = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.m.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setConsent(this.l, this.h);
    }
}
