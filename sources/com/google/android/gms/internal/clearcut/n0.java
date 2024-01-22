package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
/* loaded from: classes7.dex */
public final class n0 implements f1 {

    /* renamed from: a  reason: collision with root package name */
    public static final n0 f8589a = new n0();

    public static n0 a() {
        return f8589a;
    }

    @Override // com.google.android.gms.internal.clearcut.f1
    public final boolean zza(Class<?> cls) {
        return zzcg.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.clearcut.f1
    public final e1 zzb(Class<?> cls) {
        if (!zzcg.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (e1) zzcg.e(cls.asSubclass(zzcg.class)).zza(zzcg.zzg.zzkf, (Object) null, (Object) null);
        } catch (Exception e) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e);
        }
    }
}
