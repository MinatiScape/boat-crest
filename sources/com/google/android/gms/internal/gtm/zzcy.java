package com.google.android.gms.internal.gtm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class zzcy extends zzbs {
    public boolean zza;
    public boolean zzb;
    public final AlarmManager zzc;
    public Integer zzd;

    public zzcy(zzbv zzbvVar) {
        super(zzbvVar);
        this.zzc = (AlarmManager) zzo().getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    public final void zza() {
        this.zzb = false;
        try {
            this.zzc.cancel(zzg());
        } catch (NullPointerException unused) {
        }
        if (Build.VERSION.SDK_INT >= 24) {
            int zzf = zzf();
            zzP("Cancelling job. JobID", Integer.valueOf(zzf));
            ((JobScheduler) zzo().getSystemService("jobscheduler")).cancel(zzf);
        }
    }

    public final void zzb() {
        zzW();
        Preconditions.checkState(this.zza, "Receiver not registered");
        zzw();
        long zzd = zzct.zzd();
        if (zzd > 0) {
            zza();
            long elapsedRealtime = zzC().elapsedRealtime() + zzd;
            this.zzb = true;
            zzeu.zzR.zzb().booleanValue();
            if (Build.VERSION.SDK_INT >= 24) {
                zzO("Scheduling upload with JobScheduler");
                Context zzo = zzo();
                ComponentName componentName = new ComponentName(zzo, "com.google.android.gms.analytics.AnalyticsJobService");
                int zzf = zzf();
                PersistableBundle persistableBundle = new PersistableBundle();
                persistableBundle.putString(Constants.KEY_ACTION, "com.google.android.gms.analytics.ANALYTICS_DISPATCH");
                JobInfo build = new JobInfo.Builder(zzf, componentName).setMinimumLatency(zzd).setOverrideDeadline(zzd + zzd).setExtras(persistableBundle).build();
                zzP("Scheduling job. JobID", Integer.valueOf(zzf));
                zzfv.zza(zzo, build, "com.google.android.gms", "DispatchAlarm");
                return;
            }
            zzO("Scheduling upload with AlarmManager");
            this.zzc.setInexactRepeating(2, elapsedRealtime, zzd, zzg());
        }
    }

    public final boolean zzc() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.gtm.zzbs
    public final void zzd() {
        try {
            zza();
            zzw();
            if (zzct.zzd() > 0) {
                Context zzo = zzo();
                ActivityInfo receiverInfo = zzo.getPackageManager().getReceiverInfo(new ComponentName(zzo, "com.google.android.gms.analytics.AnalyticsReceiver"), 0);
                if (receiverInfo == null || !receiverInfo.enabled) {
                    return;
                }
                zzO("Receiver registered for local dispatch.");
                this.zza = true;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public final boolean zze() {
        return this.zzb;
    }

    public final int zzf() {
        if (this.zzd == null) {
            String valueOf = String.valueOf(zzo().getPackageName());
            this.zzd = Integer.valueOf((valueOf.length() != 0 ? "analytics".concat(valueOf) : new String("analytics")).hashCode());
        }
        return this.zzd.intValue();
    }

    public final PendingIntent zzg() {
        Context zzo = zzo();
        return zzfu.zza(zzo, 0, new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH").setComponent(new ComponentName(zzo, "com.google.android.gms.analytics.AnalyticsReceiver")), zzfu.zza);
    }
}
