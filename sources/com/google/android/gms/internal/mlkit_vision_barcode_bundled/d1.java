package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes8.dex */
public final class d1 {
    public static final d1 c = new d1();
    public final ConcurrentMap b = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    public final m1 f9589a = new s0();

    public static d1 a() {
        return c;
    }

    public final l1 b(Class cls) {
        zzem.b(cls, "messageType");
        l1 l1Var = (l1) this.b.get(cls);
        if (l1Var == null) {
            l1Var = this.f9589a.zza(cls);
            zzem.b(cls, "messageType");
            zzem.b(l1Var, "schema");
            l1 l1Var2 = (l1) this.b.putIfAbsent(cls, l1Var);
            if (l1Var2 != null) {
                return l1Var2;
            }
        }
        return l1Var;
    }
}
