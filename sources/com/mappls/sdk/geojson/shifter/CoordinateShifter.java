package com.mappls.sdk.geojson.shifter;

import com.mappls.sdk.geojson.Point;
import java.util.List;
/* loaded from: classes11.dex */
public interface CoordinateShifter {
    List<Double> shiftLonLat(double d, double d2);

    List<Double> shiftLonLatAlt(double d, double d2, double d3);

    List<Double> unshiftPoint(Point point);

    List<Double> unshiftPoint(List<Double> list);
}
