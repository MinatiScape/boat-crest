package org.bouncycastle.jce.provider;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
/* loaded from: classes13.dex */
public class e extends PBEParametersGenerator {

    /* renamed from: a  reason: collision with root package name */
    public Digest f15111a;
    public int b;
    public int c;

    public e(Digest digest) {
        this.f15111a = digest;
        if (digest instanceof MD5Digest) {
            this.b = 16;
        } else if (!(digest instanceof SHA1Digest) && !(digest instanceof RIPEMD160Digest)) {
            throw new IllegalArgumentException("Digest " + digest.getAlgorithmName() + " unsupported");
        } else {
            this.b = 20;
        }
        this.c = 64;
    }

    public final void a(byte[] bArr, int i, byte[] bArr2) {
        int i2 = (bArr2[bArr2.length - 1] & 255) + (bArr[(bArr2.length + i) - 1] & 255) + 1;
        bArr[(bArr2.length + i) - 1] = (byte) i2;
        int i3 = i2 >>> 8;
        for (int length = bArr2.length - 2; length >= 0; length--) {
            int i4 = i + length;
            int i5 = i3 + (bArr2[length] & 255) + (bArr[i4] & 255);
            bArr[i4] = (byte) i5;
            i3 = i5 >>> 8;
        }
    }

    public final byte[] b(int i, int i2) {
        byte[] bArr;
        byte[] bArr2;
        int i3;
        int i4 = this.c;
        byte[] bArr3 = new byte[i4];
        byte[] bArr4 = new byte[i2];
        int i5 = 0;
        for (int i6 = 0; i6 != i4; i6++) {
            bArr3[i6] = (byte) i;
        }
        byte[] bArr5 = this.salt;
        int i7 = 1;
        if (bArr5 == null || bArr5.length == 0) {
            bArr = new byte[0];
        } else {
            int i8 = this.c;
            int length = i8 * (((bArr5.length + i8) - 1) / i8);
            bArr = new byte[length];
            for (int i9 = 0; i9 != length; i9++) {
                byte[] bArr6 = this.salt;
                bArr[i9] = bArr6[i9 % bArr6.length];
            }
        }
        byte[] bArr7 = this.password;
        if (bArr7 == null || bArr7.length == 0) {
            bArr2 = new byte[0];
        } else {
            int i10 = this.c;
            int length2 = i10 * (((bArr7.length + i10) - 1) / i10);
            bArr2 = new byte[length2];
            for (int i11 = 0; i11 != length2; i11++) {
                byte[] bArr8 = this.password;
                bArr2[i11] = bArr8[i11 % bArr8.length];
            }
        }
        int length3 = bArr.length + bArr2.length;
        byte[] bArr9 = new byte[length3];
        System.arraycopy(bArr, 0, bArr9, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr9, bArr.length, bArr2.length);
        int i12 = this.c;
        byte[] bArr10 = new byte[i12];
        int i13 = this.b;
        int i14 = ((i2 + i13) - 1) / i13;
        int i15 = 1;
        while (i15 <= i14) {
            int i16 = this.b;
            byte[] bArr11 = new byte[i16];
            this.f15111a.update(bArr3, i5, i4);
            this.f15111a.update(bArr9, i5, length3);
            this.f15111a.doFinal(bArr11, i5);
            for (int i17 = i7; i17 != this.iterationCount; i17++) {
                this.f15111a.update(bArr11, i5, i16);
                this.f15111a.doFinal(bArr11, i5);
            }
            for (int i18 = i5; i18 != i12; i18++) {
                bArr10[i15] = bArr11[i18 % i16];
            }
            int i19 = i5;
            while (true) {
                int i20 = this.c;
                if (i19 == length3 / i20) {
                    break;
                }
                a(bArr9, i20 * i19, bArr10);
                i19++;
            }
            if (i15 == i14) {
                int i21 = i15 - 1;
                int i22 = this.b;
                i3 = 0;
                System.arraycopy(bArr11, 0, bArr4, i21 * i22, i2 - (i21 * i22));
            } else {
                i3 = 0;
                System.arraycopy(bArr11, 0, bArr4, (i15 - 1) * this.b, i16);
            }
            i15++;
            i5 = i3;
            i7 = 1;
        }
        return bArr4;
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedMacParameters(int i) {
        int i2 = i / 8;
        return new KeyParameter(b(3, i2), 0, i2);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i) {
        int i2 = i / 8;
        return new KeyParameter(b(1, i2), 0, i2);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i, int i2) {
        int i3 = i / 8;
        int i4 = i2 / 8;
        byte[] b = b(1, i3);
        return new ParametersWithIV(new KeyParameter(b, 0, i3), b(2, i4), 0, i4);
    }
}
