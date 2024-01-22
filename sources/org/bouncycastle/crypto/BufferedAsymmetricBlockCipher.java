package org.bouncycastle.crypto;
/* loaded from: classes5.dex */
public class BufferedAsymmetricBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public final AsymmetricBlockCipher f14604a;
    public byte[] buf;
    public int bufOff;

    public BufferedAsymmetricBlockCipher(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.f14604a = asymmetricBlockCipher;
    }

    public byte[] doFinal() throws InvalidCipherTextException {
        byte[] processBlock = this.f14604a.processBlock(this.buf, 0, this.bufOff);
        reset();
        return processBlock;
    }

    public int getBufferPosition() {
        return this.bufOff;
    }

    public int getInputBlockSize() {
        return this.f14604a.getInputBlockSize();
    }

    public int getOutputBlockSize() {
        return this.f14604a.getOutputBlockSize();
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.f14604a;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        reset();
        this.f14604a.init(z, cipherParameters);
        this.buf = new byte[this.f14604a.getInputBlockSize() + (z ? 1 : 0)];
        this.bufOff = 0;
    }

    public void processByte(byte b) {
        int i = this.bufOff;
        byte[] bArr = this.buf;
        if (i >= bArr.length) {
            throw new DataLengthException("attempt to process message too long for cipher");
        }
        this.bufOff = i + 1;
        bArr[i] = b;
    }

    public void processBytes(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int i3 = this.bufOff;
        int i4 = i3 + i2;
        byte[] bArr2 = this.buf;
        if (i4 > bArr2.length) {
            throw new DataLengthException("attempt to process message too long for cipher");
        }
        System.arraycopy(bArr, i, bArr2, i3, i2);
        this.bufOff += i2;
    }

    public void reset() {
        if (this.buf != null) {
            int i = 0;
            while (true) {
                byte[] bArr = this.buf;
                if (i >= bArr.length) {
                    break;
                }
                bArr[i] = 0;
                i++;
            }
        }
        this.bufOff = 0;
    }
}
