package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.MGFParameters;
/* loaded from: classes12.dex */
public class MGF1BytesGenerator implements DerivationFunction {

    /* renamed from: a  reason: collision with root package name */
    public Digest f14737a;
    public byte[] b;
    public int c;

    public MGF1BytesGenerator(Digest digest) {
        this.f14737a = digest;
        this.c = digest.getDigestSize();
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
        if (bArr.length - i2 >= i) {
            byte[] bArr2 = new byte[this.c];
            byte[] bArr3 = new byte[4];
            this.f14737a.reset();
            if (i2 > this.c) {
                i3 = 0;
                do {
                    a(i3, bArr3);
                    Digest digest = this.f14737a;
                    byte[] bArr4 = this.b;
                    digest.update(bArr4, 0, bArr4.length);
                    this.f14737a.update(bArr3, 0, 4);
                    this.f14737a.doFinal(bArr2, 0);
                    int i4 = this.c;
                    System.arraycopy(bArr2, 0, bArr, (i3 * i4) + i, i4);
                    i3++;
                } while (i3 < i2 / this.c);
            } else {
                i3 = 0;
            }
            if (this.c * i3 < i2) {
                a(i3, bArr3);
                Digest digest2 = this.f14737a;
                byte[] bArr5 = this.b;
                digest2.update(bArr5, 0, bArr5.length);
                this.f14737a.update(bArr3, 0, 4);
                this.f14737a.doFinal(bArr2, 0);
                int i5 = this.c;
                System.arraycopy(bArr2, 0, bArr, i + (i3 * i5), i2 - (i3 * i5));
            }
            return i2;
        }
        throw new OutputLengthException("output buffer too small");
    }

    public Digest getDigest() {
        return this.f14737a;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof MGFParameters)) {
            throw new IllegalArgumentException("MGF parameters required for MGF1Generator");
        }
        this.b = ((MGFParameters) derivationParameters).getSeed();
    }
}
