package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class CFBBlockCipher extends StreamBlockCipher {
    public byte[] b;
    public byte[] c;
    public byte[] d;
    public byte[] e;
    public int f;
    public BlockCipher g;
    public boolean h;
    public int i;

    public CFBBlockCipher(BlockCipher blockCipher, int i) {
        super(blockCipher);
        this.g = null;
        this.g = blockCipher;
        this.f = i / 8;
        this.b = new byte[blockCipher.getBlockSize()];
        this.c = new byte[blockCipher.getBlockSize()];
        this.d = new byte[blockCipher.getBlockSize()];
        this.e = new byte[this.f];
    }

    public final byte a(byte b) {
        if (this.i == 0) {
            this.g.processBlock(this.c, 0, this.d, 0);
        }
        byte[] bArr = this.e;
        int i = this.i;
        bArr[i] = b;
        byte[] bArr2 = this.d;
        int i2 = i + 1;
        this.i = i2;
        byte b2 = (byte) (b ^ bArr2[i]);
        int i3 = this.f;
        if (i2 == i3) {
            this.i = 0;
            byte[] bArr3 = this.c;
            System.arraycopy(bArr3, i3, bArr3, 0, bArr3.length - i3);
            byte[] bArr4 = this.e;
            byte[] bArr5 = this.c;
            int length = bArr5.length;
            int i4 = this.f;
            System.arraycopy(bArr4, 0, bArr5, length - i4, i4);
        }
        return b2;
    }

    public final byte b(byte b) {
        if (this.i == 0) {
            this.g.processBlock(this.c, 0, this.d, 0);
        }
        byte[] bArr = this.d;
        int i = this.i;
        byte b2 = (byte) (b ^ bArr[i]);
        byte[] bArr2 = this.e;
        int i2 = i + 1;
        this.i = i2;
        bArr2[i] = b2;
        int i3 = this.f;
        if (i2 == i3) {
            this.i = 0;
            byte[] bArr3 = this.c;
            System.arraycopy(bArr3, i3, bArr3, 0, bArr3.length - i3);
            byte[] bArr4 = this.e;
            byte[] bArr5 = this.c;
            int length = bArr5.length;
            int i4 = this.f;
            System.arraycopy(bArr4, 0, bArr5, length - i4, i4);
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) throws DataLengthException, IllegalStateException {
        return this.h ? b(b) : a(b);
    }

    public int decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.f, bArr2, i2);
        return this.f;
    }

    public int encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.f, bArr2, i2);
        return this.f;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.g.getAlgorithmName() + "/CFB" + (this.f * 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.f;
    }

    public byte[] getCurrentIV() {
        return Arrays.clone(this.c);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        this.h = z;
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
        byte[] bArr = this.b;
        System.arraycopy(bArr, 0, this.c, 0, bArr.length);
        Arrays.fill(this.e, (byte) 0);
        this.i = 0;
        this.g.reset();
    }
}
