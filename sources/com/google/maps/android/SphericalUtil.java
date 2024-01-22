package com.google.maps.android;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;
/* loaded from: classes10.dex */
public class SphericalUtil {
    public static double a(LatLng latLng, LatLng latLng2) {
        return c(Math.toRadians(latLng.latitude), Math.toRadians(latLng.longitude), Math.toRadians(latLng2.latitude), Math.toRadians(latLng2.longitude));
    }

    public static double b(List<LatLng> list, double d) {
        int size = list.size();
        double d2 = 0.0d;
        if (size < 3) {
            return 0.0d;
        }
        LatLng latLng = list.get(size - 1);
        double tan = Math.tan((1.5707963267948966d - Math.toRadians(latLng.latitude)) / 2.0d);
        double radians = Math.toRadians(latLng.longitude);
        double d3 = tan;
        double d4 = radians;
        for (LatLng latLng2 : list) {
            double tan2 = Math.tan((1.5707963267948966d - Math.toRadians(latLng2.latitude)) / 2.0d);
            double radians2 = Math.toRadians(latLng2.longitude);
            d2 += d(tan2, radians2, d3, d4);
            d3 = tan2;
            d4 = radians2;
        }
        return d2 * d * d;
    }

    public static double c(double d, double d2, double d3, double d4) {
        return a.a(a.d(d, d3, d2 - d4));
    }

    public static double computeArea(List<LatLng> list) {
        return Math.abs(computeSignedArea(list));
    }

    public static double computeDistanceBetween(LatLng latLng, LatLng latLng2) {
        return a(latLng, latLng2) * 6371009.0d;
    }

    public static double computeHeading(LatLng latLng, LatLng latLng2) {
        double radians = Math.toRadians(latLng.latitude);
        double radians2 = Math.toRadians(latLng.longitude);
        double radians3 = Math.toRadians(latLng2.latitude);
        double radians4 = Math.toRadians(latLng2.longitude) - radians2;
        return a.k(Math.toDegrees(Math.atan2(Math.sin(radians4) * Math.cos(radians3), (Math.cos(radians) * Math.sin(radians3)) - ((Math.sin(radians) * Math.cos(radians3)) * Math.cos(radians4)))), -180.0d, 180.0d);
    }

    public static double computeLength(List<LatLng> list) {
        double d = 0.0d;
        if (list.size() < 2) {
            return 0.0d;
        }
        LatLng latLng = null;
        for (LatLng latLng2 : list) {
            if (latLng != null) {
                d += c(Math.toRadians(latLng.latitude), Math.toRadians(latLng.longitude), Math.toRadians(latLng2.latitude), Math.toRadians(latLng2.longitude));
            }
            latLng = latLng2;
        }
        return d * 6371009.0d;
    }

    public static LatLng computeOffset(LatLng latLng, double d, double d2) {
        double d3 = d / 6371009.0d;
        double radians = Math.toRadians(d2);
        double radians2 = Math.toRadians(latLng.latitude);
        double radians3 = Math.toRadians(latLng.longitude);
        double cos = Math.cos(d3);
        double sin = Math.sin(d3);
        double sin2 = Math.sin(radians2);
        double cos2 = sin * Math.cos(radians2);
        double cos3 = (cos * sin2) + (Math.cos(radians) * cos2);
        return new LatLng(Math.toDegrees(Math.asin(cos3)), Math.toDegrees(radians3 + Math.atan2(cos2 * Math.sin(radians), cos - (sin2 * cos3))));
    }

    public static LatLng computeOffsetOrigin(LatLng latLng, double d, double d2) {
        double radians = Math.toRadians(d2);
        double d3 = d / 6371009.0d;
        double cos = Math.cos(d3);
        double sin = Math.sin(d3) * Math.cos(radians);
        double sin2 = Math.sin(d3) * Math.sin(radians);
        double sin3 = Math.sin(Math.toRadians(latLng.latitude));
        double d4 = cos * cos;
        double d5 = sin * sin;
        double d6 = ((d5 * d4) + (d4 * d4)) - ((d4 * sin3) * sin3);
        if (d6 < 0.0d) {
            return null;
        }
        double d7 = sin * sin3;
        double d8 = d4 + d5;
        double sqrt = (d7 + Math.sqrt(d6)) / d8;
        double d9 = (sin3 - (sin * sqrt)) / cos;
        double atan2 = Math.atan2(d9, sqrt);
        if (atan2 < -1.5707963267948966d || atan2 > 1.5707963267948966d) {
            atan2 = Math.atan2(d9, (d7 - Math.sqrt(d6)) / d8);
        }
        if (atan2 < -1.5707963267948966d || atan2 > 1.5707963267948966d) {
            return null;
        }
        return new LatLng(Math.toDegrees(atan2), Math.toDegrees(Math.toRadians(latLng.longitude) - Math.atan2(sin2, (cos * Math.cos(atan2)) - (sin * Math.sin(atan2)))));
    }

    public static double computeSignedArea(List<LatLng> list) {
        return b(list, 6371009.0d);
    }

    public static double d(double d, double d2, double d3, double d4) {
        double d5 = d2 - d4;
        double d6 = d * d3;
        return Math.atan2(Math.sin(d5) * d6, (d6 * Math.cos(d5)) + 1.0d) * 2.0d;
    }

    public static LatLng interpolate(LatLng latLng, LatLng latLng2, double d) {
        double radians = Math.toRadians(latLng.latitude);
        double radians2 = Math.toRadians(latLng.longitude);
        double radians3 = Math.toRadians(latLng2.latitude);
        double radians4 = Math.toRadians(latLng2.longitude);
        double cos = Math.cos(radians);
        double cos2 = Math.cos(radians3);
        double a2 = a(latLng, latLng2);
        double sin = Math.sin(a2);
        if (sin < 1.0E-6d) {
            double d2 = latLng.latitude;
            double d3 = d2 + ((latLng2.latitude - d2) * d);
            double d4 = latLng.longitude;
            return new LatLng(d3, d4 + ((latLng2.longitude - d4) * d));
        }
        double sin2 = Math.sin((1.0d - d) * a2) / sin;
        double sin3 = Math.sin(a2 * d) / sin;
        double d5 = cos * sin2;
        double d6 = cos2 * sin3;
        double cos3 = (Math.cos(radians2) * d5) + (Math.cos(radians4) * d6);
        double sin4 = (d5 * Math.sin(radians2)) + (d6 * Math.sin(radians4));
        return new LatLng(Math.toDegrees(Math.atan2((sin2 * Math.sin(radians)) + (Math.sin(radians3) * sin3), Math.sqrt((cos3 * cos3) + (sin4 * sin4)))), Math.toDegrees(Math.atan2(sin4, cos3)));
    }
}
