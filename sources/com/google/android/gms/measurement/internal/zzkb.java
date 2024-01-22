package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
/* loaded from: classes10.dex */
public final class zzkb extends r3 {
    public final AlarmManager b;
    public f c;
    public Integer d;

    public zzkb(zzkn zzknVar) {
        super(zzknVar);
        this.b = (AlarmManager) this.zzs.zzau().getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    public final int b() {
        if (this.d == null) {
            String valueOf = String.valueOf(this.zzs.zzau().getPackageName());
            this.d = Integer.valueOf((valueOf.length() != 0 ? "measurement".concat(valueOf) : new String("measurement")).hashCode());
        }
        return this.d.intValue();
    }

    public final PendingIntent c() {
        Context zzau = this.zzs.zzau();
        return com.google.android.gms.internal.measurement.zzbs.zza(zzau, 0, new Intent().setClassName(zzau, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), com.google.android.gms.internal.measurement.zzbs.zza);
    }

    public final f d() {
        if (this.c == null) {
            this.c = new p3(this, this.zzf.O());
        }
        return this.c;
    }

    public final void zza() {
        zzY();
        this.zzs.zzay().zzj().zza("Unscheduling upload");
        AlarmManager alarmManager = this.b;
        if (alarmManager != null) {
            alarmManager.cancel(c());
        }
        d().b();
        if (Build.VERSION.SDK_INT >= 24) {
            zzj();
        }
    }

    @Override // com.google.android.gms.measurement.internal.r3
    public final boolean zzb() {
        AlarmManager alarmManager = this.b;
        if (alarmManager != null) {
            alarmManager.cancel(c());
        }
        if (Build.VERSION.SDK_INT >= 24) {
            zzj();
            return false;
        }
        return false;
    }

    public final void zzd(long j) {
        zzY();
        this.zzs.zzaw();
        Context zzau = this.zzs.zzau();
        if (!zzku.z(zzau)) {
            this.zzs.zzay().zzc().zza("Receiver not registered/enabled");
        }
        if (!zzku.A(zzau, false)) {
            this.zzs.zzay().zzc().zza("Service not registered/enabled");
        }
        zza();
        this.zzs.zzay().zzj().zzb("Scheduling upload, millis", Long.valueOf(j));
        long elapsedRealtime = this.zzs.zzav().elapsedRealtime() + j;
        this.zzs.zzf();
        if (j < Math.max(0L, zzdw.zzw.zza(null).longValue()) && !d().e()) {
            d().d(j);
        }
        this.zzs.zzaw();
        if (Build.VERSION.SDK_INT < 24) {
            AlarmManager alarmManager = this.b;
            if (alarmManager != null) {
                this.zzs.zzf();
                alarmManager.setInexactRepeating(2, elapsedRealtime, Math.max(zzdw.zzr.zza(null).longValue(), j), c());
                return;
            }
            return;
        }
        Context zzau2 = this.zzs.zzau();
        ComponentName componentName = new ComponentName(zzau2, "com.google.android.gms.measurement.AppMeasurementJobService");
        int b = b();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(Constants.KEY_ACTION, "com.google.android.gms.measurement.UPLOAD");
        com.google.android.gms.internal.measurement.zzbt.zza(zzau2, new JobInfo.Builder(b, componentName).setMinimumLatency(j).setOverrideDeadline(j + j).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
    }

    @TargetApi(24)
    public final void zzj() {
        JobScheduler jobScheduler = (JobScheduler) this.zzs.zzau().getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(b());
        }
    }
}
