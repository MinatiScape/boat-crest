package com.google.android.gms.internal.auth;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes6.dex */
public final class w1 {
    public static final w1 c = new w1();
    public final ConcurrentMap b = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    public final a2 f8545a = new l1();

    public static w1 a() {
        return c;
    }

    public final z1 b(Class cls) {
        zzez.c(cls, "messageType");
        z1 z1Var = (z1) this.b.get(cls);
        if (z1Var == null) {
            z1Var = this.f8545a.zza(cls);
            zzez.c(cls, "messageType");
            zzez.c(z1Var, "schema");
            z1 z1Var2 = (z1) this.b.putIfAbsent(cls, z1Var);
            if (z1Var2 != null) {
                return z1Var2;
            }
        }
        return z1Var;
    }
}
