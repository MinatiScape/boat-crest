package com.google.zxing.common;

import java.util.Arrays;
/* loaded from: classes11.dex */
public final class BitMatrix implements Cloneable {
    public final int h;
    public final int i;
    public final int j;
    public final int[] k;

    public BitMatrix(int i) {
        this(i, i);
    }

    public static BitMatrix parse(boolean[][] zArr) {
        int length = zArr.length;
        int length2 = zArr[0].length;
        BitMatrix bitMatrix = new BitMatrix(length2, length);
        for (int i = 0; i < length; i++) {
            boolean[] zArr2 = zArr[i];
            for (int i2 = 0; i2 < length2; i2++) {
                if (zArr2[i2]) {
                    bitMatrix.set(i2, i);
                }
            }
        }
        return bitMatrix;
    }

    public final String a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(this.i * (this.h + 1));
        for (int i = 0; i < this.i; i++) {
            for (int i2 = 0; i2 < this.h; i2++) {
                sb.append(get(i2, i) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    public void clear() {
        int length = this.k.length;
        for (int i = 0; i < length; i++) {
            this.k[i] = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof BitMatrix) {
            BitMatrix bitMatrix = (BitMatrix) obj;
            return this.h == bitMatrix.h && this.i == bitMatrix.i && this.j == bitMatrix.j && Arrays.equals(this.k, bitMatrix.k);
        }
        return false;
    }

    public void flip(int i, int i2) {
        int i3 = (i2 * this.j) + (i / 32);
        int[] iArr = this.k;
        iArr[i3] = (1 << (i & 31)) ^ iArr[i3];
    }

    public boolean get(int i, int i2) {
        return ((this.k[(i2 * this.j) + (i / 32)] >>> (i & 31)) & 1) != 0;
    }

    public int[] getBottomRightOnBit() {
        int length = this.k.length - 1;
        while (length >= 0 && this.k[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i = this.j;
        int i2 = length / i;
        int i3 = (length % i) << 5;
        int i4 = 31;
        while ((this.k[length] >>> i4) == 0) {
            i4--;
        }
        return new int[]{i3 + i4, i2};
    }

    public int[] getEnclosingRectangle() {
        int i = this.h;
        int i2 = this.i;
        int i3 = -1;
        int i4 = -1;
        for (int i5 = 0; i5 < this.i; i5++) {
            int i6 = 0;
            while (true) {
                int i7 = this.j;
                if (i6 < i7) {
                    int i8 = this.k[(i7 * i5) + i6];
                    if (i8 != 0) {
                        if (i5 < i2) {
                            i2 = i5;
                        }
                        if (i5 > i4) {
                            i4 = i5;
                        }
                        int i9 = i6 << 5;
                        if (i9 < i) {
                            int i10 = 0;
                            while ((i8 << (31 - i10)) == 0) {
                                i10++;
                            }
                            int i11 = i10 + i9;
                            if (i11 < i) {
                                i = i11;
                            }
                        }
                        if (i9 + 31 > i3) {
                            int i12 = 31;
                            while ((i8 >>> i12) == 0) {
                                i12--;
                            }
                            int i13 = i9 + i12;
                            if (i13 > i3) {
                                i3 = i13;
                            }
                        }
                    }
                    i6++;
                }
            }
        }
        if (i3 < i || i4 < i2) {
            return null;
        }
        return new int[]{i, i2, (i3 - i) + 1, (i4 - i2) + 1};
    }

    public int getHeight() {
        return this.i;
    }

    public BitArray getRow(int i, BitArray bitArray) {
        if (bitArray != null && bitArray.getSize() >= this.h) {
            bitArray.clear();
        } else {
            bitArray = new BitArray(this.h);
        }
        int i2 = i * this.j;
        for (int i3 = 0; i3 < this.j; i3++) {
            bitArray.setBulk(i3 << 5, this.k[i2 + i3]);
        }
        return bitArray;
    }

    public int getRowSize() {
        return this.j;
    }

    public int[] getTopLeftOnBit() {
        int[] iArr;
        int i = 0;
        while (true) {
            iArr = this.k;
            if (i >= iArr.length || iArr[i] != 0) {
                break;
            }
            i++;
        }
        if (i == iArr.length) {
            return null;
        }
        int i2 = this.j;
        int i3 = i / i2;
        int i4 = (i % i2) << 5;
        int i5 = iArr[i];
        int i6 = 0;
        while ((i5 << (31 - i6)) == 0) {
            i6++;
        }
        return new int[]{i4 + i6, i3};
    }

    public int getWidth() {
        return this.h;
    }

    public int hashCode() {
        int i = this.h;
        return (((((((i * 31) + i) * 31) + this.i) * 31) + this.j) * 31) + Arrays.hashCode(this.k);
    }

    public void rotate180() {
        BitArray bitArray = new BitArray(this.h);
        BitArray bitArray2 = new BitArray(this.h);
        int i = (this.i + 1) / 2;
        for (int i2 = 0; i2 < i; i2++) {
            bitArray = getRow(i2, bitArray);
            int i3 = (this.i - 1) - i2;
            bitArray2 = getRow(i3, bitArray2);
            bitArray.reverse();
            bitArray2.reverse();
            setRow(i2, bitArray2);
            setRow(i3, bitArray);
        }
    }

    public void set(int i, int i2) {
        int i3 = (i2 * this.j) + (i / 32);
        int[] iArr = this.k;
        iArr[i3] = (1 << (i & 31)) | iArr[i3];
    }

    public void setRegion(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        }
        if (i4 > 0 && i3 > 0) {
            int i5 = i3 + i;
            int i6 = i4 + i2;
            if (i6 > this.i || i5 > this.h) {
                throw new IllegalArgumentException("The region must fit inside the matrix");
            }
            while (i2 < i6) {
                int i7 = this.j * i2;
                for (int i8 = i; i8 < i5; i8++) {
                    int[] iArr = this.k;
                    int i9 = (i8 / 32) + i7;
                    iArr[i9] = iArr[i9] | (1 << (i8 & 31));
                }
                i2++;
            }
            return;
        }
        throw new IllegalArgumentException("Height and width must be at least 1");
    }

    public void setRow(int i, BitArray bitArray) {
        int[] bitArray2 = bitArray.getBitArray();
        int[] iArr = this.k;
        int i2 = this.j;
        System.arraycopy(bitArray2, 0, iArr, i * i2, i2);
    }

    public String toString() {
        return toString("X ", "  ");
    }

    public void unset(int i, int i2) {
        int i3 = (i2 * this.j) + (i / 32);
        int[] iArr = this.k;
        iArr[i3] = (~(1 << (i & 31))) & iArr[i3];
    }

    public void xor(BitMatrix bitMatrix) {
        int i = this.h;
        if (i == bitMatrix.h && this.i == bitMatrix.i && this.j == bitMatrix.j) {
            BitArray bitArray = new BitArray(i);
            for (int i2 = 0; i2 < this.i; i2++) {
                int i3 = this.j * i2;
                int[] bitArray2 = bitMatrix.getRow(i2, bitArray).getBitArray();
                for (int i4 = 0; i4 < this.j; i4++) {
                    int[] iArr = this.k;
                    int i5 = i3 + i4;
                    iArr[i5] = iArr[i5] ^ bitArray2[i4];
                }
            }
            return;
        }
        throw new IllegalArgumentException("input matrix dimensions do not match");
    }

    public BitMatrix(int i, int i2) {
        if (i > 0 && i2 > 0) {
            this.h = i;
            this.i = i2;
            int i3 = (i + 31) / 32;
            this.j = i3;
            this.k = new int[i3 * i2];
            return;
        }
        throw new IllegalArgumentException("Both dimensions must be greater than 0");
    }

    /* renamed from: clone */
    public BitMatrix m120clone() {
        return new BitMatrix(this.h, this.i, this.j, (int[]) this.k.clone());
    }

    public String toString(String str, String str2) {
        return a(str, str2, "\n");
    }

    @Deprecated
    public String toString(String str, String str2, String str3) {
        return a(str, str2, str3);
    }

    public static BitMatrix parse(String str, String str2, String str3) {
        if (str != null) {
            boolean[] zArr = new boolean[str.length()];
            int i = -1;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (i2 < str.length()) {
                if (str.charAt(i2) == '\n' || str.charAt(i2) == '\r') {
                    if (i3 > i4) {
                        if (i == -1) {
                            i = i3 - i4;
                        } else if (i3 - i4 != i) {
                            throw new IllegalArgumentException("row lengths do not match");
                        }
                        i5++;
                        i4 = i3;
                    }
                    i2++;
                } else {
                    if (str.startsWith(str2, i2)) {
                        i2 += str2.length();
                        zArr[i3] = true;
                    } else if (str.startsWith(str3, i2)) {
                        i2 += str3.length();
                        zArr[i3] = false;
                    } else {
                        throw new IllegalArgumentException("illegal character encountered: " + str.substring(i2));
                    }
                    i3++;
                }
            }
            if (i3 > i4) {
                if (i == -1) {
                    i = i3 - i4;
                } else if (i3 - i4 != i) {
                    throw new IllegalArgumentException("row lengths do not match");
                }
                i5++;
            }
            BitMatrix bitMatrix = new BitMatrix(i, i5);
            for (int i6 = 0; i6 < i3; i6++) {
                if (zArr[i6]) {
                    bitMatrix.set(i6 % i, i6 / i);
                }
            }
            return bitMatrix;
        }
        throw new IllegalArgumentException();
    }

    public BitMatrix(int i, int i2, int i3, int[] iArr) {
        this.h = i;
        this.i = i2;
        this.j = i3;
        this.k = iArr;
    }
}
