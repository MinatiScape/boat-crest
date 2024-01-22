package com.google.android.gms.internal.mlkit_code_scanner;

import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes8.dex */
public abstract class zzs extends zzl implements Set {
    @CheckForNull
    private transient zzp zza;

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
        return zzz.a(this);
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzl, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    /* renamed from: zzd */
    public abstract zzaa iterator();

    public final zzp zzf() {
        zzp zzpVar = this.zza;
        if (zzpVar == null) {
            zzp zzg = zzg();
            this.zza = zzg;
            return zzg;
        }
        return zzpVar;
    }

    public zzp zzg() {
        Object[] array = toArray();
        int i = zzp.zzd;
        return zzp.zzg(array, array.length);
    }
}
