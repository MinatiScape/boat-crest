package com.google.android.material.color;
/* loaded from: classes10.dex */
public final class a {
    public static int a(int i, int i2) {
        f c = f.c(i);
        f c2 = f.c(i2);
        return f.b(g.c(c.g() + (Math.min(g.a(c.g(), c2.g()) * 0.5f, 15.0f) * b(c.g(), c2.g()))), c.f(), c.h()).k();
    }

    public static float b(float f, float f2) {
        float f3 = f2 - f;
        float f4 = f3 + 360.0f;
        float f5 = f3 - 360.0f;
        float abs = Math.abs(f3);
        float abs2 = Math.abs(f4);
        float abs3 = Math.abs(f5);
        return (abs > abs2 || abs > abs3) ? (abs2 > abs || abs2 > abs3) ? ((double) f5) >= 0.0d ? 1.0f : -1.0f : ((double) f4) >= 0.0d ? 1.0f : -1.0f : ((double) f3) >= 0.0d ? 1.0f : -1.0f;
    }
}
