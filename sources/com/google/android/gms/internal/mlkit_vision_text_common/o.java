package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public abstract class o implements zzce {
    @CheckForNull
    private transient Set zza;
    @CheckForNull
    private transient Map zzb;

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzce) {
            return zzp().equals(((zzce) obj).zzp());
        }
        return false;
    }

    public final int hashCode() {
        return zzp().hashCode();
    }

    public final String toString() {
        return ((e) zzp()).j.toString();
    }

    public abstract Map zzk();

    public abstract Set zzl();

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzce
    public boolean zzo(Object obj, Object obj2) {
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzce
    public final Map zzp() {
        Map map = this.zzb;
        if (map == null) {
            Map zzk = zzk();
            this.zzb = zzk;
            return zzk;
        }
        return map;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzce
    public final Set zzq() {
        Set set = this.zza;
        if (set == null) {
            Set zzl = zzl();
            this.zza = zzl;
            return zzl;
        }
        return set;
    }
}
