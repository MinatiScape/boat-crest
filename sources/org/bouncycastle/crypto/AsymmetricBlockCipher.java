package org.bouncycastle.crypto;
/* loaded from: classes5.dex */
public interface AsymmetricBlockCipher {
    int getInputBlockSize();

    int getOutputBlockSize();

    void init(boolean z, CipherParameters cipherParameters);

    byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException;
}
