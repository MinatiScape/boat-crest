package com.mappls.sdk.turf;

import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.Polygon;
import java.util.ArrayList;
@Keep
/* loaded from: classes8.dex */
public final class TurfTransformation {
    private static final int DEFAULT_STEPS = 64;

    private TurfTransformation() {
    }

    public static Polygon circle(@NonNull Point point, double d) {
        return circle(point, d, 64, "kilometers");
    }

    public static Polygon circle(@NonNull Point point, double d, String str) {
        return circle(point, d, 64, str);
    }

    public static Polygon circle(@NonNull Point point, double d, @IntRange(from = 1) int i, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(TurfMeasurement.destination(point, d, (i2 * 360.0d) / i, str));
        }
        if (arrayList.size() > 0) {
            arrayList.add((Point) arrayList.get(0));
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(arrayList);
        return Polygon.fromLngLats(arrayList2);
    }
}
