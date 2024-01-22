package com.google.android.libraries.places.internal;

import java.util.AbstractMap;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes10.dex */
final class zzgu<K, V> extends zzgi<Map.Entry<K, V>> {
    private final /* synthetic */ zzgv zza;

    public zzgu(zzgv zzgvVar) {
        this.zza = zzgvVar;
    }

    @Override // java.util.List
    public final /* synthetic */ Object get(int i) {
        int i2;
        Object[] objArr;
        Object[] objArr2;
        i2 = this.zza.zzd;
        zzft.zza(i, i2);
        objArr = this.zza.zzb;
        int i3 = i * 2;
        Object obj = objArr[i3];
        objArr2 = this.zza.zzb;
        return new AbstractMap.SimpleImmutableEntry(obj, objArr2[i3 + 1]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i;
        i = this.zza.zzd;
        return i;
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final boolean zzg() {
        return true;
    }
}
