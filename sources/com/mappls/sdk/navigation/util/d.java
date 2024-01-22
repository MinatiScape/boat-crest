package com.mappls.sdk.navigation.util;

import com.mappls.sdk.maps.geometry.LatLng;
/* loaded from: classes11.dex */
public final class d {
    public static double a(double d, double d2) {
        double d3 = d - d2;
        while (d3 > 180.0d) {
            d3 -= 360.0d;
        }
        while (d3 <= -180.0d) {
            d3 += 360.0d;
        }
        return d3;
    }

    public static double a(double d, double d2, double d3, double d4) {
        double d5 = (((d3 - d) / 180.0d) * 3.141592653589793d) / 2.0d;
        double sin = Math.sin(d5);
        double cos = Math.cos((d3 / 180.0d) * 3.141592653589793d) * Math.cos((d / 180.0d) * 3.141592653589793d);
        double d6 = (((d4 - d2) / 180.0d) * 3.141592653589793d) / 2.0d;
        return Math.asin(Math.sqrt((Math.sin(d6) * Math.sin(d6) * cos) + (Math.sin(d5) * sin))) * 1.27456E7d;
    }

    public static double a(double d, double d2, double d3, double d4, double d5, double d6) {
        LatLng b = b(d, d2, d3, d4, d5, d6);
        return a(b.getLatitude(), b.getLongitude(), d, d2);
    }

    public static double a(LatLng latLng, double d, double d2) {
        return a(latLng.getLatitude(), latLng.getLongitude(), d, d2);
    }

    public static double a(LatLng latLng, LatLng latLng2) {
        return a(latLng.getLatitude(), latLng.getLongitude(), latLng2.getLatitude(), latLng2.getLongitude());
    }

    public static LatLng b(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7;
        double d8;
        double d9 = d3 - d5;
        double d10 = d4 - d6;
        double d11 = (d10 * d10) + (d9 * d9);
        double d12 = d5 - d3;
        double d13 = d6 - d4;
        double d14 = ((d2 - d4) * d13) + ((d - d3) * d12);
        if (d14 < 0.0d) {
            d7 = d3;
            d8 = d4;
        } else if (d14 >= d11) {
            d7 = d5;
            d8 = d6;
        } else {
            double d15 = d14 / d11;
            d7 = (d12 * d15) + d3;
            d8 = (d13 * d15) + d4;
        }
        return new LatLng(d7, d8);
    }
}
