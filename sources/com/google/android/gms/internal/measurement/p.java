package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* loaded from: classes8.dex */
public final class p extends r0 {
    public final /* synthetic */ Activity l;
    public final /* synthetic */ String m;
    public final /* synthetic */ String n;
    public final /* synthetic */ zzee o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(zzee zzeeVar, Activity activity, String str, String str2) {
        super(zzeeVar, true);
        this.o = zzeeVar;
        this.l = activity;
        this.m = str;
        this.n = str2;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.o.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setCurrentScreen(ObjectWrapper.wrap(this.l), this.m, this.n, this.h);
    }
}
