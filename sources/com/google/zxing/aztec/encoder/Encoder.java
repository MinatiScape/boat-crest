package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
/* loaded from: classes11.dex */
public final class Encoder {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f11774a = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    public static int[] a(BitArray bitArray, int i, int i2) {
        int[] iArr = new int[i2];
        int size = bitArray.getSize() / i;
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                i4 |= bitArray.get((i3 * i) + i5) ? 1 << ((i - i5) - 1) : 0;
            }
            iArr[i3] = i4;
        }
        return iArr;
    }

    public static void b(BitMatrix bitMatrix, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 <= i6) {
                    bitMatrix.set(i5, i4);
                    bitMatrix.set(i5, i6);
                    bitMatrix.set(i4, i5);
                    bitMatrix.set(i6, i5);
                    i5++;
                }
            }
        }
        int i7 = i - i2;
        bitMatrix.set(i7, i7);
        int i8 = i7 + 1;
        bitMatrix.set(i8, i7);
        bitMatrix.set(i7, i8);
        int i9 = i + i2;
        bitMatrix.set(i9, i7);
        bitMatrix.set(i9, i8);
        bitMatrix.set(i9, i9 - 1);
    }

    public static void c(BitMatrix bitMatrix, boolean z, int i, BitArray bitArray) {
        int i2 = i / 2;
        int i3 = 0;
        if (z) {
            while (i3 < 7) {
                int i4 = (i2 - 3) + i3;
                if (bitArray.get(i3)) {
                    bitMatrix.set(i4, i2 - 5);
                }
                if (bitArray.get(i3 + 7)) {
                    bitMatrix.set(i2 + 5, i4);
                }
                if (bitArray.get(20 - i3)) {
                    bitMatrix.set(i4, i2 + 5);
                }
                if (bitArray.get(27 - i3)) {
                    bitMatrix.set(i2 - 5, i4);
                }
                i3++;
            }
            return;
        }
        while (i3 < 10) {
            int i5 = (i2 - 5) + i3 + (i3 / 5);
            if (bitArray.get(i3)) {
                bitMatrix.set(i5, i2 - 7);
            }
            if (bitArray.get(i3 + 10)) {
                bitMatrix.set(i2 + 7, i5);
            }
            if (bitArray.get(29 - i3)) {
                bitMatrix.set(i5, i2 + 7);
            }
            if (bitArray.get(39 - i3)) {
                bitMatrix.set(i2 - 7, i5);
            }
            i3++;
        }
    }

    public static BitArray d(BitArray bitArray, int i, int i2) {
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(f(i2));
        int i3 = i / i2;
        int[] a2 = a(bitArray, i2, i3);
        reedSolomonEncoder.encode(a2, i3 - (bitArray.getSize() / i2));
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(0, i % i2);
        for (int i4 : a2) {
            bitArray2.appendBits(i4, i2);
        }
        return bitArray2;
    }

    public static BitArray e(boolean z, int i, int i2) {
        BitArray bitArray = new BitArray();
        if (z) {
            bitArray.appendBits(i - 1, 2);
            bitArray.appendBits(i2 - 1, 6);
            return d(bitArray, 28, 4);
        }
        bitArray.appendBits(i - 1, 5);
        bitArray.appendBits(i2 - 1, 11);
        return d(bitArray, 40, 4);
    }

    public static AztecCode encode(byte[] bArr) {
        return encode(bArr, 33, 0);
    }

    public static GenericGF f(int i) {
        if (i != 4) {
            if (i != 6) {
                if (i != 8) {
                    if (i != 10) {
                        if (i == 12) {
                            return GenericGF.AZTEC_DATA_12;
                        }
                        throw new IllegalArgumentException("Unsupported word size ".concat(String.valueOf(i)));
                    }
                    return GenericGF.AZTEC_DATA_10;
                }
                return GenericGF.AZTEC_DATA_8;
            }
            return GenericGF.AZTEC_DATA_6;
        }
        return GenericGF.AZTEC_PARAM;
    }

    public static BitArray g(BitArray bitArray, int i) {
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int i2 = (1 << i) - 2;
        int i3 = 0;
        while (i3 < size) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = i3 + i5;
                if (i6 >= size || bitArray.get(i6)) {
                    i4 |= 1 << ((i - 1) - i5);
                }
            }
            int i7 = i4 & i2;
            if (i7 == i2) {
                bitArray2.appendBits(i7, i);
            } else if (i7 == 0) {
                bitArray2.appendBits(i4 | 1, i);
            } else {
                bitArray2.appendBits(i4, i);
                i3 += i;
            }
            i3--;
            i3 += i;
        }
        return bitArray2;
    }

    public static int h(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AztecCode encode(byte[] bArr, int i, int i2) {
        BitArray bitArray;
        int i3;
        boolean z;
        int i4;
        int i5;
        int i6;
        int i7;
        BitArray encode = new HighLevelEncoder(bArr).encode();
        int size = ((encode.getSize() * i) / 100) + 11;
        int size2 = encode.getSize() + size;
        int i8 = 0;
        int i9 = 1;
        if (i2 != 0) {
            z = i2 < 0;
            i4 = Math.abs(i2);
            if (i4 <= (z ? 4 : 32)) {
                i5 = h(i4, z);
                i3 = f11774a[i4];
                int i10 = i5 - (i5 % i3);
                bitArray = g(encode, i3);
                if (bitArray.getSize() + size <= i10) {
                    if (z && bitArray.getSize() > (i3 << 6)) {
                        throw new IllegalArgumentException("Data to large for user specified layer");
                    }
                } else {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                }
            } else {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i2)));
            }
        } else {
            BitArray bitArray2 = null;
            int i11 = 0;
            int i12 = 0;
            while (i11 <= 32) {
                boolean z2 = i11 <= 3 ? i9 : i8;
                int i13 = z2 ? i11 + 1 : i11;
                int h = h(i13, z2);
                if (size2 <= h) {
                    if (bitArray2 == null || i12 != f11774a[i13]) {
                        int i14 = f11774a[i13];
                        i12 = i14;
                        bitArray2 = g(encode, i14);
                    }
                    int i15 = h - (h % i12);
                    if ((!z2 || bitArray2.getSize() <= (i12 << 6)) && bitArray2.getSize() + size <= i15) {
                        bitArray = bitArray2;
                        i3 = i12;
                        z = z2;
                        i4 = i13;
                        i5 = h;
                        i8 = i8;
                    }
                }
                i11++;
                i9 = i9;
                i8 = 0;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray d = d(bitArray, i5, i3);
        int size3 = bitArray.getSize() / i3;
        BitArray e = e(z, i4, size3);
        int i16 = (z ? 11 : 14) + (i4 << 2);
        int[] iArr = new int[i16];
        int i17 = 2;
        if (z) {
            for (int i18 = i8; i18 < i16; i18++) {
                iArr[i18] = i18;
            }
            i6 = i16;
        } else {
            int i19 = i16 / 2;
            i6 = i16 + 1 + (((i19 - 1) / 15) * 2);
            int i20 = i6 / 2;
            for (int i21 = i8; i21 < i19; i21++) {
                iArr[(i19 - i21) - i9] = (i20 - i7) - 1;
                iArr[i19 + i21] = (i21 / 15) + i21 + i20 + i9;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(i6);
        int i22 = i8;
        int i23 = i22;
        while (i22 < i4) {
            int i24 = ((i4 - i22) << i17) + (z ? 9 : 12);
            int i25 = i8;
            while (i25 < i24) {
                int i26 = i25 << 1;
                while (i8 < i17) {
                    if (d.get(i23 + i26 + i8)) {
                        int i27 = i22 << 1;
                        bitMatrix.set(iArr[i27 + i8], iArr[i27 + i25]);
                    }
                    if (d.get((i24 << 1) + i23 + i26 + i8)) {
                        int i28 = i22 << 1;
                        bitMatrix.set(iArr[i28 + i25], iArr[((i16 - 1) - i28) - i8]);
                    }
                    if (d.get((i24 << 2) + i23 + i26 + i8)) {
                        int i29 = (i16 - 1) - (i22 << 1);
                        bitMatrix.set(iArr[i29 - i8], iArr[i29 - i25]);
                    }
                    if (d.get((i24 * 6) + i23 + i26 + i8)) {
                        int i30 = i22 << 1;
                        bitMatrix.set(iArr[((i16 - 1) - i30) - i25], iArr[i30 + i8]);
                    }
                    i8++;
                    i17 = 2;
                }
                i25++;
                i8 = 0;
                i17 = 2;
            }
            i23 += i24 << 3;
            i22++;
            i8 = 0;
            i17 = 2;
        }
        c(bitMatrix, z, i6, e);
        if (z) {
            b(bitMatrix, i6 / 2, 5);
        } else {
            int i31 = i6 / 2;
            b(bitMatrix, i31, 7);
            int i32 = 0;
            int i33 = 0;
            while (i33 < (i16 / 2) - 1) {
                for (int i34 = i31 & 1; i34 < i6; i34 += 2) {
                    int i35 = i31 - i32;
                    bitMatrix.set(i35, i34);
                    int i36 = i31 + i32;
                    bitMatrix.set(i36, i34);
                    bitMatrix.set(i34, i35);
                    bitMatrix.set(i34, i36);
                }
                i33 += 15;
                i32 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setCompact(z);
        aztecCode.setSize(i6);
        aztecCode.setLayers(i4);
        aztecCode.setCodeWords(size3);
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }
}
