package com.google.android.material.color;
/* loaded from: classes10.dex */
public final class b {
    public static final float[][] g = {new float[]{0.401288f, 0.650173f, -0.051461f}, new float[]{-0.250268f, 1.204414f, 0.045854f}, new float[]{-0.002079f, 0.048952f, 0.953127f}};
    public static final float[][] h = {new float[]{1.8620678f, -1.0112547f, 0.14918678f}, new float[]{0.38752654f, 0.62144744f, -0.00897398f}, new float[]{-0.0158415f, -0.03412294f, 1.0499644f}};

    /* renamed from: a  reason: collision with root package name */
    public final float f10259a;
    public final float b;
    public final float c;
    public final float d;
    public final float e;
    public final float f;

    public b(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.f10259a = f;
        this.b = f2;
        this.c = f3;
        this.d = f7;
        this.e = f8;
        this.f = f9;
    }

    public static b b(int i) {
        return c(i, i.k);
    }

    public static b c(int i, i iVar) {
        double d;
        double d2;
        float pow;
        float i2 = e.i(((16711680 & i) >> 16) / 255.0f) * 100.0f;
        float i3 = e.i(((65280 & i) >> 8) / 255.0f) * 100.0f;
        float i4 = e.i((i & 255) / 255.0f) * 100.0f;
        float f = (0.41233894f * i2) + (0.35762063f * i3) + (0.18051042f * i4);
        float f2 = (0.2126f * i2) + (0.7152f * i3) + (0.0722f * i4);
        float f3 = (i2 * 0.01932141f) + (i3 * 0.11916382f) + (i4 * 0.9503448f);
        float[][] fArr = g;
        float f4 = (fArr[0][0] * f) + (fArr[0][1] * f2) + (fArr[0][2] * f3);
        float f5 = (fArr[1][0] * f) + (fArr[1][1] * f2) + (fArr[1][2] * f3);
        float f6 = (f * fArr[2][0]) + (f2 * fArr[2][1]) + (f3 * fArr[2][2]);
        float f7 = iVar.i()[0] * f4;
        float f8 = iVar.i()[1] * f5;
        float f9 = iVar.i()[2] * f6;
        float pow2 = (float) Math.pow((iVar.c() * Math.abs(f7)) / 100.0d, 0.42d);
        float pow3 = (float) Math.pow((iVar.c() * Math.abs(f8)) / 100.0d, 0.42d);
        float pow4 = (float) Math.pow((iVar.c() * Math.abs(f9)) / 100.0d, 0.42d);
        float signum = ((Math.signum(f7) * 400.0f) * pow2) / (pow2 + 27.13f);
        float signum2 = ((Math.signum(f8) * 400.0f) * pow3) / (pow3 + 27.13f);
        float signum3 = ((Math.signum(f9) * 400.0f) * pow4) / (pow4 + 27.13f);
        double d3 = (signum * 11.0d) + (signum2 * (-12.0d));
        double d4 = signum3;
        float f10 = signum2 * 20.0f;
        float f11 = (((signum * 20.0f) + f10) + (21.0f * signum3)) / 20.0f;
        float f12 = (((signum * 40.0f) + f10) + signum3) / 20.0f;
        float atan2 = (((float) Math.atan2(((float) ((signum + signum2) - (d4 * 2.0d))) / 9.0f, ((float) (d3 + d4)) / 11.0f)) * 180.0f) / 3.1415927f;
        if (atan2 < 0.0f) {
            atan2 += 360.0f;
        } else if (atan2 >= 360.0f) {
            atan2 -= 360.0f;
        }
        float f13 = (3.1415927f * atan2) / 180.0f;
        float pow5 = ((float) Math.pow((f12 * iVar.f()) / iVar.a(), iVar.b() * iVar.j())) * 100.0f;
        float b = (4.0f / iVar.b()) * ((float) Math.sqrt(pow5 / 100.0f)) * (iVar.a() + 4.0f) * iVar.d();
        float pow6 = ((float) Math.pow(1.64d - Math.pow(0.29d, iVar.e()), 0.73d)) * ((float) Math.pow((((((((float) (Math.cos(Math.toRadians(((double) atan2) < 20.14d ? 360.0f + atan2 : atan2) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * iVar.g()) * iVar.h()) * ((float) Math.hypot(d2, d))) / (f11 + 0.305f), 0.9d)) * ((float) Math.sqrt(pow5 / 100.0d));
        float d5 = pow6 * iVar.d();
        float sqrt = ((float) Math.sqrt((pow * iVar.b()) / (iVar.a() + 4.0f))) * 50.0f;
        float f14 = (1.7f * pow5) / ((0.007f * pow5) + 1.0f);
        float log1p = ((float) Math.log1p(0.0228f * d5)) * 43.85965f;
        double d6 = f13;
        return new b(atan2, pow6, pow5, b, d5, sqrt, f14, log1p * ((float) Math.cos(d6)), log1p * ((float) Math.sin(d6)));
    }

    public static b d(float f, float f2, float f3) {
        return e(f, f2, f3, i.k);
    }

    public static b e(float f, float f2, float f3, i iVar) {
        double d;
        float b = (4.0f / iVar.b()) * ((float) Math.sqrt(f / 100.0d)) * (iVar.a() + 4.0f) * iVar.d();
        float d2 = f2 * iVar.d();
        float sqrt = ((float) Math.sqrt(((f2 / ((float) Math.sqrt(d))) * iVar.b()) / (iVar.a() + 4.0f))) * 50.0f;
        float f4 = (1.7f * f) / ((0.007f * f) + 1.0f);
        float log1p = ((float) Math.log1p(d2 * 0.0228d)) * 43.85965f;
        double d3 = (3.1415927f * f3) / 180.0f;
        return new b(f3, f2, f, b, d2, sqrt, f4, log1p * ((float) Math.cos(d3)), log1p * ((float) Math.sin(d3)));
    }

    public float a(b bVar) {
        float l = l() - bVar.l();
        float f = f() - bVar.f();
        float g2 = g() - bVar.g();
        return (float) (Math.pow(Math.sqrt((l * l) + (f * f) + (g2 * g2)), 0.63d) * 1.41d);
    }

    public float f() {
        return this.e;
    }

    public float g() {
        return this.f;
    }

    public float h() {
        return this.b;
    }

    public float i() {
        return this.f10259a;
    }

    public int j() {
        return m(i.k);
    }

    public float k() {
        return this.c;
    }

    public float l() {
        return this.d;
    }

    public int m(i iVar) {
        float f;
        float pow = (float) Math.pow(((((double) h()) == 0.0d || ((double) k()) == 0.0d) ? 0.0f : h() / ((float) Math.sqrt(k() / 100.0d))) / Math.pow(1.64d - Math.pow(0.29d, iVar.e()), 0.73d), 1.1111111111111112d);
        double i = (i() * 3.1415927f) / 180.0f;
        float a2 = iVar.a() * ((float) Math.pow(k() / 100.0d, (1.0d / iVar.b()) / iVar.j()));
        float cos = ((float) (Math.cos(2.0d + i) + 3.8d)) * 0.25f * 3846.1538f * iVar.g() * iVar.h();
        float f2 = a2 / iVar.f();
        float sin = (float) Math.sin(i);
        float cos2 = (float) Math.cos(i);
        float f3 = (((0.305f + f2) * 23.0f) * pow) / (((cos * 23.0f) + ((11.0f * pow) * cos2)) + ((pow * 108.0f) * sin));
        float f4 = cos2 * f3;
        float f5 = f3 * sin;
        float f6 = f2 * 460.0f;
        float f7 = (((451.0f * f4) + f6) + (288.0f * f5)) / 1403.0f;
        float f8 = ((f6 - (891.0f * f4)) - (261.0f * f5)) / 1403.0f;
        float signum = Math.signum(f7) * (100.0f / iVar.c()) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f7) * 27.13d) / (400.0d - Math.abs(f7))), 2.380952380952381d));
        float signum2 = Math.signum(f8) * (100.0f / iVar.c()) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f8) * 27.13d) / (400.0d - Math.abs(f8))), 2.380952380952381d));
        float signum3 = Math.signum(((f6 - (f4 * 220.0f)) - (f5 * 6300.0f)) / 1403.0f) * (100.0f / iVar.c()) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f) * 27.13d) / (400.0d - Math.abs(f))), 2.380952380952381d));
        float f9 = signum / iVar.i()[0];
        float f10 = signum2 / iVar.i()[1];
        float f11 = signum3 / iVar.i()[2];
        float[][] fArr = h;
        return e.g((fArr[0][0] * f9) + (fArr[0][1] * f10) + (fArr[0][2] * f11), (fArr[1][0] * f9) + (fArr[1][1] * f10) + (fArr[1][2] * f11), (f9 * fArr[2][0]) + (f10 * fArr[2][1]) + (f11 * fArr[2][2]));
    }
}
