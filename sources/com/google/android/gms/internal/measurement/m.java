package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class m extends r0 {
    public final /* synthetic */ String l;
    public final /* synthetic */ String m;
    public final /* synthetic */ Bundle n;
    public final /* synthetic */ zzee o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(zzee zzeeVar, String str, String str2, Bundle bundle) {
        super(zzeeVar, true);
        this.o = zzeeVar;
        this.l = str;
        this.m = str2;
        this.n = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.o.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).clearConditionalUserProperty(this.l, this.m, this.n);
    }
}
