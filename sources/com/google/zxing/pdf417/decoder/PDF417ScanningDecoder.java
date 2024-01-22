package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formatter;
/* loaded from: classes11.dex */
public final class PDF417ScanningDecoder {

    /* renamed from: a  reason: collision with root package name */
    public static final ErrorCorrection f11846a = new ErrorCorrection();

    public static c a(h hVar) throws NotFoundException {
        int[] j;
        if (hVar == null || (j = hVar.j()) == null) {
            return null;
        }
        int o = o(j);
        int i = 0;
        int i2 = 0;
        for (int i3 : j) {
            i2 += o - i3;
            if (i3 > 0) {
                break;
            }
        }
        d[] d = hVar.d();
        for (int i4 = 0; i2 > 0 && d[i4] == null; i4++) {
            i2--;
        }
        for (int length = j.length - 1; length >= 0; length--) {
            i += o - j[length];
            if (j[length] > 0) {
                break;
            }
        }
        for (int length2 = d.length - 1; i > 0 && d[length2] == null; length2--) {
            i--;
        }
        return hVar.a().a(i2, i, hVar.k());
    }

    public static void b(f fVar, b[][] bVarArr) throws NotFoundException {
        b bVar = bVarArr[0][1];
        int[] b = bVar.b();
        int j = (fVar.j() * fVar.l()) - q(fVar.k());
        if (b.length == 0) {
            if (j > 0 && j <= 928) {
                bVar.c(j);
                return;
            }
            throw NotFoundException.getNotFoundInstance();
        } else if (b[0] == j || j <= 0 || j > 928) {
        } else {
            bVar.c(j);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0022, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0022, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0022, code lost:
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int c(com.google.zxing.common.BitMatrix r5, int r6, int r7, boolean r8, int r9, int r10) {
        /*
            if (r8 == 0) goto L4
            r0 = -1
            goto L5
        L4:
            r0 = 1
        L5:
            r1 = 0
            r2 = r9
        L7:
            r3 = 2
            if (r1 >= r3) goto L28
        La:
            if (r8 == 0) goto Lf
            if (r2 < r6) goto L22
            goto L11
        Lf:
            if (r2 >= r7) goto L22
        L11:
            boolean r4 = r5.get(r2, r10)
            if (r8 != r4) goto L22
            int r4 = r9 - r2
            int r4 = java.lang.Math.abs(r4)
            if (r4 <= r3) goto L20
            return r9
        L20:
            int r2 = r2 + r0
            goto La
        L22:
            int r0 = -r0
            r8 = r8 ^ 1
            int r1 = r1 + 1
            goto L7
        L28:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.c(com.google.zxing.common.BitMatrix, int, int, boolean, int, int):int");
    }

    public static boolean d(int i, int i2, int i3) {
        return i2 + (-2) <= i && i <= i3 + 2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0068, code lost:
        if (r0 == null) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006a, code lost:
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006c, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006d, code lost:
        r3 = r27;
        r6 = r28;
        r7 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0072, code lost:
        if (r7 > r5) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0074, code lost:
        if (r0 == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0076, code lost:
        r8 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0078, code lost:
        r8 = r5 - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007e, code lost:
        if (r4.n(r8) != null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0080, code lost:
        if (r8 == 0) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0082, code lost:
        if (r8 != r5) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0085, code lost:
        r9 = new com.google.zxing.pdf417.decoder.g(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x008d, code lost:
        if (r8 != 0) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008f, code lost:
        r11 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0091, code lost:
        r11 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0092, code lost:
        r9 = new com.google.zxing.pdf417.decoder.h(r10, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0095, code lost:
        r4.q(r8, r9);
        r15 = -1;
        r14 = r10.g();
        r13 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a3, code lost:
        if (r14 > r10.e()) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a5, code lost:
        r11 = s(r4, r8, r14, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a9, code lost:
        if (r11 < 0) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00af, code lost:
        if (r11 <= r10.d()) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b2, code lost:
        r19 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b5, code lost:
        if (r13 == r15) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b7, code lost:
        r19 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00b9, code lost:
        r20 = r13;
        r23 = r14;
        r21 = r15;
        r11 = j(r22, r10.f(), r10.d(), r0, r19, r23, r3, r6);
        r12 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00da, code lost:
        if (r11 == null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00dc, code lost:
        r9.f(r12, r11);
        r3 = java.lang.Math.min(r3, r11.f());
        r6 = java.lang.Math.max(r6, r11.f());
        r13 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00f2, code lost:
        r20 = r13;
        r12 = r14;
        r21 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f7, code lost:
        r13 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00f9, code lost:
        r14 = r12 + 1;
        r15 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00fe, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0106, code lost:
        return g(r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.zxing.common.DecoderResult decode(com.google.zxing.common.BitMatrix r22, com.google.zxing.ResultPoint r23, com.google.zxing.ResultPoint r24, com.google.zxing.ResultPoint r25, com.google.zxing.ResultPoint r26, int r27, int r28) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
            Method dump skipped, instructions count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.decode(com.google.zxing.common.BitMatrix, com.google.zxing.ResultPoint, com.google.zxing.ResultPoint, com.google.zxing.ResultPoint, com.google.zxing.ResultPoint, int, int):com.google.zxing.common.DecoderResult");
    }

    public static int e(int[] iArr, int[] iArr2, int i) throws ChecksumException {
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            return f11846a.decode(iArr, i, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    public static b[][] f(f fVar) {
        g[] o;
        d[] d;
        int c;
        b[][] bVarArr = (b[][]) Array.newInstance(b.class, fVar.l(), fVar.j() + 2);
        for (int i = 0; i < bVarArr.length; i++) {
            for (int i2 = 0; i2 < bVarArr[i].length; i2++) {
                bVarArr[i][i2] = new b();
            }
        }
        int i3 = 0;
        for (g gVar : fVar.o()) {
            if (gVar != null) {
                for (d dVar : gVar.d()) {
                    if (dVar != null && (c = dVar.c()) >= 0 && c < bVarArr.length) {
                        bVarArr[c][i3].c(dVar.e());
                    }
                }
            }
            i3++;
        }
        return bVarArr;
    }

    public static DecoderResult g(f fVar) throws FormatException, ChecksumException, NotFoundException {
        b[][] f = f(fVar);
        b(fVar, f);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[fVar.l() * fVar.j()];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < fVar.l(); i++) {
            int i2 = 0;
            while (i2 < fVar.j()) {
                int i3 = i2 + 1;
                int[] b = f[i][i3].b();
                int j = (fVar.j() * i) + i2;
                if (b.length == 0) {
                    arrayList.add(Integer.valueOf(j));
                } else if (b.length == 1) {
                    iArr[j] = b[0];
                } else {
                    arrayList3.add(Integer.valueOf(j));
                    arrayList2.add(b);
                }
                i2 = i3;
            }
        }
        int size = arrayList2.size();
        int[][] iArr2 = new int[size];
        for (int i4 = 0; i4 < size; i4++) {
            iArr2[i4] = (int[]) arrayList2.get(i4);
        }
        return h(fVar.k(), iArr, PDF417Common.toIntArray(arrayList), PDF417Common.toIntArray(arrayList3), iArr2);
    }

    public static DecoderResult h(int i, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) throws FormatException, ChecksumException {
        int length = iArr3.length;
        int[] iArr5 = new int[length];
        int i2 = 100;
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                for (int i4 = 0; i4 < length; i4++) {
                    iArr[iArr3[i4]] = iArr4[i4][iArr5[i4]];
                }
                try {
                    return i(iArr, i, iArr2);
                } catch (ChecksumException unused) {
                    if (length == 0) {
                        throw ChecksumException.getChecksumInstance();
                    }
                    int i5 = 0;
                    while (true) {
                        if (i5 >= length) {
                            break;
                        } else if (iArr5[i5] < iArr4[i5].length - 1) {
                            iArr5[i5] = iArr5[i5] + 1;
                            break;
                        } else {
                            iArr5[i5] = 0;
                            if (i5 == length - 1) {
                                throw ChecksumException.getChecksumInstance();
                            }
                            i5++;
                        }
                    }
                    i2 = i3;
                }
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    public static DecoderResult i(int[] iArr, int i, int[] iArr2) throws FormatException, ChecksumException {
        if (iArr.length != 0) {
            int i2 = 1 << (i + 1);
            int e = e(iArr, iArr2, i2);
            v(iArr, i2);
            DecoderResult b = e.b(iArr, String.valueOf(i));
            b.setErrorsCorrected(Integer.valueOf(e));
            b.setErasures(Integer.valueOf(iArr2.length));
            return b;
        }
        throw FormatException.getFormatInstance();
    }

    public static d j(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        int i7;
        int d;
        int codeword;
        int c = c(bitMatrix, i, i2, z, i3, i4);
        int[] p = p(bitMatrix, i, i2, z, c, i4);
        if (p == null) {
            return null;
        }
        int sum = MathUtils.sum(p);
        if (z) {
            i7 = c + sum;
        } else {
            for (int i8 = 0; i8 < p.length / 2; i8++) {
                int i9 = p[i8];
                p[i8] = p[(p.length - 1) - i8];
                p[(p.length - 1) - i8] = i9;
            }
            c -= sum;
            i7 = c;
        }
        if (d(sum, i5, i6) && (codeword = PDF417Common.getCodeword((d = i.d(p)))) != -1) {
            return new d(c, i7, m(d), codeword);
        }
        return null;
    }

    public static a k(h hVar, h hVar2) {
        a i;
        a i2;
        if (hVar == null || (i = hVar.i()) == null) {
            if (hVar2 == null) {
                return null;
            }
            return hVar2.i();
        } else if (hVar2 == null || (i2 = hVar2.i()) == null || i.a() == i2.a() || i.b() == i2.b() || i.c() == i2.c()) {
            return i;
        } else {
            return null;
        }
    }

    public static int[] l(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = 7;
        while (true) {
            int i4 = i & 1;
            if (i4 != i2) {
                i3--;
                if (i3 < 0) {
                    return iArr;
                }
                i2 = i4;
            }
            iArr[i3] = iArr[i3] + 1;
            i >>= 1;
        }
    }

    public static int m(int i) {
        return n(l(i));
    }

    public static int n(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    public static int o(int[] iArr) {
        int i = -1;
        for (int i2 : iArr) {
            i = Math.max(i, i2);
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0015  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0027 A[EDGE_INSN: B:27:0x0027->B:16:0x0027 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int[] p(com.google.zxing.common.BitMatrix r7, int r8, int r9, boolean r10, int r11, int r12) {
        /*
            r0 = 8
            int[] r1 = new int[r0]
            r2 = 1
            if (r10 == 0) goto L9
            r3 = r2
            goto La
        L9:
            r3 = -1
        La:
            r4 = 0
            r5 = r10
        Lc:
            if (r10 == 0) goto L11
            if (r11 >= r9) goto L27
            goto L13
        L11:
            if (r11 < r8) goto L27
        L13:
            if (r4 >= r0) goto L27
            boolean r6 = r7.get(r11, r12)
            if (r6 != r5) goto L22
            r6 = r1[r4]
            int r6 = r6 + r2
            r1[r4] = r6
            int r11 = r11 + r3
            goto Lc
        L22:
            int r4 = r4 + 1
            r5 = r5 ^ 1
            goto Lc
        L27:
            if (r4 == r0) goto L34
            if (r10 == 0) goto L2c
            r8 = r9
        L2c:
            if (r11 != r8) goto L32
            r7 = 7
            if (r4 != r7) goto L32
            goto L34
        L32:
            r7 = 0
            return r7
        L34:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.p(com.google.zxing.common.BitMatrix, int, int, boolean, int, int):int[]");
    }

    public static int q(int i) {
        return 2 << i;
    }

    public static h r(BitMatrix bitMatrix, c cVar, ResultPoint resultPoint, boolean z, int i, int i2) {
        int b;
        h hVar = new h(cVar, z);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = i3 == 0 ? 1 : -1;
            int x = (int) resultPoint.getX();
            for (int y = (int) resultPoint.getY(); y <= cVar.e() && y >= cVar.g(); y += i4) {
                d j = j(bitMatrix, 0, bitMatrix.getWidth(), z, x, y, i, i2);
                if (j != null) {
                    hVar.f(y, j);
                    if (z) {
                        b = j.d();
                    } else {
                        b = j.b();
                    }
                    x = b;
                }
            }
            i3++;
        }
        return hVar;
    }

    public static int s(f fVar, int i, int i2, boolean z) {
        d[] d;
        int i3 = z ? 1 : -1;
        int i4 = i - i3;
        d b = t(fVar, i4) ? fVar.n(i4).b(i2) : null;
        if (b != null) {
            return z ? b.b() : b.d();
        }
        d c = fVar.n(i).c(i2);
        if (c != null) {
            return z ? c.d() : c.b();
        }
        if (t(fVar, i4)) {
            c = fVar.n(i4).c(i2);
        }
        if (c != null) {
            return z ? c.b() : c.d();
        }
        int i5 = 0;
        while (true) {
            i -= i3;
            if (t(fVar, i)) {
                for (d dVar : fVar.n(i).d()) {
                    if (dVar != null) {
                        return (z ? dVar.b() : dVar.d()) + (i3 * i5 * (dVar.b() - dVar.d()));
                    }
                }
                i5++;
            } else {
                c m = fVar.m();
                return z ? m.f() : m.d();
            }
        }
    }

    public static boolean t(f fVar, int i) {
        return i >= 0 && i <= fVar.j() + 1;
    }

    public static String toString(b[][] bVarArr) {
        Formatter formatter = new Formatter();
        for (int i = 0; i < bVarArr.length; i++) {
            try {
                formatter.format("Row %2d: ", Integer.valueOf(i));
                for (int i2 = 0; i2 < bVarArr[i].length; i2++) {
                    b bVar = bVarArr[i][i2];
                    if (bVar.b().length == 0) {
                        formatter.format("        ", null);
                    } else {
                        formatter.format("%4d(%2d)", Integer.valueOf(bVar.b()[0]), bVar.a(bVar.b()[0]));
                    }
                }
                formatter.format("%n", new Object[0]);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        formatter.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }

    public static f u(h hVar, h hVar2) throws NotFoundException {
        a k;
        if ((hVar == null && hVar2 == null) || (k = k(hVar, hVar2)) == null) {
            return null;
        }
        return new f(k, c.j(a(hVar), a(hVar2)));
    }

    public static void v(int[] iArr, int i) throws FormatException {
        if (iArr.length >= 4) {
            int i2 = iArr[0];
            if (i2 > iArr.length) {
                throw FormatException.getFormatInstance();
            }
            if (i2 == 0) {
                if (i < iArr.length) {
                    iArr[0] = iArr.length - i;
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            return;
        }
        throw FormatException.getFormatInstance();
    }
}
