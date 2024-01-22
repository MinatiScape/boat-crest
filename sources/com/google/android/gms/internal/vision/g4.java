package com.google.android.gms.internal.vision;

import java.io.IOException;
/* loaded from: classes10.dex */
public abstract class g4<T, B> {
    public abstract void a(B b, int i, long j);

    public abstract void b(B b, int i, zzfh zzfhVar);

    public abstract void c(B b, int i, T t);

    public abstract void d(T t, x4 x4Var) throws IOException;

    public abstract boolean e(p3 p3Var);

    public final boolean f(B b, p3 p3Var) throws IOException {
        int tag = p3Var.getTag();
        int i = tag >>> 3;
        int i2 = tag & 7;
        if (i2 == 0) {
            a(b, i, p3Var.B());
            return true;
        } else if (i2 == 1) {
            g(b, i, p3Var.y());
            return true;
        } else if (i2 == 2) {
            b(b, i, p3Var.g());
            return true;
        } else if (i2 != 3) {
            if (i2 != 4) {
                if (i2 == 5) {
                    h(b, i, p3Var.i());
                    return true;
                }
                throw zzhc.zzgr();
            }
            return false;
        } else {
            B n = n();
            int i3 = 4 | (i << 3);
            while (p3Var.m() != Integer.MAX_VALUE && f(n, p3Var)) {
            }
            if (i3 == p3Var.getTag()) {
                c(b, i, o(n));
                return true;
            }
            throw zzhc.zzgq();
        }
    }

    public abstract void g(B b, int i, long j);

    public abstract void h(B b, int i, int i2);

    public abstract void i(T t, x4 x4Var) throws IOException;

    public abstract void j(Object obj, T t);

    public abstract void k(Object obj, B b);

    public abstract T l(T t, T t2);

    public abstract void m(Object obj);

    public abstract B n();

    public abstract T o(B b);

    public abstract int p(T t);

    public abstract T q(Object obj);

    public abstract B r(Object obj);

    public abstract int s(T t);
}
