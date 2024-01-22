package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzwz;
/* loaded from: classes7.dex */
public final class v6 implements n7 {

    /* renamed from: a  reason: collision with root package name */
    public static final v6 f8745a = new v6();

    public static v6 c() {
        return f8745a;
    }

    @Override // com.google.android.gms.internal.firebase_ml.n7
    public final boolean a(Class<?> cls) {
        return zzwz.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.firebase_ml.n7
    public final l7 b(Class<?> cls) {
        if (!zzwz.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (l7) zzwz.e(cls.asSubclass(zzwz.class)).zza(zzwz.zzg.zzclv, (Object) null, (Object) null);
        } catch (Exception e) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e);
        }
    }
}
