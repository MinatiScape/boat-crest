package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class KCTRBlockCipher extends StreamBlockCipher {
    public byte[] b;
    public byte[] c;
    public byte[] d;
    public int e;
    public boolean f;
    public BlockCipher g;

    public KCTRBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.g = blockCipher;
        this.b = new byte[blockCipher.getBlockSize()];
        this.c = new byte[blockCipher.getBlockSize()];
        this.d = new byte[blockCipher.getBlockSize()];
    }

    public final void a() {
    }

    public final void b(int i) {
        while (true) {
            byte[] bArr = this.c;
            if (i >= bArr.length) {
                return;
            }
            int i2 = i + 1;
            byte b = (byte) (bArr[i] + 1);
            bArr[i] = b;
            if (b != 0) {
                return;
            }
            i = i2;
        }
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) {
        int i = this.e;
        if (i == 0) {
            b(0);
            a();
            this.g.processBlock(this.c, 0, this.d, 0);
            byte[] bArr = this.d;
            int i2 = this.e;
            this.e = i2 + 1;
            return (byte) (b ^ bArr[i2]);
        }
        byte[] bArr2 = this.d;
        int i3 = i + 1;
        this.e = i3;
        byte b2 = (byte) (b ^ bArr2[i]);
        if (i3 == this.c.length) {
            this.e = 0;
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.g.getAlgorithmName() + "/KCTR";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.g.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.f = true;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameter passed");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        byte[] bArr = this.b;
        Arrays.fill(bArr, (byte) 0);
        System.arraycopy(iv, 0, this.b, bArr.length - iv.length, iv.length);
        CipherParameters parameters = parametersWithIV.getParameters();
        if (parameters != null) {
            this.g.init(true, parameters);
        }
        reset();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (bArr.length - i >= getBlockSize()) {
            if (bArr2.length - i2 >= getBlockSize()) {
                processBytes(bArr, i, getBlockSize(), bArr2, i2);
                return getBlockSize();
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too short");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        if (this.f) {
            this.g.processBlock(this.b, 0, this.c, 0);
        }
        this.g.reset();
        this.e = 0;
    }
}
