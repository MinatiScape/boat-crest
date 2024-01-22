package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.BinderThread;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzpl;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
/* loaded from: classes10.dex */
public final class zzgk extends zzdy {

    /* renamed from: a  reason: collision with root package name */
    public final zzkn f10155a;
    public Boolean b;
    public String c;

    public zzgk(zzkn zzknVar, String str) {
        Preconditions.checkNotNull(zzknVar);
        this.f10155a = zzknVar;
        this.c = null;
    }

    @BinderThread
    public final void a(String str, boolean z) {
        boolean z2;
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    if (this.b == null) {
                        if (!"com.google.android.gms".equals(this.c) && !UidVerifier.isGooglePlayServicesUid(this.f10155a.zzau(), Binder.getCallingUid()) && !GoogleSignatureVerifier.getInstance(this.f10155a.zzau()).isUidGoogleSigned(Binder.getCallingUid())) {
                            z2 = false;
                            this.b = Boolean.valueOf(z2);
                        }
                        z2 = true;
                        this.b = Boolean.valueOf(z2);
                    }
                    if (this.b.booleanValue()) {
                        return;
                    }
                } catch (SecurityException e) {
                    this.f10155a.zzay().zzd().zzb("Measurement Service called with invalid calling package. appId", zzei.zzn(str));
                    throw e;
                }
            }
            if (this.c == null && GooglePlayServicesUtilLight.uidHasPackageName(this.f10155a.zzau(), Binder.getCallingUid(), str)) {
                this.c = str;
            }
            if (str.equals(this.c)) {
                return;
            }
            throw new SecurityException(String.format("Unknown calling package name '%s'.", str));
        }
        this.f10155a.zzay().zzd().zza("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    public final void b(zzat zzatVar, zzp zzpVar) {
        this.f10155a.a();
        this.f10155a.d(zzatVar, zzpVar);
    }

    @VisibleForTesting
    public final zzat c(zzat zzatVar, zzp zzpVar) {
        zzar zzarVar;
        if (Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zzatVar.zza) && (zzarVar = zzatVar.zzb) != null && zzarVar.zza() != 0) {
            String e = zzatVar.zzb.e("_cis");
            if ("referrer broadcast".equals(e) || "referrer API".equals(e)) {
                this.f10155a.zzay().zzi().zzb("Event has been filtered ", zzatVar.toString());
                return new zzat("_cmpx", zzatVar.zzb, zzatVar.zzc, zzatVar.zzd);
            }
        }
        return zzatVar;
    }

    public final void f(zzat zzatVar, zzp zzpVar) {
        if (!this.f10155a.zzo().zzl(zzpVar.zza)) {
            b(zzatVar, zzpVar);
            return;
        }
        this.f10155a.zzay().zzj().zzb("EES config found for", zzpVar.zza);
        zzfj zzo = this.f10155a.zzo();
        String str = zzpVar.zza;
        zzpl.zzc();
        zzc zzcVar = null;
        if (zzo.zzs.zzf().zzs(null, zzdw.zzav) && !TextUtils.isEmpty(str)) {
            zzcVar = zzo.g.get(str);
        }
        if (zzcVar != null) {
            try {
                Map<String, Object> z = zzkp.z(zzatVar.zzb.zzc(), true);
                String zza = zzgp.zza(zzatVar.zza);
                if (zza == null) {
                    zza = zzatVar.zza;
                }
                if (zzcVar.zze(new com.google.android.gms.internal.measurement.zzaa(zza, zzatVar.zzd, z))) {
                    if (zzcVar.zzg()) {
                        this.f10155a.zzay().zzj().zzb("EES edited event", zzatVar.zza);
                        b(zzkp.q(zzcVar.zza().zzb()), zzpVar);
                    } else {
                        b(zzatVar, zzpVar);
                    }
                    if (zzcVar.zzf()) {
                        for (com.google.android.gms.internal.measurement.zzaa zzaaVar : zzcVar.zza().zzc()) {
                            this.f10155a.zzay().zzj().zzb("EES logging created event", zzaaVar.zzd());
                            b(zzkp.q(zzaaVar), zzpVar);
                        }
                        return;
                    }
                    return;
                }
            } catch (com.google.android.gms.internal.measurement.zzd unused) {
                this.f10155a.zzay().zzd().zzc("EES error. appId, eventName", zzpVar.zzb, zzatVar.zza);
            }
            this.f10155a.zzay().zzj().zzb("EES was not applied to event", zzatVar.zza);
            b(zzatVar, zzpVar);
            return;
        }
        this.f10155a.zzay().zzj().zzb("EES not loaded for", zzpVar.zza);
        b(zzatVar, zzpVar);
    }

    @VisibleForTesting
    public final void g(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.f10155a.zzaz().zzs()) {
            runnable.run();
        } else {
            this.f10155a.zzaz().zzp(runnable);
        }
    }

    @BinderThread
    public final void h(zzp zzpVar, boolean z) {
        Preconditions.checkNotNull(zzpVar);
        Preconditions.checkNotEmpty(zzpVar.zza);
        a(zzpVar.zza, false);
        this.f10155a.zzv().m(zzpVar.zzb, zzpVar.zzq, zzpVar.zzu);
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final String zzd(zzp zzpVar) {
        h(zzpVar, false);
        return this.f10155a.Q(zzpVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final List<zzkq> zze(zzp zzpVar, boolean z) {
        h(zzpVar, false);
        String str = zzpVar.zza;
        Preconditions.checkNotNull(str);
        try {
            List<z3> list = (List) this.f10155a.zzaz().zzh(new t0(this, str)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (z3 z3Var : list) {
                if (z || !zzku.x(z3Var.c)) {
                    arrayList.add(new zzkq(z3Var));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.f10155a.zzay().zzd().zzc("Failed to get user properties. appId", zzei.zzn(zzpVar.zza), e);
            return null;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final List<zzab> zzf(String str, String str2, zzp zzpVar) {
        h(zzpVar, false);
        String str3 = zzpVar.zza;
        Preconditions.checkNotNull(str3);
        try {
            return (List) this.f10155a.zzaz().zzh(new j0(this, str3, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.f10155a.zzay().zzd().zzb("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final List<zzab> zzg(String str, String str2, String str3) {
        a(str, true);
        try {
            return (List) this.f10155a.zzaz().zzh(new k0(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.f10155a.zzay().zzd().zzb("Failed to get conditional user properties as", e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final List<zzkq> zzh(String str, String str2, boolean z, zzp zzpVar) {
        h(zzpVar, false);
        String str3 = zzpVar.zza;
        Preconditions.checkNotNull(str3);
        try {
            List<z3> list = (List) this.f10155a.zzaz().zzh(new h0(this, str3, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (z3 z3Var : list) {
                if (z || !zzku.x(z3Var.c)) {
                    arrayList.add(new zzkq(z3Var));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.f10155a.zzay().zzd().zzc("Failed to query user properties. appId", zzei.zzn(zzpVar.zza), e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final List<zzkq> zzi(String str, String str2, String str3, boolean z) {
        a(str, true);
        try {
            List<z3> list = (List) this.f10155a.zzaz().zzh(new i0(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (z3 z3Var : list) {
                if (z || !zzku.x(z3Var.c)) {
                    arrayList.add(new zzkq(z3Var));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.f10155a.zzay().zzd().zzc("Failed to get user properties as. appId", zzei.zzn(str), e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final void zzj(zzp zzpVar) {
        h(zzpVar, false);
        g(new u0(this, zzpVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final void zzk(zzat zzatVar, zzp zzpVar) {
        Preconditions.checkNotNull(zzatVar);
        h(zzpVar, false);
        g(new p0(this, zzatVar, zzpVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final void zzl(zzat zzatVar, String str, String str2) {
        Preconditions.checkNotNull(zzatVar);
        Preconditions.checkNotEmpty(str);
        a(str, true);
        g(new q0(this, zzatVar, str));
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final void zzm(zzp zzpVar) {
        Preconditions.checkNotEmpty(zzpVar.zza);
        a(zzpVar.zza, false);
        g(new m0(this, zzpVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final void zzn(zzab zzabVar, zzp zzpVar) {
        Preconditions.checkNotNull(zzabVar);
        Preconditions.checkNotNull(zzabVar.zzc);
        h(zzpVar, false);
        zzab zzabVar2 = new zzab(zzabVar);
        zzabVar2.zza = zzpVar.zza;
        g(new f0(this, zzabVar2, zzpVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final void zzo(zzab zzabVar) {
        Preconditions.checkNotNull(zzabVar);
        Preconditions.checkNotNull(zzabVar.zzc);
        Preconditions.checkNotEmpty(zzabVar.zza);
        a(zzabVar.zza, true);
        g(new g0(this, new zzab(zzabVar)));
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final void zzp(zzp zzpVar) {
        Preconditions.checkNotEmpty(zzpVar.zza);
        Preconditions.checkNotNull(zzpVar.zzv);
        o0 o0Var = new o0(this, zzpVar);
        Preconditions.checkNotNull(o0Var);
        if (this.f10155a.zzaz().zzs()) {
            o0Var.run();
        } else {
            this.f10155a.zzaz().zzq(o0Var);
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final void zzq(long j, String str, String str2, String str3) {
        g(new v0(this, str2, str3, str, j));
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final void zzr(final Bundle bundle, zzp zzpVar) {
        h(zzpVar, false);
        final String str = zzpVar.zza;
        Preconditions.checkNotNull(str);
        g(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzft
            @Override // java.lang.Runnable
            public final void run() {
                zzgk.this.zzx(str, bundle);
            }
        });
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final void zzs(zzp zzpVar) {
        h(zzpVar, false);
        g(new n0(this, zzpVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final void zzt(zzkq zzkqVar, zzp zzpVar) {
        Preconditions.checkNotNull(zzkqVar);
        h(zzpVar, false);
        g(new s0(this, zzkqVar, zzpVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzdz
    @BinderThread
    public final byte[] zzu(zzat zzatVar, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzatVar);
        a(str, true);
        this.f10155a.zzay().zzc().zzb("Log and bundle. event", this.f10155a.zzj().zzc(zzatVar.zza));
        long nanoTime = this.f10155a.zzav().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.f10155a.zzaz().zzi(new r0(this, zzatVar, str)).get();
            if (bArr == null) {
                this.f10155a.zzay().zzd().zzb("Log and bundle returned null. appId", zzei.zzn(str));
                bArr = new byte[0];
            }
            this.f10155a.zzay().zzc().zzd("Log and bundle processed. event, size, time_ms", this.f10155a.zzj().zzc(zzatVar.zza), Integer.valueOf(bArr.length), Long.valueOf((this.f10155a.zzav().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.f10155a.zzay().zzd().zzd("Failed to log and bundle. appId, event, error", zzei.zzn(str), this.f10155a.zzj().zzc(zzatVar.zza), e);
            return null;
        }
    }

    public final /* synthetic */ void zzx(String str, Bundle bundle) {
        d zzi = this.f10155a.zzi();
        zzi.zzg();
        zzi.zzY();
        byte[] zzbs = zzi.zzf.zzu().r(new zzao(zzi.zzs, "", str, "dep", 0L, 0L, bundle)).zzbs();
        zzi.zzs.zzay().zzj().zzc("Saving default event parameters, appId, data size", zzi.zzs.zzj().zzc(str), Integer.valueOf(zzbs.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("parameters", zzbs);
        try {
            if (zzi.F().insertWithOnConflict("default_event_params", null, contentValues, 5) == -1) {
                zzi.zzs.zzay().zzd().zzb("Failed to insert default event parameters (got -1). appId", zzei.zzn(str));
            }
        } catch (SQLiteException e) {
            zzi.zzs.zzay().zzd().zzc("Error storing default event parameters. appId", zzei.zzn(str), e);
        }
    }
}
