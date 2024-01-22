package com.google.android.gms.internal.fitness;

import java.util.Map;
/* loaded from: classes8.dex */
public final class v2<K> implements Map.Entry<K, Object> {
    public Map.Entry<K, zzhl> h;

    public v2(Map.Entry<K, zzhl> entry) {
        this.h = entry;
    }

    public final zzhl a() {
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
        return zzhl.zzce();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj instanceof zzik) {
            return this.h.getValue().zzh((zzik) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
