package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class t extends r0 {
    public final /* synthetic */ zzee l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(zzee zzeeVar) {
        super(zzeeVar, true);
        this.l = zzeeVar;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.l.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).resetAnalyticsData(this.h);
    }
}
