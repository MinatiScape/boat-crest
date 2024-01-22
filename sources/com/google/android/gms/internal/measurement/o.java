package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class o extends r0 {
    public final /* synthetic */ String l;
    public final /* synthetic */ zzee m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(zzee zzeeVar, String str) {
        super(zzeeVar, true);
        this.m = zzeeVar;
        this.l = str;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.m.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setUserId(this.l, this.h);
    }
}
