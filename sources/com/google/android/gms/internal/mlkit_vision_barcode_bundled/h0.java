package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class h0 implements w0 {

    /* renamed from: a  reason: collision with root package name */
    public static final h0 f9598a = new h0();

    public static h0 a() {
        return f9598a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.w0
    public final v0 zzb(Class cls) {
        if (zzed.class.isAssignableFrom(cls)) {
            try {
                return (v0) zzed.c(cls.asSubclass(zzed.class)).zzg(3, null, null);
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e);
            }
        }
        throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.w0
    public final boolean zzc(Class cls) {
        return zzed.class.isAssignableFrom(cls);
    }
}
