package com.google.android.gms.internal.mlkit_code_scanner;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes8.dex */
public final class z5 extends zzs {
    private final transient zzr zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    public z5(zzr zzrVar, Object[] objArr, int i, int i2) {
        this.zza = zzrVar;
        this.zzb = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzl, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzs, com.google.android.gms.internal.mlkit_code_scanner.zzl, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return zzf().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzl
    public final int zza(Object[] objArr, int i) {
        return zzf().zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzs, com.google.android.gms.internal.mlkit_code_scanner.zzl
    public final zzaa zzd() {
        return zzf().listIterator(0);
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzs
    public final zzp zzg() {
        return new y5(this);
    }
}
