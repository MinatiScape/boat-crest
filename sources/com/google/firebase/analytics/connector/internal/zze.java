package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes10.dex */
public final class zze implements zza {

    /* renamed from: a  reason: collision with root package name */
    public final Set<String> f11082a;
    public final AnalyticsConnector.AnalyticsConnectorListener b;
    public final AppMeasurementSdk c;
    public final a d;

    public zze(AppMeasurementSdk appMeasurementSdk, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        this.b = analyticsConnectorListener;
        this.c = appMeasurementSdk;
        a aVar = new a(this);
        this.d = aVar;
        appMeasurementSdk.registerOnMeasurementEventListener(aVar);
        this.f11082a = new HashSet();
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final AnalyticsConnector.AnalyticsConnectorListener zza() {
        return this.b;
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final void zzb(Set<String> set) {
        this.f11082a.clear();
        Set<String> set2 = this.f11082a;
        HashSet hashSet = new HashSet();
        for (String str : set) {
            if (hashSet.size() >= 50) {
                break;
            } else if (zzc.zzf(str) && zzc.zzg(str)) {
                String zzd = zzc.zzd(str);
                Preconditions.checkNotNull(zzd);
                hashSet.add(zzd);
            }
        }
        set2.addAll(hashSet);
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final void zzc() {
        this.f11082a.clear();
    }
}
