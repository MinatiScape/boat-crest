package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ParametersWithIV;
/* loaded from: classes12.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14765a;
    public byte[] b;
    public byte[] c;
    public int d;
    public BlockCipher e;

    public a(BlockCipher blockCipher, int i) {
        this.e = null;
        this.e = blockCipher;
        this.d = i / 8;
        this.f14765a = new byte[blockCipher.getBlockSize()];
        this.b = new byte[blockCipher.getBlockSize()];
        this.c = new byte[blockCipher.getBlockSize()];
    }

    public String a() {
        return this.e.getAlgorithmName() + "/CFB" + (this.d * 8);
    }

    public int b() {
        return this.d;
    }

    public void c(byte[] bArr) {
        this.e.processBlock(this.b, 0, bArr, 0);
    }

    public void d(CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            int length = iv.length;
            byte[] bArr = this.f14765a;
            if (length < bArr.length) {
                System.arraycopy(iv, 0, bArr, bArr.length - iv.length, iv.length);
            } else {
                System.arraycopy(iv, 0, bArr, 0, bArr.length);
            }
            f();
            blockCipher = this.e;
            cipherParameters = parametersWithIV.getParameters();
        } else {
            f();
            blockCipher = this.e;
        }
        blockCipher.init(true, cipherParameters);
    }

    public int e(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3 = this.d;
        if (i + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.e.processBlock(this.b, 0, this.c, 0);
        int i4 = 0;
        while (true) {
            int i5 = this.d;
            if (i4 >= i5) {
                byte[] bArr3 = this.b;
                System.arraycopy(bArr3, i5, bArr3, 0, bArr3.length - i5);
                byte[] bArr4 = this.b;
                int length = bArr4.length;
                int i6 = this.d;
                System.arraycopy(bArr2, i2, bArr4, length - i6, i6);
                return this.d;
            }
            bArr2[i2 + i4] = (byte) (this.c[i4] ^ bArr[i + i4]);
            i4++;
        }
    }

    public void f() {
        byte[] bArr = this.f14765a;
        System.arraycopy(bArr, 0, this.b, 0, bArr.length);
        this.e.reset();
    }
}
