package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
/* loaded from: classes8.dex */
public final class i0 implements ListenerHolder.Notifier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LocationAvailability f8887a;

    public i0(k0 k0Var, LocationAvailability locationAvailability) {
        this.f8887a = locationAvailability;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(Object obj) {
        ((LocationCallback) obj).onLocationAvailability(this.f8887a);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
