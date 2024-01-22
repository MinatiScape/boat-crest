package com.google.android.gms.internal.vision;

import java.util.Map;
/* loaded from: classes10.dex */
public final class n2<K> implements Map.Entry<K, Object> {
    public Map.Entry<K, zzhd> h;

    public n2(Map.Entry<K, zzhd> entry) {
        this.h = entry;
    }

    public final zzhd a() {
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
        return zzhd.zzgu();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj instanceof zzic) {
            return this.h.getValue().zzi((zzic) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
