package com.google.android.gms.internal.firebase_ml;

import java.util.Locale;
import java.util.Map;
/* loaded from: classes7.dex */
public final class b1 implements Map.Entry<String, Object> {
    public Object h;
    public final zzjd i;
    public final /* synthetic */ c1 j;

    public b1(c1 c1Var, zzjd zzjdVar, Object obj) {
        this.j = c1Var;
        this.i = zzjdVar;
        this.h = zzml.checkNotNull(obj);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            return ((String) getKey()).equals(entry.getKey()) && getValue().equals(entry.getValue());
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ String getKey() {
        String name = this.i.getName();
        return this.j.i.zzhy() ? name.toLowerCase(Locale.US) : name;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.h;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return ((String) getKey()).hashCode() ^ getValue().hashCode();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.h;
        this.h = zzml.checkNotNull(obj);
        this.i.zzb(this.j.h, obj);
        return obj2;
    }
}
