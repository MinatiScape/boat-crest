package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;
/* loaded from: classes8.dex */
public final class m0 implements ListenerHolder.Notifier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ n0 f8892a;

    public m0(n0 n0Var) {
        this.f8892a = n0Var;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        zzcs zzcsVar;
        LocationListener locationListener = (LocationListener) obj;
        zzcsVar = this.f8892a.f8893a;
        zzcsVar.zzb();
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
