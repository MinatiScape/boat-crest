package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.google.android.gms.measurement.internal.zzjo;
import com.google.android.gms.measurement.internal.zzjp;
@TargetApi(24)
/* loaded from: classes10.dex */
public final class AppMeasurementJobService extends JobService implements zzjo {
    public zzjp<AppMeasurementJobService> h;

    public final zzjp<AppMeasurementJobService> a() {
        if (this.h == null) {
            this.h = new zzjp<>(this);
        }
        return this.h;
    }

    @Override // android.app.Service
    @MainThread
    public void onCreate() {
        super.onCreate();
        a().zze();
    }

    @Override // android.app.Service
    @MainThread
    public void onDestroy() {
        a().zzf();
        super.onDestroy();
    }

    @Override // android.app.Service
    @MainThread
    public void onRebind(@NonNull Intent intent) {
        a().zzg(intent);
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(@NonNull JobParameters jobParameters) {
        a().zzi(jobParameters);
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(@NonNull JobParameters jobParameters) {
        return false;
    }

    @Override // android.app.Service
    @MainThread
    public boolean onUnbind(@NonNull Intent intent) {
        a().zzj(intent);
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zzjo
    public final void zza(@NonNull Intent intent) {
    }

    @Override // com.google.android.gms.measurement.internal.zzjo
    @TargetApi(24)
    public final void zzb(@NonNull JobParameters jobParameters, boolean z) {
        jobFinished(jobParameters, false);
    }

    @Override // com.google.android.gms.measurement.internal.zzjo
    public final boolean zzc(int i) {
        throw new UnsupportedOperationException();
    }
}
