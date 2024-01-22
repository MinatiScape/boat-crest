package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.HttpUrl;
/* loaded from: classes10.dex */
public final class zzed extends x0 {
    public static final AtomicReference<String[]> zza = new AtomicReference<>();
    public static final AtomicReference<String[]> zzb = new AtomicReference<>();
    public static final AtomicReference<String[]> zzc = new AtomicReference<>();

    public zzed(zzfs zzfsVar) {
        super(zzfsVar);
    }

    public static final String b(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (zzku.B(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    str2 = strArr3[i];
                    if (str2 == null) {
                        str2 = strArr2[i] + "(" + strArr[i] + ")";
                        strArr3[i] = str2;
                    }
                }
                return str2;
            }
        }
        return str;
    }

    public final String zza(Object[] objArr) {
        String valueOf;
        if (objArr == null) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object obj : objArr) {
            if (obj instanceof Bundle) {
                valueOf = zzb((Bundle) obj);
            } else {
                valueOf = String.valueOf(obj);
            }
            if (valueOf != null) {
                if (sb.length() != 1) {
                    sb.append(", ");
                }
                sb.append(valueOf);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public final String zzb(Bundle bundle) {
        String valueOf;
        if (bundle == null) {
            return null;
        }
        if (!zzh()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Bundle[{");
        for (String str : bundle.keySet()) {
            if (sb.length() != 8) {
                sb.append(", ");
            }
            sb.append(zzd(str));
            sb.append("=");
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                valueOf = zza(new Object[]{obj});
            } else if (obj instanceof Object[]) {
                valueOf = zza((Object[]) obj);
            } else if (obj instanceof ArrayList) {
                valueOf = zza(((ArrayList) obj).toArray());
            } else {
                valueOf = String.valueOf(obj);
            }
            sb.append(valueOf);
        }
        sb.append("}]");
        return sb.toString();
    }

    public final String zzc(String str) {
        if (str == null) {
            return null;
        }
        return !zzh() ? str : b(str, zzgp.zzc, zzgp.zza, zza);
    }

    public final String zzd(String str) {
        if (str == null) {
            return null;
        }
        return !zzh() ? str : b(str, zzgq.zzb, zzgq.zza, zzb);
    }

    public final String zze(String str) {
        if (str == null) {
            return null;
        }
        if (zzh()) {
            if (str.startsWith("_exp_")) {
                return "experiment_id(" + str + ")";
            }
            return b(str, zzgr.zzb, zzgr.zza, zzc);
        }
        return str;
    }

    @Override // com.google.android.gms.measurement.internal.x0
    public final boolean zzf() {
        return false;
    }

    public final boolean zzh() {
        this.zzs.zzaw();
        return this.zzs.zzL() && Log.isLoggable(this.zzs.zzay().zzq(), 3);
    }
}
