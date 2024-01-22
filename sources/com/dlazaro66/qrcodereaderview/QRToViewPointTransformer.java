package com.dlazaro66.qrcodereaderview;

import android.graphics.Point;
import android.graphics.PointF;
import com.google.zxing.ResultPoint;
/* loaded from: classes9.dex */
public class QRToViewPointTransformer {
    public PointF[] transform(ResultPoint[] resultPointArr, boolean z, Orientation orientation, Point point, Point point2) {
        PointF[] pointFArr = new PointF[resultPointArr.length];
        int i = 0;
        for (ResultPoint resultPoint : resultPointArr) {
            pointFArr[i] = transform(resultPoint, z, orientation, point, point2);
            i++;
        }
        return pointFArr;
    }

    public PointF transform(ResultPoint resultPoint, boolean z, Orientation orientation, Point point, Point point2) {
        float f = point2.x;
        float f2 = point2.y;
        if (orientation == Orientation.PORTRAIT) {
            PointF pointF = new PointF((f2 - resultPoint.getY()) * (point.x / f2), resultPoint.getX() * (point.y / f));
            if (z) {
                pointF.y = point.y - pointF.y;
                return pointF;
            }
            return pointF;
        } else if (orientation == Orientation.LANDSCAPE) {
            PointF pointF2 = new PointF(point.x - (resultPoint.getX() * (point.x / f)), point.y - (resultPoint.getY() * (point.y / f2)));
            if (z) {
                pointF2.x = point.x - pointF2.x;
            }
            return pointF2;
        } else {
            return null;
        }
    }
}
