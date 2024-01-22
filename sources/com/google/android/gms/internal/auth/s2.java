package com.google.android.gms.internal.auth;

import sun.misc.Unsafe;
/* loaded from: classes6.dex */
public final class s2 extends t2 {
    public s2(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.auth.t2
    public final double a(Object obj, long j) {
        return Double.longBitsToDouble(j(obj, j));
    }

    @Override // com.google.android.gms.internal.auth.t2
    public final float b(Object obj, long j) {
        return Float.intBitsToFloat(i(obj, j));
    }

    @Override // com.google.android.gms.internal.auth.t2
    public final void c(Object obj, long j, boolean z) {
        if (u2.g) {
            u2.i(obj, j, z);
        } else {
            u2.j(obj, j, z);
        }
    }

    @Override // com.google.android.gms.internal.auth.t2
    public final void d(Object obj, long j, double d) {
        n(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.auth.t2
    public final void e(Object obj, long j, float f) {
        m(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.auth.t2
    public final boolean f(Object obj, long j) {
        if (u2.g) {
            return u2.q(obj, j);
        }
        return u2.r(obj, j);
    }
}
