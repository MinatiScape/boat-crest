package org.bouncycastle.crypto.engines;

import java.security.SecureRandom;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
/* loaded from: classes12.dex */
public class RFC3211WrapEngine implements Wrapper {

    /* renamed from: a  reason: collision with root package name */
    public CBCBlockCipher f14697a;
    public ParametersWithIV b;
    public boolean c;
    public SecureRandom d;

    public RFC3211WrapEngine(BlockCipher blockCipher) {
        this.f14697a = new CBCBlockCipher(blockCipher);
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return this.f14697a.getUnderlyingCipher().getAlgorithmName() + "/RFC3211Wrap";
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) {
        this.c = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.d = parametersWithRandom.getRandom();
            this.b = (ParametersWithIV) parametersWithRandom.getParameters();
            return;
        }
        if (z) {
            this.d = new SecureRandom();
        }
        this.b = (ParametersWithIV) cipherParameters;
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.c) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int blockSize = this.f14697a.getBlockSize();
        if (i2 >= blockSize * 2) {
            byte[] bArr2 = new byte[i2];
            byte[] bArr3 = new byte[blockSize];
            int i3 = 0;
            System.arraycopy(bArr, i, bArr2, 0, i2);
            System.arraycopy(bArr, i, bArr3, 0, blockSize);
            this.f14697a.init(false, new ParametersWithIV(this.b.getParameters(), bArr3));
            for (int i4 = blockSize; i4 < i2; i4 += blockSize) {
                this.f14697a.processBlock(bArr2, i4, bArr2, i4);
            }
            System.arraycopy(bArr2, i2 - blockSize, bArr3, 0, blockSize);
            this.f14697a.init(false, new ParametersWithIV(this.b.getParameters(), bArr3));
            this.f14697a.processBlock(bArr2, 0, bArr2, 0);
            this.f14697a.init(false, this.b);
            for (int i5 = 0; i5 < i2; i5 += blockSize) {
                this.f14697a.processBlock(bArr2, i5, bArr2, i5);
            }
            if ((bArr2[0] & 255) <= i2 - 4) {
                byte[] bArr4 = new byte[bArr2[0] & 255];
                System.arraycopy(bArr2, 4, bArr4, 0, bArr2[0]);
                int i6 = 0;
                while (i3 != 3) {
                    int i7 = i3 + 1;
                    i6 |= ((byte) (~bArr2[i7])) ^ bArr4[i3];
                    i3 = i7;
                }
                if (i6 == 0) {
                    return bArr4;
                }
                throw new InvalidCipherTextException("wrapped key fails checksum");
            }
            throw new InvalidCipherTextException("wrapped key corrupted");
        }
        throw new InvalidCipherTextException("input too short");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int i, int i2) {
        if (this.c) {
            this.f14697a.init(true, this.b);
            int blockSize = this.f14697a.getBlockSize();
            int i3 = i2 + 4;
            int i4 = blockSize * 2;
            if (i3 >= i4) {
                i4 = i3 % blockSize == 0 ? i3 : ((i3 / blockSize) + 1) * blockSize;
            }
            byte[] bArr2 = new byte[i4];
            bArr2[0] = (byte) i2;
            bArr2[1] = (byte) (~bArr[i]);
            bArr2[2] = (byte) (~bArr[i + 1]);
            bArr2[3] = (byte) (~bArr[i + 2]);
            System.arraycopy(bArr, i, bArr2, 4, i2);
            int length = bArr2.length - i3;
            byte[] bArr3 = new byte[length];
            this.d.nextBytes(bArr3);
            System.arraycopy(bArr3, 0, bArr2, i3, length);
            for (int i5 = 0; i5 < bArr2.length; i5 += blockSize) {
                this.f14697a.processBlock(bArr2, i5, bArr2, i5);
            }
            for (int i6 = 0; i6 < bArr2.length; i6 += blockSize) {
                this.f14697a.processBlock(bArr2, i6, bArr2, i6);
            }
            return bArr2;
        }
        throw new IllegalStateException("not set for wrapping");
    }
}
