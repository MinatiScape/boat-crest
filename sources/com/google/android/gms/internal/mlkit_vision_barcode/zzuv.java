package com.google.android.gms.internal.mlkit_vision_barcode;
/* loaded from: classes9.dex */
public abstract class zzuv {
    public static zzuv zzg(float f, float f2, float f3, float f4, float f5) {
        return new u8(f, f2, f3, f4, 0.0f);
    }

    public abstract float a();

    public abstract float b();

    public abstract float c();

    public abstract float d();

    public abstract float e();

    public final float f() {
        if (g()) {
            return (b() - c()) * (d() - e());
        }
        return 0.0f;
    }

    public final boolean g() {
        return c() >= 0.0f && c() < b() && b() <= 1.0f && e() >= 0.0f && e() < d() && d() <= 1.0f;
    }
}
