package com.google.zxing.common.reedsolomon;

import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes11.dex */
public final class GenericGF {
    public static final GenericGF AZTEC_DATA_6;
    public static final GenericGF AZTEC_DATA_8;
    public static final GenericGF AZTEC_PARAM;
    public static final GenericGF DATA_MATRIX_FIELD_256;
    public static final GenericGF MAXICODE_FIELD_64;
    public static final GenericGF QR_CODE_FIELD_256;

    /* renamed from: a  reason: collision with root package name */
    public final int[] f11794a;
    public final int[] b;
    public final a c;
    public final a d;
    public final int e;
    public final int f;
    public final int g;
    public static final GenericGF AZTEC_DATA_12 = new GenericGF(4201, 4096, 1);
    public static final GenericGF AZTEC_DATA_10 = new GenericGF(1033, 1024, 1);

    static {
        GenericGF genericGF = new GenericGF(67, 64, 1);
        AZTEC_DATA_6 = genericGF;
        AZTEC_PARAM = new GenericGF(19, 16, 1);
        QR_CODE_FIELD_256 = new GenericGF(285, 256, 0);
        GenericGF genericGF2 = new GenericGF(301, 256, 1);
        DATA_MATRIX_FIELD_256 = genericGF2;
        AZTEC_DATA_8 = genericGF2;
        MAXICODE_FIELD_64 = genericGF;
    }

    public GenericGF(int i, int i2, int i3) {
        this.f = i;
        this.e = i2;
        this.g = i3;
        this.f11794a = new int[i2];
        this.b = new int[i2];
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            this.f11794a[i5] = i4;
            i4 <<= 1;
            if (i4 >= i2) {
                i4 = (i4 ^ i) & (i2 - 1);
            }
        }
        for (int i6 = 0; i6 < i2 - 1; i6++) {
            this.b[this.f11794a[i6]] = i6;
        }
        this.c = new a(this, new int[]{0});
        this.d = new a(this, new int[]{1});
    }

    public static int a(int i, int i2) {
        return i ^ i2;
    }

    public a b(int i, int i2) {
        if (i >= 0) {
            if (i2 == 0) {
                return this.c;
            }
            int[] iArr = new int[i + 1];
            iArr[0] = i2;
            return new a(this, iArr);
        }
        throw new IllegalArgumentException();
    }

    public int c(int i) {
        return this.f11794a[i];
    }

    public a d() {
        return this.d;
    }

    public a e() {
        return this.c;
    }

    public int f(int i) {
        if (i != 0) {
            return this.f11794a[(this.e - this.b[i]) - 1];
        }
        throw new ArithmeticException();
    }

    public int g(int i) {
        if (i != 0) {
            return this.b[i];
        }
        throw new IllegalArgumentException();
    }

    public int getGeneratorBase() {
        return this.g;
    }

    public int getSize() {
        return this.e;
    }

    public int h(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int[] iArr = this.f11794a;
        int[] iArr2 = this.b;
        return iArr[(iArr2[i] + iArr2[i2]) % (this.e - 1)];
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.f) + ',' + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }
}
