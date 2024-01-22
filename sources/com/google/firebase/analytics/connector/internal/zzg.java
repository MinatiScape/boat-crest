package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.Set;
/* loaded from: classes10.dex */
public final class zzg implements zza {

    /* renamed from: a  reason: collision with root package name */
    public final AnalyticsConnector.AnalyticsConnectorListener f11083a;
    public final AppMeasurementSdk b;
    public final b c;

    public zzg(AppMeasurementSdk appMeasurementSdk, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        this.f11083a = analyticsConnectorListener;
        this.b = appMeasurementSdk;
        b bVar = new b(this);
        this.c = bVar;
        appMeasurementSdk.registerOnMeasurementEventListener(bVar);
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final AnalyticsConnector.AnalyticsConnectorListener zza() {
        return this.f11083a;
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final void zzb(Set<String> set) {
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final void zzc() {
    }
}
