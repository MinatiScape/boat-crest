package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class EAXBlockCipher implements AEADBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public SICBlockCipher f14769a;
    public boolean b;
    public int c;
    public Mac d;
    public byte[] e;
    public byte[] f;
    public byte[] g;
    public int h;
    public byte[] i;
    public int j;
    public boolean k;
    public byte[] l;

    public EAXBlockCipher(BlockCipher blockCipher) {
        this.c = blockCipher.getBlockSize();
        CMac cMac = new CMac(blockCipher);
        this.d = cMac;
        this.g = new byte[this.c];
        this.f = new byte[cMac.getMacSize()];
        this.e = new byte[this.d.getMacSize()];
        this.f14769a = new SICBlockCipher(blockCipher);
    }

    public final void a() {
        byte[] bArr = new byte[this.c];
        int i = 0;
        this.d.doFinal(bArr, 0);
        while (true) {
            byte[] bArr2 = this.g;
            if (i >= bArr2.length) {
                return;
            }
            bArr2[i] = (byte) ((this.e[i] ^ this.f[i]) ^ bArr[i]);
            i++;
        }
    }

    public final void b() {
        if (this.k) {
            return;
        }
        this.k = true;
        this.d.doFinal(this.f, 0);
        int i = this.c;
        byte[] bArr = new byte[i];
        bArr[i - 1] = 2;
        this.d.update(bArr, 0, i);
    }

    public final int c(byte b, byte[] bArr, int i) {
        int processBlock;
        byte[] bArr2 = this.i;
        int i2 = this.j;
        int i3 = i2 + 1;
        this.j = i3;
        bArr2[i2] = b;
        if (i3 == bArr2.length) {
            int length = bArr.length;
            int i4 = this.c;
            if (length >= i + i4) {
                if (this.b) {
                    processBlock = this.f14769a.processBlock(bArr2, 0, bArr, i);
                    this.d.update(bArr, i, this.c);
                } else {
                    this.d.update(bArr2, 0, i4);
                    processBlock = this.f14769a.processBlock(this.i, 0, bArr, i);
                }
                this.j = 0;
                if (!this.b) {
                    byte[] bArr3 = this.i;
                    System.arraycopy(bArr3, this.c, bArr3, 0, this.h);
                    this.j = this.h;
                }
                return processBlock;
            }
            throw new OutputLengthException("Output buffer is too short");
        }
        return 0;
    }

    public final void d(boolean z) {
        this.f14769a.reset();
        this.d.reset();
        this.j = 0;
        Arrays.fill(this.i, (byte) 0);
        if (z) {
            Arrays.fill(this.g, (byte) 0);
        }
        int i = this.c;
        byte[] bArr = new byte[i];
        bArr[i - 1] = 1;
        this.d.update(bArr, 0, i);
        this.k = false;
        byte[] bArr2 = this.l;
        if (bArr2 != null) {
            processAADBytes(bArr2, 0, bArr2.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        b();
        int i2 = this.j;
        byte[] bArr2 = this.i;
        byte[] bArr3 = new byte[bArr2.length];
        this.j = 0;
        if (this.b) {
            int i3 = i + i2;
            if (bArr.length >= this.h + i3) {
                this.f14769a.processBlock(bArr2, 0, bArr3, 0);
                System.arraycopy(bArr3, 0, bArr, i, i2);
                this.d.update(bArr3, 0, i2);
                a();
                System.arraycopy(this.g, 0, bArr, i3, this.h);
                d(false);
                return i2 + this.h;
            }
            throw new OutputLengthException("Output buffer too short");
        }
        int i4 = this.h;
        if (i2 >= i4) {
            if (bArr.length >= (i + i2) - i4) {
                if (i2 > i4) {
                    this.d.update(bArr2, 0, i2 - i4);
                    this.f14769a.processBlock(this.i, 0, bArr3, 0);
                    System.arraycopy(bArr3, 0, bArr, i, i2 - this.h);
                }
                a();
                if (e(this.i, i2 - this.h)) {
                    d(false);
                    return i2 - this.h;
                }
                throw new InvalidCipherTextException("mac check in EAX failed");
            }
            throw new OutputLengthException("Output buffer too short");
        }
        throw new InvalidCipherTextException("data too short");
    }

    public final boolean e(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.h; i3++) {
            i2 |= this.g[i3] ^ bArr[i + i3];
        }
        return i2 == 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public String getAlgorithmName() {
        return this.f14769a.getUnderlyingCipher().getAlgorithmName() + "/EAX";
    }

    public int getBlockSize() {
        return this.f14769a.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public byte[] getMac() {
        int i = this.h;
        byte[] bArr = new byte[i];
        System.arraycopy(this.g, 0, bArr, 0, i);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getOutputSize(int i) {
        int i2 = i + this.j;
        if (this.b) {
            return i2 + this.h;
        }
        int i3 = this.h;
        if (i2 < i3) {
            return 0;
        }
        return i2 - i3;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.f14769a.getUnderlyingCipher();
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getUpdateOutputSize(int i) {
        int i2 = i + this.j;
        if (!this.b) {
            int i3 = this.h;
            if (i2 < i3) {
                return 0;
            }
            i2 -= i3;
        }
        return i2 - (i2 % this.c);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] iv;
        CipherParameters parameters;
        this.b = z;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            iv = aEADParameters.getNonce();
            this.l = aEADParameters.getAssociatedText();
            this.h = aEADParameters.getMacSize() / 8;
            parameters = aEADParameters.getKey();
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameters passed to EAX");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            iv = parametersWithIV.getIV();
            this.l = null;
            this.h = this.d.getMacSize() / 2;
            parameters = parametersWithIV.getParameters();
        }
        this.i = new byte[z ? this.c : this.c + this.h];
        byte[] bArr = new byte[this.c];
        this.d.init(parameters);
        int i = this.c;
        bArr[i - 1] = 0;
        this.d.update(bArr, 0, i);
        this.d.update(iv, 0, iv.length);
        this.d.doFinal(this.e, 0);
        this.f14769a.init(true, new ParametersWithIV(null, this.e));
        reset();
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADByte(byte b) {
        if (this.k) {
            throw new IllegalStateException("AAD data cannot be added after encryption/decryption processing has begun.");
        }
        this.d.update(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (this.k) {
            throw new IllegalStateException("AAD data cannot be added after encryption/decryption processing has begun.");
        }
        this.d.update(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        b();
        return c(b, bArr, i);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        b();
        if (bArr.length >= i + i2) {
            int i4 = 0;
            for (int i5 = 0; i5 != i2; i5++) {
                i4 += c(bArr[i + i5], bArr2, i3 + i4);
            }
            return i4;
        }
        throw new DataLengthException("Input buffer too short");
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void reset() {
        d(true);
    }
}
