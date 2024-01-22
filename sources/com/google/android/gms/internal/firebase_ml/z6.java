package com.google.android.gms.internal.firebase_ml;

import java.util.Map;
/* loaded from: classes7.dex */
public final class z6<K> implements Map.Entry<K, Object> {
    public Map.Entry<K, zzxp> h;

    public z6(Map.Entry<K, zzxp> entry) {
        this.h = entry;
    }

    public final zzxp a() {
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
        return zzxp.zzvl();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj instanceof zzyk) {
            return this.h.getValue().zzi((zzyk) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
