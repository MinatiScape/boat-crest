package com.google.firebase.ml.common.internal.modeldownload;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzoc;
import com.google.android.gms.internal.firebase_ml.zzod;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqg;
import com.google.android.gms.internal.firebase_ml.zzqu;
import com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel;
/* loaded from: classes10.dex */
public final class zzw {
    public static final GmsLogger d = new GmsLogger("ModelDownloadLogger", "");

    /* renamed from: a  reason: collision with root package name */
    public final zzqg f11399a;
    public final FirebaseRemoteModel b;
    public final zzqu c;

    public zzw(@NonNull zzqf zzqfVar, @NonNull FirebaseRemoteModel firebaseRemoteModel) {
        this.f11399a = zzqg.zza(zzqfVar, 4);
        this.b = firebaseRemoteModel;
        this.c = zzqu.zzb(zzqfVar);
    }

    public final void a(zzoc zzocVar, int i) {
        b(zzocVar, "NA", false, false, zzn.UNKNOWN, zzns.zzai.zza.MODEL_INFO_RETRIEVAL_FAILED, i);
    }

    public final void b(zzoc zzocVar, String str, boolean z, boolean z2, zzn zznVar, zzns.zzai.zza zzaVar, int i) {
        zzns.zzai.zzb zzk = zzns.zzai.zzmj().zzl(zzocVar).zzb(zzaVar).zzq(i).zzk(zzt.zza(this.b, zznVar));
        if (z) {
            long zzf = this.c.zzf(this.b);
            if (zzf == 0) {
                d.w("ModelDownloadLogger", "Model downloaded without its beginning time recorded.");
            } else {
                long zzg = this.c.zzg(this.b);
                if (zzg == 0) {
                    zzg = SystemClock.elapsedRealtime();
                    this.c.zza(this.b, zzg);
                }
                zzk.zzo(zzg - zzf);
            }
        }
        if (z2) {
            long zzf2 = this.c.zzf(this.b);
            if (zzf2 == 0) {
                d.w("ModelDownloadLogger", "Model downloaded without its beginning time recorded.");
            } else {
                zzk.zzp(SystemClock.elapsedRealtime() - zzf2);
            }
        }
        this.f11399a.zza(zzns.zzad.zzma().zza(zzns.zzbc.zzny().zzbt(str)).zza(zzk), zzod.MODEL_DOWNLOAD);
    }

    public final void c(zzoc zzocVar) {
        a(zzocVar, 0);
    }

    public final void zza(zzoc zzocVar, String str, boolean z, zzn zznVar) {
        b(zzocVar, str, false, false, zznVar, zzns.zzai.zza.UNKNOWN_STATUS, 0);
    }

    public final void zza(zzoc zzocVar, boolean z, zzn zznVar, zzns.zzai.zza zzaVar) {
        b(zzocVar, "NA", z, false, zznVar, zzaVar, 0);
    }

    public final void zza(zzoc zzocVar, zzn zznVar, zzns.zzai.zza zzaVar) {
        b(zzocVar, "NA", false, true, zznVar, zzaVar, 0);
    }

    public final void zza(boolean z, zzn zznVar, int i) {
        b(zzoc.DOWNLOAD_FAILED, "NA", false, false, zznVar, zzns.zzai.zza.FAILED, i);
    }
}
