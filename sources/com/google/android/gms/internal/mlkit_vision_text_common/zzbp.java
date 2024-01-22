package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public abstract class zzbp extends zzbh implements Set {
    @CheckForNull
    private transient zzbm zza;

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzcn.a(this);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbh, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    /* renamed from: zzd */
    public abstract zzcq iterator();

    public final zzbm zzf() {
        zzbm zzbmVar = this.zza;
        if (zzbmVar == null) {
            zzbm zzg = zzg();
            this.zza = zzg;
            return zzg;
        }
        return zzbmVar;
    }

    public zzbm zzg() {
        return zzbm.zzh(toArray());
    }
}
