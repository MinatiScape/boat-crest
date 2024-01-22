package com.google.android.gms.internal.gtm;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
/* loaded from: classes8.dex */
public class zzbr {
    public final zzbv zza;

    public zzbr(zzbv zzbvVar) {
        Preconditions.checkNotNull(zzbvVar);
        this.zza = zzbvVar;
    }

    public static String zzD(String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = "";
        }
        String zza = zza(obj);
        String zza2 = zza(obj2);
        String zza3 = zza(obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(zza)) {
            sb.append(str2);
            sb.append(zza);
            str2 = ", ";
        }
        if (TextUtils.isEmpty(zza2)) {
            str3 = str2;
        } else {
            sb.append(str2);
            sb.append(zza2);
        }
        if (!TextUtils.isEmpty(zza3)) {
            sb.append(str3);
            sb.append(zza3);
        }
        return sb.toString();
    }

    public static final boolean zzV() {
        return Log.isLoggable(zzeu.zzc.zzb(), 2);
    }

    public static String zza(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Boolean) {
            return obj == Boolean.TRUE ? "true" : "false";
        } else if (obj instanceof Throwable) {
            return ((Throwable) obj).toString();
        } else {
            return obj.toString();
        }
    }

    public final zzfh zzA() {
        return this.zza.zzo();
    }

    public final zzft zzB() {
        return this.zza.zzq();
    }

    public final Clock zzC() {
        return this.zza.zzr();
    }

    public final void zzE() {
        this.zza.zzj();
    }

    public final void zzF(String str) {
        zzb(3, str, null, null, null);
    }

    public final void zzG(String str, @Nullable Object obj) {
        zzb(3, str, obj, null, null);
    }

    public final void zzH(String str, Object obj, Object obj2) {
        zzb(3, str, obj, obj2, null);
    }

    public final void zzI(String str, Object obj, Object obj2, Object obj3) {
        zzb(3, "POST compressed size, ratio %, url", obj, obj2, obj3);
    }

    public final void zzJ(String str) {
        zzb(6, str, null, null, null);
    }

    public final void zzK(String str, @Nullable Object obj) {
        zzb(6, str, obj, null, null);
    }

    public final void zzL(String str, @Nullable Object obj, @Nullable Object obj2) {
        zzb(6, str, obj, obj2, null);
    }

    public final void zzM(String str) {
        zzb(4, str, null, null, null);
    }

    public final void zzN(String str, @Nullable Object obj) {
        zzb(4, str, obj, null, null);
    }

    public final void zzO(String str) {
        zzb(2, str, null, null, null);
    }

    public final void zzP(String str, @Nullable Object obj) {
        zzb(2, str, obj, null, null);
    }

    public final void zzQ(String str, @Nullable Object obj, @Nullable Object obj2) {
        zzb(2, str, obj, obj2, null);
    }

    public final void zzR(String str) {
        zzb(5, str, null, null, null);
    }

    public final void zzS(String str, @Nullable Object obj) {
        zzb(5, str, obj, null, null);
    }

    public final void zzT(String str, @Nullable Object obj, @Nullable Object obj2) {
        zzb(5, str, obj, obj2, null);
    }

    public final void zzU(String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        zzb(5, "Deleted fewer hits then expected", obj, obj2, obj3);
    }

    public final void zzb(int i, String str, Object obj, Object obj2, Object obj3) {
        zzbv zzbvVar = this.zza;
        zzfb zzn = zzbvVar != null ? zzbvVar.zzn() : null;
        if (zzn != null) {
            String zzb = zzeu.zzc.zzb();
            if (Log.isLoggable(zzb, i)) {
                Log.println(i, zzb, zzD(str, obj, obj2, obj3));
            }
            if (i >= 5) {
                zzn.zze(i, str, obj, obj2, obj3);
                return;
            }
            return;
        }
        String zzb2 = zzeu.zzc.zzb();
        if (Log.isLoggable(zzb2, i)) {
            Log.println(i, zzb2, zzD(str, obj, obj2, obj3));
        }
    }

    public final Context zzo() {
        return this.zza.zza();
    }

    public final GoogleAnalytics zzp() {
        return this.zza.zzc();
    }

    public final com.google.android.gms.analytics.zzr zzq() {
        return this.zza.zzd();
    }

    public final zzbi zzr() {
        return this.zza.zze();
    }

    public final zzbq zzs() {
        return this.zza.zzf();
    }

    public final zzbv zzt() {
        return this.zza;
    }

    public final zzcf zzu() {
        return this.zza.zzh();
    }

    public final zzcn zzv() {
        return this.zza.zzi();
    }

    public final zzct zzw() {
        return this.zza.zzj();
    }

    public final zzcx zzx() {
        return this.zza.zzk();
    }

    public final zzcy zzy() {
        return this.zza.zzl();
    }

    public final zzfb zzz() {
        return this.zza.zzm();
    }
}
