package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
public final class a0<K, V> extends zzdj<Map.Entry<K, V>> {
    private final transient int size;
    private final transient zzdg<K, V> zzma;
    private final transient Object[] zzmb;
    private final transient int zzmc = 0;

    public a0(zzdg<K, V> zzdgVar, Object[] objArr, int i, int i2) {
        this.zzma = zzdgVar;
        this.zzmb = objArr;
        this.size = i2;
    }

    @Override // com.google.android.gms.internal.vision.zzdc, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zzma.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzdj, com.google.android.gms.internal.vision.zzdc, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final int zza(Object[] objArr, int i) {
        return zzcc().zza(objArr, i);
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final zzdr<Map.Entry<K, V>> zzby() {
        return (zzdr) zzcc().iterator();
    }

    @Override // com.google.android.gms.internal.vision.zzdj
    public final zzdf<Map.Entry<K, V>> zzch() {
        return new d0(this);
    }
}
