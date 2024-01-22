package com.mappls.sdk.turf;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.geojson.GeometryCollection;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.MultiLineString;
import com.mappls.sdk.geojson.MultiPoint;
import com.mappls.sdk.geojson.MultiPolygon;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.Polygon;
import java.util.ArrayList;
import java.util.List;
@Keep
/* loaded from: classes8.dex */
public final class TurfMeta {
    private TurfMeta() {
    }

    @NonNull
    private static List<Point> addCoordAll(@NonNull List<Point> list, @NonNull Feature feature, @NonNull boolean z) {
        return coordAllFromSingleGeometry(list, feature.geometry(), z);
    }

    @NonNull
    public static List<Point> coordAll(@NonNull Point point) {
        return coordAll(new ArrayList(), point);
    }

    @NonNull
    private static List<Point> coordAllFromSingleGeometry(@NonNull List<Point> list, @NonNull Geometry geometry, @NonNull boolean z) {
        if (geometry instanceof Point) {
            list.add((Point) geometry);
        } else if (geometry instanceof MultiPoint) {
            list.addAll(((MultiPoint) geometry).coordinates());
        } else if (geometry instanceof LineString) {
            list.addAll(((LineString) geometry).coordinates());
        } else if (geometry instanceof MultiLineString) {
            coordAll(list, (MultiLineString) geometry);
        } else if (geometry instanceof Polygon) {
            coordAll(list, (Polygon) geometry, z);
        } else if (geometry instanceof MultiPolygon) {
            coordAll(list, (MultiPolygon) geometry, z);
        } else if (geometry instanceof GeometryCollection) {
            for (Geometry geometry2 : ((GeometryCollection) geometry).geometries()) {
                coordAllFromSingleGeometry(list, geometry2, z);
            }
        }
        return list;
    }

    public static Point getCoord(Feature feature) {
        if (feature.geometry() instanceof Point) {
            return (Point) feature.geometry();
        }
        throw new TurfException("A Feature with a Point geometry is required.");
    }

    @NonNull
    private static List<Point> coordAll(@NonNull List<Point> list, @NonNull Point point) {
        list.add(point);
        return list;
    }

    @NonNull
    public static List<Point> coordAll(@NonNull MultiPoint multiPoint) {
        return coordAll(new ArrayList(), multiPoint);
    }

    @NonNull
    private static List<Point> coordAll(@NonNull List<Point> list, @NonNull MultiPoint multiPoint) {
        list.addAll(multiPoint.coordinates());
        return list;
    }

    @NonNull
    public static List<Point> coordAll(@NonNull LineString lineString) {
        return coordAll(new ArrayList(), lineString);
    }

    @NonNull
    private static List<Point> coordAll(@NonNull List<Point> list, @NonNull LineString lineString) {
        list.addAll(lineString.coordinates());
        return list;
    }

    @NonNull
    public static List<Point> coordAll(@NonNull Polygon polygon, @NonNull boolean z) {
        return coordAll(new ArrayList(), polygon, z);
    }

    @NonNull
    private static List<Point> coordAll(@NonNull List<Point> list, @NonNull Polygon polygon, @NonNull boolean z) {
        for (int i = 0; i < polygon.coordinates().size(); i++) {
            for (int i2 = 0; i2 < polygon.coordinates().get(i).size() - (z ? 1 : 0); i2++) {
                list.add(polygon.coordinates().get(i).get(i2));
            }
        }
        return list;
    }

    @NonNull
    public static List<Point> coordAll(@NonNull MultiLineString multiLineString) {
        return coordAll(new ArrayList(), multiLineString);
    }

    @NonNull
    private static List<Point> coordAll(@NonNull List<Point> list, @NonNull MultiLineString multiLineString) {
        for (int i = 0; i < multiLineString.coordinates().size(); i++) {
            list.addAll(multiLineString.coordinates().get(i));
        }
        return list;
    }

    @NonNull
    public static List<Point> coordAll(@NonNull MultiPolygon multiPolygon, @NonNull boolean z) {
        return coordAll(new ArrayList(), multiPolygon, z);
    }

    @NonNull
    private static List<Point> coordAll(@NonNull List<Point> list, @NonNull MultiPolygon multiPolygon, @NonNull boolean z) {
        for (int i = 0; i < multiPolygon.coordinates().size(); i++) {
            for (int i2 = 0; i2 < multiPolygon.coordinates().get(i).size(); i2++) {
                for (int i3 = 0; i3 < multiPolygon.coordinates().get(i).get(i2).size() - (z ? 1 : 0); i3++) {
                    list.add(multiPolygon.coordinates().get(i).get(i2).get(i3));
                }
            }
        }
        return list;
    }

    @NonNull
    public static List<Point> coordAll(@NonNull Feature feature, @NonNull boolean z) {
        return addCoordAll(new ArrayList(), feature, z);
    }

    @NonNull
    public static List<Point> coordAll(@NonNull FeatureCollection featureCollection, @NonNull boolean z) {
        ArrayList arrayList = new ArrayList();
        for (Feature feature : featureCollection.features()) {
            addCoordAll(arrayList, feature, z);
        }
        return arrayList;
    }
}
