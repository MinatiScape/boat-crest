package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* loaded from: classes8.dex */
public final class u0 extends r0 {
    public final /* synthetic */ Bundle l;
    public final /* synthetic */ Activity m;
    public final /* synthetic */ b1 n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u0(b1 b1Var, Bundle bundle, Activity activity) {
        super(b1Var.h, true);
        this.n = b1Var;
        this.l = bundle;
        this.m = activity;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() throws RemoteException {
        Bundle bundle;
        zzcc zzccVar;
        if (this.l != null) {
            bundle = new Bundle();
            if (this.l.containsKey("com.google.app_measurement.screen_service")) {
                Object obj = this.l.get("com.google.app_measurement.screen_service");
                if (obj instanceof Bundle) {
                    bundle.putBundle("com.google.app_measurement.screen_service", (Bundle) obj);
                }
            }
        } else {
            bundle = null;
        }
        zzccVar = this.n.h.g;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).onActivityCreated(ObjectWrapper.wrap(this.m), bundle, this.i);
    }
}
