package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.WhiteRectangleDetector;
/* loaded from: classes11.dex */
public final class Detector {

    /* renamed from: a  reason: collision with root package name */
    public final BitMatrix f11807a;
    public final WhiteRectangleDetector b;

    public Detector(BitMatrix bitMatrix) throws NotFoundException {
        this.f11807a = bitMatrix;
        this.b = new WhiteRectangleDetector(bitMatrix);
    }

    public static ResultPoint e(ResultPoint resultPoint, float f, float f2) {
        float x = resultPoint.getX();
        float y = resultPoint.getY();
        return new ResultPoint(x < f ? x - 1.0f : x + 1.0f, y < f2 ? y - 1.0f : y + 1.0f);
    }

    public static BitMatrix f(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) throws NotFoundException {
        float f = i - 0.5f;
        float f2 = i2 - 0.5f;
        return GridSampler.getInstance().sampleGrid(bitMatrix, i, i2, 0.5f, 0.5f, f, 0.5f, f, f2, 0.5f, f2, resultPoint.getX(), resultPoint.getY(), resultPoint4.getX(), resultPoint4.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    public static ResultPoint g(ResultPoint resultPoint, ResultPoint resultPoint2, int i) {
        float f = i + 1;
        return new ResultPoint(resultPoint.getX() + ((resultPoint2.getX() - resultPoint.getX()) / f), resultPoint.getY() + ((resultPoint2.getY() - resultPoint.getY()) / f));
    }

    public final ResultPoint a(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint = resultPointArr[0];
        ResultPoint resultPoint2 = resultPointArr[1];
        ResultPoint resultPoint3 = resultPointArr[2];
        ResultPoint resultPoint4 = resultPointArr[3];
        int i = i(resultPoint, resultPoint4);
        ResultPoint g = g(resultPoint, resultPoint2, (i(resultPoint2, resultPoint4) + 1) << 2);
        ResultPoint g2 = g(resultPoint3, resultPoint2, (i + 1) << 2);
        int i2 = i(g, resultPoint4);
        int i3 = i(g2, resultPoint4);
        float f = i2 + 1;
        ResultPoint resultPoint5 = new ResultPoint(resultPoint4.getX() + ((resultPoint3.getX() - resultPoint2.getX()) / f), resultPoint4.getY() + ((resultPoint3.getY() - resultPoint2.getY()) / f));
        float f2 = i3 + 1;
        ResultPoint resultPoint6 = new ResultPoint(resultPoint4.getX() + ((resultPoint.getX() - resultPoint2.getX()) / f2), resultPoint4.getY() + ((resultPoint.getY() - resultPoint2.getY()) / f2));
        if (d(resultPoint5)) {
            return (d(resultPoint6) && i(g, resultPoint5) + i(g2, resultPoint5) <= i(g, resultPoint6) + i(g2, resultPoint6)) ? resultPoint6 : resultPoint5;
        } else if (d(resultPoint6)) {
            return resultPoint6;
        } else {
            return null;
        }
    }

    public final ResultPoint[] b(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint = resultPointArr[0];
        ResultPoint resultPoint2 = resultPointArr[1];
        ResultPoint resultPoint3 = resultPointArr[3];
        ResultPoint resultPoint4 = resultPointArr[2];
        int i = i(resultPoint, resultPoint2);
        int i2 = i(resultPoint2, resultPoint3);
        int i3 = i(resultPoint3, resultPoint4);
        int i4 = i(resultPoint4, resultPoint);
        ResultPoint[] resultPointArr2 = {resultPoint4, resultPoint, resultPoint2, resultPoint3};
        if (i > i2) {
            resultPointArr2[0] = resultPoint;
            resultPointArr2[1] = resultPoint2;
            resultPointArr2[2] = resultPoint3;
            resultPointArr2[3] = resultPoint4;
            i = i2;
        }
        if (i > i3) {
            resultPointArr2[0] = resultPoint2;
            resultPointArr2[1] = resultPoint3;
            resultPointArr2[2] = resultPoint4;
            resultPointArr2[3] = resultPoint;
        } else {
            i3 = i;
        }
        if (i3 > i4) {
            resultPointArr2[0] = resultPoint3;
            resultPointArr2[1] = resultPoint4;
            resultPointArr2[2] = resultPoint;
            resultPointArr2[3] = resultPoint2;
        }
        return resultPointArr2;
    }

    public final ResultPoint[] c(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint = resultPointArr[0];
        ResultPoint resultPoint2 = resultPointArr[1];
        ResultPoint resultPoint3 = resultPointArr[2];
        ResultPoint resultPoint4 = resultPointArr[3];
        int i = (i(resultPoint, resultPoint4) + 1) << 2;
        if (i(g(resultPoint2, resultPoint3, i), resultPoint) < i(g(resultPoint3, resultPoint2, i), resultPoint4)) {
            resultPointArr[0] = resultPoint;
            resultPointArr[1] = resultPoint2;
            resultPointArr[2] = resultPoint3;
            resultPointArr[3] = resultPoint4;
        } else {
            resultPointArr[0] = resultPoint2;
            resultPointArr[1] = resultPoint3;
            resultPointArr[2] = resultPoint4;
            resultPointArr[3] = resultPoint;
        }
        return resultPointArr;
    }

    public final boolean d(ResultPoint resultPoint) {
        return resultPoint.getX() >= 0.0f && resultPoint.getX() < ((float) this.f11807a.getWidth()) && resultPoint.getY() > 0.0f && resultPoint.getY() < ((float) this.f11807a.getHeight());
    }

    public DetectorResult detect() throws NotFoundException {
        int i;
        int i2;
        ResultPoint[] c = c(b(this.b.detect()));
        c[3] = a(c);
        if (c[3] != null) {
            ResultPoint[] h = h(c);
            ResultPoint resultPoint = h[0];
            ResultPoint resultPoint2 = h[1];
            ResultPoint resultPoint3 = h[2];
            ResultPoint resultPoint4 = h[3];
            int i3 = i(resultPoint, resultPoint4) + 1;
            int i4 = i(resultPoint3, resultPoint4) + 1;
            if ((i3 & 1) == 1) {
                i3++;
            }
            if ((i4 & 1) == 1) {
                i4++;
            }
            if (i3 * 4 >= i4 * 7 || i4 * 4 >= i3 * 7) {
                i = i3;
                i2 = i4;
            } else {
                i = Math.max(i3, i4);
                i2 = i;
            }
            return new DetectorResult(f(this.f11807a, resultPoint, resultPoint2, resultPoint3, resultPoint4, i, i2), new ResultPoint[]{resultPoint, resultPoint2, resultPoint3, resultPoint4});
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final ResultPoint[] h(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint = resultPointArr[0];
        ResultPoint resultPoint2 = resultPointArr[1];
        ResultPoint resultPoint3 = resultPointArr[2];
        ResultPoint resultPoint4 = resultPointArr[3];
        ResultPoint g = g(resultPoint, resultPoint2, (i(resultPoint3, resultPoint4) + 1) << 2);
        ResultPoint g2 = g(resultPoint3, resultPoint2, (i(resultPoint, resultPoint4) + 1) << 2);
        int i = i(g, resultPoint4) + 1;
        int i2 = i(g2, resultPoint4) + 1;
        if ((i & 1) == 1) {
            i++;
        }
        if ((i2 & 1) == 1) {
            i2++;
        }
        float x = (((resultPoint.getX() + resultPoint2.getX()) + resultPoint3.getX()) + resultPoint4.getX()) / 4.0f;
        float y = (((resultPoint.getY() + resultPoint2.getY()) + resultPoint3.getY()) + resultPoint4.getY()) / 4.0f;
        ResultPoint e = e(resultPoint, x, y);
        ResultPoint e2 = e(resultPoint2, x, y);
        ResultPoint e3 = e(resultPoint3, x, y);
        ResultPoint e4 = e(resultPoint4, x, y);
        int i3 = i2 << 2;
        int i4 = i << 2;
        return new ResultPoint[]{g(g(e, e2, i3), e4, i4), g(g(e2, e, i3), e3, i4), g(g(e3, e4, i3), e2, i4), g(g(e4, e3, i3), e, i4)};
    }

    public final int i(ResultPoint resultPoint, ResultPoint resultPoint2) {
        int x = (int) resultPoint.getX();
        int y = (int) resultPoint.getY();
        int x2 = (int) resultPoint2.getX();
        int y2 = (int) resultPoint2.getY();
        int i = 0;
        boolean z = Math.abs(y2 - y) > Math.abs(x2 - x);
        if (z) {
            y = x;
            x = y;
            y2 = x2;
            x2 = y2;
        }
        int abs = Math.abs(x2 - x);
        int abs2 = Math.abs(y2 - y);
        int i2 = (-abs) / 2;
        int i3 = y < y2 ? 1 : -1;
        int i4 = x >= x2 ? -1 : 1;
        boolean z2 = this.f11807a.get(z ? y : x, z ? x : y);
        while (x != x2) {
            boolean z3 = this.f11807a.get(z ? y : x, z ? x : y);
            if (z3 != z2) {
                i++;
                z2 = z3;
            }
            i2 += abs2;
            if (i2 > 0) {
                if (y == y2) {
                    break;
                }
                y += i3;
                i2 -= abs;
            }
            x += i4;
        }
        return i;
    }
}
