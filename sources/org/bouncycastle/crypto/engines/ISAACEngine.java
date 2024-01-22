package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class ISAACEngine implements StreamCipher {

    /* renamed from: a  reason: collision with root package name */
    public int[] f14687a = null;
    public int[] b = null;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = 0;
    public byte[] g = new byte[1024];
    public byte[] h = null;
    public boolean i = false;

    public final void a() {
        int i;
        int i2;
        int i3 = this.d;
        int i4 = this.e + 1;
        this.e = i4;
        this.d = i3 + i4;
        for (int i5 = 0; i5 < 256; i5++) {
            int[] iArr = this.f14687a;
            int i6 = iArr[i5];
            int i7 = i5 & 3;
            if (i7 == 0) {
                i = this.c;
                i2 = i << 13;
            } else if (i7 == 1) {
                i = this.c;
                i2 = i >>> 6;
            } else if (i7 == 2) {
                i = this.c;
                i2 = i << 2;
            } else if (i7 != 3) {
                int i8 = this.c + iArr[(i5 + 128) & 255];
                this.c = i8;
                int i9 = iArr[(i6 >>> 2) & 255] + i8 + this.d;
                iArr[i5] = i9;
                int[] iArr2 = this.b;
                int i10 = iArr[(i9 >>> 10) & 255] + i6;
                this.d = i10;
                iArr2[i5] = i10;
            } else {
                i = this.c;
                i2 = i >>> 16;
            }
            this.c = i ^ i2;
            int i82 = this.c + iArr[(i5 + 128) & 255];
            this.c = i82;
            int i92 = iArr[(i6 >>> 2) & 255] + i82 + this.d;
            iArr[i5] = i92;
            int[] iArr22 = this.b;
            int i102 = iArr[(i92 >>> 10) & 255] + i6;
            this.d = i102;
            iArr22[i5] = i102;
        }
    }

    public final void b(int[] iArr) {
        iArr[0] = iArr[0] ^ (iArr[1] << 11);
        iArr[3] = iArr[3] + iArr[0];
        iArr[1] = iArr[1] + iArr[2];
        iArr[1] = iArr[1] ^ (iArr[2] >>> 2);
        iArr[4] = iArr[4] + iArr[1];
        iArr[2] = iArr[2] + iArr[3];
        iArr[2] = iArr[2] ^ (iArr[3] << 8);
        iArr[5] = iArr[5] + iArr[2];
        iArr[3] = iArr[3] + iArr[4];
        iArr[3] = iArr[3] ^ (iArr[4] >>> 16);
        iArr[6] = iArr[6] + iArr[3];
        iArr[4] = iArr[4] + iArr[5];
        iArr[4] = iArr[4] ^ (iArr[5] << 10);
        iArr[7] = iArr[7] + iArr[4];
        iArr[5] = iArr[5] + iArr[6];
        iArr[5] = (iArr[6] >>> 4) ^ iArr[5];
        iArr[0] = iArr[0] + iArr[5];
        iArr[6] = iArr[6] + iArr[7];
        iArr[6] = iArr[6] ^ (iArr[7] << 8);
        iArr[1] = iArr[1] + iArr[6];
        iArr[7] = iArr[7] + iArr[0];
        iArr[7] = iArr[7] ^ (iArr[0] >>> 9);
        iArr[2] = iArr[2] + iArr[7];
        iArr[0] = iArr[0] + iArr[1];
    }

    public final void c(byte[] bArr) {
        this.h = bArr;
        if (this.f14687a == null) {
            this.f14687a = new int[256];
        }
        if (this.b == null) {
            this.b = new int[256];
        }
        for (int i = 0; i < 256; i++) {
            int[] iArr = this.f14687a;
            this.b[i] = 0;
            iArr[i] = 0;
        }
        this.e = 0;
        this.d = 0;
        this.c = 0;
        this.f = 0;
        int length = bArr.length + (bArr.length & 3);
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        for (int i2 = 0; i2 < length; i2 += 4) {
            this.b[i2 >>> 2] = Pack.littleEndianToInt(bArr2, i2);
        }
        int[] iArr2 = new int[8];
        for (int i3 = 0; i3 < 8; i3++) {
            iArr2[i3] = -1640531527;
        }
        for (int i4 = 0; i4 < 4; i4++) {
            b(iArr2);
        }
        int i5 = 0;
        while (i5 < 2) {
            for (int i6 = 0; i6 < 256; i6 += 8) {
                for (int i7 = 0; i7 < 8; i7++) {
                    iArr2[i7] = iArr2[i7] + (i5 < 1 ? this.b[i6 + i7] : this.f14687a[i6 + i7]);
                }
                b(iArr2);
                for (int i8 = 0; i8 < 8; i8++) {
                    this.f14687a[i6 + i8] = iArr2[i8];
                }
            }
            i5++;
        }
        a();
        this.i = true;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "ISAAC";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            c(((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to ISAAC init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (!this.i) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i + i2 <= bArr.length) {
            if (i3 + i2 <= bArr2.length) {
                for (int i4 = 0; i4 < i2; i4++) {
                    if (this.f == 0) {
                        a();
                        this.g = Pack.intToBigEndian(this.b);
                    }
                    byte[] bArr3 = this.g;
                    int i5 = this.f;
                    bArr2[i4 + i3] = (byte) (bArr3[i5] ^ bArr[i4 + i]);
                    this.f = (i5 + 1) & 1023;
                }
                return i2;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        c(this.h);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (this.f == 0) {
            a();
            this.g = Pack.intToBigEndian(this.b);
        }
        byte[] bArr = this.g;
        int i = this.f;
        byte b2 = (byte) (b ^ bArr[i]);
        this.f = (i + 1) & 1023;
        return b2;
    }
}
