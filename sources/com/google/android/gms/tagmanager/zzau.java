package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class zzau {
    public final String zza;
    public final Object zzb;

    public zzau(String str, Object obj) {
        this.zza = str;
        this.zzb = obj;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzau) {
            zzau zzauVar = (zzau) obj;
            if (this.zza.equals(zzauVar.zza)) {
                Object obj2 = this.zzb;
                if (obj2 == null && zzauVar.zzb == null) {
                    return true;
                }
                return obj2 != null && obj2.equals(zzauVar.zzb);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        Preconditions.checkNotNull(this.zzb);
        return Arrays.hashCode(new Integer[]{Integer.valueOf(this.zza.hashCode()), Integer.valueOf(this.zzb.hashCode())});
    }

    public final String toString() {
        String str = this.zza;
        String valueOf = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13 + valueOf.length());
        sb.append("Key: ");
        sb.append(str);
        sb.append(" value: ");
        sb.append(valueOf);
        return sb.toString();
    }
}
