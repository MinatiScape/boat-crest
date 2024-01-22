package com.google.android.gms.internal.measurement;

import sun.misc.Unsafe;
/* loaded from: classes8.dex */
public final class r4 extends s4 {
    public r4(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.measurement.s4
    public final double a(Object obj, long j) {
        return Double.longBitsToDouble(k(obj, j));
    }

    @Override // com.google.android.gms.internal.measurement.s4
    public final float b(Object obj, long j) {
        return Float.intBitsToFloat(j(obj, j));
    }

    @Override // com.google.android.gms.internal.measurement.s4
    public final void c(Object obj, long j, boolean z) {
        if (t4.i) {
            t4.d(obj, j, r3 ? (byte) 1 : (byte) 0);
        } else {
            t4.e(obj, j, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.measurement.s4
    public final void d(Object obj, long j, byte b) {
        if (t4.i) {
            t4.d(obj, j, b);
        } else {
            t4.e(obj, j, b);
        }
    }

    @Override // com.google.android.gms.internal.measurement.s4
    public final void e(Object obj, long j, double d) {
        o(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.measurement.s4
    public final void f(Object obj, long j, float f) {
        n(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.measurement.s4
    public final boolean g(Object obj, long j) {
        if (t4.i) {
            return t4.y(obj, j);
        }
        return t4.z(obj, j);
    }
}
