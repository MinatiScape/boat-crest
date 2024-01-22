package com.google.zxing.pdf417.decoder;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
/* loaded from: classes11.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final BitMatrix f11849a;
    public final ResultPoint b;
    public final ResultPoint c;
    public final ResultPoint d;
    public final ResultPoint e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;

    public c(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        boolean z = false;
        boolean z2 = resultPoint == null || resultPoint2 == null;
        z = (resultPoint3 == null || resultPoint4 == null) ? true : z;
        if (z2 && z) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (z2) {
            resultPoint = new ResultPoint(0.0f, resultPoint3.getY());
            resultPoint2 = new ResultPoint(0.0f, resultPoint4.getY());
        } else if (z) {
            resultPoint3 = new ResultPoint(bitMatrix.getWidth() - 1, resultPoint.getY());
            resultPoint4 = new ResultPoint(bitMatrix.getWidth() - 1, resultPoint2.getY());
        }
        this.f11849a = bitMatrix;
        this.b = resultPoint;
        this.c = resultPoint2;
        this.d = resultPoint3;
        this.e = resultPoint4;
        this.f = (int) Math.min(resultPoint.getX(), resultPoint2.getX());
        this.g = (int) Math.max(resultPoint3.getX(), resultPoint4.getX());
        this.h = (int) Math.min(resultPoint.getY(), resultPoint3.getY());
        this.i = (int) Math.max(resultPoint2.getY(), resultPoint4.getY());
    }

    public static c j(c cVar, c cVar2) throws NotFoundException {
        return cVar == null ? cVar2 : cVar2 == null ? cVar : new c(cVar.f11849a, cVar.b, cVar.c, cVar2.d, cVar2.e);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.zxing.pdf417.decoder.c a(int r13, int r14, boolean r15) throws com.google.zxing.NotFoundException {
        /*
            r12 = this;
            com.google.zxing.ResultPoint r0 = r12.b
            com.google.zxing.ResultPoint r1 = r12.c
            com.google.zxing.ResultPoint r2 = r12.d
            com.google.zxing.ResultPoint r3 = r12.e
            if (r13 <= 0) goto L29
            if (r15 == 0) goto Le
            r4 = r0
            goto Lf
        Le:
            r4 = r2
        Lf:
            float r5 = r4.getY()
            int r5 = (int) r5
            int r5 = r5 - r13
            if (r5 >= 0) goto L18
            r5 = 0
        L18:
            com.google.zxing.ResultPoint r13 = new com.google.zxing.ResultPoint
            float r4 = r4.getX()
            float r5 = (float) r5
            r13.<init>(r4, r5)
            if (r15 == 0) goto L26
            r8 = r13
            goto L2a
        L26:
            r10 = r13
            r8 = r0
            goto L2b
        L29:
            r8 = r0
        L2a:
            r10 = r2
        L2b:
            if (r14 <= 0) goto L5b
            if (r15 == 0) goto L32
            com.google.zxing.ResultPoint r13 = r12.c
            goto L34
        L32:
            com.google.zxing.ResultPoint r13 = r12.e
        L34:
            float r0 = r13.getY()
            int r0 = (int) r0
            int r0 = r0 + r14
            com.google.zxing.common.BitMatrix r14 = r12.f11849a
            int r14 = r14.getHeight()
            if (r0 < r14) goto L4a
            com.google.zxing.common.BitMatrix r14 = r12.f11849a
            int r14 = r14.getHeight()
            int r0 = r14 + (-1)
        L4a:
            com.google.zxing.ResultPoint r14 = new com.google.zxing.ResultPoint
            float r13 = r13.getX()
            float r0 = (float) r0
            r14.<init>(r13, r0)
            if (r15 == 0) goto L58
            r9 = r14
            goto L5c
        L58:
            r11 = r14
            r9 = r1
            goto L5d
        L5b:
            r9 = r1
        L5c:
            r11 = r3
        L5d:
            com.google.zxing.pdf417.decoder.c r13 = new com.google.zxing.pdf417.decoder.c
            com.google.zxing.common.BitMatrix r7 = r12.f11849a
            r6 = r13
            r6.<init>(r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.c.a(int, int, boolean):com.google.zxing.pdf417.decoder.c");
    }

    public ResultPoint b() {
        return this.c;
    }

    public ResultPoint c() {
        return this.e;
    }

    public int d() {
        return this.g;
    }

    public int e() {
        return this.i;
    }

    public int f() {
        return this.f;
    }

    public int g() {
        return this.h;
    }

    public ResultPoint h() {
        return this.b;
    }

    public ResultPoint i() {
        return this.d;
    }

    public c(c cVar) {
        this.f11849a = cVar.f11849a;
        this.b = cVar.b;
        this.c = cVar.c;
        this.d = cVar.d;
        this.e = cVar.e;
        this.f = cVar.f;
        this.g = cVar.g;
        this.h = cVar.h;
        this.i = cVar.i;
    }
}
