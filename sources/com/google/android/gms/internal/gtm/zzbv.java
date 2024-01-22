package com.google.android.gms.internal.gtm;

import android.annotation.SuppressLint;
import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
@SuppressLint({"StaticFieldLeak"})
/* loaded from: classes8.dex */
public final class zzbv {
    public static volatile zzbv zza;
    public final Context zzb;
    public final Context zzc;
    public final Clock zzd;
    public final zzct zze;
    public final zzfb zzf;
    public final com.google.android.gms.analytics.zzr zzg;
    public final zzbq zzh;
    public final zzcy zzi;
    public final zzft zzj;
    public final zzfh zzk;
    public final GoogleAnalytics zzl;
    public final zzcn zzm;
    public final zzbi zzn;
    public final zzcf zzo;
    public final zzcx zzp;

    public zzbv(zzbw zzbwVar) {
        Context zza2 = zzbwVar.zza();
        Preconditions.checkNotNull(zza2, "Application context can't be null");
        Context zzb = zzbwVar.zzb();
        Preconditions.checkNotNull(zzb);
        this.zzb = zza2;
        this.zzc = zzb;
        this.zzd = DefaultClock.getInstance();
        this.zze = new zzct(this);
        zzfb zzfbVar = new zzfb(this);
        zzfbVar.zzX();
        this.zzf = zzfbVar;
        zzfb zzm = zzm();
        String str = zzbt.zza;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 134);
        sb.append("Google Analytics ");
        sb.append(str);
        sb.append(" is starting up. To enable debug logging on a device run:\n  adb shell setprop log.tag.GAv4 DEBUG\n  adb logcat -s GAv4");
        zzm.zzM(sb.toString());
        zzfh zzfhVar = new zzfh(this);
        zzfhVar.zzX();
        this.zzk = zzfhVar;
        zzft zzftVar = new zzft(this);
        zzftVar.zzX();
        this.zzj = zzftVar;
        zzbq zzbqVar = new zzbq(this, zzbwVar);
        zzcn zzcnVar = new zzcn(this);
        zzbi zzbiVar = new zzbi(this);
        zzcf zzcfVar = new zzcf(this);
        zzcx zzcxVar = new zzcx(this);
        com.google.android.gms.analytics.zzr zzb2 = com.google.android.gms.analytics.zzr.zzb(zza2);
        zzb2.zzj(new zzbu(this));
        this.zzg = zzb2;
        GoogleAnalytics googleAnalytics = new GoogleAnalytics(this);
        zzcnVar.zzX();
        this.zzm = zzcnVar;
        zzbiVar.zzX();
        this.zzn = zzbiVar;
        zzcfVar.zzX();
        this.zzo = zzcfVar;
        zzcxVar.zzX();
        this.zzp = zzcxVar;
        zzcy zzcyVar = new zzcy(this);
        zzcyVar.zzX();
        this.zzi = zzcyVar;
        zzbqVar.zzX();
        this.zzh = zzbqVar;
        googleAnalytics.zzg();
        this.zzl = googleAnalytics;
        zzbqVar.zzm();
    }

    public static zzbv zzg(Context context) {
        Preconditions.checkNotNull(context);
        if (zza == null) {
            synchronized (zzbv.class) {
                if (zza == null) {
                    Clock defaultClock = DefaultClock.getInstance();
                    long elapsedRealtime = defaultClock.elapsedRealtime();
                    zzbv zzbvVar = new zzbv(new zzbw(context));
                    zza = zzbvVar;
                    GoogleAnalytics.zzf();
                    long elapsedRealtime2 = defaultClock.elapsedRealtime() - elapsedRealtime;
                    long longValue = zzeu.zzQ.zzb().longValue();
                    if (elapsedRealtime2 > longValue) {
                        zzbvVar.zzm().zzT("Slow initialization (ms)", Long.valueOf(elapsedRealtime2), Long.valueOf(longValue));
                    }
                }
            }
        }
        return zza;
    }

    public static final void zzs(zzbs zzbsVar) {
        Preconditions.checkNotNull(zzbsVar, "Analytics service not created/initialized");
        Preconditions.checkArgument(zzbsVar.zzY(), "Analytics service not initialized");
    }

    public final Context zza() {
        return this.zzb;
    }

    public final Context zzb() {
        return this.zzc;
    }

    public final GoogleAnalytics zzc() {
        Preconditions.checkNotNull(this.zzl);
        Preconditions.checkArgument(this.zzl.zzj(), "Analytics instance not initialized");
        return this.zzl;
    }

    public final com.google.android.gms.analytics.zzr zzd() {
        Preconditions.checkNotNull(this.zzg);
        return this.zzg;
    }

    public final zzbi zze() {
        zzs(this.zzn);
        return this.zzn;
    }

    public final zzbq zzf() {
        zzs(this.zzh);
        return this.zzh;
    }

    public final zzcf zzh() {
        zzs(this.zzo);
        return this.zzo;
    }

    public final zzcn zzi() {
        zzs(this.zzm);
        return this.zzm;
    }

    public final zzct zzj() {
        return this.zze;
    }

    public final zzcx zzk() {
        return this.zzp;
    }

    public final zzcy zzl() {
        zzs(this.zzi);
        return this.zzi;
    }

    public final zzfb zzm() {
        zzs(this.zzf);
        return this.zzf;
    }

    public final zzfb zzn() {
        return this.zzf;
    }

    public final zzfh zzo() {
        zzs(this.zzk);
        return this.zzk;
    }

    public final zzfh zzp() {
        zzfh zzfhVar = this.zzk;
        if (zzfhVar == null || !zzfhVar.zzY()) {
            return null;
        }
        return this.zzk;
    }

    public final zzft zzq() {
        zzs(this.zzj);
        return this.zzj;
    }

    public final Clock zzr() {
        return this.zzd;
    }
}
