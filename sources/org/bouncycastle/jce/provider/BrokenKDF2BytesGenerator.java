package org.bouncycastle.jce.provider;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KDFParameters;
/* loaded from: classes13.dex */
public class BrokenKDF2BytesGenerator implements DerivationFunction {

    /* renamed from: a  reason: collision with root package name */
    public Digest f15085a;
    public byte[] b;
    public byte[] c;

    public BrokenKDF2BytesGenerator(Digest digest) {
        this.f15085a = digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        if (bArr.length - i2 >= i) {
            long j = i2 * 8;
            if (j > this.f15085a.getDigestSize() * 8 * 2147483648L) {
                new IllegalArgumentException("Output length to large");
            }
            int digestSize = (int) (j / this.f15085a.getDigestSize());
            int digestSize2 = this.f15085a.getDigestSize();
            byte[] bArr2 = new byte[digestSize2];
            for (int i3 = 1; i3 <= digestSize; i3++) {
                Digest digest = this.f15085a;
                byte[] bArr3 = this.b;
                digest.update(bArr3, 0, bArr3.length);
                this.f15085a.update((byte) (i3 & 255));
                this.f15085a.update((byte) ((i3 >> 8) & 255));
                this.f15085a.update((byte) ((i3 >> 16) & 255));
                this.f15085a.update((byte) ((i3 >> 24) & 255));
                Digest digest2 = this.f15085a;
                byte[] bArr4 = this.c;
                digest2.update(bArr4, 0, bArr4.length);
                this.f15085a.doFinal(bArr2, 0);
                int i4 = i2 - i;
                if (i4 > digestSize2) {
                    System.arraycopy(bArr2, 0, bArr, i, digestSize2);
                    i += digestSize2;
                } else {
                    System.arraycopy(bArr2, 0, bArr, i, i4);
                }
            }
            this.f15085a.reset();
            return i2;
        }
        throw new OutputLengthException("output buffer too small");
    }

    public Digest getDigest() {
        return this.f15085a;
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
