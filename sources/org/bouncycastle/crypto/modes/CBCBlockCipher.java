package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class CBCBlockCipher implements BlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14766a;
    public byte[] b;
    public byte[] c;
    public int d;
    public BlockCipher e;
    public boolean f;

    public CBCBlockCipher(BlockCipher blockCipher) {
        this.e = null;
        this.e = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.d = blockSize;
        this.f14766a = new byte[blockSize];
        this.b = new byte[blockSize];
        this.c = new byte[blockSize];
    }

    public final int a(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3 = this.d;
        if (i + i3 <= bArr.length) {
            System.arraycopy(bArr, i, this.c, 0, i3);
            int processBlock = this.e.processBlock(bArr, i, bArr2, i2);
            for (int i4 = 0; i4 < this.d; i4++) {
                int i5 = i2 + i4;
                bArr2[i5] = (byte) (bArr2[i5] ^ this.b[i4]);
            }
            byte[] bArr3 = this.b;
            this.b = this.c;
            this.c = bArr3;
            return processBlock;
        }
        throw new DataLengthException("input buffer too short");
    }

    public final int b(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.d + i <= bArr.length) {
            for (int i3 = 0; i3 < this.d; i3++) {
                byte[] bArr3 = this.b;
                bArr3[i3] = (byte) (bArr3[i3] ^ bArr[i + i3]);
            }
            int processBlock = this.e.processBlock(this.b, 0, bArr2, i2);
            byte[] bArr4 = this.b;
            System.arraycopy(bArr2, i2, bArr4, 0, bArr4.length);
            return processBlock;
        }
        throw new DataLengthException("input buffer too short");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.e.getAlgorithmName() + "/CBC";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.e.getBlockSize();
    }

    public BlockCipher getUnderlyingCipher() {
        return this.e;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        boolean z2 = this.f;
        this.f = z;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            if (iv.length != this.d) {
                throw new IllegalArgumentException("initialisation vector must be the same length as block size");
            }
            System.arraycopy(iv, 0, this.f14766a, 0, iv.length);
            reset();
            if (parametersWithIV.getParameters() == null) {
                if (z2 != z) {
                    throw new IllegalArgumentException("cannot change encrypting state without providing key.");
                }
                return;
            }
            blockCipher = this.e;
            cipherParameters = parametersWithIV.getParameters();
        } else {
            reset();
            if (cipherParameters == null) {
                if (z2 != z) {
                    throw new IllegalArgumentException("cannot change encrypting state without providing key.");
                }
                return;
            }
            blockCipher = this.e;
        }
        blockCipher.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        return this.f ? b(bArr, i, bArr2, i2) : a(bArr, i, bArr2, i2);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.f14766a;
        System.arraycopy(bArr, 0, this.b, 0, bArr.length);
        Arrays.fill(this.c, (byte) 0);
        this.e.reset();
    }
}
