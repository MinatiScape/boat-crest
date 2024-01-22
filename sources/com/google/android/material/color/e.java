package com.google.android.material.color;

import androidx.core.view.ViewCompat;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public static final float[] f10272a = {95.047f, 100.0f, 108.883f};

    public static int a(int i) {
        return i & 255;
    }

    public static float b(float f) {
        return f <= 0.0031308f ? f * 12.92f : (((float) Math.pow(f, 0.4166666567325592d)) * 1.055f) - 0.055f;
    }

    public static int c(int i) {
        return (i & 65280) >> 8;
    }

    public static int d(float f) {
        float f2 = (f + 16.0f) / 116.0f;
        float f3 = f2 * f2 * f2;
        boolean z = f3 > 0.008856452f;
        float f4 = (f > 8.0f ? 1 : (f == 8.0f ? 0 : -1)) > 0 ? f3 : f / 903.2963f;
        float f5 = z ? f3 : ((f2 * 116.0f) - 16.0f) / 903.2963f;
        if (!z) {
            f3 = ((f2 * 116.0f) - 16.0f) / 903.2963f;
        }
        float[] fArr = f10272a;
        return f(new float[]{f5 * fArr[0], f4 * fArr[1], f3 * fArr[2]});
    }

    public static int e(int i, int i2, int i3) {
        return (((((i & 255) << 16) | ViewCompat.MEASURED_STATE_MASK) | ((i2 & 255) << 8)) | (i3 & 255)) >>> 0;
    }

    public static int f(float[] fArr) {
        return g(fArr[0], fArr[1], fArr[2]);
    }

    public static int g(float f, float f2, float f3) {
        float f4 = f / 100.0f;
        float f5 = f2 / 100.0f;
        float f6 = f3 / 100.0f;
        float f7 = (3.2406f * f4) + ((-1.5372f) * f5) + ((-0.4986f) * f6);
        float f8 = (f4 * 0.0557f) + (f5 * (-0.204f)) + (f6 * 1.057f);
        return e(Math.max(Math.min(255, Math.round(b(f7) * 255.0f)), 0), Math.max(Math.min(255, Math.round(b(((-0.9689f) * f4) + (1.8758f * f5) + (0.0415f * f6)) * 255.0f)), 0), Math.max(Math.min(255, Math.round(b(f8) * 255.0f)), 0));
    }

    public static double[] h(int i) {
        float[] m = m(i);
        float f = m[1];
        float[] fArr = f10272a;
        double d = f / fArr[1];
        double cbrt = d > 0.008856451679035631d ? Math.cbrt(d) : ((d * 903.2962962962963d) + 16.0d) / 116.0d;
        double d2 = m[0] / fArr[0];
        double cbrt2 = d2 > 0.008856451679035631d ? Math.cbrt(d2) : ((d2 * 903.2962962962963d) + 16.0d) / 116.0d;
        double d3 = m[2] / fArr[2];
        return new double[]{(116.0d * cbrt) - 16.0d, (cbrt2 - cbrt) * 500.0d, (cbrt - (d3 > 0.008856451679035631d ? Math.cbrt(d3) : ((d3 * 903.2962962962963d) + 16.0d) / 116.0d)) * 200.0d};
    }

    public static float i(float f) {
        return f <= 0.04045f ? f / 12.92f : (float) Math.pow((f + 0.055f) / 1.055f, 2.4000000953674316d);
    }

    public static float j(int i) {
        return (float) h(i)[0];
    }

    public static int k(int i) {
        return (i & 16711680) >> 16;
    }

    public static final float[] l() {
        return Arrays.copyOf(f10272a, 3);
    }

    public static float[] m(int i) {
        float i2 = i(k(i) / 255.0f) * 100.0f;
        float i3 = i(c(i) / 255.0f) * 100.0f;
        float i4 = i(a(i) / 255.0f) * 100.0f;
        return new float[]{(0.41233894f * i2) + (0.35762063f * i3) + (0.18051042f * i4), (0.2126f * i2) + (0.7152f * i3) + (0.0722f * i4), (i2 * 0.01932141f) + (i3 * 0.11916382f) + (i4 * 0.9503448f)};
    }

    public static float n(float f) {
        return (f > 8.0f ? (float) Math.pow((f + 16.0d) / 116.0d, 3.0d) : f / 903.2963f) * 100.0f;
    }
}
