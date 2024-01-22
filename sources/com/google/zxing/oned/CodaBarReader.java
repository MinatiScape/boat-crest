package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;
/* loaded from: classes11.dex */
public final class CodaBarReader extends OneDReader {
    public static final char[] d = "0123456789-$:/.+ABCD".toCharArray();
    public static final int[] e = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    public static final char[] f = {'A', 'B', 'C', 'D'};

    /* renamed from: a  reason: collision with root package name */
    public final StringBuilder f11818a = new StringBuilder(20);
    public int[] b = new int[80];
    public int c = 0;

    public static boolean b(char[] cArr, char c) {
        if (cArr != null) {
            for (char c2 : cArr) {
                if (c2 == c) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void c(int i) {
        int[] iArr = this.b;
        int i2 = this.c;
        iArr[i2] = i;
        int i3 = i2 + 1;
        this.c = i3;
        if (i3 >= iArr.length) {
            int[] iArr2 = new int[i3 << 1];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.b = iArr2;
        }
    }

    public final int d() throws NotFoundException {
        for (int i = 1; i < this.c; i += 2) {
            int f2 = f(i);
            if (f2 != -1 && b(f, d[f2])) {
                int i2 = 0;
                for (int i3 = i; i3 < i + 7; i3++) {
                    i2 += this.b[i3];
                }
                if (i == 1 || this.b[i - 1] >= i2 / 2) {
                    return i;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException {
        Arrays.fill(this.b, 0);
        e(bitArray);
        int d2 = d();
        this.f11818a.setLength(0);
        int i2 = d2;
        do {
            int f2 = f(i2);
            if (f2 != -1) {
                this.f11818a.append((char) f2);
                i2 += 8;
                if (this.f11818a.length() > 1 && b(f, d[f2])) {
                    break;
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } while (i2 < this.c);
        int i3 = i2 - 1;
        int i4 = this.b[i3];
        int i5 = 0;
        for (int i6 = -8; i6 < -1; i6++) {
            i5 += this.b[i2 + i6];
        }
        if (i2 < this.c && i4 < i5 / 2) {
            throw NotFoundException.getNotFoundInstance();
        }
        g(d2);
        for (int i7 = 0; i7 < this.f11818a.length(); i7++) {
            StringBuilder sb = this.f11818a;
            sb.setCharAt(i7, d[sb.charAt(i7)]);
        }
        char charAt = this.f11818a.charAt(0);
        char[] cArr = f;
        if (b(cArr, charAt)) {
            StringBuilder sb2 = this.f11818a;
            if (b(cArr, sb2.charAt(sb2.length() - 1))) {
                if (this.f11818a.length() > 3) {
                    if (map == null || !map.containsKey(DecodeHintType.RETURN_CODABAR_START_END)) {
                        StringBuilder sb3 = this.f11818a;
                        sb3.deleteCharAt(sb3.length() - 1);
                        this.f11818a.deleteCharAt(0);
                    }
                    int i8 = 0;
                    for (int i9 = 0; i9 < d2; i9++) {
                        i8 += this.b[i9];
                    }
                    float f3 = i8;
                    while (d2 < i3) {
                        i8 += this.b[d2];
                        d2++;
                    }
                    float f4 = i;
                    return new Result(this.f11818a.toString(), null, new ResultPoint[]{new ResultPoint(f3, f4), new ResultPoint(i8, f4)}, BarcodeFormat.CODABAR);
                }
                throw NotFoundException.getNotFoundInstance();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final void e(BitArray bitArray) throws NotFoundException {
        int i = 0;
        this.c = 0;
        int nextUnset = bitArray.getNextUnset(0);
        int size = bitArray.getSize();
        if (nextUnset < size) {
            boolean z = true;
            while (nextUnset < size) {
                if (bitArray.get(nextUnset) != z) {
                    i++;
                } else {
                    c(i);
                    z = !z;
                    i = 1;
                }
                nextUnset++;
            }
            c(i);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final int f(int i) {
        int i2 = i + 7;
        if (i2 >= this.c) {
            return -1;
        }
        int[] iArr = this.b;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        int i5 = Integer.MAX_VALUE;
        int i6 = 0;
        for (int i7 = i; i7 < i2; i7 += 2) {
            int i8 = iArr[i7];
            if (i8 < i5) {
                i5 = i8;
            }
            if (i8 > i6) {
                i6 = i8;
            }
        }
        int i9 = (i5 + i6) / 2;
        int i10 = 0;
        for (int i11 = i + 1; i11 < i2; i11 += 2) {
            int i12 = iArr[i11];
            if (i12 < i3) {
                i3 = i12;
            }
            if (i12 > i10) {
                i10 = i12;
            }
        }
        int i13 = (i3 + i10) / 2;
        int i14 = 128;
        int i15 = 0;
        for (int i16 = 0; i16 < 7; i16++) {
            i14 >>= 1;
            if (iArr[i + i16] > ((i16 & 1) == 0 ? i9 : i13)) {
                i15 |= i14;
            }
        }
        while (true) {
            int[] iArr2 = e;
            if (i4 >= iArr2.length) {
                return -1;
            }
            if (iArr2[i4] == i15) {
                return i4;
            }
            i4++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x00ad, code lost:
        throw com.google.zxing.NotFoundException.getNotFoundInstance();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(int r15) throws com.google.zxing.NotFoundException {
        /*
            Method dump skipped, instructions count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.CodaBarReader.g(int):void");
    }
}
