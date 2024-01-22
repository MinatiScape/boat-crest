package com.mappls.sdk.navigation.camera;

import androidx.annotation.Keep;
@Keep
/* loaded from: classes11.dex */
public class MathUtils {
    public static double clamp(double d, double d2, double d3) {
        return Math.max(d2, Math.min(d3, d));
    }

    public static double convertNativeBearing(double d) {
        double d2 = -d;
        while (d2 > 360.0d) {
            d2 -= 360.0d;
        }
        while (d2 < 0.0d) {
            d2 += 360.0d;
        }
        return d2;
    }

    public static double differenceBetweenAngles(double d, double d2) {
        double abs = Math.abs(d2 - d) % 360.0d;
        return abs > 180.0d ? 360.0d - abs : abs;
    }

    public static double wrap(double d, double d2, double d3) {
        double d4 = d3 - d2;
        return ((((d - d2) % d4) + d4) % d4) + d2;
    }

    public static float clamp(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f3, f));
    }
}
