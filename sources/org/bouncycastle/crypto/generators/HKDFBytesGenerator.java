package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.HKDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
/* loaded from: classes12.dex */
public class HKDFBytesGenerator implements DerivationFunction {

    /* renamed from: a  reason: collision with root package name */
    public HMac f14733a;
    public int b;
    public byte[] c;
    public byte[] d;
    public int e;

    public HKDFBytesGenerator(Digest digest) {
        this.f14733a = new HMac(digest);
        this.b = digest.getDigestSize();
    }

    public final void a() throws DataLengthException {
        int i = this.e;
        int i2 = this.b;
        int i3 = (i / i2) + 1;
        if (i3 >= 256) {
            throw new DataLengthException("HKDF cannot generate more than 255 blocks of HashLen size");
        }
        if (i != 0) {
            this.f14733a.update(this.d, 0, i2);
        }
        HMac hMac = this.f14733a;
        byte[] bArr = this.c;
        hMac.update(bArr, 0, bArr.length);
        this.f14733a.update((byte) i3);
        this.f14733a.doFinal(this.d, 0);
    }

    public final KeyParameter b(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            this.f14733a.init(new KeyParameter(new byte[this.b]));
        } else {
            this.f14733a.init(new KeyParameter(bArr));
        }
        this.f14733a.update(bArr2, 0, bArr2.length);
        byte[] bArr3 = new byte[this.b];
        this.f14733a.doFinal(bArr3, 0);
        return new KeyParameter(bArr3);
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        int i3 = this.e;
        int i4 = i3 + i2;
        int i5 = this.b;
        if (i4 > i5 * 255) {
            throw new DataLengthException("HKDF may only be used for 255 * HashLen bytes of output");
        }
        if (i3 % i5 == 0) {
            a();
        }
        int i6 = this.e;
        int i7 = this.b;
        int i8 = i6 % i7;
        int min = Math.min(i7 - (i6 % i7), i2);
        System.arraycopy(this.d, i8, bArr, i, min);
        this.e += min;
        int i9 = i2 - min;
        while (true) {
            i += min;
            if (i9 <= 0) {
                return i2;
            }
            a();
            min = Math.min(this.b, i9);
            System.arraycopy(this.d, 0, bArr, i, min);
            this.e += min;
            i9 -= min;
        }
    }

    public Digest getDigest() {
        return this.f14733a.getUnderlyingDigest();
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        HMac hMac;
        KeyParameter b;
        if (!(derivationParameters instanceof HKDFParameters)) {
            throw new IllegalArgumentException("HKDF parameters required for HKDFBytesGenerator");
        }
        HKDFParameters hKDFParameters = (HKDFParameters) derivationParameters;
        if (hKDFParameters.skipExtract()) {
            hMac = this.f14733a;
            b = new KeyParameter(hKDFParameters.getIKM());
        } else {
            hMac = this.f14733a;
            b = b(hKDFParameters.getSalt(), hKDFParameters.getIKM());
        }
        hMac.init(b);
        this.c = hKDFParameters.getInfo();
        this.e = 0;
        this.d = new byte[this.b];
    }
}
