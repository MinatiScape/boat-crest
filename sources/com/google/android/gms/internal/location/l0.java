package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;
/* loaded from: classes8.dex */
public final class l0 implements ListenerHolder.Notifier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Location f8890a;

    public l0(n0 n0Var, Location location) {
        this.f8890a = location;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        ((LocationListener) obj).onLocationChanged(this.f8890a);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
