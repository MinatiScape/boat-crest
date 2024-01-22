package com.google.zxing.aztec.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import kotlin.text.Typography;
/* loaded from: classes11.dex */
public final class Detector {
    public static final int[] g = {3808, 476, 2107, 1799};

    /* renamed from: a  reason: collision with root package name */
    public final BitMatrix f11771a;
    public boolean b;
    public int c;
    public int d;
    public int e;
    public int f;

    /* loaded from: classes11.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final int f11772a;
        public final int b;

        public a(int i, int i2) {
            this.f11772a = i;
            this.b = i2;
        }

        public int a() {
            return this.f11772a;
        }

        public int b() {
            return this.b;
        }

        public ResultPoint c() {
            return new ResultPoint(this.f11772a, this.b);
        }

        public String toString() {
            return "<" + this.f11772a + ' ' + this.b + Typography.greater;
        }
    }

    public Detector(BitMatrix bitMatrix) {
        this.f11771a = bitMatrix;
    }

    public static float a(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    public static float b(a aVar, a aVar2) {
        return MathUtils.distance(aVar.a(), aVar.b(), aVar2.a(), aVar2.b());
    }

    public static ResultPoint[] c(ResultPoint[] resultPointArr, int i, int i2) {
        float f = i2 / (i * 2.0f);
        float x = resultPointArr[0].getX() - resultPointArr[2].getX();
        float y = resultPointArr[0].getY() - resultPointArr[2].getY();
        float x2 = (resultPointArr[0].getX() + resultPointArr[2].getX()) / 2.0f;
        float y2 = (resultPointArr[0].getY() + resultPointArr[2].getY()) / 2.0f;
        float f2 = x * f;
        float f3 = y * f;
        ResultPoint resultPoint = new ResultPoint(x2 + f2, y2 + f3);
        ResultPoint resultPoint2 = new ResultPoint(x2 - f2, y2 - f3);
        float x3 = resultPointArr[1].getX() - resultPointArr[3].getX();
        float y3 = resultPointArr[1].getY() - resultPointArr[3].getY();
        float x4 = (resultPointArr[1].getX() + resultPointArr[3].getX()) / 2.0f;
        float y4 = (resultPointArr[1].getY() + resultPointArr[3].getY()) / 2.0f;
        float f4 = x3 * f;
        float f5 = f * y3;
        return new ResultPoint[]{resultPoint, new ResultPoint(x4 + f4, y4 + f5), resultPoint2, new ResultPoint(x4 - f4, y4 - f5)};
    }

    public static int g(long j, boolean z) throws NotFoundException {
        int i;
        int i2;
        if (z) {
            i = 7;
            i2 = 2;
        } else {
            i = 10;
            i2 = 4;
        }
        int i3 = i - i2;
        int[] iArr = new int[i];
        for (int i4 = i - 1; i4 >= 0; i4--) {
            iArr[i4] = ((int) j) & 15;
            j >>= 4;
        }
        try {
            new ReedSolomonDecoder(GenericGF.AZTEC_PARAM).decode(iArr, i3);
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                i5 = (i5 << 4) + iArr[i6];
            }
            return i5;
        } catch (ReedSolomonException unused) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public static int l(int[] iArr, int i) throws NotFoundException {
        int i2 = 0;
        for (int i3 : iArr) {
            i2 = (i2 << 3) + ((i3 >> (i - 2)) << 1) + (i3 & 1);
        }
        int i4 = ((i2 & 1) << 11) + (i2 >> 1);
        for (int i5 = 0; i5 < 4; i5++) {
            if (Integer.bitCount(g[i5] ^ i4) <= 2) {
                return i5;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final void d(ResultPoint[] resultPointArr) throws NotFoundException {
        long j;
        long j2;
        if (n(resultPointArr[0]) && n(resultPointArr[1]) && n(resultPointArr[2]) && n(resultPointArr[3])) {
            int i = this.e * 2;
            int[] iArr = {q(resultPointArr[0], resultPointArr[1], i), q(resultPointArr[1], resultPointArr[2], i), q(resultPointArr[2], resultPointArr[3], i), q(resultPointArr[3], resultPointArr[0], i)};
            this.f = l(iArr, i);
            long j3 = 0;
            for (int i2 = 0; i2 < 4; i2++) {
                int i3 = iArr[(this.f + i2) % 4];
                if (this.b) {
                    j = j3 << 7;
                    j2 = (i3 >> 1) & 127;
                } else {
                    j = j3 << 10;
                    j2 = ((i3 >> 2) & 992) + ((i3 >> 1) & 31);
                }
                j3 = j + j2;
            }
            int g2 = g(j3, this.b);
            if (this.b) {
                this.c = (g2 >> 6) + 1;
                this.d = (g2 & 63) + 1;
                return;
            }
            this.c = (g2 >> 11) + 1;
            this.d = (g2 & 2047) + 1;
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public AztecDetectorResult detect() throws NotFoundException {
        return detect(false);
    }

    public final ResultPoint[] e(a aVar) throws NotFoundException {
        this.e = 1;
        a aVar2 = aVar;
        a aVar3 = aVar2;
        a aVar4 = aVar3;
        boolean z = true;
        while (this.e < 9) {
            a i = i(aVar, z, 1, -1);
            a i2 = i(aVar2, z, 1, 1);
            a i3 = i(aVar3, z, -1, 1);
            a i4 = i(aVar4, z, -1, -1);
            if (this.e > 2) {
                double b = (b(i4, i) * this.e) / (b(aVar4, aVar) * (this.e + 2));
                if (b < 0.75d || b > 1.25d || !o(i, i2, i3, i4)) {
                    break;
                }
            }
            z = !z;
            this.e++;
            aVar4 = i4;
            aVar = i;
            aVar2 = i2;
            aVar3 = i3;
        }
        int i5 = this.e;
        if (i5 != 5 && i5 != 7) {
            throw NotFoundException.getNotFoundInstance();
        }
        this.b = i5 == 5;
        ResultPoint[] resultPointArr = {new ResultPoint(aVar.a() + 0.5f, aVar.b() - 0.5f), new ResultPoint(aVar2.a() + 0.5f, aVar2.b() + 0.5f), new ResultPoint(aVar3.a() - 0.5f, aVar3.b() + 0.5f), new ResultPoint(aVar4.a() - 0.5f, aVar4.b() - 0.5f)};
        int i6 = this.e;
        return c(resultPointArr, (i6 * 2) - 3, i6 * 2);
    }

    public final int f(a aVar, a aVar2) {
        float b = b(aVar, aVar2);
        float a2 = (aVar2.a() - aVar.a()) / b;
        float b2 = (aVar2.b() - aVar.b()) / b;
        float a3 = aVar.a();
        float b3 = aVar.b();
        boolean z = this.f11771a.get(aVar.a(), aVar.b());
        int ceil = (int) Math.ceil(b);
        int i = 0;
        for (int i2 = 0; i2 < ceil; i2++) {
            a3 += a2;
            b3 += b2;
            if (this.f11771a.get(MathUtils.round(a3), MathUtils.round(b3)) != z) {
                i++;
            }
        }
        float f = i / b;
        if (f <= 0.1f || f >= 0.9f) {
            return (f <= 0.1f) == z ? 1 : -1;
        }
        return 0;
    }

    public final int h() {
        if (this.b) {
            return (this.c * 4) + 11;
        }
        int i = this.c;
        return i <= 4 ? (i * 4) + 15 : (i * 4) + ((((i - 4) / 8) + 1) * 2) + 15;
    }

    public final a i(a aVar, boolean z, int i, int i2) {
        int a2 = aVar.a() + i;
        int b = aVar.b();
        while (true) {
            b += i2;
            if (!m(a2, b) || this.f11771a.get(a2, b) != z) {
                break;
            }
            a2 += i;
        }
        int i3 = a2 - i;
        int i4 = b - i2;
        while (m(i3, i4) && this.f11771a.get(i3, i4) == z) {
            i3 += i;
        }
        int i5 = i3 - i;
        while (m(i5, i4) && this.f11771a.get(i5, i4) == z) {
            i4 += i2;
        }
        return new a(i5, i4 - i2);
    }

    public final a j() {
        ResultPoint c;
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        ResultPoint c2;
        ResultPoint c3;
        ResultPoint c4;
        ResultPoint c5;
        try {
            ResultPoint[] detect = new WhiteRectangleDetector(this.f11771a).detect();
            resultPoint2 = detect[0];
            resultPoint3 = detect[1];
            resultPoint = detect[2];
            c = detect[3];
        } catch (NotFoundException unused) {
            int width = this.f11771a.getWidth() / 2;
            int height = this.f11771a.getHeight() / 2;
            int i = width + 7;
            int i2 = height - 7;
            ResultPoint c6 = i(new a(i, i2), false, 1, -1).c();
            int i3 = height + 7;
            ResultPoint c7 = i(new a(i, i3), false, 1, 1).c();
            int i4 = width - 7;
            ResultPoint c8 = i(new a(i4, i3), false, -1, 1).c();
            c = i(new a(i4, i2), false, -1, -1).c();
            resultPoint = c8;
            resultPoint2 = c6;
            resultPoint3 = c7;
        }
        int round = MathUtils.round((((resultPoint2.getX() + c.getX()) + resultPoint3.getX()) + resultPoint.getX()) / 4.0f);
        int round2 = MathUtils.round((((resultPoint2.getY() + c.getY()) + resultPoint3.getY()) + resultPoint.getY()) / 4.0f);
        try {
            ResultPoint[] detect2 = new WhiteRectangleDetector(this.f11771a, 15, round, round2).detect();
            c2 = detect2[0];
            c3 = detect2[1];
            c4 = detect2[2];
            c5 = detect2[3];
        } catch (NotFoundException unused2) {
            int i5 = round + 7;
            int i6 = round2 - 7;
            c2 = i(new a(i5, i6), false, 1, -1).c();
            int i7 = round2 + 7;
            c3 = i(new a(i5, i7), false, 1, 1).c();
            int i8 = round - 7;
            c4 = i(new a(i8, i7), false, -1, 1).c();
            c5 = i(new a(i8, i6), false, -1, -1).c();
        }
        return new a(MathUtils.round((((c2.getX() + c5.getX()) + c3.getX()) + c4.getX()) / 4.0f), MathUtils.round((((c2.getY() + c5.getY()) + c3.getY()) + c4.getY()) / 4.0f));
    }

    public final ResultPoint[] k(ResultPoint[] resultPointArr) {
        return c(resultPointArr, this.e * 2, h());
    }

    public final boolean m(int i, int i2) {
        return i >= 0 && i < this.f11771a.getWidth() && i2 > 0 && i2 < this.f11771a.getHeight();
    }

    public final boolean n(ResultPoint resultPoint) {
        return m(MathUtils.round(resultPoint.getX()), MathUtils.round(resultPoint.getY()));
    }

    public final boolean o(a aVar, a aVar2, a aVar3, a aVar4) {
        a aVar5 = new a(aVar.a() - 3, aVar.b() + 3);
        a aVar6 = new a(aVar2.a() - 3, aVar2.b() - 3);
        a aVar7 = new a(aVar3.a() + 3, aVar3.b() - 3);
        a aVar8 = new a(aVar4.a() + 3, aVar4.b() + 3);
        int f = f(aVar8, aVar5);
        return f != 0 && f(aVar5, aVar6) == f && f(aVar6, aVar7) == f && f(aVar7, aVar8) == f;
    }

    public final BitMatrix p(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        GridSampler gridSampler = GridSampler.getInstance();
        int h = h();
        float f = h / 2.0f;
        int i = this.e;
        float f2 = f - i;
        float f3 = f + i;
        return gridSampler.sampleGrid(bitMatrix, h, h, f2, f2, f3, f2, f3, f3, f2, f3, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint4.getX(), resultPoint4.getY());
    }

    public final int q(ResultPoint resultPoint, ResultPoint resultPoint2, int i) {
        float a2 = a(resultPoint, resultPoint2);
        float f = a2 / i;
        float x = resultPoint.getX();
        float y = resultPoint.getY();
        float x2 = ((resultPoint2.getX() - resultPoint.getX()) * f) / a2;
        float y2 = (f * (resultPoint2.getY() - resultPoint.getY())) / a2;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            float f2 = i3;
            if (this.f11771a.get(MathUtils.round((f2 * x2) + x), MathUtils.round((f2 * y2) + y))) {
                i2 |= 1 << ((i - i3) - 1);
            }
        }
        return i2;
    }

    public AztecDetectorResult detect(boolean z) throws NotFoundException {
        ResultPoint[] e = e(j());
        if (z) {
            ResultPoint resultPoint = e[0];
            e[0] = e[2];
            e[2] = resultPoint;
        }
        d(e);
        BitMatrix bitMatrix = this.f11771a;
        int i = this.f;
        return new AztecDetectorResult(p(bitMatrix, e[i % 4], e[(i + 1) % 4], e[(i + 2) % 4], e[(i + 3) % 4]), k(e), this.b, this.d, this.c);
    }
}
