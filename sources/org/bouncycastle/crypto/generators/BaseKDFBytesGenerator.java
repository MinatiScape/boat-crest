package org.bouncycastle.crypto.generators;

import androidx.core.view.InputDeviceCompat;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.DigestDerivationFunction;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ISO18033KDFParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class BaseKDFBytesGenerator implements DigestDerivationFunction {

    /* renamed from: a  reason: collision with root package name */
    public int f14718a;
    public Digest b;
    public byte[] c;
    public byte[] d;

    public BaseKDFBytesGenerator(int i, Digest digest) {
        this.f14718a = i;
        this.b = digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        int i3 = i2;
        int i4 = i;
        if (bArr.length - i3 >= i4) {
            long j = i3;
            int digestSize = this.b.getDigestSize();
            if (j <= 8589934591L) {
                long j2 = digestSize;
                int i5 = (int) (((j + j2) - 1) / j2);
                byte[] bArr2 = new byte[this.b.getDigestSize()];
                byte[] bArr3 = new byte[4];
                Pack.intToBigEndian(this.f14718a, bArr3, 0);
                int i6 = this.f14718a & InputDeviceCompat.SOURCE_ANY;
                for (int i7 = 0; i7 < i5; i7++) {
                    Digest digest = this.b;
                    byte[] bArr4 = this.c;
                    digest.update(bArr4, 0, bArr4.length);
                    this.b.update(bArr3, 0, 4);
                    byte[] bArr5 = this.d;
                    if (bArr5 != null) {
                        this.b.update(bArr5, 0, bArr5.length);
                    }
                    this.b.doFinal(bArr2, 0);
                    if (i3 > digestSize) {
                        System.arraycopy(bArr2, 0, bArr, i4, digestSize);
                        i4 += digestSize;
                        i3 -= digestSize;
                    } else {
                        System.arraycopy(bArr2, 0, bArr, i4, i3);
                    }
                    byte b = (byte) (bArr3[3] + 1);
                    bArr3[3] = b;
                    if (b == 0) {
                        i6 += 256;
                        Pack.intToBigEndian(i6, bArr3, 0);
                    }
                }
                this.b.reset();
                return (int) j;
            }
            throw new IllegalArgumentException("Output length too large");
        }
        throw new OutputLengthException("output buffer too small");
    }

    @Override // org.bouncycastle.crypto.DigestDerivationFunction
    public Digest getDigest() {
        return this.b;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (derivationParameters instanceof KDFParameters) {
            KDFParameters kDFParameters = (KDFParameters) derivationParameters;
            this.c = kDFParameters.getSharedSecret();
            this.d = kDFParameters.getIV();
        } else if (!(derivationParameters instanceof ISO18033KDFParameters)) {
            throw new IllegalArgumentException("KDF parameters required for generator");
        } else {
            this.c = ((ISO18033KDFParameters) derivationParameters).getSeed();
            this.d = null;
        }
    }
}
