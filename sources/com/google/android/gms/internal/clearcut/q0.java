package com.google.android.gms.internal.clearcut;

import java.util.Map;
/* loaded from: classes7.dex */
public final class q0<K> implements Map.Entry<K, Object> {
    public Map.Entry<K, zzcr> h;

    public q0(Map.Entry<K, zzcr> entry) {
        this.h = entry;
    }

    public final zzcr a() {
        return this.h.getValue();
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.h.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.h.getValue() == null) {
            return null;
        }
        return zzcr.zzbr();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj instanceof zzdo) {
            return this.h.getValue().zzi((zzdo) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
