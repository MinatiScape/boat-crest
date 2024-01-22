package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzee;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.analytics.connector.internal.zzc;
import com.google.firebase.analytics.connector.internal.zze;
import com.google.firebase.analytics.connector.internal.zzg;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public class AnalyticsConnectorImpl implements AnalyticsConnector {
    public static volatile AnalyticsConnector c;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final AppMeasurementSdk f11077a;
    @VisibleForTesting
    public final Map<String, com.google.firebase.analytics.connector.internal.zza> b;

    /* loaded from: classes10.dex */
    public class a implements AnalyticsConnector.AnalyticsConnectorHandle {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f11078a;

        public a(String str) {
            this.f11078a = str;
        }

        @Override // com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorHandle
        @KeepForSdk
        public void registerEventNames(Set<String> set) {
            if (!AnalyticsConnectorImpl.this.c(this.f11078a) || !this.f11078a.equals("fiam") || set == null || set.isEmpty()) {
                return;
            }
            AnalyticsConnectorImpl.this.b.get(this.f11078a).zzb(set);
        }

        @Override // com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorHandle
        public final void unregister() {
            if (AnalyticsConnectorImpl.this.c(this.f11078a)) {
                AnalyticsConnector.AnalyticsConnectorListener zza = AnalyticsConnectorImpl.this.b.get(this.f11078a).zza();
                if (zza != null) {
                    zza.onMessageTriggered(0, null);
                }
                AnalyticsConnectorImpl.this.b.remove(this.f11078a);
            }
        }

        @Override // com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorHandle
        @KeepForSdk
        public void unregisterEventNames() {
            if (AnalyticsConnectorImpl.this.c(this.f11078a) && this.f11078a.equals("fiam")) {
                AnalyticsConnectorImpl.this.b.get(this.f11078a).zzc();
            }
        }
    }

    public AnalyticsConnectorImpl(AppMeasurementSdk appMeasurementSdk) {
        Preconditions.checkNotNull(appMeasurementSdk);
        this.f11077a = appMeasurementSdk;
        this.b = new ConcurrentHashMap();
    }

    public static /* synthetic */ void a(Event event) {
        boolean z = ((DataCollectionDefaultChange) event.getPayload()).enabled;
        synchronized (AnalyticsConnectorImpl.class) {
            ((AnalyticsConnectorImpl) Preconditions.checkNotNull(c)).f11077a.zza(z);
        }
    }

    @NonNull
    @KeepForSdk
    public static AnalyticsConnector getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public final boolean c(@NonNull String str) {
        return (str.isEmpty() || !this.b.containsKey(str) || this.b.get(str) == null) ? false : true;
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @KeepForSdk
    public void clearConditionalUserProperty(@NonNull @Size(max = 24, min = 1) String str, @NonNull String str2, @NonNull Bundle bundle) {
        if (str2 == null || zzc.zzj(str2, bundle)) {
            this.f11077a.clearConditionalUserProperty(str, str2, bundle);
        }
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @NonNull
    @KeepForSdk
    @WorkerThread
    public List<AnalyticsConnector.ConditionalUserProperty> getConditionalUserProperties(@NonNull String str, @NonNull @Size(max = 23, min = 1) String str2) {
        ArrayList arrayList = new ArrayList();
        for (Bundle bundle : this.f11077a.getConditionalUserProperties(str, str2)) {
            arrayList.add(zzc.zzb(bundle));
        }
        return arrayList;
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @KeepForSdk
    @WorkerThread
    public int getMaxUserProperties(@NonNull @Size(min = 1) String str) {
        return this.f11077a.getMaxUserProperties(str);
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @NonNull
    @KeepForSdk
    @WorkerThread
    public Map<String, Object> getUserProperties(boolean z) {
        return this.f11077a.getUserProperties(null, null, z);
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @KeepForSdk
    public void logEvent(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (zzc.zzl(str) && zzc.zzj(str2, bundle) && zzc.zzh(str, str2, bundle)) {
            zzc.zze(str, str2, bundle);
            this.f11077a.logEvent(str, str2, bundle);
        }
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @NonNull
    @KeepForSdk
    @WorkerThread
    public AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener(@NonNull String str, @NonNull AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        com.google.firebase.analytics.connector.internal.zza zzgVar;
        Preconditions.checkNotNull(analyticsConnectorListener);
        if (zzc.zzl(str) && !c(str)) {
            AppMeasurementSdk appMeasurementSdk = this.f11077a;
            if ("fiam".equals(str)) {
                zzgVar = new zze(appMeasurementSdk, analyticsConnectorListener);
            } else {
                zzgVar = (AppMeasurement.CRASH_ORIGIN.equals(str) || "clx".equals(str)) ? new zzg(appMeasurementSdk, analyticsConnectorListener) : null;
            }
            if (zzgVar != null) {
                this.b.put(str, zzgVar);
                return new a(str);
            }
            return null;
        }
        return null;
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @KeepForSdk
    public void setConditionalUserProperty(@NonNull AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        if (zzc.zzi(conditionalUserProperty)) {
            this.f11077a.setConditionalUserProperty(zzc.zza(conditionalUserProperty));
        }
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    @KeepForSdk
    public void setUserProperty(@NonNull String str, @NonNull String str2, @NonNull Object obj) {
        if (zzc.zzl(str) && zzc.zzm(str, str2)) {
            this.f11077a.setUserProperty(str, str2, obj);
        }
    }

    @NonNull
    @KeepForSdk
    public static AnalyticsConnector getInstance(@NonNull FirebaseApp firebaseApp) {
        return (AnalyticsConnector) firebaseApp.get(AnalyticsConnector.class);
    }

    @NonNull
    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
    @KeepForSdk
    public static AnalyticsConnector getInstance(@NonNull FirebaseApp firebaseApp, @NonNull Context context, @NonNull Subscriber subscriber) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(subscriber);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (c == null) {
            synchronized (AnalyticsConnectorImpl.class) {
                if (c == null) {
                    Bundle bundle = new Bundle(1);
                    if (firebaseApp.isDefaultApp()) {
                        subscriber.subscribe(DataCollectionDefaultChange.class, new Executor() { // from class: com.google.firebase.analytics.connector.zzb
                            @Override // java.util.concurrent.Executor
                            public final void execute(Runnable runnable) {
                                runnable.run();
                            }
                        }, new EventHandler() { // from class: com.google.firebase.analytics.connector.zza
                            @Override // com.google.firebase.events.EventHandler
                            public final void handle(Event event) {
                                AnalyticsConnectorImpl.a(event);
                            }
                        });
                        bundle.putBoolean("dataCollectionDefaultEnabled", firebaseApp.isDataCollectionDefaultEnabled());
                    }
                    c = new AnalyticsConnectorImpl(zzee.zzg(context, null, null, null, bundle).zzd());
                }
            }
        }
        return c;
    }
}
