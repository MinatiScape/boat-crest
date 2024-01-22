package org.bouncycastle.crypto.modes;

import androidx.core.view.ViewCompat;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
/* loaded from: classes13.dex */
public class GOFBBlockCipher extends StreamBlockCipher {
    public byte[] b;
    public byte[] c;
    public byte[] d;
    public int e;
    public final int f;
    public final BlockCipher g;
    public boolean h;
    public int i;
    public int j;

    public GOFBBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.h = true;
        this.g = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.f = blockSize;
        if (blockSize != 8) {
            throw new IllegalArgumentException("GCTR only for 64 bit block ciphers");
        }
        this.b = new byte[blockCipher.getBlockSize()];
        this.c = new byte[blockCipher.getBlockSize()];
        this.d = new byte[blockCipher.getBlockSize()];
    }

    public final int a(byte[] bArr, int i) {
        return ((bArr[i + 3] << 24) & ViewCompat.MEASURED_STATE_MASK) + ((bArr[i + 2] << 16) & 16711680) + ((bArr[i + 1] << 8) & 65280) + (bArr[i] & 255);
    }

    public final void b(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >>> 24);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2] = (byte) i;
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) {
        if (this.e == 0) {
            if (this.h) {
                this.h = false;
                this.g.processBlock(this.c, 0, this.d, 0);
                this.i = a(this.d, 0);
                this.j = a(this.d, 4);
            }
            int i = this.i + 16843009;
            this.i = i;
            int i2 = this.j + 16843012;
            this.j = i2;
            if (i2 < 16843012 && i2 > 0) {
                this.j = i2 + 1;
            }
            b(i, this.c, 0);
            b(this.j, this.c, 4);
            this.g.processBlock(this.c, 0, this.d, 0);
        }
        byte[] bArr = this.d;
        int i3 = this.e;
        int i4 = i3 + 1;
        this.e = i4;
        byte b2 = (byte) (b ^ bArr[i3]);
        int i5 = this.f;
        if (i4 == i5) {
            this.e = 0;
            byte[] bArr2 = this.c;
            System.arraycopy(bArr2, i5, bArr2, 0, bArr2.length - i5);
            byte[] bArr3 = this.d;
            byte[] bArr4 = this.c;
            int length = bArr4.length;
            int i6 = this.f;
            System.arraycopy(bArr3, 0, bArr4, length - i6, i6);
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.g.getAlgorithmName() + "/GCTR";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.f;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        this.h = true;
        this.i = 0;
        this.j = 0;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            int length = iv.length;
            byte[] bArr = this.b;
            if (length < bArr.length) {
                System.arraycopy(iv, 0, bArr, bArr.length - iv.length, iv.length);
                int i = 0;
                while (true) {
                    byte[] bArr2 = this.b;
                    if (i >= bArr2.length - iv.length) {
                        break;
                    }
                    bArr2[i] = 0;
                    i++;
                }
            } else {
                System.arraycopy(iv, 0, bArr, 0, bArr.length);
            }
            reset();
            if (parametersWithIV.getParameters() == null) {
                return;
            }
            blockCipher = this.g;
            cipherParameters = parametersWithIV.getParameters();
        } else {
            reset();
            if (cipherParameters == null) {
                return;
            }
            blockCipher = this.g;
        }
        blockCipher.init(true, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.f, bArr2, i2);
        return this.f;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        this.h = true;
        this.i = 0;
        this.j = 0;
        byte[] bArr = this.b;
        System.arraycopy(bArr, 0, this.c, 0, bArr.length);
        this.e = 0;
        this.g.reset();
    }
}
