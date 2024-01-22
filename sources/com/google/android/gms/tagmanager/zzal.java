package com.google.android.gms.tagmanager;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Looper;
import androidx.work.PeriodicWorkRequest;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzrk;
import com.google.android.gms.internal.gtm.zzrl;
import com.google.android.gms.internal.gtm.zzrm;
import com.google.android.gms.internal.gtm.zzrs;
@ShowFirstParty
/* loaded from: classes10.dex */
public final class zzal extends BasePendingResult<ContainerHolder> {
    public final Clock zza;
    public final zzai zzb;
    public final Looper zzc;
    public final zzec zzd;
    public final int zze;
    public final Context zzf;
    public final TagManager zzg;
    public final String zzh;
    public final zzam zzi;
    public zzak zzj;
    public final zzrm zzk;
    public volatile zzaa zzl;
    public volatile boolean zzm;
    public com.google.android.gms.internal.gtm.zzai zzn;
    public long zzo;
    public String zzp;
    public zzaj zzq;
    public zzac zzr;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzal(Context context, TagManager tagManager, Looper looper, String str, int i, zzap zzapVar) {
        super(looper == null ? Looper.getMainLooper() : looper);
        zzem zzemVar = new zzem(context, str);
        zzej zzejVar = new zzej(context, str, zzapVar, null, null, null);
        zzrm zzrmVar = new zzrm(context);
        Clock defaultClock = DefaultClock.getInstance();
        zzdf zzdfVar = new zzdf(1, 5, PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, 5000L, "refreshing", DefaultClock.getInstance());
        zzam zzamVar = new zzam(context, str);
        this.zzf = context;
        this.zzg = tagManager;
        this.zzc = looper == null ? Looper.getMainLooper() : looper;
        this.zzh = str;
        this.zze = i;
        this.zzj = zzemVar;
        this.zzq = zzejVar;
        this.zzk = zzrmVar;
        this.zzb = new zzai(this, null);
        this.zzn = com.google.android.gms.internal.gtm.zzai.zzd().zzC();
        this.zza = defaultClock;
        this.zzd = zzdfVar;
        this.zzi = zzamVar;
        if (zzv()) {
            zzo(zzea.zza().zzb());
        }
        zzapVar.zza();
    }

    public static /* bridge */ /* synthetic */ boolean zzp(zzal zzalVar) {
        boolean z = zzalVar.zzm;
        return false;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    /* renamed from: zzd */
    public final ContainerHolder createFailedResult(Status status) {
        if (this.zzl != null) {
            return this.zzl;
        }
        if (status == Status.RESULT_TIMEOUT) {
            zzdh.zza("timer expired: setting result to failure");
        }
        return new zzaa(status);
    }

    public final synchronized String zzh() {
        return this.zzp;
    }

    public final void zzl() {
        zzrs zza = this.zzj.zza(this.zze);
        if (zza != null) {
            setResult(new zzaa(this.zzg, this.zzc, new Container(this.zzf, this.zzg.getDataLayer(), this.zzh, 0L, zza), new zzab(this)));
        } else {
            zzdh.zza("Default was requested, but no default container was found");
            setResult(createFailedResult(new Status(10, "Default was requested, but no default container was found", (PendingIntent) null)));
        }
        this.zzq = null;
        this.zzj = null;
    }

    public final void zzm() {
        zzs(true);
    }

    public final void zzn() {
        zzs(false);
    }

    @VisibleForTesting
    public final synchronized void zzo(String str) {
        this.zzp = str;
        zzaj zzajVar = this.zzq;
        if (zzajVar != null) {
            zzajVar.zzb(str);
        }
    }

    public final synchronized void zzr(long j) {
        zzaj zzajVar = this.zzq;
        if (zzajVar == null) {
            zzdh.zzc("Refresh requested, but no network load scheduler.");
        } else {
            zzajVar.zza(j, this.zzn.zzh());
        }
    }

    public final void zzs(boolean z) {
        this.zzj.zzd(new zzae(this, null));
        this.zzq.zzc(new zzag(this, null));
        zzrs zza = this.zzj.zza(this.zze);
        if (zza != null) {
            TagManager tagManager = this.zzg;
            this.zzl = new zzaa(tagManager, this.zzc, new Container(this.zzf, tagManager.getDataLayer(), this.zzh, 0L, zza), this.zzb);
        }
        this.zzr = new zzac(this, z);
        if (zzv()) {
            this.zzq.zza(0L, "");
        } else {
            this.zzj.zzb();
        }
    }

    public final synchronized void zzt(com.google.android.gms.internal.gtm.zzai zzaiVar) {
        if (this.zzj != null) {
            zzrk zze = zzrl.zze();
            zze.zzc(0L);
            zze.zza(com.google.android.gms.internal.gtm.zzaa.zzl());
            zze.zzc(this.zzo);
            zze.zza(com.google.android.gms.internal.gtm.zzaa.zzj().zzC());
            zze.zzb(zzaiVar);
            this.zzj.zzc(zze.zzC());
        }
    }

    public final synchronized void zzu(com.google.android.gms.internal.gtm.zzai zzaiVar, long j, boolean z) {
        if (isReady() && this.zzl == null) {
            return;
        }
        this.zzn = zzaiVar;
        this.zzo = j;
        long zza = this.zzi.zza();
        zzr(Math.max(0L, Math.min(zza, (this.zzo + zza) - this.zza.currentTimeMillis())));
        Container container = new Container(this.zzf, this.zzg.getDataLayer(), this.zzh, j, zzaiVar);
        if (this.zzl == null) {
            this.zzl = new zzaa(this.zzg, this.zzc, container, this.zzb);
        } else {
            this.zzl.zzc(container);
        }
        if (isReady() || !this.zzr.zza(container)) {
            return;
        }
        setResult(this.zzl);
    }

    public final boolean zzv() {
        zzea zza = zzea.zza();
        return (zza.zze() == 2 || zza.zze() == 3) && this.zzh.equals(zza.zzc());
    }
}
