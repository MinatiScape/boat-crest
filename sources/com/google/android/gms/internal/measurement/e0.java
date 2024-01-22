package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* loaded from: classes8.dex */
public final class e0 extends r0 {
    public final /* synthetic */ String l;
    public final /* synthetic */ Object m;
    public final /* synthetic */ zzee n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e0(zzee zzeeVar, boolean z, int i, String str, Object obj, Object obj2, Object obj3) {
        super(zzeeVar, false);
        this.n = zzeeVar;
        this.l = str;
        this.m = obj;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        zzcc zzccVar;
        zzccVar = this.n.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).logHealthData(5, this.l, ObjectWrapper.wrap(this.m), ObjectWrapper.wrap(null), ObjectWrapper.wrap(null));
    }
}
