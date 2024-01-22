package com.google.zxing;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.zxing.common.detector.MathUtils;
/* loaded from: classes11.dex */
public class ResultPoint {

    /* renamed from: a  reason: collision with root package name */
    public final float f11768a;
    public final float b;

    public ResultPoint(float f, float f2) {
        this.f11768a = f;
        this.b = f2;
    }

    public static float a(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        float f = resultPoint2.f11768a;
        float f2 = resultPoint2.b;
        return ((resultPoint3.f11768a - f) * (resultPoint.b - f2)) - ((resultPoint3.b - f2) * (resultPoint.f11768a - f));
    }

    public static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.f11768a, resultPoint.b, resultPoint2.f11768a, resultPoint2.b);
    }

    public static void orderBestPatterns(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        float distance = distance(resultPointArr[0], resultPointArr[1]);
        float distance2 = distance(resultPointArr[1], resultPointArr[2]);
        float distance3 = distance(resultPointArr[0], resultPointArr[2]);
        if (distance2 >= distance && distance2 >= distance3) {
            resultPoint = resultPointArr[0];
            resultPoint2 = resultPointArr[1];
            resultPoint3 = resultPointArr[2];
        } else if (distance3 >= distance2 && distance3 >= distance) {
            resultPoint = resultPointArr[1];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[2];
        } else {
            resultPoint = resultPointArr[2];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[1];
        }
        if (a(resultPoint2, resultPoint, resultPoint3) < 0.0f) {
            ResultPoint resultPoint4 = resultPoint3;
            resultPoint3 = resultPoint2;
            resultPoint2 = resultPoint4;
        }
        resultPointArr[0] = resultPoint2;
        resultPointArr[1] = resultPoint;
        resultPointArr[2] = resultPoint3;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ResultPoint) {
            ResultPoint resultPoint = (ResultPoint) obj;
            if (this.f11768a == resultPoint.f11768a && this.b == resultPoint.b) {
                return true;
            }
        }
        return false;
    }

    public final float getX() {
        return this.f11768a;
    }

    public final float getY() {
        return this.b;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f11768a) * 31) + Float.floatToIntBits(this.b);
    }

    public final String toString() {
        return "(" + this.f11768a + ',' + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
