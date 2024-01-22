package com.mappls.sdk.navigation.ui.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.utils.PolylineUtils;
import com.mappls.sdk.services.api.directions.models.LegStep;
import com.mappls.sdk.turf.TurfMeasurement;
import com.realsil.sdk.dfu.DfuException;
import java.util.List;
/* loaded from: classes11.dex */
public class d {
    public static float a(@NonNull LegStep legStep, @Nullable LegStep legStep2) {
        double d;
        if (legStep.maneuver().degree() != null) {
            return legStep.maneuver().degree().floatValue();
        }
        List<Point> decode = PolylineUtils.decode(legStep.geometry(), 6);
        Point point = decode.get(0);
        Point point2 = decode.get(decode.size() - 1);
        Point point3 = decode.get(decode.size() % 2 == 0 ? decode.size() / 2 : (decode.size() / 2) + 1);
        double latitude = point.latitude();
        double longitude = point.longitude();
        double latitude2 = point3.latitude();
        double longitude2 = point3.longitude();
        double latitude3 = point2.latitude();
        double longitude3 = point2.longitude();
        double d2 = longitude2 - longitude3;
        double d3 = longitude3 - longitude;
        double d4 = longitude - longitude2;
        double d5 = ((latitude3 * d4) + (latitude2 * d3) + (latitude * d2)) * 2.0d;
        double d6 = (longitude * longitude) + (latitude * latitude);
        double d7 = (longitude2 * longitude2) + (latitude2 * latitude2);
        double d8 = (longitude3 * longitude3) + (latitude3 * latitude3);
        double d9 = ((d4 * d8) + ((d3 * d7) + (d2 * d6))) / d5;
        double d10 = (((latitude2 - latitude) * d8) + (((latitude - latitude3) * d7) + ((latitude3 - latitude2) * d6))) / d5;
        double radians = Math.toRadians(point.latitude());
        double radians2 = Math.toRadians(point.longitude());
        double radians3 = Math.toRadians(point3.latitude());
        Math.acos((Math.cos(radians2 - Math.toRadians(point3.longitude())) * Math.cos(radians3) * Math.cos(radians)) + (Math.sin(radians3) * Math.sin(radians)));
        double radians4 = Math.toRadians(point3.latitude());
        double radians5 = Math.toRadians(point3.longitude());
        double radians6 = Math.toRadians(point2.latitude());
        Math.acos((Math.cos(radians5 - Math.toRadians(point2.longitude())) * Math.cos(radians6) * Math.cos(radians4)) + (Math.sin(radians6) * Math.sin(radians4)));
        double radians7 = Math.toRadians(point.latitude());
        double radians8 = Math.toRadians(point.longitude());
        double radians9 = Math.toRadians(point2.latitude());
        double radians10 = Math.toRadians(point2.longitude());
        double sin = Math.sin(radians7);
        double cos = Math.cos(radians8 - radians10) * Math.cos(radians9) * Math.cos(radians7);
        double radians11 = Math.toRadians(d9);
        double radians12 = Math.toRadians(d10);
        double radians13 = Math.toRadians(point.latitude());
        double radians14 = Math.toRadians(point.longitude());
        double sin2 = Math.sin(radians11);
        double cos2 = Math.cos(radians12 - radians14) * Math.cos(radians13) * Math.cos(radians11);
        double radians15 = Math.toRadians(d9);
        double radians16 = Math.toRadians(d10);
        double radians17 = Math.toRadians(point3.latitude());
        double acos = Math.acos((Math.cos(radians16 - Math.toRadians(point3.longitude())) * Math.cos(radians17) * Math.cos(radians15)) + (Math.sin(radians17) * Math.sin(radians15))) * 6371.01d;
        double acos2 = ((Math.acos(cos + (Math.sin(radians9) * sin)) * 6371.01d) / 2.0d) / (Math.acos(cos2 + (Math.sin(radians13) * sin2)) * 6371.01d);
        if (acos2 > 1.0d) {
            acos2 = 1.0d;
        }
        double acos3 = (Math.acos(acos2) * 180.0d) / 3.141592653589793d;
        Point fromLngLat = Point.fromLngLat((point.longitude() > point2.longitude() ? point.longitude() : point2.longitude()) - (Math.abs(point.longitude() - point2.longitude()) / 2.0d), (point.latitude() > point2.latitude() ? point.latitude() : point2.latitude()) - (Math.abs(point.latitude() - point2.latitude()) / 2.0d));
        double radians18 = Math.toRadians(point3.latitude());
        double radians19 = Math.toRadians(point3.longitude());
        double radians20 = Math.toRadians(fromLngLat.latitude());
        double acos4 = Math.acos((Math.cos(radians19 - Math.toRadians(fromLngLat.longitude())) * Math.cos(radians20) * Math.cos(radians18)) + (Math.sin(radians20) * Math.sin(radians18))) * 6371.01d;
        if (!Double.isNaN(180.0d - (acos3 * 2.0d))) {
            return acos4 >= acos ? b((float) (360.0d - d)) : b((float) d);
        } else if (legStep2 == null) {
            return 0.0f;
        } else {
            double bearing = TurfMeasurement.bearing(legStep.maneuver().location(), legStep2.maneuver().location());
            if (bearing < 0.0d) {
                bearing += 360.0d;
            }
            double doubleValue = legStep.maneuver().bearingBefore().doubleValue();
            if (doubleValue < 0.0d) {
                doubleValue += 360.0d;
            }
            double d11 = (bearing - doubleValue) + 180.0d;
            if (d11 < 0.0d) {
                d11 += 360.0d;
            }
            if (d11 > 360.0d) {
                d11 -= 360.0d;
            }
            return (float) d11;
        }
    }

    public static int a(float f) {
        if (f <= 45.0f) {
            return 65;
        }
        if (f <= 90.0f) {
            return 66;
        }
        if (f <= 135.0f) {
            return 67;
        }
        if (f <= 180.0f) {
            return 68;
        }
        if (f <= 225.0f) {
            return 69;
        }
        return f <= 270.0f ? 70 : 71;
    }

    public static int b(float f) {
        if (f <= 45.0f) {
            return 45;
        }
        if (f <= 90.0f) {
            return 90;
        }
        if (f <= 135.0f) {
            return 135;
        }
        if (f <= 180.0f) {
            return 180;
        }
        if (f <= 225.0f) {
            return 225;
        }
        if (f <= 270.0f) {
            return DfuException.ERROR_READ_DEVICE_INFO_ERROR;
        }
        return 315;
    }
}
