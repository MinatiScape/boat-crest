package com.mappls.sdk.turf;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.mappls.sdk.geojson.Point;
import java.util.List;
@Keep
/* loaded from: classes8.dex */
public class TurfClassification {
    private TurfClassification() {
    }

    @NonNull
    public static Point nearestPoint(@NonNull Point point, @NonNull List<Point> list) {
        if (list.isEmpty()) {
            return point;
        }
        Point point2 = list.get(0);
        double d = Double.POSITIVE_INFINITY;
        for (Point point3 : list) {
            double distance = TurfMeasurement.distance(point, point3);
            if (distance < d) {
                point2 = point3;
                d = distance;
            }
        }
        return point2;
    }
}
