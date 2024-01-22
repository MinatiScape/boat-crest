package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzjo;
import com.google.android.gms.measurement.internal.zzjp;
/* loaded from: classes10.dex */
public final class AppMeasurementService extends Service implements zzjo {
    public zzjp<AppMeasurementService> h;

    public final zzjp<AppMeasurementService> a() {
        if (this.h == null) {
            this.h = new zzjp<>(this);
        }
        return this.h;
    }

    @Override // android.app.Service
    @NonNull
    @MainThread
    public IBinder onBind(@NonNull Intent intent) {
        return a().zzb(intent);
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

    @Override // android.app.Service
    @MainThread
    public int onStartCommand(@NonNull Intent intent, int i, int i2) {
        a().zza(intent, i, i2);
        return 2;
    }

    @Override // android.app.Service
    @MainThread
    public boolean onUnbind(@NonNull Intent intent) {
        a().zzj(intent);
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zzjo
    public final void zza(@NonNull Intent intent) {
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }

    @Override // com.google.android.gms.measurement.internal.zzjo
    public final void zzb(@NonNull JobParameters jobParameters, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.measurement.internal.zzjo
    public final boolean zzc(int i) {
        return stopSelfResult(i);
    }
}
