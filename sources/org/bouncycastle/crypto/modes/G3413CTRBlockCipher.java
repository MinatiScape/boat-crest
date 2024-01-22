package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class G3413CTRBlockCipher extends StreamBlockCipher {
    public final int b;
    public byte[] c;
    public byte[] d;
    public byte[] e;
    public final int f;
    public final BlockCipher g;
    public int h;
    public boolean i;

    public G3413CTRBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8);
    }

    public G3413CTRBlockCipher(BlockCipher blockCipher, int i) {
        super(blockCipher);
        this.h = 0;
        if (i < 0 || i > blockCipher.getBlockSize() * 8) {
            throw new IllegalArgumentException("Parameter bitBlockSize must be in range 0 < bitBlockSize <= " + (blockCipher.getBlockSize() * 8));
        }
        this.g = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.f = blockSize;
        this.b = i / 8;
        this.c = new byte[blockSize];
    }

    public final byte[] a() {
        byte[] bArr = this.c;
        byte[] bArr2 = new byte[bArr.length];
        this.g.processBlock(bArr, 0, bArr2, 0);
        return a.b(bArr2, this.b);
    }

    public final void b() {
        byte[] bArr = this.c;
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + 1);
    }

    public final void c() {
        int i = this.f;
        this.d = new byte[i / 2];
        this.c = new byte[i];
        this.e = new byte[this.b];
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) {
        if (this.h == 0) {
            this.e = a();
        }
        byte[] bArr = this.e;
        int i = this.h;
        byte b2 = (byte) (b ^ bArr[i]);
        int i2 = i + 1;
        this.h = i2;
        if (i2 == this.b) {
            this.h = 0;
            b();
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.g.getAlgorithmName() + "/GCTR";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.b;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            c();
            if (cipherParameters != null) {
                blockCipher = this.g;
                blockCipher.init(true, cipherParameters);
            }
            this.i = true;
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        c();
        byte[] clone = Arrays.clone(parametersWithIV.getIV());
        this.d = clone;
        if (clone.length != this.f / 2) {
            throw new IllegalArgumentException("Parameter IV length must be == blockSize/2");
        }
        System.arraycopy(clone, 0, this.c, 0, clone.length);
        for (int length = this.d.length; length < this.f; length++) {
            this.c[length] = 0;
        }
        if (parametersWithIV.getParameters() != null) {
            blockCipher = this.g;
            cipherParameters = parametersWithIV.getParameters();
            blockCipher.init(true, cipherParameters);
        }
        this.i = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.b, bArr2, i2);
        return this.b;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        if (this.i) {
            byte[] bArr = this.d;
            System.arraycopy(bArr, 0, this.c, 0, bArr.length);
            for (int length = this.d.length; length < this.f; length++) {
                this.c[length] = 0;
            }
            this.h = 0;
            this.g.reset();
        }
    }
}
