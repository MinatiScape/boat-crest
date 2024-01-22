package org.bouncycastle.crypto.macs;

import androidx.core.view.ViewCompat;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithSBox;
/* loaded from: classes12.dex */
public class GOST28147Mac implements Mac {

    /* renamed from: a  reason: collision with root package name */
    public int f14757a = 8;
    public int b = 4;
    public boolean f = true;
    public int[] g = null;
    public byte[] h = null;
    public byte[] i = {9, 6, 3, 2, 8, 11, 1, 7, 10, 4, 14, 15, 12, 0, 13, 5, 3, 7, 14, 9, 8, 10, 15, 0, 5, 2, 6, 12, 11, 4, 13, 1, 14, 4, 6, 2, 11, 3, 13, 8, 12, 15, 5, 10, 0, 7, 1, 9, 14, 7, 10, 12, 13, 1, 3, 9, 0, 2, 11, 4, 15, 8, 5, 6, 11, 5, 1, 9, 8, 13, 15, 0, 14, 4, 2, 3, 12, 7, 10, 6, 3, 10, 13, 12, 1, 2, 0, 11, 7, 5, 9, 4, 8, 15, 14, 6, 1, 13, 2, 9, 7, 10, 6, 0, 8, 12, 4, 5, 15, 3, 11, 14, 11, 10, 15, 5, 0, 12, 14, 8, 6, 2, 3, 9, 1, 7, 13, 4};
    public byte[] e = new byte[8];
    public byte[] d = new byte[8];
    public int c = 0;

    public final byte[] a(byte[] bArr, int i, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length - i];
        System.arraycopy(bArr, i, bArr3, 0, bArr2.length);
        for (int i2 = 0; i2 != bArr2.length; i2++) {
            bArr3[i2] = (byte) (bArr3[i2] ^ bArr2[i2]);
        }
        return bArr3;
    }

    public final int b(byte[] bArr, int i) {
        return ((bArr[i + 3] << 24) & ViewCompat.MEASURED_STATE_MASK) + ((bArr[i + 2] << 16) & 16711680) + ((bArr[i + 1] << 8) & 65280) + (bArr[i] & 255);
    }

    public final int[] c(byte[] bArr) {
        if (bArr.length == 32) {
            int[] iArr = new int[8];
            for (int i = 0; i != 8; i++) {
                iArr[i] = b(bArr, i * 4);
            }
            return iArr;
        }
        throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
    }

    public final void d(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int b = b(bArr, i);
        int b2 = b(bArr, i + 4);
        for (int i3 = 0; i3 < 2; i3++) {
            int i4 = 0;
            while (i4 < 8) {
                i4++;
                int i5 = b;
                b = b2 ^ e(b, iArr[i4]);
                b2 = i5;
            }
        }
        f(b, bArr2, i2);
        f(b2, bArr2, i2 + 4);
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        while (true) {
            int i2 = this.c;
            if (i2 >= this.f14757a) {
                break;
            }
            this.d[i2] = 0;
            this.c = i2 + 1;
        }
        byte[] bArr2 = this.d;
        byte[] bArr3 = new byte[bArr2.length];
        System.arraycopy(bArr2, 0, bArr3, 0, this.e.length);
        if (this.f) {
            this.f = false;
        } else {
            bArr3 = a(this.d, 0, this.e);
        }
        d(this.g, bArr3, 0, this.e, 0);
        byte[] bArr4 = this.e;
        int i3 = this.b;
        System.arraycopy(bArr4, (bArr4.length / 2) - i3, bArr, i, i3);
        reset();
        return this.b;
    }

    public final int e(int i, int i2) {
        int i3 = i2 + i;
        byte[] bArr = this.i;
        int i4 = (bArr[((i3 >> 0) & 15) + 0] << 0) + (bArr[((i3 >> 4) & 15) + 16] << 4) + (bArr[((i3 >> 8) & 15) + 32] << 8) + (bArr[((i3 >> 12) & 15) + 48] << 12) + (bArr[((i3 >> 16) & 15) + 64] << 16) + (bArr[((i3 >> 20) & 15) + 80] << 20) + (bArr[((i3 >> 24) & 15) + 96] << 24) + (bArr[((i3 >> 28) & 15) + 112] << 28);
        return (i4 << 11) | (i4 >>> 21);
    }

    public final void f(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >>> 24);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2] = (byte) i;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "GOST28147Mac";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.b;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        reset();
        this.d = new byte[this.f14757a];
        this.h = null;
        if (cipherParameters instanceof ParametersWithSBox) {
            ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
            System.arraycopy(parametersWithSBox.getSBox(), 0, this.i, 0, parametersWithSBox.getSBox().length);
            if (parametersWithSBox.getParameters() != null) {
                this.g = c(((KeyParameter) parametersWithSBox.getParameters()).getKey());
            }
        } else if (cipherParameters instanceof KeyParameter) {
            this.g = c(((KeyParameter) cipherParameters).getKey());
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + cipherParameters.getClass().getName());
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.g = c(((KeyParameter) parametersWithIV.getParameters()).getKey());
            byte[] iv = parametersWithIV.getIV();
            byte[] bArr = this.e;
            System.arraycopy(iv, 0, bArr, 0, bArr.length);
            this.h = parametersWithIV.getIV();
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        int i = 0;
        while (true) {
            byte[] bArr = this.d;
            if (i >= bArr.length) {
                this.c = 0;
                this.f = true;
                return;
            }
            bArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        int i = this.c;
        byte[] bArr = this.d;
        if (i == bArr.length) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, this.e.length);
            if (this.f) {
                this.f = false;
                byte[] bArr3 = this.h;
                if (bArr3 != null) {
                    bArr2 = a(this.d, 0, bArr3);
                }
            } else {
                bArr2 = a(this.d, 0, this.e);
            }
            d(this.g, bArr2, 0, this.e, 0);
            this.c = 0;
        }
        byte[] bArr4 = this.d;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr4[i2] = b;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int i3 = this.f14757a;
        int i4 = this.c;
        int i5 = i3 - i4;
        if (i2 > i5) {
            System.arraycopy(bArr, i, this.d, i4, i5);
            byte[] bArr2 = this.d;
            byte[] bArr3 = new byte[bArr2.length];
            System.arraycopy(bArr2, 0, bArr3, 0, this.e.length);
            if (this.f) {
                this.f = false;
                byte[] bArr4 = this.h;
                if (bArr4 != null) {
                    bArr3 = a(this.d, 0, bArr4);
                }
            } else {
                bArr3 = a(this.d, 0, this.e);
            }
            d(this.g, bArr3, 0, this.e, 0);
            this.c = 0;
            while (true) {
                i2 -= i5;
                i += i5;
                if (i2 <= this.f14757a) {
                    break;
                }
                d(this.g, a(bArr, i, this.e), 0, this.e, 0);
                i5 = this.f14757a;
            }
        }
        System.arraycopy(bArr, i, this.d, this.c, i2);
        this.c += i2;
    }
}
