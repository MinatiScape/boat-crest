package com.google.zxing.pdf417.decoder;

import com.google.zxing.ResultPoint;
/* loaded from: classes11.dex */
public final class h extends g {
    public final boolean c;

    public h(c cVar, boolean z) {
        super(cVar);
        this.c = z;
    }

    public void g(a aVar) {
        d[] d = d();
        m();
        l(d, aVar);
        c a2 = a();
        ResultPoint h = this.c ? a2.h() : a2.i();
        ResultPoint b = this.c ? a2.b() : a2.c();
        int e = e((int) h.getY());
        int e2 = e((int) b.getY());
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        while (e < e2) {
            if (d[e] != null) {
                d dVar = d[e];
                int c = dVar.c() - i;
                if (c == 0) {
                    i2++;
                } else {
                    if (c == 1) {
                        i3 = Math.max(i3, i2);
                        i = dVar.c();
                    } else if (c < 0 || dVar.c() >= aVar.c() || c > e) {
                        d[e] = null;
                    } else {
                        if (i3 > 2) {
                            c *= i3 - 2;
                        }
                        boolean z = c >= e;
                        for (int i4 = 1; i4 <= c && !z; i4++) {
                            z = d[e - i4] != null;
                        }
                        if (z) {
                            d[e] = null;
                        } else {
                            i = dVar.c();
                        }
                    }
                    i2 = 1;
                }
            }
            e++;
        }
    }

    public final void h(a aVar) {
        c a2 = a();
        ResultPoint h = this.c ? a2.h() : a2.i();
        ResultPoint b = this.c ? a2.b() : a2.c();
        int e = e((int) b.getY());
        d[] d = d();
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        for (int e2 = e((int) h.getY()); e2 < e; e2++) {
            if (d[e2] != null) {
                d dVar = d[e2];
                dVar.j();
                int c = dVar.c() - i;
                if (c == 0) {
                    i2++;
                } else {
                    if (c == 1) {
                        i3 = Math.max(i3, i2);
                        i = dVar.c();
                    } else if (dVar.c() >= aVar.c()) {
                        d[e2] = null;
                    } else {
                        i = dVar.c();
                    }
                    i2 = 1;
                }
            }
        }
    }

    public a i() {
        d[] d = d();
        b bVar = new b();
        b bVar2 = new b();
        b bVar3 = new b();
        b bVar4 = new b();
        for (d dVar : d) {
            if (dVar != null) {
                dVar.j();
                int e = dVar.e() % 30;
                int c = dVar.c();
                if (!this.c) {
                    c += 2;
                }
                int i = c % 3;
                if (i == 0) {
                    bVar2.c((e * 3) + 1);
                } else if (i == 1) {
                    bVar4.c(e / 3);
                    bVar3.c(e % 3);
                } else if (i == 2) {
                    bVar.c(e + 1);
                }
            }
        }
        if (bVar.b().length == 0 || bVar2.b().length == 0 || bVar3.b().length == 0 || bVar4.b().length == 0 || bVar.b()[0] <= 0 || bVar2.b()[0] + bVar3.b()[0] < 3 || bVar2.b()[0] + bVar3.b()[0] > 90) {
            return null;
        }
        a aVar = new a(bVar.b()[0], bVar2.b()[0], bVar3.b()[0], bVar4.b()[0]);
        l(d, aVar);
        return aVar;
    }

    public int[] j() {
        d[] d;
        int c;
        a i = i();
        if (i == null) {
            return null;
        }
        h(i);
        int c2 = i.c();
        int[] iArr = new int[c2];
        for (d dVar : d()) {
            if (dVar != null && (c = dVar.c()) < c2) {
                iArr[c] = iArr[c] + 1;
            }
        }
        return iArr;
    }

    public boolean k() {
        return this.c;
    }

    public final void l(d[] dVarArr, a aVar) {
        for (int i = 0; i < dVarArr.length; i++) {
            d dVar = dVarArr[i];
            if (dVarArr[i] != null) {
                int e = dVar.e() % 30;
                int c = dVar.c();
                if (c > aVar.c()) {
                    dVarArr[i] = null;
                } else {
                    if (!this.c) {
                        c += 2;
                    }
                    int i2 = c % 3;
                    if (i2 != 0) {
                        if (i2 != 1) {
                            if (i2 == 2 && e + 1 != aVar.a()) {
                                dVarArr[i] = null;
                            }
                        } else if (e / 3 != aVar.b() || e % 3 != aVar.d()) {
                            dVarArr[i] = null;
                        }
                    } else if ((e * 3) + 1 != aVar.e()) {
                        dVarArr[i] = null;
                    }
                }
            }
        }
    }

    public final void m() {
        d[] d;
        for (d dVar : d()) {
            if (dVar != null) {
                dVar.j();
            }
        }
    }

    @Override // com.google.zxing.pdf417.decoder.g
    public String toString() {
        return "IsLeft: " + this.c + '\n' + super.toString();
    }
}
