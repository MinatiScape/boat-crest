package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class q extends r0 {
    public final /* synthetic */ Boolean l;
    public final /* synthetic */ zzee m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(zzee zzeeVar, Boolean bool) {
        super(zzeeVar, true);
        this.m = zzeeVar;
        this.l = bool;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzcc zzccVar2;
        if (this.l != null) {
            zzccVar2 = this.m.g;
            ((zzcc) Preconditions.checkNotNull(zzccVar2)).setMeasurementEnabled(this.l.booleanValue(), this.h);
            return;
        }
        zzccVar = this.m.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).clearMeasurementEnabled(this.h);
    }
}
