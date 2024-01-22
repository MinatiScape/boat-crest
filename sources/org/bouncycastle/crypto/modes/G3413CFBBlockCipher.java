package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class G3413CFBBlockCipher extends StreamBlockCipher {
    public final int b;
    public int c;
    public int d;
    public byte[] e;
    public byte[] f;
    public BlockCipher g;
    public boolean h;
    public boolean i;
    public byte[] j;
    public byte[] k;
    public int l;

    public G3413CFBBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8);
    }

    public G3413CFBBlockCipher(BlockCipher blockCipher, int i) {
        super(blockCipher);
        this.i = false;
        if (i < 0 || i > blockCipher.getBlockSize() * 8) {
            throw new IllegalArgumentException("Parameter bitBlockSize must be in range 0 < bitBlockSize <= " + (blockCipher.getBlockSize() * 8));
        }
        this.d = blockCipher.getBlockSize();
        this.g = blockCipher;
        this.b = i / 8;
        this.k = new byte[getBlockSize()];
    }

    public byte[] a() {
        byte[] b = a.b(this.e, this.d);
        byte[] bArr = new byte[b.length];
        this.g.processBlock(b, 0, bArr, 0);
        return a.b(bArr, this.b);
    }

    public void b(byte[] bArr) {
        byte[] a2 = a.a(this.e, this.c - this.b);
        System.arraycopy(a2, 0, this.e, 0, a2.length);
        System.arraycopy(bArr, 0, this.e, a2.length, this.c - a2.length);
    }

    public final void c() {
        int i = this.c;
        this.e = new byte[i];
        this.f = new byte[i];
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) {
        if (this.l == 0) {
            this.j = a();
        }
        byte[] bArr = this.j;
        int i = this.l;
        byte b2 = (byte) (bArr[i] ^ b);
        byte[] bArr2 = this.k;
        int i2 = i + 1;
        this.l = i2;
        if (this.h) {
            b = b2;
        }
        bArr2[i] = b;
        if (i2 == getBlockSize()) {
            this.l = 0;
            b(this.k);
        }
        return b2;
    }

    public final void d() {
        this.c = this.d * 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.g.getAlgorithmName() + "/CFB" + (this.d * 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.b;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        this.h = z;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            d();
            c();
            byte[] bArr = this.f;
            System.arraycopy(bArr, 0, this.e, 0, bArr.length);
            if (cipherParameters != null) {
                blockCipher = this.g;
                blockCipher.init(true, cipherParameters);
            }
            this.i = true;
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv.length < this.d) {
            throw new IllegalArgumentException("Parameter m must blockSize <= m");
        }
        this.c = iv.length;
        c();
        byte[] clone = Arrays.clone(iv);
        this.f = clone;
        System.arraycopy(clone, 0, this.e, 0, clone.length);
        if (parametersWithIV.getParameters() != null) {
            blockCipher = this.g;
            cipherParameters = parametersWithIV.getParameters();
            blockCipher.init(true, cipherParameters);
        }
        this.i = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, getBlockSize(), bArr2, i2);
        return getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        this.l = 0;
        Arrays.clear(this.k);
        Arrays.clear(this.j);
        if (this.i) {
            byte[] bArr = this.f;
            System.arraycopy(bArr, 0, this.e, 0, bArr.length);
            this.g.reset();
        }
    }
}
