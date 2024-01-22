package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import sun.misc.Unsafe;
/* loaded from: classes8.dex */
public final class e2 extends f2 {
    public e2(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.f2
    public final double a(Object obj, long j) {
        return Double.longBitsToDouble(this.f9595a.getLong(obj, j));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.f2
    public final float b(Object obj, long j) {
        return Float.intBitsToFloat(this.f9595a.getInt(obj, j));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.f2
    public final void c(Object obj, long j, boolean z) {
        if (g2.h) {
            g2.d(obj, j, r3 ? (byte) 1 : (byte) 0);
        } else {
            g2.e(obj, j, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.f2
    public final void d(Object obj, long j, byte b) {
        if (g2.h) {
            g2.d(obj, j, b);
        } else {
            g2.e(obj, j, b);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.f2
    public final void e(Object obj, long j, double d) {
        this.f9595a.putLong(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.f2
    public final void f(Object obj, long j, float f) {
        this.f9595a.putInt(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.f2
    public final boolean g(Object obj, long j) {
        if (g2.h) {
            return g2.y(obj, j);
        }
        return g2.z(obj, j);
    }
}
