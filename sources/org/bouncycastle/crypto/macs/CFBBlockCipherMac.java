package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
/* loaded from: classes12.dex */
public class CFBBlockCipherMac implements Mac {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14752a;
    public byte[] b;
    public int c;
    public a d;
    public BlockCipherPadding e;
    public int f;

    public CFBBlockCipherMac(BlockCipher blockCipher) {
        this(blockCipher, 8, (blockCipher.getBlockSize() * 8) / 2, null);
    }

    public CFBBlockCipherMac(BlockCipher blockCipher, int i, int i2) {
        this(blockCipher, i, i2, null);
    }

    public CFBBlockCipherMac(BlockCipher blockCipher, int i, int i2, BlockCipherPadding blockCipherPadding) {
        this.e = null;
        if (i2 % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        this.f14752a = new byte[blockCipher.getBlockSize()];
        a aVar = new a(blockCipher, i);
        this.d = aVar;
        this.e = blockCipherPadding;
        this.f = i2 / 8;
        this.b = new byte[aVar.b()];
        this.c = 0;
    }

    public CFBBlockCipherMac(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
        this(blockCipher, 8, (blockCipher.getBlockSize() * 8) / 2, blockCipherPadding);
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) {
        int b = this.d.b();
        BlockCipherPadding blockCipherPadding = this.e;
        if (blockCipherPadding == null) {
            while (true) {
                int i2 = this.c;
                if (i2 >= b) {
                    break;
                }
                this.b[i2] = 0;
                this.c = i2 + 1;
            }
        } else {
            blockCipherPadding.addPadding(this.b, this.c);
        }
        this.d.e(this.b, 0, this.f14752a, 0);
        this.d.c(this.f14752a);
        System.arraycopy(this.f14752a, 0, bArr, i, this.f);
        reset();
        return this.f;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.d.a();
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.f;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        reset();
        this.d.d(cipherParameters);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        int i = 0;
        while (true) {
            byte[] bArr = this.b;
            if (i >= bArr.length) {
                this.c = 0;
                this.d.f();
                return;
            }
            bArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) {
        int i = this.c;
        byte[] bArr = this.b;
        if (i == bArr.length) {
            this.d.e(bArr, 0, this.f14752a, 0);
            this.c = 0;
        }
        byte[] bArr2 = this.b;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr2[i2] = b;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int b = this.d.b();
        int i3 = this.c;
        int i4 = b - i3;
        if (i2 > i4) {
            System.arraycopy(bArr, i, this.b, i3, i4);
            this.d.e(this.b, 0, this.f14752a, 0);
            this.c = 0;
            i2 -= i4;
            i += i4;
            while (i2 > b) {
                this.d.e(bArr, i, this.f14752a, 0);
                i2 -= b;
                i += b;
            }
        }
        System.arraycopy(bArr, i, this.b, this.c, i2);
        this.c += i2;
    }
}
