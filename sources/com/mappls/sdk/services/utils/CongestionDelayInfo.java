package com.mappls.sdk.services.utils;

import androidx.annotation.Keep;
import com.mappls.sdk.geojson.Point;
import java.util.List;
@Keep
/* loaded from: classes8.dex */
public class CongestionDelayInfo {
    private int delayDuration;
    private Point point;
    private List<Point> points;

    public CongestionDelayInfo(List<Point> list, int i) {
        this.points = list;
        this.delayDuration = i;
        this.point = list.get(list.size() / 2);
    }

    public int getDelayDuration() {
        return this.delayDuration;
    }

    public Point getPoint() {
        return this.point;
    }

    public List<Point> getPoints() {
        return this.points;
    }

    public void setDelayDuration(int i) {
        this.delayDuration = i;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setPoints(List<Point> list) {
        this.points = list;
    }

    public String toString() {
        return "CongestionSegment{point=" + this.point + ", delayDuration=" + this.delayDuration + '}';
    }
}
