package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class G3413CBCBlockCipher implements BlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public int f14770a;
    public int b;
    public byte[] c;
    public byte[] d;
    public BlockCipher e;
    public boolean f = false;
    public boolean g;

    public G3413CBCBlockCipher(BlockCipher blockCipher) {
        this.b = blockCipher.getBlockSize();
        this.e = blockCipher;
    }

    public final int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[] b = a.b(this.c, this.b);
        byte[] c = a.c(bArr, this.b, i);
        byte[] bArr3 = new byte[c.length];
        this.e.processBlock(c, 0, bArr3, 0);
        byte[] d = a.d(bArr3, b);
        System.arraycopy(d, 0, bArr2, i2, d.length);
        if (bArr2.length > i2 + d.length) {
            c(c);
        }
        return d.length;
    }

    public final int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[] d = a.d(a.c(bArr, this.b, i), a.b(this.c, this.b));
        int length = d.length;
        byte[] bArr3 = new byte[length];
        this.e.processBlock(d, 0, bArr3, 0);
        System.arraycopy(bArr3, 0, bArr2, i2, length);
        if (bArr2.length > i2 + d.length) {
            c(bArr3);
        }
        return length;
    }

    public final void c(byte[] bArr) {
        byte[] a2 = a.a(this.c, this.f14770a - this.b);
        System.arraycopy(a2, 0, this.c, 0, a2.length);
        System.arraycopy(bArr, 0, this.c, a2.length, this.f14770a - a2.length);
    }

    public final void d() {
        int i = this.f14770a;
        this.c = new byte[i];
        this.d = new byte[i];
    }

    public final void e() {
        this.f14770a = this.b;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.e.getAlgorithmName() + "/CBC";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.b;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        this.g = z;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            e();
            d();
            byte[] bArr = this.d;
            System.arraycopy(bArr, 0, this.c, 0, bArr.length);
            if (cipherParameters != null) {
                blockCipher = this.e;
                blockCipher.init(z, cipherParameters);
            }
            this.f = true;
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv.length < this.b) {
            throw new IllegalArgumentException("Parameter m must blockSize <= m");
        }
        this.f14770a = iv.length;
        d();
        byte[] clone = Arrays.clone(iv);
        this.d = clone;
        System.arraycopy(clone, 0, this.c, 0, clone.length);
        if (parametersWithIV.getParameters() != null) {
            blockCipher = this.e;
            cipherParameters = parametersWithIV.getParameters();
            blockCipher.init(z, cipherParameters);
        }
        this.f = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        return this.g ? b(bArr, i, bArr2, i2) : a(bArr, i, bArr2, i2);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        if (this.f) {
            byte[] bArr = this.d;
            System.arraycopy(bArr, 0, this.c, 0, bArr.length);
            this.e.reset();
        }
    }
}
