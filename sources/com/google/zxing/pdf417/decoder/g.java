package com.google.zxing.pdf417.decoder;

import java.util.Formatter;
/* loaded from: classes11.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public final c f11857a;
    public final d[] b;

    public g(c cVar) {
        this.f11857a = new c(cVar);
        this.b = new d[(cVar.e() - cVar.g()) + 1];
    }

    public final c a() {
        return this.f11857a;
    }

    public final d b(int i) {
        return this.b[e(i)];
    }

    public final d c(int i) {
        d dVar;
        d dVar2;
        d b = b(i);
        if (b != null) {
            return b;
        }
        for (int i2 = 1; i2 < 5; i2++) {
            int e = e(i) - i2;
            if (e >= 0 && (dVar2 = this.b[e]) != null) {
                return dVar2;
            }
            int e2 = e(i) + i2;
            d[] dVarArr = this.b;
            if (e2 < dVarArr.length && (dVar = dVarArr[e2]) != null) {
                return dVar;
            }
        }
        return null;
    }

    public final d[] d() {
        return this.b;
    }

    public final int e(int i) {
        return i - this.f11857a.g();
    }

    public final void f(int i, d dVar) {
        this.b[e(i)] = dVar;
    }

    public String toString() {
        d[] dVarArr;
        Formatter formatter = new Formatter();
        try {
            int i = 0;
            for (d dVar : this.b) {
                if (dVar == null) {
                    formatter.format("%3d:    |   %n", Integer.valueOf(i));
                    i++;
                } else {
                    formatter.format("%3d: %3d|%3d%n", Integer.valueOf(i), Integer.valueOf(dVar.c()), Integer.valueOf(dVar.e()));
                    i++;
                }
            }
            String formatter2 = formatter.toString();
            formatter.close();
            return formatter2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    formatter.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }
}
