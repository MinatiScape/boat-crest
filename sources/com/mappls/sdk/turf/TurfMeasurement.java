package com.mappls.sdk.turf;

import androidx.annotation.FloatRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.BoundingBox;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.GeoJson;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.geojson.GeometryCollection;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.MultiLineString;
import com.mappls.sdk.geojson.MultiPoint;
import com.mappls.sdk.geojson.MultiPolygon;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Keep
/* loaded from: classes8.dex */
public final class TurfMeasurement {
    public static double EARTH_RADIUS = 6378137.0d;

    private TurfMeasurement() {
        throw new AssertionError("No Instances.");
    }

    public static Point along(@NonNull LineString lineString, @FloatRange(from = 0.0d) double d, @NonNull String str) {
        return along(lineString.coordinates(), d, str);
    }

    public static double area(@NonNull Feature feature) {
        if (feature.geometry() != null) {
            return area(feature.geometry());
        }
        return 0.0d;
    }

    public static double[] bbox(@NonNull Point point) {
        return bboxCalculator(TurfMeta.coordAll(point));
    }

    private static double[] bboxCalculator(List<Point> list) {
        double[] dArr = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY};
        for (Point point : list) {
            if (dArr[0] > point.longitude()) {
                dArr[0] = point.longitude();
            }
            if (dArr[1] > point.latitude()) {
                dArr[1] = point.latitude();
            }
            if (dArr[2] < point.longitude()) {
                dArr[2] = point.longitude();
            }
            if (dArr[3] < point.latitude()) {
                dArr[3] = point.latitude();
            }
        }
        return dArr;
    }

    public static Feature bboxPolygon(@NonNull BoundingBox boundingBox) {
        return bboxPolygon(boundingBox, (JsonObject) null, (String) null);
    }

    public static double bearing(@NonNull Point point, @NonNull Point point2) {
        double degreesToRadians = TurfConversion.degreesToRadians(point.longitude());
        double degreesToRadians2 = TurfConversion.degreesToRadians(point2.longitude());
        double degreesToRadians3 = TurfConversion.degreesToRadians(point.latitude());
        double degreesToRadians4 = TurfConversion.degreesToRadians(point2.latitude());
        double d = degreesToRadians2 - degreesToRadians;
        return TurfConversion.radiansToDegrees(Math.atan2(Math.sin(d) * Math.cos(degreesToRadians4), (Math.cos(degreesToRadians3) * Math.sin(degreesToRadians4)) - ((Math.sin(degreesToRadians3) * Math.cos(degreesToRadians4)) * Math.cos(d))));
    }

    private static double calculateArea(@NonNull Geometry geometry) {
        if (geometry instanceof Polygon) {
            return polygonArea(((Polygon) geometry).coordinates());
        }
        double d = 0.0d;
        if (geometry instanceof MultiPolygon) {
            List<List<List<Point>>> coordinates = ((MultiPolygon) geometry).coordinates();
            for (int i = 0; i < coordinates.size(); i++) {
                d += polygonArea(coordinates.get(i));
            }
        }
        return d;
    }

    public static Feature center(Feature feature, @Nullable JsonObject jsonObject, @Nullable String str) {
        return center(FeatureCollection.fromFeature(feature), jsonObject, str);
    }

    @NonNull
    public static Point destination(@NonNull Point point, @FloatRange(from = 0.0d) double d, @FloatRange(from = -180.0d, to = 180.0d) double d2, @NonNull String str) {
        double degreesToRadians = TurfConversion.degreesToRadians(point.longitude());
        double degreesToRadians2 = TurfConversion.degreesToRadians(point.latitude());
        double degreesToRadians3 = TurfConversion.degreesToRadians(d2);
        double lengthToRadians = TurfConversion.lengthToRadians(d, str);
        double asin = Math.asin((Math.sin(degreesToRadians2) * Math.cos(lengthToRadians)) + (Math.cos(degreesToRadians2) * Math.sin(lengthToRadians) * Math.cos(degreesToRadians3)));
        return Point.fromLngLat(TurfConversion.radiansToDegrees(degreesToRadians + Math.atan2(Math.sin(degreesToRadians3) * Math.sin(lengthToRadians) * Math.cos(degreesToRadians2), Math.cos(lengthToRadians) - (Math.sin(degreesToRadians2) * Math.sin(asin)))), TurfConversion.radiansToDegrees(asin));
    }

    public static double distance(@NonNull Point point, @NonNull Point point2) {
        return distance(point, point2, "kilometers");
    }

    public static Polygon envelope(GeoJson geoJson) {
        return (Polygon) bboxPolygon(bbox(geoJson)).geometry();
    }

    public static double length(@NonNull LineString lineString, @NonNull String str) {
        return length(lineString.coordinates(), str);
    }

    public static Point midpoint(@NonNull Point point, @NonNull Point point2) {
        return destination(point, distance(point, point2, TurfConstants.UNIT_MILES) / 2.0d, bearing(point, point2), TurfConstants.UNIT_MILES);
    }

    private static double polygonArea(@NonNull List<List<Point>> list) {
        double d = 0.0d;
        if (list.size() > 0) {
            d = Math.abs(ringArea(list.get(0))) + 0.0d;
            for (int i = 1; i < list.size(); i++) {
                d -= Math.abs(ringArea(list.get(i)));
            }
        }
        return d;
    }

    private static double rad(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    private static double ringArea(@NonNull List<Point> list) {
        int i;
        int i2;
        int size = list.size();
        double d = 0.0d;
        if (size > 2) {
            for (int i3 = 0; i3 < size; i3++) {
                int i4 = size - 2;
                if (i3 == i4) {
                    i = size - 1;
                    i2 = 0;
                } else {
                    i4 = size - 1;
                    if (i3 == i4) {
                        i2 = 1;
                        i = 0;
                    } else {
                        i = i3 + 1;
                        i2 = i3 + 2;
                        i4 = i3;
                    }
                }
                d += (rad(list.get(i2).longitude()) - rad(list.get(i4).longitude())) * Math.sin(rad(list.get(i).latitude()));
            }
            double d2 = EARTH_RADIUS;
            return ((d * d2) * d2) / 2.0d;
        }
        return 0.0d;
    }

    public static BoundingBox square(@NonNull BoundingBox boundingBox) {
        if (distance(boundingBox.southwest(), Point.fromLngLat(boundingBox.east(), boundingBox.south())) >= distance(Point.fromLngLat(boundingBox.west(), boundingBox.south()), Point.fromLngLat(boundingBox.west(), boundingBox.north()))) {
            double south = (boundingBox.south() + boundingBox.north()) / 2.0d;
            return BoundingBox.fromLngLats(boundingBox.west(), south - ((boundingBox.east() - boundingBox.west()) / 2.0d), boundingBox.east(), ((boundingBox.east() - boundingBox.west()) / 2.0d) + south);
        }
        double west = (boundingBox.west() + boundingBox.east()) / 2.0d;
        return BoundingBox.fromLngLats(west - ((boundingBox.north() - boundingBox.south()) / 2.0d), boundingBox.south(), west + ((boundingBox.north() - boundingBox.south()) / 2.0d), boundingBox.north());
    }

    public static Point along(@NonNull List<Point> list, @FloatRange(from = 0.0d) double d, @NonNull String str) {
        int i = 0;
        double d2 = 0.0d;
        while (i < list.size() && (d < d2 || i != list.size() - 1)) {
            if (d2 >= d) {
                double d3 = d - d2;
                if (d3 == 0.0d) {
                    return list.get(i);
                }
                return destination(list.get(i), d3, bearing(list.get(i), list.get(i - 1)) - 180.0d, str);
            }
            i++;
            d2 += distance(list.get(i), list.get(i), str);
        }
        return list.get(list.size() - 1);
    }

    public static double area(@NonNull FeatureCollection featureCollection) {
        List<Feature> features = featureCollection.features();
        double d = 0.0d;
        if (features != null) {
            for (Feature feature : features) {
                d += area(feature);
            }
        }
        return d;
    }

    public static Feature bboxPolygon(@NonNull BoundingBox boundingBox, @Nullable JsonObject jsonObject, @Nullable String str) {
        return Feature.fromGeometry(Polygon.fromLngLats(Collections.singletonList(Arrays.asList(Point.fromLngLat(boundingBox.west(), boundingBox.south()), Point.fromLngLat(boundingBox.east(), boundingBox.south()), Point.fromLngLat(boundingBox.east(), boundingBox.north()), Point.fromLngLat(boundingBox.west(), boundingBox.north()), Point.fromLngLat(boundingBox.west(), boundingBox.south())))), jsonObject, str);
    }

    public static Feature center(Feature feature) {
        return center(FeatureCollection.fromFeature(feature), (JsonObject) null, (String) null);
    }

    public static double distance(@NonNull Point point, @NonNull Point point2, @NonNull String str) {
        double pow = Math.pow(Math.sin(TurfConversion.degreesToRadians(point2.latitude() - point.latitude()) / 2.0d), 2.0d) + (Math.pow(Math.sin(TurfConversion.degreesToRadians(point2.longitude() - point.longitude()) / 2.0d), 2.0d) * Math.cos(TurfConversion.degreesToRadians(point.latitude())) * Math.cos(TurfConversion.degreesToRadians(point2.latitude())));
        return TurfConversion.radiansToLength(Math.atan2(Math.sqrt(pow), Math.sqrt(1.0d - pow)) * 2.0d, str);
    }

    public static double[] bbox(@NonNull LineString lineString) {
        return bboxCalculator(TurfMeta.coordAll(lineString));
    }

    public static Feature center(FeatureCollection featureCollection, @Nullable JsonObject jsonObject, @Nullable String str) {
        double[] bbox = bbox(featureCollection);
        return Feature.fromGeometry(Point.fromLngLat((bbox[0] + bbox[2]) / 2.0d, (bbox[1] + bbox[3]) / 2.0d), jsonObject, str);
    }

    public static double length(@NonNull MultiLineString multiLineString, @NonNull String str) {
        double d = 0.0d;
        for (List<Point> list : multiLineString.coordinates()) {
            d += length(list, str);
        }
        return d;
    }

    public static double area(@NonNull Geometry geometry) {
        return calculateArea(geometry);
    }

    public static double[] bbox(@NonNull MultiPoint multiPoint) {
        return bboxCalculator(TurfMeta.coordAll(multiPoint));
    }

    public static double length(@NonNull Polygon polygon, @NonNull String str) {
        double d = 0.0d;
        for (List<Point> list : polygon.coordinates()) {
            d += length(list, str);
        }
        return d;
    }

    public static double[] bbox(@NonNull Polygon polygon) {
        return bboxCalculator(TurfMeta.coordAll(polygon, false));
    }

    public static Feature center(FeatureCollection featureCollection) {
        return center(featureCollection, (JsonObject) null, (String) null);
    }

    public static double length(@NonNull MultiPolygon multiPolygon, @NonNull String str) {
        double d = 0.0d;
        for (List<List<Point>> list : multiPolygon.coordinates()) {
            for (List<Point> list2 : list) {
                d += length(list2, str);
            }
        }
        return d;
    }

    public static double[] bbox(@NonNull MultiLineString multiLineString) {
        return bboxCalculator(TurfMeta.coordAll(multiLineString));
    }

    public static Feature bboxPolygon(@NonNull double[] dArr) {
        return bboxPolygon(dArr, (JsonObject) null, (String) null);
    }

    public static double[] bbox(MultiPolygon multiPolygon) {
        return bboxCalculator(TurfMeta.coordAll(multiPolygon, false));
    }

    public static Feature bboxPolygon(@NonNull double[] dArr, @Nullable JsonObject jsonObject, @Nullable String str) {
        return Feature.fromGeometry(Polygon.fromLngLats(Collections.singletonList(Arrays.asList(Point.fromLngLat(dArr[0], dArr[1]), Point.fromLngLat(dArr[2], dArr[1]), Point.fromLngLat(dArr[2], dArr[3]), Point.fromLngLat(dArr[0], dArr[3]), Point.fromLngLat(dArr[0], dArr[1])))), jsonObject, str);
    }

    public static double length(List<Point> list, String str) {
        Point point = list.get(0);
        int i = 1;
        double d = 0.0d;
        while (i < list.size()) {
            Point point2 = list.get(i);
            d += distance(point, point2, str);
            i++;
            point = point2;
        }
        return d;
    }

    public static double[] bbox(GeoJson geoJson) {
        BoundingBox bbox = geoJson.bbox();
        if (bbox != null) {
            return new double[]{bbox.west(), bbox.south(), bbox.east(), bbox.north()};
        }
        if (geoJson instanceof Geometry) {
            return bbox((Geometry) geoJson);
        }
        if (geoJson instanceof FeatureCollection) {
            return bbox((FeatureCollection) geoJson);
        }
        if (geoJson instanceof Feature) {
            return bbox((Feature) geoJson);
        }
        throw new UnsupportedOperationException("bbox type not supported for GeoJson instance");
    }

    public static double[] bbox(FeatureCollection featureCollection) {
        return bboxCalculator(TurfMeta.coordAll(featureCollection, false));
    }

    public static double[] bbox(Feature feature) {
        return bboxCalculator(TurfMeta.coordAll(feature, false));
    }

    public static double[] bbox(Geometry geometry) {
        if (geometry instanceof Point) {
            return bbox((Point) geometry);
        }
        if (geometry instanceof MultiPoint) {
            return bbox((MultiPoint) geometry);
        }
        if (geometry instanceof LineString) {
            return bbox((LineString) geometry);
        }
        if (geometry instanceof MultiLineString) {
            return bbox((MultiLineString) geometry);
        }
        if (geometry instanceof Polygon) {
            return bbox((Polygon) geometry);
        }
        if (geometry instanceof MultiPolygon) {
            return bbox((MultiPolygon) geometry);
        }
        if (geometry instanceof GeometryCollection) {
            ArrayList arrayList = new ArrayList();
            for (Geometry geometry2 : ((GeometryCollection) geometry).geometries()) {
                double[] bbox = bbox(geometry2);
                arrayList.add(Point.fromLngLat(bbox[0], bbox[1]));
                arrayList.add(Point.fromLngLat(bbox[2], bbox[1]));
                arrayList.add(Point.fromLngLat(bbox[2], bbox[3]));
                arrayList.add(Point.fromLngLat(bbox[0], bbox[3]));
            }
            return bbox(MultiPoint.fromLngLats(arrayList));
        }
        throw new RuntimeException("Unknown geometry class: " + geometry.getClass());
    }
}
