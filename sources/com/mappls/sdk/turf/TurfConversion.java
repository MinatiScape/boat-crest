package com.mappls.sdk.turf;

import androidx.annotation.FloatRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.MultiLineString;
import com.mappls.sdk.geojson.MultiPoint;
import com.mappls.sdk.geojson.MultiPolygon;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.Polygon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Keep
/* loaded from: classes8.dex */
public final class TurfConversion {
    private static final Map<String, Double> FACTORS;

    static {
        HashMap hashMap = new HashMap();
        FACTORS = hashMap;
        hashMap.put(TurfConstants.UNIT_MILES, Double.valueOf(3960.0d));
        hashMap.put(TurfConstants.UNIT_NAUTICAL_MILES, Double.valueOf(3441.145d));
        hashMap.put(TurfConstants.UNIT_DEGREES, Double.valueOf(57.2957795d));
        hashMap.put(TurfConstants.UNIT_RADIANS, Double.valueOf(1.0d));
        hashMap.put(TurfConstants.UNIT_INCHES, Double.valueOf(2.509056E8d));
        hashMap.put(TurfConstants.UNIT_YARDS, Double.valueOf(6969600.0d));
        Double valueOf = Double.valueOf(6373000.0d);
        hashMap.put(TurfConstants.UNIT_METERS, valueOf);
        Double valueOf2 = Double.valueOf(6.373E8d);
        hashMap.put(TurfConstants.UNIT_CENTIMETERS, valueOf2);
        Double valueOf3 = Double.valueOf(6373.0d);
        hashMap.put("kilometers", valueOf3);
        hashMap.put(TurfConstants.UNIT_FEET, Double.valueOf(2.090879265E7d));
        hashMap.put(TurfConstants.UNIT_CENTIMETRES, valueOf2);
        hashMap.put(TurfConstants.UNIT_METRES, valueOf);
        hashMap.put(TurfConstants.UNIT_KILOMETRES, valueOf3);
    }

    private TurfConversion() {
    }

    public static FeatureCollection combine(@NonNull FeatureCollection featureCollection) {
        if (featureCollection.features() != null) {
            if (featureCollection.features().size() != 0) {
                ArrayList arrayList = new ArrayList(0);
                ArrayList arrayList2 = new ArrayList(0);
                ArrayList arrayList3 = new ArrayList(0);
                for (Feature feature : featureCollection.features()) {
                    Geometry geometry = feature.geometry();
                    boolean z = geometry instanceof Point;
                    if (!z && !(geometry instanceof MultiPoint)) {
                        boolean z2 = geometry instanceof LineString;
                        if (!z2 && !(geometry instanceof MultiLineString)) {
                            boolean z3 = geometry instanceof Polygon;
                            if (z3 || (geometry instanceof MultiPolygon)) {
                                if (z3) {
                                    arrayList3.add((Polygon) geometry);
                                } else {
                                    arrayList3.addAll(((MultiPolygon) geometry).polygons());
                                }
                            }
                        } else if (z2) {
                            arrayList2.add((LineString) geometry);
                        } else {
                            arrayList2.addAll(((MultiLineString) geometry).lineStrings());
                        }
                    } else if (z) {
                        arrayList.add((Point) geometry);
                    } else {
                        arrayList.addAll(((MultiPoint) geometry).coordinates());
                    }
                }
                ArrayList arrayList4 = new ArrayList(0);
                if (!arrayList.isEmpty()) {
                    arrayList4.add(Feature.fromGeometry(MultiPoint.fromLngLats(arrayList)));
                }
                if (!arrayList2.isEmpty()) {
                    arrayList4.add(Feature.fromGeometry(MultiLineString.fromLineStrings(arrayList2)));
                }
                if (!arrayList3.isEmpty()) {
                    arrayList4.add(Feature.fromGeometry(MultiPolygon.fromPolygons(arrayList3)));
                }
                return arrayList4.isEmpty() ? featureCollection : FeatureCollection.fromFeatures(arrayList4);
            }
            throw new TurfException("Your FeatureCollection doesn't have any Feature objects in it.");
        }
        throw new TurfException("Your FeatureCollection is null.");
    }

    public static double convertLength(@FloatRange(from = 0.0d) double d, @NonNull String str) {
        return convertLength(d, str, "kilometers");
    }

    @Nullable
    private static Feature coordsToLine(@NonNull List<List<Point>> list, @Nullable JsonObject jsonObject) {
        if (list.size() > 1) {
            return Feature.fromGeometry(MultiLineString.fromLngLats(list), jsonObject);
        }
        if (list.size() == 1) {
            return Feature.fromGeometry(LineString.fromLngLats(list.get(0)), jsonObject);
        }
        return null;
    }

    public static double degreesToRadians(double d) {
        return ((d % 360.0d) * 3.141592653589793d) / 180.0d;
    }

    public static FeatureCollection explode(@NonNull FeatureCollection featureCollection) {
        ArrayList arrayList = new ArrayList();
        for (Point point : TurfMeta.coordAll(featureCollection, true)) {
            arrayList.add(Feature.fromGeometry(point));
        }
        return FeatureCollection.fromFeatures(arrayList);
    }

    public static double lengthToDegrees(double d, String str) {
        return radiansToDegrees(lengthToRadians(d, str));
    }

    public static double lengthToRadians(double d) {
        return lengthToRadians(d, "kilometers");
    }

    public static FeatureCollection multiPolygonToLine(@NonNull Feature feature) {
        return multiPolygonToLine(feature, null);
    }

    public static Feature polygonToLine(@NonNull Feature feature) {
        return polygonToLine(feature, (JsonObject) null);
    }

    public static double radiansToDegrees(double d) {
        return ((d % 6.283185307179586d) * 180.0d) / 3.141592653589793d;
    }

    public static double radiansToLength(double d) {
        return radiansToLength(d, "kilometers");
    }

    public static double convertLength(@FloatRange(from = 0.0d) double d, @NonNull String str, @Nullable String str2) {
        if (str2 == null) {
            str2 = "kilometers";
        }
        return radiansToLength(lengthToRadians(d, str), str2);
    }

    public static double lengthToRadians(double d, @NonNull String str) {
        return d / FACTORS.get(str).doubleValue();
    }

    public static FeatureCollection multiPolygonToLine(@NonNull Feature feature, @Nullable JsonObject jsonObject) {
        Geometry geometry = feature.geometry();
        if (geometry instanceof MultiPolygon) {
            MultiPolygon multiPolygon = (MultiPolygon) geometry;
            if (jsonObject == null) {
                jsonObject = feature.type().equals("Feature") ? feature.properties() : new JsonObject();
            }
            return polygonToLine(multiPolygon, jsonObject);
        }
        throw new TurfException("Feature's geometry must be MultiPolygon");
    }

    public static Feature polygonToLine(@NonNull Feature feature, @Nullable JsonObject jsonObject) {
        Geometry geometry = feature.geometry();
        if (geometry instanceof Polygon) {
            Polygon polygon = (Polygon) geometry;
            if (jsonObject == null) {
                jsonObject = feature.type().equals("Feature") ? feature.properties() : new JsonObject();
            }
            return polygonToLine(polygon, jsonObject);
        }
        throw new TurfException("Feature's geometry must be Polygon");
    }

    public static double radiansToLength(double d, @NonNull String str) {
        return d * FACTORS.get(str).doubleValue();
    }

    public static FeatureCollection explode(@NonNull Feature feature) {
        ArrayList arrayList = new ArrayList();
        for (Point point : TurfMeta.coordAll(feature, true)) {
            arrayList.add(Feature.fromGeometry(point));
        }
        return FeatureCollection.fromFeatures(arrayList);
    }

    public static Feature polygonToLine(@NonNull Polygon polygon) {
        return polygonToLine(polygon, (JsonObject) null);
    }

    public static FeatureCollection polygonToLine(@NonNull MultiPolygon multiPolygon) {
        return polygonToLine(multiPolygon, (JsonObject) null);
    }

    public static Feature polygonToLine(@NonNull Polygon polygon, @Nullable JsonObject jsonObject) {
        return coordsToLine(polygon.coordinates(), jsonObject);
    }

    public static FeatureCollection polygonToLine(@NonNull MultiPolygon multiPolygon, @Nullable JsonObject jsonObject) {
        List<List<List<Point>>> coordinates = multiPolygon.coordinates();
        ArrayList arrayList = new ArrayList();
        for (List<List<Point>> list : coordinates) {
            arrayList.add(coordsToLine(list, jsonObject));
        }
        return FeatureCollection.fromFeatures(arrayList);
    }
}
