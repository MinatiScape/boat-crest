package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Field;
import java.util.Map;
/* loaded from: classes7.dex */
public final class zzis {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, z0> f8778a = new zziq();
    public final Map<Field, z0> b = new zziq();
    public final Object c;

    public zzis(Object obj) {
        this.c = obj;
    }

    public final void zza(Field field, Class<?> cls, Object obj) {
        z0 z0Var = this.b.get(field);
        if (z0Var == null) {
            z0Var = new z0(cls);
            this.b.put(field, z0Var);
        }
        zzml.checkArgument(cls == z0Var.f8753a);
        z0Var.b.add(obj);
    }

    public final void zzhw() {
        for (Map.Entry<String, z0> entry : this.f8778a.entrySet()) {
            ((Map) this.c).put(entry.getKey(), entry.getValue().a());
        }
        for (Map.Entry<Field, z0> entry2 : this.b.entrySet()) {
            zzjd.zza(entry2.getKey(), this.c, entry2.getValue().a());
        }
    }
}
