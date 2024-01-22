package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzgv<K, V> extends zzgp<Map.Entry<K, V>> {
    private final transient zzgn<K, V> zza;
    private final transient Object[] zzb;
    private final transient int zzc = 0;
    private final transient int zzd;

    public zzgv(zzgn<K, V> zzgnVar, Object[] objArr, int i, int i2) {
        this.zza = zzgnVar;
        this.zzb = objArr;
        this.zzd = i2;
    }

    @Override // com.google.android.libraries.places.internal.zzgj, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
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

    @Override // com.google.android.libraries.places.internal.zzgp, com.google.android.libraries.places.internal.zzgj, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final int zza(Object[] objArr, int i) {
        return zzc().zza(objArr, i);
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final zzgy<Map.Entry<K, V>> zzb() {
        return (zzgy) zzc().iterator();
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final boolean zzg() {
        return true;
    }

    @Override // com.google.android.libraries.places.internal.zzgp
    public final zzgi<Map.Entry<K, V>> zza() {
        return new zzgu(this);
    }
}
