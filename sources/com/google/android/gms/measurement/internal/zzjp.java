package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.MainThread;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzjo;
/* loaded from: classes10.dex */
public final class zzjp<T extends Context & zzjo> {

    /* renamed from: a  reason: collision with root package name */
    public final T f10158a;

    public zzjp(T t) {
        Preconditions.checkNotNull(t);
        this.f10158a = t;
    }

    public final zzei a() {
        return zzfs.zzp(this.f10158a, null, null).zzay();
    }

    @MainThread
    public final int zza(final Intent intent, int i, final int i2) {
        zzfs zzp = zzfs.zzp(this.f10158a, null, null);
        final zzei zzay = zzp.zzay();
        if (intent == null) {
            zzay.zzk().zza("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzp.zzaw();
        zzay.zzj().zzc("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zzh(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzjl
                @Override // java.lang.Runnable
                public final void run() {
                    zzjp.this.zzc(i2, zzay, intent);
                }
            });
        }
        return 2;
    }

    @MainThread
    public final IBinder zzb(Intent intent) {
        if (intent == null) {
            a().zzd().zza("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzgk(zzkn.zzt(this.f10158a), null);
        }
        a().zzk().zzb("onBind received unknown action", action);
        return null;
    }

    public final /* synthetic */ void zzc(int i, zzei zzeiVar, Intent intent) {
        if (this.f10158a.zzc(i)) {
            zzeiVar.zzj().zzb("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            a().zzj().zza("Completed wakeful intent.");
            this.f10158a.zza(intent);
        }
    }

    public final /* synthetic */ void zzd(zzei zzeiVar, JobParameters jobParameters) {
        zzeiVar.zzj().zza("AppMeasurementJobService processed last upload request.");
        this.f10158a.zzb(jobParameters, false);
    }

    @MainThread
    public final void zze() {
        zzfs zzp = zzfs.zzp(this.f10158a, null, null);
        zzei zzay = zzp.zzay();
        zzp.zzaw();
        zzay.zzj().zza("Local AppMeasurementService is starting up");
    }

    @MainThread
    public final void zzf() {
        zzfs zzp = zzfs.zzp(this.f10158a, null, null);
        zzei zzay = zzp.zzay();
        zzp.zzaw();
        zzay.zzj().zza("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public final void zzg(Intent intent) {
        if (intent == null) {
            a().zzd().zza("onRebind called with null intent");
            return;
        }
        a().zzj().zzb("onRebind called. action", intent.getAction());
    }

    public final void zzh(Runnable runnable) {
        zzkn zzt = zzkn.zzt(this.f10158a);
        zzt.zzaz().zzp(new f3(this, zzt, runnable));
    }

    @TargetApi(24)
    @MainThread
    public final boolean zzi(final JobParameters jobParameters) {
        zzfs zzp = zzfs.zzp(this.f10158a, null, null);
        final zzei zzay = zzp.zzay();
        String string = jobParameters.getExtras().getString(Constants.KEY_ACTION);
        zzp.zzaw();
        zzay.zzj().zzb("Local AppMeasurementJobService called. action", string);
        if ("com.google.android.gms.measurement.UPLOAD".equals(string)) {
            zzh(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzjm
                @Override // java.lang.Runnable
                public final void run() {
                    zzjp.this.zzd(zzay, jobParameters);
                }
            });
            return true;
        }
        return true;
    }

    @MainThread
    public final boolean zzj(Intent intent) {
        if (intent == null) {
            a().zzd().zza("onUnbind called with null intent");
            return true;
        }
        a().zzj().zzb("onUnbind called for intent. action", intent.getAction());
        return true;
    }
}
