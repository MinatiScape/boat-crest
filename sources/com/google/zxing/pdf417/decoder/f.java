package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.Formatter;
/* loaded from: classes11.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    public final a f11856a;
    public final g[] b;
    public c c;
    public final int d;

    public f(a aVar, c cVar) {
        this.f11856a = aVar;
        int a2 = aVar.a();
        this.d = a2;
        this.c = cVar;
        this.b = new g[a2 + 2];
    }

    public static boolean b(d dVar, d dVar2) {
        if (dVar2 != null && dVar2.g() && dVar2.a() == dVar.a()) {
            dVar.i(dVar2.c());
            return true;
        }
        return false;
    }

    public static int c(int i, int i2, d dVar) {
        if (dVar == null || dVar.g()) {
            return i2;
        }
        if (dVar.h(i)) {
            dVar.i(i);
            return 0;
        }
        return i2 + 1;
    }

    public final void a(g gVar) {
        if (gVar != null) {
            ((h) gVar).g(this.f11856a);
        }
    }

    public final int d() {
        int f = f();
        if (f == 0) {
            return 0;
        }
        for (int i = 1; i < this.d + 1; i++) {
            d[] d = this.b[i].d();
            for (int i2 = 0; i2 < d.length; i2++) {
                if (d[i2] != null && !d[i2].g()) {
                    e(i, i2, d);
                }
            }
        }
        return f;
    }

    public final void e(int i, int i2, d[] dVarArr) {
        d dVar = dVarArr[i2];
        d[] d = this.b[i - 1].d();
        g[] gVarArr = this.b;
        int i3 = i + 1;
        d[] d2 = gVarArr[i3] != null ? gVarArr[i3].d() : d;
        d[] dVarArr2 = new d[14];
        dVarArr2[2] = d[i2];
        dVarArr2[3] = d2[i2];
        if (i2 > 0) {
            int i4 = i2 - 1;
            dVarArr2[0] = dVarArr[i4];
            dVarArr2[4] = d[i4];
            dVarArr2[5] = d2[i4];
        }
        if (i2 > 1) {
            int i5 = i2 - 2;
            dVarArr2[8] = dVarArr[i5];
            dVarArr2[10] = d[i5];
            dVarArr2[11] = d2[i5];
        }
        if (i2 < dVarArr.length - 1) {
            int i6 = i2 + 1;
            dVarArr2[1] = dVarArr[i6];
            dVarArr2[6] = d[i6];
            dVarArr2[7] = d2[i6];
        }
        if (i2 < dVarArr.length - 2) {
            int i7 = i2 + 2;
            dVarArr2[9] = dVarArr[i7];
            dVarArr2[12] = d[i7];
            dVarArr2[13] = d2[i7];
        }
        for (int i8 = 0; i8 < 14 && !b(dVar, dVarArr2[i8]); i8++) {
        }
    }

    public final int f() {
        g();
        return h() + i();
    }

    public final void g() {
        g[] gVarArr = this.b;
        if (gVarArr[0] == null || gVarArr[this.d + 1] == null) {
            return;
        }
        d[] d = gVarArr[0].d();
        d[] d2 = this.b[this.d + 1].d();
        for (int i = 0; i < d.length; i++) {
            if (d[i] != null && d2[i] != null && d[i].c() == d2[i].c()) {
                for (int i2 = 1; i2 <= this.d; i2++) {
                    d dVar = this.b[i2].d()[i];
                    if (dVar != null) {
                        dVar.i(d[i].c());
                        if (!dVar.g()) {
                            this.b[i2].d()[i] = null;
                        }
                    }
                }
            }
        }
    }

    public final int h() {
        g[] gVarArr = this.b;
        if (gVarArr[0] == null) {
            return 0;
        }
        d[] d = gVarArr[0].d();
        int i = 0;
        for (int i2 = 0; i2 < d.length; i2++) {
            if (d[i2] != null) {
                int c = d[i2].c();
                int i3 = 0;
                for (int i4 = 1; i4 < this.d + 1 && i3 < 2; i4++) {
                    d dVar = this.b[i4].d()[i2];
                    if (dVar != null) {
                        i3 = c(c, i3, dVar);
                        if (!dVar.g()) {
                            i++;
                        }
                    }
                }
            }
        }
        return i;
    }

    public final int i() {
        g[] gVarArr = this.b;
        int i = this.d;
        if (gVarArr[i + 1] == null) {
            return 0;
        }
        d[] d = gVarArr[i + 1].d();
        int i2 = 0;
        for (int i3 = 0; i3 < d.length; i3++) {
            if (d[i3] != null) {
                int c = d[i3].c();
                int i4 = 0;
                for (int i5 = this.d + 1; i5 > 0 && i4 < 2; i5--) {
                    d dVar = this.b[i5].d()[i3];
                    if (dVar != null) {
                        i4 = c(c, i4, dVar);
                        if (!dVar.g()) {
                            i2++;
                        }
                    }
                }
            }
        }
        return i2;
    }

    public int j() {
        return this.d;
    }

    public int k() {
        return this.f11856a.b();
    }

    public int l() {
        return this.f11856a.c();
    }

    public c m() {
        return this.c;
    }

    public g n(int i) {
        return this.b[i];
    }

    public g[] o() {
        a(this.b[0]);
        a(this.b[this.d + 1]);
        int i = PDF417Common.MAX_CODEWORDS_IN_BARCODE;
        while (true) {
            int d = d();
            if (d <= 0 || d >= i) {
                break;
            }
            i = d;
        }
        return this.b;
    }

    public void p(c cVar) {
        this.c = cVar;
    }

    public void q(int i, g gVar) {
        this.b[i] = gVar;
    }

    public String toString() {
        g[] gVarArr = this.b;
        g gVar = gVarArr[0];
        if (gVar == null) {
            gVar = gVarArr[this.d + 1];
        }
        Formatter formatter = new Formatter();
        for (int i = 0; i < gVar.d().length; i++) {
            try {
                formatter.format("CW %3d:", Integer.valueOf(i));
                for (int i2 = 0; i2 < this.d + 2; i2++) {
                    g[] gVarArr2 = this.b;
                    if (gVarArr2[i2] == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        d dVar = gVarArr2[i2].d()[i];
                        if (dVar == null) {
                            formatter.format("    |   ", new Object[0]);
                        } else {
                            formatter.format(" %3d|%3d", Integer.valueOf(dVar.c()), Integer.valueOf(dVar.e()));
                        }
                    }
                }
                formatter.format("%n", new Object[0]);
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
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
