package org.bouncycastle.crypto.signers;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class GenericSigner implements Signer {

    /* renamed from: a  reason: collision with root package name */
    public final AsymmetricBlockCipher f14836a;
    public final Digest b;
    public boolean c;

    public GenericSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest) {
        this.f14836a = asymmetricBlockCipher;
        this.b = digest;
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException, DataLengthException {
        if (this.c) {
            int digestSize = this.b.getDigestSize();
            byte[] bArr = new byte[digestSize];
            this.b.doFinal(bArr, 0);
            return this.f14836a.processBlock(bArr, 0, digestSize);
        }
        throw new IllegalStateException("GenericSigner not initialised for signature generation.");
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        this.c = z;
        AsymmetricKeyParameter asymmetricKeyParameter = cipherParameters instanceof ParametersWithRandom ? (AsymmetricKeyParameter) ((ParametersWithRandom) cipherParameters).getParameters() : (AsymmetricKeyParameter) cipherParameters;
        if (z && !asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("signing requires private key");
        }
        if (!z && asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("verification requires public key");
        }
        reset();
        this.f14836a.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.b.reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.b.update(b);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        this.b.update(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        if (this.c) {
            throw new IllegalStateException("GenericSigner not initialised for verification");
        }
        int digestSize = this.b.getDigestSize();
        byte[] bArr2 = new byte[digestSize];
        this.b.doFinal(bArr2, 0);
        try {
            byte[] processBlock = this.f14836a.processBlock(bArr, 0, bArr.length);
            if (processBlock.length < digestSize) {
                byte[] bArr3 = new byte[digestSize];
                System.arraycopy(processBlock, 0, bArr3, digestSize - processBlock.length, processBlock.length);
                processBlock = bArr3;
            }
            return Arrays.constantTimeAreEqual(processBlock, bArr2);
        } catch (Exception unused) {
            return false;
        }
    }
}
