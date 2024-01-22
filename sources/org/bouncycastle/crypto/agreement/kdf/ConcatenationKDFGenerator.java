package org.bouncycastle.crypto.agreement.kdf;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KDFParameters;
/* loaded from: classes8.dex */
public class ConcatenationKDFGenerator implements DerivationFunction {

    /* renamed from: a  reason: collision with root package name */
    public Digest f14625a;
    public byte[] b;
    public byte[] c;
    public int d;

    public ConcatenationKDFGenerator(Digest digest) {
        this.f14625a = digest;
        this.d = digest.getDigestSize();
    }

    public final void a(int i, byte[] bArr) {
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 0);
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        int i3;
        int i4;
        if (bArr.length - i2 >= i) {
            byte[] bArr2 = new byte[this.d];
            byte[] bArr3 = new byte[4];
            this.f14625a.reset();
            int i5 = 1;
            if (i2 > this.d) {
                i3 = 0;
                while (true) {
                    a(i5, bArr3);
                    this.f14625a.update(bArr3, 0, 4);
                    Digest digest = this.f14625a;
                    byte[] bArr4 = this.b;
                    digest.update(bArr4, 0, bArr4.length);
                    Digest digest2 = this.f14625a;
                    byte[] bArr5 = this.c;
                    digest2.update(bArr5, 0, bArr5.length);
                    this.f14625a.doFinal(bArr2, 0);
                    System.arraycopy(bArr2, 0, bArr, i + i3, this.d);
                    int i6 = this.d;
                    i3 += i6;
                    i4 = i5 + 1;
                    if (i5 >= i2 / i6) {
                        break;
                    }
                    i5 = i4;
                }
                i5 = i4;
            } else {
                i3 = 0;
            }
            if (i3 < i2) {
                a(i5, bArr3);
                this.f14625a.update(bArr3, 0, 4);
                Digest digest3 = this.f14625a;
                byte[] bArr6 = this.b;
                digest3.update(bArr6, 0, bArr6.length);
                Digest digest4 = this.f14625a;
                byte[] bArr7 = this.c;
                digest4.update(bArr7, 0, bArr7.length);
                this.f14625a.doFinal(bArr2, 0);
                System.arraycopy(bArr2, 0, bArr, i + i3, i2 - i3);
            }
            return i2;
        }
        throw new OutputLengthException("output buffer too small");
    }

    public Digest getDigest() {
        return this.f14625a;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof KDFParameters)) {
            throw new IllegalArgumentException("KDF parameters required for generator");
        }
        KDFParameters kDFParameters = (KDFParameters) derivationParameters;
        this.b = kDFParameters.getSharedSecret();
        this.c = kDFParameters.getIV();
    }
}
