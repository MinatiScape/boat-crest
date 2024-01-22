package com.google.zxing.common;

import java.util.Arrays;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes11.dex */
public final class BitArray implements Cloneable {
    public int[] h;
    public int i;

    public BitArray() {
        this.i = 0;
        this.h = new int[1];
    }

    public static int[] b(int i) {
        return new int[(i + 31) / 32];
    }

    public final void a(int i) {
        if (i > (this.h.length << 5)) {
            int[] b = b(i);
            int[] iArr = this.h;
            System.arraycopy(iArr, 0, b, 0, iArr.length);
            this.h = b;
        }
    }

    public void appendBit(boolean z) {
        a(this.i + 1);
        if (z) {
            int[] iArr = this.h;
            int i = this.i;
            int i2 = i / 32;
            iArr[i2] = (1 << (i & 31)) | iArr[i2];
        }
        this.i++;
    }

    public void appendBitArray(BitArray bitArray) {
        int i = bitArray.i;
        a(this.i + i);
        for (int i2 = 0; i2 < i; i2++) {
            appendBit(bitArray.get(i2));
        }
    }

    public void appendBits(int i, int i2) {
        if (i2 >= 0 && i2 <= 32) {
            a(this.i + i2);
            while (i2 > 0) {
                boolean z = true;
                if (((i >> (i2 - 1)) & 1) != 1) {
                    z = false;
                }
                appendBit(z);
                i2--;
            }
            return;
        }
        throw new IllegalArgumentException("Num bits must be between 0 and 32");
    }

    public void clear() {
        int length = this.h.length;
        for (int i = 0; i < length; i++) {
            this.h[i] = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof BitArray) {
            BitArray bitArray = (BitArray) obj;
            return this.i == bitArray.i && Arrays.equals(this.h, bitArray.h);
        }
        return false;
    }

    public void flip(int i) {
        int[] iArr = this.h;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) ^ iArr[i2];
    }

    public boolean get(int i) {
        return ((1 << (i & 31)) & this.h[i / 32]) != 0;
    }

    public int[] getBitArray() {
        return this.h;
    }

    public int getNextSet(int i) {
        int i2 = this.i;
        if (i >= i2) {
            return i2;
        }
        int i3 = i / 32;
        int i4 = (-(1 << (i & 31))) & this.h[i3];
        while (i4 == 0) {
            i3++;
            int[] iArr = this.h;
            if (i3 == iArr.length) {
                return this.i;
            }
            i4 = iArr[i3];
        }
        return Math.min((i3 << 5) + Integer.numberOfTrailingZeros(i4), this.i);
    }

    public int getNextUnset(int i) {
        int i2 = this.i;
        if (i >= i2) {
            return i2;
        }
        int i3 = i / 32;
        int i4 = (-(1 << (i & 31))) & (~this.h[i3]);
        while (i4 == 0) {
            i3++;
            int[] iArr = this.h;
            if (i3 == iArr.length) {
                return this.i;
            }
            i4 = ~iArr[i3];
        }
        return Math.min((i3 << 5) + Integer.numberOfTrailingZeros(i4), this.i);
    }

    public int getSize() {
        return this.i;
    }

    public int getSizeInBytes() {
        return (this.i + 7) / 8;
    }

    public int hashCode() {
        return (this.i * 31) + Arrays.hashCode(this.h);
    }

    public boolean isRange(int i, int i2, boolean z) {
        if (i2 < i || i < 0 || i2 > this.i) {
            throw new IllegalArgumentException();
        }
        if (i2 == i) {
            return true;
        }
        int i3 = i2 - 1;
        int i4 = i / 32;
        int i5 = i3 / 32;
        int i6 = i4;
        while (i6 <= i5) {
            int i7 = (2 << (i6 >= i5 ? 31 & i3 : 31)) - (1 << (i6 > i4 ? 0 : i & 31));
            int i8 = this.h[i6] & i7;
            if (!z) {
                i7 = 0;
            }
            if (i8 != i7) {
                return false;
            }
            i6++;
        }
        return true;
    }

    public void reverse() {
        int[] iArr = new int[this.h.length];
        int i = (this.i - 1) / 32;
        int i2 = i + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            long j = this.h[i3];
            long j2 = ((j & 1431655765) << 1) | ((j >> 1) & 1431655765);
            long j3 = ((j2 & 858993459) << 2) | ((j2 >> 2) & 858993459);
            long j4 = ((j3 & 252645135) << 4) | ((j3 >> 4) & 252645135);
            long j5 = ((j4 & 16711935) << 8) | ((j4 >> 8) & 16711935);
            iArr[i - i3] = (int) (((j5 & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16) | ((j5 >> 16) & WebSocketProtocol.PAYLOAD_SHORT_MAX));
        }
        int i4 = this.i;
        int i5 = i2 << 5;
        if (i4 != i5) {
            int i6 = i5 - i4;
            int i7 = iArr[0] >>> i6;
            for (int i8 = 1; i8 < i2; i8++) {
                int i9 = iArr[i8];
                iArr[i8 - 1] = i7 | (i9 << (32 - i6));
                i7 = i9 >>> i6;
            }
            iArr[i2 - 1] = i7;
        }
        this.h = iArr;
    }

    public void set(int i) {
        int[] iArr = this.h;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    public void setBulk(int i, int i2) {
        this.h[i / 32] = i2;
    }

    public void setRange(int i, int i2) {
        if (i2 < i || i < 0 || i2 > this.i) {
            throw new IllegalArgumentException();
        }
        if (i2 == i) {
            return;
        }
        int i3 = i2 - 1;
        int i4 = i / 32;
        int i5 = i3 / 32;
        int i6 = i4;
        while (i6 <= i5) {
            int i7 = 31;
            int i8 = i6 > i4 ? 0 : i & 31;
            if (i6 >= i5) {
                i7 = 31 & i3;
            }
            int i9 = (2 << i7) - (1 << i8);
            int[] iArr = this.h;
            iArr[i6] = i9 | iArr[i6];
            i6++;
        }
    }

    public void toBytes(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < 8; i6++) {
                if (get(i)) {
                    i5 |= 1 << (7 - i6);
                }
                i++;
            }
            bArr[i2 + i4] = (byte) i5;
        }
    }

    public String toString() {
        int i = this.i;
        StringBuilder sb = new StringBuilder(i + (i / 8) + 1);
        for (int i2 = 0; i2 < this.i; i2++) {
            if ((i2 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(get(i2) ? 'X' : '.');
        }
        return sb.toString();
    }

    public void xor(BitArray bitArray) {
        if (this.i != bitArray.i) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        int i = 0;
        while (true) {
            int[] iArr = this.h;
            if (i >= iArr.length) {
                return;
            }
            iArr[i] = iArr[i] ^ bitArray.h[i];
            i++;
        }
    }

    /* renamed from: clone */
    public BitArray m119clone() {
        return new BitArray((int[]) this.h.clone(), this.i);
    }

    public BitArray(int i) {
        this.i = i;
        this.h = b(i);
    }

    public BitArray(int[] iArr, int i) {
        this.h = iArr;
        this.i = i;
    }
}
