package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.fitness.data.DataPoint;
/* loaded from: classes6.dex */
public final class b implements ListenerHolder.Notifier<OnDataPointListener> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DataPoint f8459a;

    public b(zzam zzamVar, DataPoint dataPoint) {
        this.f8459a = dataPoint;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(OnDataPointListener onDataPointListener) {
        onDataPointListener.onDataPoint(this.f8459a);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
