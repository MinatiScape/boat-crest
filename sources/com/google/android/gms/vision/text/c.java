package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzw;
/* loaded from: classes10.dex */
public final class c {
    public static Rect a(Text text) {
        Point[] cornerPoints;
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        for (Point point : text.getCornerPoints()) {
            i4 = Math.min(i4, point.x);
            i = Math.max(i, point.x);
            i2 = Math.min(i2, point.y);
            i3 = Math.max(i3, point.y);
        }
        return new Rect(i4, i2, i, i3);
    }

    public static Point[] b(zzw zzwVar) {
        double sin = Math.sin(Math.toRadians(zzwVar.zzeh));
        double cos = Math.cos(Math.toRadians(zzwVar.zzeh));
        int i = zzwVar.width;
        int i2 = zzwVar.height;
        Point[] pointArr = {new Point(zzwVar.left, zzwVar.top), new Point((int) (zzwVar.left + (i * cos)), (int) (zzwVar.top + (i * sin))), new Point((int) (pointArr[1].x - (i2 * sin)), (int) (pointArr[1].y + (i2 * cos))), new Point(pointArr[0].x + (pointArr[2].x - pointArr[1].x), pointArr[0].y + (pointArr[2].y - pointArr[1].y))};
        return pointArr;
    }
}
