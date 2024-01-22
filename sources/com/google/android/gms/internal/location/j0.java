package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationCallback;
/* loaded from: classes8.dex */
public final class j0 implements ListenerHolder.Notifier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ k0 f8888a;

    public j0(k0 k0Var) {
        this.f8888a = k0Var;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        zzcs zzcsVar;
        LocationCallback locationCallback = (LocationCallback) obj;
        zzcsVar = this.f8888a.f8889a;
        zzcsVar.zzb();
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
