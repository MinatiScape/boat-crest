package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Map;
/* loaded from: classes8.dex */
public final class k0 implements Map.Entry {
    public final Map.Entry h;

    public final zzet a() {
        return (zzet) this.h.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.h.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (((zzet) this.h.getValue()) == null) {
            return null;
        }
        throw null;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj instanceof zzfo) {
            return ((zzet) this.h.getValue()).zzc((zzfo) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
