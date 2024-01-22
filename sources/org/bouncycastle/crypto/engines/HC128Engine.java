package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
/* loaded from: classes12.dex */
public class HC128Engine implements StreamCipher {
    public byte[] d;
    public byte[] e;
    public boolean f;

    /* renamed from: a  reason: collision with root package name */
    public int[] f14683a = new int[512];
    public int[] b = new int[512];
    public int c = 0;
    public byte[] g = new byte[4];
    public int h = 0;

    public static int a(int i, int i2) {
        return k(i - i2);
    }

    public static int b(int i) {
        return (i >>> 3) ^ (m(i, 7) ^ m(i, 18));
    }

    public static int c(int i) {
        return (i >>> 10) ^ (m(i, 17) ^ m(i, 19));
    }

    public static int j(int i) {
        return i & 1023;
    }

    public static int k(int i) {
        return i & 511;
    }

    public static int l(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    public static int m(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    public final int d(int i, int i2, int i3) {
        return (m(i, 10) ^ m(i3, 23)) + m(i2, 8);
    }

    public final int e(int i, int i2, int i3) {
        return (l(i, 10) ^ l(i3, 23)) + l(i2, 8);
    }

    public final byte f() {
        if (this.h == 0) {
            int n = n();
            byte[] bArr = this.g;
            bArr[0] = (byte) (n & 255);
            int i = n >> 8;
            bArr[1] = (byte) (i & 255);
            int i2 = i >> 8;
            bArr[2] = (byte) (i2 & 255);
            bArr[3] = (byte) ((i2 >> 8) & 255);
        }
        byte[] bArr2 = this.g;
        int i3 = this.h;
        byte b = bArr2[i3];
        this.h = 3 & (i3 + 1);
        return b;
    }

    public final int g(int i) {
        int[] iArr = this.b;
        return iArr[i & 255] + iArr[((i >> 16) & 255) + 256];
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "HC-128";
    }

    public final int h(int i) {
        int[] iArr = this.f14683a;
        return iArr[i & 255] + iArr[((i >> 16) & 255) + 256];
    }

    public final void i() {
        if (this.d.length != 16) {
            throw new IllegalArgumentException("The key must be 128 bits long");
        }
        this.h = 0;
        this.c = 0;
        int[] iArr = new int[1280];
        for (int i = 0; i < 16; i++) {
            int i2 = i >> 2;
            iArr[i2] = ((this.d[i] & 255) << ((i & 3) * 8)) | iArr[i2];
        }
        System.arraycopy(iArr, 0, iArr, 4, 4);
        int i3 = 0;
        while (true) {
            byte[] bArr = this.e;
            if (i3 >= bArr.length || i3 >= 16) {
                break;
            }
            int i4 = (i3 >> 2) + 8;
            iArr[i4] = ((bArr[i3] & 255) << ((i3 & 3) * 8)) | iArr[i4];
            i3++;
        }
        System.arraycopy(iArr, 8, iArr, 12, 4);
        for (int i5 = 16; i5 < 1280; i5++) {
            iArr[i5] = c(iArr[i5 - 2]) + iArr[i5 - 7] + b(iArr[i5 - 15]) + iArr[i5 - 16] + i5;
        }
        System.arraycopy(iArr, 256, this.f14683a, 0, 512);
        System.arraycopy(iArr, 768, this.b, 0, 512);
        for (int i6 = 0; i6 < 512; i6++) {
            this.f14683a[i6] = n();
        }
        for (int i7 = 0; i7 < 512; i7++) {
            this.b[i7] = n();
        }
        this.c = 0;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters cipherParameters2;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.e = parametersWithIV.getIV();
            cipherParameters2 = parametersWithIV.getParameters();
        } else {
            this.e = new byte[0];
            cipherParameters2 = cipherParameters;
        }
        if (cipherParameters2 instanceof KeyParameter) {
            this.d = ((KeyParameter) cipherParameters2).getKey();
            i();
            this.f = true;
            return;
        }
        throw new IllegalArgumentException("Invalid parameter passed to HC128 init - " + cipherParameters.getClass().getName());
    }

    public final int n() {
        int h;
        int i;
        int k = k(this.c);
        if (this.c < 512) {
            int[] iArr = this.f14683a;
            iArr[k] = iArr[k] + d(iArr[a(k, 3)], this.f14683a[a(k, 10)], this.f14683a[a(k, 511)]);
            h = g(this.f14683a[a(k, 12)]);
            i = this.f14683a[k];
        } else {
            int[] iArr2 = this.b;
            iArr2[k] = iArr2[k] + e(iArr2[a(k, 3)], this.b[a(k, 10)], this.b[a(k, 511)]);
            h = h(this.b[a(k, 12)]);
            i = this.b[k];
        }
        int i2 = i ^ h;
        this.c = j(this.c + 1);
        return i2;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (!this.f) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i + i2 <= bArr.length) {
            if (i3 + i2 <= bArr2.length) {
                for (int i4 = 0; i4 < i2; i4++) {
                    bArr2[i3 + i4] = (byte) (bArr[i + i4] ^ f());
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
        i();
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        return (byte) (b ^ f());
    }
}
