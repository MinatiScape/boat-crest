package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class b3 implements n3 {

    /* renamed from: a  reason: collision with root package name */
    public static final b3 f8904a = new b3();

    public static b3 a() {
        return f8904a;
    }

    @Override // com.google.android.gms.internal.measurement.n3
    public final m3 zzb(Class<?> cls) {
        if (!zzjz.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (m3) zzjz.d(cls.asSubclass(zzjz.class)).zzl(3, null, null);
        } catch (Exception e) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e);
        }
    }

    @Override // com.google.android.gms.internal.measurement.n3
    public final boolean zzc(Class<?> cls) {
        return zzjz.class.isAssignableFrom(cls);
    }
}
