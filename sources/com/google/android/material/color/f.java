package com.google.android.material.color;
/* loaded from: classes10.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    public float f10273a;
    public float b;
    public float c;

    public f(float f, float f2, float f3) {
        i(d(f, f2, f3));
    }

    public static b a(float f, float f2, float f3) {
        float f4 = 1000.0f;
        float f5 = 0.0f;
        b bVar = null;
        float f6 = 100.0f;
        float f7 = 1000.0f;
        while (Math.abs(f5 - f6) > 0.01f) {
            float f8 = ((f6 - f5) / 2.0f) + f5;
            int j = b.d(f8, f2, f).j();
            float j2 = e.j(j);
            float abs = Math.abs(f3 - j2);
            if (abs < 0.2f) {
                b b = b.b(j);
                float a2 = b.a(b.d(b.k(), b.h(), f));
                if (a2 <= 1.0f && a2 <= f4) {
                    bVar = b;
                    f7 = abs;
                    f4 = a2;
                }
            }
            if (f7 == 0.0f && f4 < 1.0E-9f) {
                break;
            } else if (j2 < f3) {
                f5 = f8;
            } else {
                f6 = f8;
            }
        }
        return bVar;
    }

    public static f b(float f, float f2, float f3) {
        return new f(f, f2, f3);
    }

    public static f c(int i) {
        b b = b.b(i);
        return new f(b.i(), b.h(), e.j(i));
    }

    public static int d(float f, float f2, float f3) {
        return e(f, f2, f3, i.k);
    }

    public static int e(float f, float f2, float f3, i iVar) {
        if (f2 >= 1.0d && Math.round(f3) > 0.0d && Math.round(f3) < 100.0d) {
            float c = g.c(f);
            b bVar = null;
            boolean z = true;
            float f4 = 0.0f;
            float f5 = f2;
            while (Math.abs(f4 - f2) >= 0.4f) {
                b a2 = a(c, f5, f3);
                if (z) {
                    if (a2 != null) {
                        return a2.m(iVar);
                    }
                    z = false;
                } else if (a2 == null) {
                    f2 = f5;
                } else {
                    f4 = f5;
                    bVar = a2;
                }
                f5 = ((f2 - f4) / 2.0f) + f4;
            }
            if (bVar == null) {
                return e.d(f3);
            }
            return bVar.m(iVar);
        }
        return e.d(f3);
    }

    public float f() {
        return this.b;
    }

    public float g() {
        return this.f10273a;
    }

    public float h() {
        return this.c;
    }

    public final void i(int i) {
        b b = b.b(i);
        float j = e.j(i);
        this.f10273a = b.i();
        this.b = b.h();
        this.c = j;
    }

    public void j(float f) {
        i(d(this.f10273a, this.b, f));
    }

    public int k() {
        return d(this.f10273a, this.b, this.c);
    }
}
