package org.bouncycastle.crypto.engines;

import com.jieli.jl_rcsp.constant.Command;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
/* loaded from: classes12.dex */
public class NoekeonEngine implements BlockCipher {
    public static final int[] f = {0, 0, 0, 0};
    public static final int[] g = {128, 27, 54, 108, Command.CMD_SET_DEVICE_STORAGE, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212};

    /* renamed from: a  reason: collision with root package name */
    public int[] f14689a = new int[4];
    public int[] b = new int[4];
    public int[] c = new int[4];
    public boolean d = false;
    public boolean e;

    public final int a(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i] << 24) | ((bArr[i2] & 255) << 16);
        return (bArr[i3 + 1] & 255) | i4 | ((bArr[i3] & 255) << 8);
    }

    public final int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.f14689a[0] = a(bArr, i);
        this.f14689a[1] = a(bArr, i + 4);
        this.f14689a[2] = a(bArr, i + 8);
        this.f14689a[3] = a(bArr, i + 12);
        int[] iArr = this.b;
        System.arraycopy(iArr, 0, this.c, 0, iArr.length);
        j(this.c, f);
        int i3 = 16;
        while (true) {
            int[] iArr2 = this.f14689a;
            int[] iArr3 = this.c;
            if (i3 <= 0) {
                j(iArr2, iArr3);
                int[] iArr4 = this.f14689a;
                iArr4[0] = g[i3] ^ iArr4[0];
                e(iArr4[0], bArr2, i2);
                e(this.f14689a[1], bArr2, i2 + 4);
                e(this.f14689a[2], bArr2, i2 + 8);
                e(this.f14689a[3], bArr2, i2 + 12);
                return 16;
            }
            j(iArr2, iArr3);
            int[] iArr5 = this.f14689a;
            iArr5[0] = iArr5[0] ^ g[i3];
            f(iArr5);
            d(this.f14689a);
            g(this.f14689a);
            i3--;
        }
    }

    public final int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.f14689a[0] = a(bArr, i);
        this.f14689a[1] = a(bArr, i + 4);
        this.f14689a[2] = a(bArr, i + 8);
        this.f14689a[3] = a(bArr, i + 12);
        int i3 = 0;
        while (i3 < 16) {
            int[] iArr = this.f14689a;
            iArr[0] = iArr[0] ^ g[i3];
            j(iArr, this.b);
            f(this.f14689a);
            d(this.f14689a);
            g(this.f14689a);
            i3++;
        }
        int[] iArr2 = this.f14689a;
        iArr2[0] = g[i3] ^ iArr2[0];
        j(iArr2, this.b);
        e(this.f14689a[0], bArr2, i2);
        e(this.f14689a[1], bArr2, i2 + 4);
        e(this.f14689a[2], bArr2, i2 + 8);
        e(this.f14689a[3], bArr2, i2 + 12);
        return 16;
    }

    public final void d(int[] iArr) {
        iArr[1] = iArr[1] ^ ((~iArr[3]) & (~iArr[2]));
        iArr[0] = iArr[0] ^ (iArr[2] & iArr[1]);
        int i = iArr[3];
        iArr[3] = iArr[0];
        iArr[0] = i;
        iArr[2] = iArr[2] ^ ((iArr[0] ^ iArr[1]) ^ iArr[3]);
        iArr[1] = ((~iArr[3]) & (~iArr[2])) ^ iArr[1];
        iArr[0] = (iArr[1] & iArr[2]) ^ iArr[0];
    }

    public final void e(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 16);
        bArr[i4] = (byte) (i >>> 8);
        bArr[i4 + 1] = (byte) i;
    }

    public final void f(int[] iArr) {
        iArr[1] = h(iArr[1], 1);
        iArr[2] = h(iArr[2], 5);
        iArr[3] = h(iArr[3], 2);
    }

    public final void g(int[] iArr) {
        iArr[1] = h(iArr[1], 31);
        iArr[2] = h(iArr[2], 27);
        iArr[3] = h(iArr[3], 30);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Noekeon";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    public final int h(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public final void i(byte[] bArr) {
        this.b[0] = a(bArr, 0);
        this.b[1] = a(bArr, 4);
        this.b[2] = a(bArr, 8);
        this.b[3] = a(bArr, 12);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.e = z;
            this.d = true;
            i(((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Noekeon init - " + cipherParameters.getClass().getName());
    }

    public final void j(int[] iArr, int[] iArr2) {
        int i = iArr[0] ^ iArr[2];
        int h = i ^ (h(i, 8) ^ h(i, 24));
        iArr[1] = iArr[1] ^ h;
        iArr[3] = h ^ iArr[3];
        for (int i2 = 0; i2 < 4; i2++) {
            iArr[i2] = iArr[i2] ^ iArr2[i2];
        }
        int i3 = iArr[1] ^ iArr[3];
        int h2 = i3 ^ (h(i3, 8) ^ h(i3, 24));
        iArr[0] = iArr[0] ^ h2;
        iArr[2] = h2 ^ iArr[2];
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (!this.d) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i + 16 <= bArr.length) {
            if (i2 + 16 <= bArr2.length) {
                return this.e ? c(bArr, i, bArr2, i2) : b(bArr, i, bArr2, i2);
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
