package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzgy;
/* loaded from: classes8.dex */
public final class t2 implements k3 {

    /* renamed from: a  reason: collision with root package name */
    public static final t2 f8851a = new t2();

    public static t2 a() {
        return f8851a;
    }

    @Override // com.google.android.gms.internal.fitness.k3
    public final boolean zzb(Class<?> cls) {
        return zzgy.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.fitness.k3
    public final l3 zzc(Class<?> cls) {
        if (!zzgy.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (l3) zzgy.c(cls.asSubclass(zzgy.class)).zza(zzgy.zze.zzyf, (Object) null, (Object) null);
        } catch (Exception e) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e);
        }
    }
}
