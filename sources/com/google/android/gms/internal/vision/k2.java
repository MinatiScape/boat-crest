package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
/* loaded from: classes10.dex */
public final class k2 implements c3 {

    /* renamed from: a  reason: collision with root package name */
    public static final k2 f9987a = new k2();

    public static k2 a() {
        return f9987a;
    }

    @Override // com.google.android.gms.internal.vision.c3
    public final boolean zza(Class<?> cls) {
        return zzgs.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.vision.c3
    public final d3 zzb(Class<?> cls) {
        if (!zzgs.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (d3) zzgs.h(cls.asSubclass(zzgs.class)).zza(zzgs.zzf.zzwt, (Object) null, (Object) null);
        } catch (Exception e) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e);
        }
    }
}
