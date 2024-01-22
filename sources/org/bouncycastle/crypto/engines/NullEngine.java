package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
/* loaded from: classes12.dex */
public class NullEngine implements BlockCipher {
    public static final int DEFAULT_BLOCK_SIZE = 1;

    /* renamed from: a  reason: collision with root package name */
    public boolean f14690a;
    public final int b;

    public NullEngine() {
        this(1);
    }

    public NullEngine(int i) {
        this.b = i;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Null";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.b;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.f14690a = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (!this.f14690a) {
            throw new IllegalStateException("Null engine not initialised");
        }
        int i3 = this.b;
        if (i + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i4 = 0;
        while (true) {
            int i5 = this.b;
            if (i4 >= i5) {
                return i5;
            }
            bArr2[i2 + i4] = bArr[i + i4];
            i4++;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
