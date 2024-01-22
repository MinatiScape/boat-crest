package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class G3413OFBBlockCipher extends StreamBlockCipher {
    public int b;
    public int c;
    public byte[] d;
    public byte[] e;
    public byte[] f;
    public BlockCipher g;
    public int h;
    public boolean i;

    public G3413OFBBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.i = false;
        int blockSize = blockCipher.getBlockSize();
        this.c = blockSize;
        this.g = blockCipher;
        this.f = new byte[blockSize];
    }

    public final void a() {
        byte[] a2 = a.a(this.d, this.b - this.c);
        System.arraycopy(a2, 0, this.d, 0, a2.length);
        System.arraycopy(this.f, 0, this.d, a2.length, this.b - a2.length);
    }

    public final void b() {
        this.g.processBlock(a.b(this.d, this.c), 0, this.f, 0);
    }

    public final void c() {
        int i = this.b;
        this.d = new byte[i];
        this.e = new byte[i];
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) {
        if (this.h == 0) {
            b();
        }
        byte[] bArr = this.f;
        int i = this.h;
        byte b2 = (byte) (b ^ bArr[i]);
        int i2 = i + 1;
        this.h = i2;
        if (i2 == getBlockSize()) {
            this.h = 0;
            a();
        }
        return b2;
    }

    public final void d() {
        this.b = this.c * 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.g.getAlgorithmName() + "/OFB";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.c;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            d();
            c();
            byte[] bArr = this.e;
            System.arraycopy(bArr, 0, this.d, 0, bArr.length);
            if (cipherParameters != null) {
                blockCipher = this.g;
                blockCipher.init(true, cipherParameters);
            }
            this.i = true;
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv.length < this.c) {
            throw new IllegalArgumentException("Parameter m must blockSize <= m");
        }
        this.b = iv.length;
        c();
        byte[] clone = Arrays.clone(iv);
        this.e = clone;
        System.arraycopy(clone, 0, this.d, 0, clone.length);
        if (parametersWithIV.getParameters() != null) {
            blockCipher = this.g;
            cipherParameters = parametersWithIV.getParameters();
            blockCipher.init(true, cipherParameters);
        }
        this.i = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.c, bArr2, i2);
        return this.c;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        if (this.i) {
            byte[] bArr = this.e;
            System.arraycopy(bArr, 0, this.d, 0, bArr.length);
            Arrays.clear(this.f);
            this.h = 0;
            this.g.reset();
        }
    }
}
