package com.google.android.libraries.places.internal;

import java.util.Map;
/* loaded from: classes10.dex */
final class zzsv<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzst> zza;

    private zzsv(Map.Entry<K, zzst> entry) {
        this.zza = entry;
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.zza.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.zza.getValue() == null) {
            return null;
        }
        return zzst.zza();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj instanceof zzto) {
            return this.zza.getValue().zza((zzto) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzst zza() {
        return this.zza.getValue();
    }
}
