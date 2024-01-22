package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
/* loaded from: classes10.dex */
public final class i1 implements Runnable {
    public final /* synthetic */ Bundle h;
    public final /* synthetic */ zzhv i;

    public i1(zzhv zzhvVar, Bundle bundle) {
        this.i = zzhvVar;
        this.h = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkq zzkqVar;
        zzhv zzhvVar = this.i;
        Bundle bundle = this.h;
        zzhvVar.zzg();
        zzhvVar.zza();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        if (zzhvVar.zzs.zzJ()) {
            zzaf zzf = zzhvVar.zzs.zzf();
            zzdv<Boolean> zzdvVar = zzdw.zzap;
            if (zzf.zzs(null, zzdvVar)) {
                zzkqVar = new zzkq(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), 0L, null, "");
            } else {
                zzkqVar = new zzkq(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), 0L, null, null);
            }
            try {
                zzhvVar.zzs.zzt().zzE(new zzab(bundle.getString("app_id"), zzhvVar.zzs.zzf().zzs(null, zzdvVar) ? "" : bundle.getString("origin"), zzkqVar, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), bundle.getBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzhvVar.zzs.zzv().S(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), zzhvVar.zzs.zzf().zzs(null, zzdvVar) ? "" : bundle.getString("origin"), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, true)));
                return;
            } catch (IllegalArgumentException unused) {
                return;
            }
        }
        zzhvVar.zzs.zzay().zzj().zza("Conditional property not cleared since app measurement is disabled");
    }
}
