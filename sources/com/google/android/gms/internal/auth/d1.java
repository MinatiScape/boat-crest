package com.google.android.gms.internal.auth;
/* loaded from: classes6.dex */
public final class d1 implements p1 {

    /* renamed from: a  reason: collision with root package name */
    public static final d1 f8521a = new d1();

    public static d1 a() {
        return f8521a;
    }

    @Override // com.google.android.gms.internal.auth.p1
    public final o1 zzb(Class cls) {
        if (zzeu.class.isAssignableFrom(cls)) {
            try {
                return (o1) zzeu.a(cls.asSubclass(zzeu.class)).zzi(3, null, null);
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e);
            }
        }
        throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
    }

    @Override // com.google.android.gms.internal.auth.p1
    public final boolean zzc(Class cls) {
        return zzeu.class.isAssignableFrom(cls);
    }
}
