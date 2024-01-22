package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
/* loaded from: classes12.dex */
public class ISO9797Alg3Mac implements Mac {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14759a;
    public byte[] b;
    public int c;
    public BlockCipher d;
    public BlockCipherPadding e;
    public int f;
    public KeyParameter g;
    public KeyParameter h;

    public ISO9797Alg3Mac(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8, null);
    }

    public ISO9797Alg3Mac(BlockCipher blockCipher, int i) {
        this(blockCipher, i, null);
    }

    public ISO9797Alg3Mac(BlockCipher blockCipher, int i, BlockCipherPadding blockCipherPadding) {
        if (i % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        if (!(blockCipher instanceof DESEngine)) {
            throw new IllegalArgumentException("cipher must be instance of DESEngine");
        }
        this.d = new CBCBlockCipher(blockCipher);
        this.e = blockCipherPadding;
        this.f = i / 8;
        this.f14759a = new byte[blockCipher.getBlockSize()];
        this.b = new byte[blockCipher.getBlockSize()];
        this.c = 0;
    }

    public ISO9797Alg3Mac(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
        this(blockCipher, blockCipher.getBlockSize() * 8, blockCipherPadding);
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) {
        int blockSize = this.d.getBlockSize();
        if (this.e == null) {
            while (true) {
                int i2 = this.c;
                if (i2 >= blockSize) {
                    break;
                }
                this.b[i2] = 0;
                this.c = i2 + 1;
            }
        } else {
            if (this.c == blockSize) {
                this.d.processBlock(this.b, 0, this.f14759a, 0);
                this.c = 0;
            }
            this.e.addPadding(this.b, this.c);
        }
        this.d.processBlock(this.b, 0, this.f14759a, 0);
        DESEngine dESEngine = new DESEngine();
        dESEngine.init(false, this.g);
        byte[] bArr2 = this.f14759a;
        dESEngine.processBlock(bArr2, 0, bArr2, 0);
        dESEngine.init(true, this.h);
        byte[] bArr3 = this.f14759a;
        dESEngine.processBlock(bArr3, 0, bArr3, 0);
        System.arraycopy(this.f14759a, 0, bArr, i, this.f);
        reset();
        return this.f;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "ISO9797Alg3";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.f;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        KeyParameter keyParameter;
        reset();
        boolean z = cipherParameters instanceof KeyParameter;
        if (!z && !(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("params must be an instance of KeyParameter or ParametersWithIV");
        }
        byte[] key = (z ? (KeyParameter) cipherParameters : (KeyParameter) ((ParametersWithIV) cipherParameters).getParameters()).getKey();
        if (key.length == 16) {
            keyParameter = new KeyParameter(key, 0, 8);
            this.g = new KeyParameter(key, 8, 8);
            this.h = keyParameter;
        } else if (key.length != 24) {
            throw new IllegalArgumentException("Key must be either 112 or 168 bit long");
        } else {
            keyParameter = new KeyParameter(key, 0, 8);
            this.g = new KeyParameter(key, 8, 8);
            this.h = new KeyParameter(key, 16, 8);
        }
        if (cipherParameters instanceof ParametersWithIV) {
            this.d.init(true, new ParametersWithIV(keyParameter, ((ParametersWithIV) cipherParameters).getIV()));
        } else {
            this.d.init(true, keyParameter);
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        int i = 0;
        while (true) {
            byte[] bArr = this.b;
            if (i >= bArr.length) {
                this.c = 0;
                this.d.reset();
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
            this.d.processBlock(bArr, 0, this.f14759a, 0);
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
        int blockSize = this.d.getBlockSize();
        int i3 = this.c;
        int i4 = blockSize - i3;
        if (i2 > i4) {
            System.arraycopy(bArr, i, this.b, i3, i4);
            this.d.processBlock(this.b, 0, this.f14759a, 0);
            this.c = 0;
            i2 -= i4;
            i += i4;
            while (i2 > blockSize) {
                this.d.processBlock(bArr, i, this.f14759a, 0);
                i2 -= blockSize;
                i += blockSize;
            }
        }
        System.arraycopy(bArr, i, this.b, this.c, i2);
        this.c += i2;
    }
}
