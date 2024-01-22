package androidx.core.content.res;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import kotlin.jvm.internal.DoubleCompanionObject;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final float f1029a;
    public final float b;
    public final float c;
    public final float d;
    public final float e;
    public final float f;

    public a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.f1029a = f;
        this.b = f2;
        this.c = f3;
        this.d = f7;
        this.e = f8;
        this.f = f9;
    }

    @Nullable
    public static a b(@FloatRange(from = 0.0d, to = 360.0d) float f, @FloatRange(from = 0.0d, to = Double.POSITIVE_INFINITY, toInclusive = false) float f2, @FloatRange(from = 0.0d, to = 100.0d) float f3) {
        float f4 = 1000.0f;
        float f5 = 0.0f;
        a aVar = null;
        float f6 = 100.0f;
        float f7 = 1000.0f;
        while (Math.abs(f5 - f6) > 0.01f) {
            float f8 = ((f6 - f5) / 2.0f) + f5;
            int p = e(f8, f2, f).p();
            float b = b.b(p);
            float abs = Math.abs(f3 - b);
            if (abs < 0.2f) {
                a c = c(p);
                float a2 = c.a(e(c.k(), c.i(), f));
                if (a2 <= 1.0f) {
                    aVar = c;
                    f4 = abs;
                    f7 = a2;
                }
            }
            if (f4 == 0.0f && f7 == 0.0f) {
                break;
            } else if (b < f3) {
                f5 = f8;
            } else {
                f6 = f8;
            }
        }
        return aVar;
    }

    @NonNull
    public static a c(@ColorInt int i) {
        return d(i, h.k);
    }

    @NonNull
    public static a d(@ColorInt int i, @NonNull h hVar) {
        float pow;
        float[] f = b.f(i);
        float[][] fArr = b.f1030a;
        float f2 = (f[0] * fArr[0][0]) + (f[1] * fArr[0][1]) + (f[2] * fArr[0][2]);
        float f3 = (f[0] * fArr[1][0]) + (f[1] * fArr[1][1]) + (f[2] * fArr[1][2]);
        float f4 = (f[0] * fArr[2][0]) + (f[1] * fArr[2][1]) + (f[2] * fArr[2][2]);
        float f5 = hVar.i()[0] * f2;
        float f6 = hVar.i()[1] * f3;
        float f7 = hVar.i()[2] * f4;
        float pow2 = (float) Math.pow((hVar.c() * Math.abs(f5)) / 100.0d, 0.42d);
        float pow3 = (float) Math.pow((hVar.c() * Math.abs(f6)) / 100.0d, 0.42d);
        float pow4 = (float) Math.pow((hVar.c() * Math.abs(f7)) / 100.0d, 0.42d);
        float signum = ((Math.signum(f5) * 400.0f) * pow2) / (pow2 + 27.13f);
        float signum2 = ((Math.signum(f6) * 400.0f) * pow3) / (pow3 + 27.13f);
        float signum3 = ((Math.signum(f7) * 400.0f) * pow4) / (pow4 + 27.13f);
        double d = signum3;
        float f8 = ((float) (((signum * 11.0d) + (signum2 * (-12.0d))) + d)) / 11.0f;
        float f9 = ((float) ((signum + signum2) - (d * 2.0d))) / 9.0f;
        float f10 = signum2 * 20.0f;
        float f11 = (((signum * 20.0f) + f10) + (21.0f * signum3)) / 20.0f;
        float f12 = (((signum * 40.0f) + f10) + signum3) / 20.0f;
        float atan2 = (((float) Math.atan2(f9, f8)) * 180.0f) / 3.1415927f;
        if (atan2 < 0.0f) {
            atan2 += 360.0f;
        } else if (atan2 >= 360.0f) {
            atan2 -= 360.0f;
        }
        float f13 = atan2;
        float f14 = (3.1415927f * f13) / 180.0f;
        float pow5 = ((float) Math.pow((f12 * hVar.f()) / hVar.a(), hVar.b() * hVar.j())) * 100.0f;
        float d2 = hVar.d() * (4.0f / hVar.b()) * ((float) Math.sqrt(pow5 / 100.0f)) * (hVar.a() + 4.0f);
        float pow6 = ((float) Math.pow(1.64d - Math.pow(0.29d, hVar.e()), 0.73d)) * ((float) Math.pow((((((((float) (Math.cos((((((double) f13) < 20.14d ? 360.0f + f13 : f13) * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * hVar.g()) * hVar.h()) * ((float) Math.sqrt((f8 * f8) + (f9 * f9)))) / (f11 + 0.305f), 0.9d)) * ((float) Math.sqrt(pow5 / 100.0d));
        float d3 = pow6 * hVar.d();
        float sqrt = ((float) Math.sqrt((pow * hVar.b()) / (hVar.a() + 4.0f))) * 50.0f;
        float f15 = (1.7f * pow5) / ((0.007f * pow5) + 1.0f);
        float log = ((float) Math.log((0.0228f * d3) + 1.0f)) * 43.85965f;
        double d4 = f14;
        return new a(f13, pow6, pow5, d2, d3, sqrt, f15, log * ((float) Math.cos(d4)), log * ((float) Math.sin(d4)));
    }

    @NonNull
    public static a e(@FloatRange(from = 0.0d, to = 100.0d) float f, @FloatRange(from = 0.0d, to = Double.POSITIVE_INFINITY, toInclusive = false) float f2, @FloatRange(from = 0.0d, to = 360.0d) float f3) {
        return f(f, f2, f3, h.k);
    }

    @NonNull
    public static a f(@FloatRange(from = 0.0d, to = 100.0d) float f, @FloatRange(from = 0.0d, to = Double.POSITIVE_INFINITY, toInclusive = false) float f2, @FloatRange(from = 0.0d, to = 360.0d) float f3, h hVar) {
        double d;
        float b = (4.0f / hVar.b()) * ((float) Math.sqrt(f / 100.0d)) * (hVar.a() + 4.0f) * hVar.d();
        float d2 = f2 * hVar.d();
        float sqrt = ((float) Math.sqrt(((f2 / ((float) Math.sqrt(d))) * hVar.b()) / (hVar.a() + 4.0f))) * 50.0f;
        float f4 = (1.7f * f) / ((0.007f * f) + 1.0f);
        float log = ((float) Math.log((d2 * 0.0228d) + 1.0d)) * 43.85965f;
        double d3 = (3.1415927f * f3) / 180.0f;
        return new a(f3, f2, f, b, d2, sqrt, f4, log * ((float) Math.cos(d3)), log * ((float) Math.sin(d3)));
    }

    public static int m(@FloatRange(from = 0.0d, to = 360.0d) float f, @FloatRange(from = 0.0d, to = Double.POSITIVE_INFINITY, toInclusive = false) float f2, @FloatRange(from = 0.0d, to = 100.0d) float f3) {
        return n(f, f2, f3, h.k);
    }

    @ColorInt
    public static int n(@FloatRange(from = 0.0d, to = 360.0d) float f, @FloatRange(from = 0.0d, to = Double.POSITIVE_INFINITY, toInclusive = false) float f2, @FloatRange(from = 0.0d, to = 100.0d) float f3, @NonNull h hVar) {
        if (f2 >= 1.0d && Math.round(f3) > 0.0d && Math.round(f3) < 100.0d) {
            float min = f < 0.0f ? 0.0f : Math.min(360.0f, f);
            a aVar = null;
            boolean z = true;
            float f4 = 0.0f;
            float f5 = f2;
            while (Math.abs(f4 - f2) >= 0.4f) {
                a b = b(min, f5, f3);
                if (z) {
                    if (b != null) {
                        return b.o(hVar);
                    }
                    z = false;
                } else if (b == null) {
                    f2 = f5;
                } else {
                    f4 = f5;
                    aVar = b;
                }
                f5 = ((f2 - f4) / 2.0f) + f4;
            }
            if (aVar == null) {
                return b.a(f3);
            }
            return aVar.o(hVar);
        }
        return b.a(f3);
    }

    public float a(@NonNull a aVar) {
        float l = l() - aVar.l();
        float g = g() - aVar.g();
        float h = h() - aVar.h();
        return (float) (Math.pow(Math.sqrt((l * l) + (g * g) + (h * h)), 0.63d) * 1.41d);
    }

    @FloatRange(from = DoubleCompanionObject.NEGATIVE_INFINITY, fromInclusive = false, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false)
    public float g() {
        return this.e;
    }

    @FloatRange(from = DoubleCompanionObject.NEGATIVE_INFINITY, fromInclusive = false, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false)
    public float h() {
        return this.f;
    }

    @FloatRange(from = 0.0d, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false)
    public float i() {
        return this.b;
    }

    @FloatRange(from = 0.0d, to = 360.0d, toInclusive = false)
    public float j() {
        return this.f1029a;
    }

    @FloatRange(from = 0.0d, to = 100.0d)
    public float k() {
        return this.c;
    }

    @FloatRange(from = 0.0d, to = 100.0d)
    public float l() {
        return this.d;
    }

    @ColorInt
    public int o(@NonNull h hVar) {
        float f;
        float pow = (float) Math.pow(((((double) i()) == 0.0d || ((double) k()) == 0.0d) ? 0.0f : i() / ((float) Math.sqrt(k() / 100.0d))) / Math.pow(1.64d - Math.pow(0.29d, hVar.e()), 0.73d), 1.1111111111111112d);
        double j = (j() * 3.1415927f) / 180.0f;
        float a2 = hVar.a() * ((float) Math.pow(k() / 100.0d, (1.0d / hVar.b()) / hVar.j()));
        float cos = ((float) (Math.cos(2.0d + j) + 3.8d)) * 0.25f * 3846.1538f * hVar.g() * hVar.h();
        float f2 = a2 / hVar.f();
        float sin = (float) Math.sin(j);
        float cos2 = (float) Math.cos(j);
        float f3 = (((0.305f + f2) * 23.0f) * pow) / (((cos * 23.0f) + ((11.0f * pow) * cos2)) + ((pow * 108.0f) * sin));
        float f4 = cos2 * f3;
        float f5 = f3 * sin;
        float f6 = f2 * 460.0f;
        float f7 = (((451.0f * f4) + f6) + (288.0f * f5)) / 1403.0f;
        float f8 = ((f6 - (891.0f * f4)) - (261.0f * f5)) / 1403.0f;
        float signum = Math.signum(f7) * (100.0f / hVar.c()) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f7) * 27.13d) / (400.0d - Math.abs(f7))), 2.380952380952381d));
        float signum2 = Math.signum(f8) * (100.0f / hVar.c()) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f8) * 27.13d) / (400.0d - Math.abs(f8))), 2.380952380952381d));
        float signum3 = Math.signum(((f6 - (f4 * 220.0f)) - (f5 * 6300.0f)) / 1403.0f) * (100.0f / hVar.c()) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f) * 27.13d) / (400.0d - Math.abs(f))), 2.380952380952381d));
        float f9 = signum / hVar.i()[0];
        float f10 = signum2 / hVar.i()[1];
        float f11 = signum3 / hVar.i()[2];
        float[][] fArr = b.b;
        return ColorUtils.XYZToColor((fArr[0][0] * f9) + (fArr[0][1] * f10) + (fArr[0][2] * f11), (fArr[1][0] * f9) + (fArr[1][1] * f10) + (fArr[1][2] * f11), (f9 * fArr[2][0]) + (f10 * fArr[2][1]) + (f11 * fArr[2][2]));
    }

    @ColorInt
    public int p() {
        return o(h.k);
    }
}
