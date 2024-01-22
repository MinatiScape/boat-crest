package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final BitMatrix f11882a;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final float g;
    public final ResultPointCallback i;
    public final List<AlignmentPattern> b = new ArrayList(5);
    public final int[] h = new int[3];

    public a(BitMatrix bitMatrix, int i, int i2, int i3, int i4, float f, ResultPointCallback resultPointCallback) {
        this.f11882a = bitMatrix;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = f;
        this.i = resultPointCallback;
    }

    public static float a(int[] iArr, int i) {
        return (i - iArr[2]) - (iArr[1] / 2.0f);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0062, code lost:
        if (r2[1] <= r12) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0065, code lost:
        if (r10 >= r1) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006b, code lost:
        if (r0.get(r11, r10) != false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006f, code lost:
        if (r2[2] > r12) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0071, code lost:
        r2[2] = r2[2] + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x007b, code lost:
        if (r2[2] <= r12) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x007d, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x008e, code lost:
        if ((java.lang.Math.abs(((r2[0] + r2[1]) + r2[2]) - r13) * 5) < (r13 * 2)) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0090, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0095, code lost:
        if (d(r2) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x009b, code lost:
        return a(r2, r10);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float b(int r10, int r11, int r12, int r13) {
        /*
            r9 = this;
            com.google.zxing.common.BitMatrix r0 = r9.f11882a
            int r1 = r0.getHeight()
            int[] r2 = r9.h
            r3 = 0
            r2[r3] = r3
            r4 = 1
            r2[r4] = r3
            r5 = 2
            r2[r5] = r3
            r6 = r10
        L12:
            if (r6 < 0) goto L26
            boolean r7 = r0.get(r11, r6)
            if (r7 == 0) goto L26
            r7 = r2[r4]
            if (r7 > r12) goto L26
            r7 = r2[r4]
            int r7 = r7 + r4
            r2[r4] = r7
            int r6 = r6 + (-1)
            goto L12
        L26:
            r7 = 2143289344(0x7fc00000, float:NaN)
            if (r6 < 0) goto L9c
            r8 = r2[r4]
            if (r8 <= r12) goto L30
            goto L9c
        L30:
            if (r6 < 0) goto L44
            boolean r8 = r0.get(r11, r6)
            if (r8 != 0) goto L44
            r8 = r2[r3]
            if (r8 > r12) goto L44
            r8 = r2[r3]
            int r8 = r8 + r4
            r2[r3] = r8
            int r6 = r6 + (-1)
            goto L30
        L44:
            r6 = r2[r3]
            if (r6 <= r12) goto L49
            return r7
        L49:
            int r10 = r10 + r4
        L4a:
            if (r10 >= r1) goto L5e
            boolean r6 = r0.get(r11, r10)
            if (r6 == 0) goto L5e
            r6 = r2[r4]
            if (r6 > r12) goto L5e
            r6 = r2[r4]
            int r6 = r6 + r4
            r2[r4] = r6
            int r10 = r10 + 1
            goto L4a
        L5e:
            if (r10 == r1) goto L9c
            r6 = r2[r4]
            if (r6 <= r12) goto L65
            goto L9c
        L65:
            if (r10 >= r1) goto L79
            boolean r6 = r0.get(r11, r10)
            if (r6 != 0) goto L79
            r6 = r2[r5]
            if (r6 > r12) goto L79
            r6 = r2[r5]
            int r6 = r6 + r4
            r2[r5] = r6
            int r10 = r10 + 1
            goto L65
        L79:
            r11 = r2[r5]
            if (r11 <= r12) goto L7e
            return r7
        L7e:
            r11 = r2[r3]
            r12 = r2[r4]
            int r11 = r11 + r12
            r12 = r2[r5]
            int r11 = r11 + r12
            int r11 = r11 - r13
            int r11 = java.lang.Math.abs(r11)
            int r11 = r11 * 5
            int r13 = r13 * r5
            if (r11 < r13) goto L91
            return r7
        L91:
            boolean r11 = r9.d(r2)
            if (r11 == 0) goto L9c
            float r10 = a(r2, r10)
            return r10
        L9c:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.a.b(int, int, int, int):float");
    }

    public AlignmentPattern c() throws NotFoundException {
        AlignmentPattern e;
        AlignmentPattern e2;
        int i = this.c;
        int i2 = this.f;
        int i3 = this.e + i;
        int i4 = this.d + (i2 / 2);
        int[] iArr = new int[3];
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = ((i5 & 1) == 0 ? (i5 + 1) / 2 : -((i5 + 1) / 2)) + i4;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i7 = i;
            while (i7 < i3 && !this.f11882a.get(i7, i6)) {
                i7++;
            }
            int i8 = 0;
            while (i7 < i3) {
                if (!this.f11882a.get(i7, i6)) {
                    if (i8 == 1) {
                        i8++;
                    }
                    iArr[i8] = iArr[i8] + 1;
                } else if (i8 == 1) {
                    iArr[1] = iArr[1] + 1;
                } else if (i8 == 2) {
                    if (d(iArr) && (e2 = e(iArr, i6, i7)) != null) {
                        return e2;
                    }
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i8 = 1;
                } else {
                    i8++;
                    iArr[i8] = iArr[i8] + 1;
                }
                i7++;
            }
            if (d(iArr) && (e = e(iArr, i6, i3)) != null) {
                return e;
            }
        }
        if (!this.b.isEmpty()) {
            return this.b.get(0);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final boolean d(int[] iArr) {
        float f = this.g;
        float f2 = f / 2.0f;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(f - iArr[i]) >= f2) {
                return false;
            }
        }
        return true;
    }

    public final AlignmentPattern e(int[] iArr, int i, int i2) {
        int i3 = iArr[0] + iArr[1] + iArr[2];
        float a2 = a(iArr, i2);
        float b = b(i, (int) a2, iArr[1] * 2, i3);
        if (Float.isNaN(b)) {
            return null;
        }
        float f = ((iArr[0] + iArr[1]) + iArr[2]) / 3.0f;
        for (AlignmentPattern alignmentPattern : this.b) {
            if (alignmentPattern.b(f, b, a2)) {
                return alignmentPattern.c(b, a2, f);
            }
        }
        AlignmentPattern alignmentPattern2 = new AlignmentPattern(a2, b, f);
        this.b.add(alignmentPattern2);
        ResultPointCallback resultPointCallback = this.i;
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(alignmentPattern2);
            return null;
        }
        return null;
    }
}
