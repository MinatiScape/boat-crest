package org.bouncycastle.crypto.modes;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier;
import org.bouncycastle.crypto.modes.kgcm.Tables16kKGCMMultiplier_512;
import org.bouncycastle.crypto.modes.kgcm.Tables4kKGCMMultiplier_128;
import org.bouncycastle.crypto.modes.kgcm.Tables8kKGCMMultiplier_256;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public class KGCMBlockCipher implements AEADBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public BlockCipher f14773a;
    public BufferedBlockCipher b;
    public boolean d;
    public byte[] e;
    public byte[] f;
    public byte[] g;
    public KGCMMultiplier h;
    public long[] i;
    public final int j;
    public a k = new a(this);
    public a l = new a(this);
    public int c = -1;

    /* loaded from: classes13.dex */
    public class a extends ByteArrayOutputStream {
        public a(KGCMBlockCipher kGCMBlockCipher) {
        }

        public byte[] a() {
            return ((ByteArrayOutputStream) this).buf;
        }
    }

    public KGCMBlockCipher(BlockCipher blockCipher) {
        this.f14773a = blockCipher;
        this.b = new BufferedBlockCipher(new KCTRBlockCipher(blockCipher));
        int blockSize = this.f14773a.getBlockSize();
        this.j = blockSize;
        this.e = new byte[blockSize];
        this.g = new byte[blockSize];
        this.h = b(blockSize);
        this.i = new long[blockSize >>> 3];
        this.f = null;
    }

    public static KGCMMultiplier b(int i) {
        if (i != 16) {
            if (i != 32) {
                if (i == 64) {
                    return new Tables16kKGCMMultiplier_512();
                }
                throw new IllegalArgumentException("Only 128, 256, and 512 -bit block sizes supported");
            }
            return new Tables8kKGCMMultiplier_256();
        }
        return new Tables4kKGCMMultiplier_128();
    }

    public static void d(long[] jArr, byte[] bArr, int i) {
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = jArr[i2] ^ Pack.littleEndianToLong(bArr, i);
            i += 8;
        }
    }

    public final void a(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i2;
        while (i < i4) {
            d(this.i, bArr, i);
            this.h.multiplyH(this.i);
            i += this.j;
        }
        long[] jArr = this.i;
        jArr[0] = ((i3 & 4294967295L) << 3) ^ jArr[0];
        int i5 = this.j >>> 4;
        jArr[i5] = jArr[i5] ^ ((4294967295L & i2) << 3);
        byte[] longToLittleEndian = Pack.longToLittleEndian(jArr);
        this.f = longToLittleEndian;
        this.f14773a.processBlock(longToLittleEndian, 0, longToLittleEndian, 0);
    }

    public final void c(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            d(this.i, bArr, i);
            this.h.multiplyH(this.i);
            i += this.j;
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        int doFinal;
        int size = this.l.size();
        if (this.d || size >= this.c) {
            byte[] bArr2 = new byte[this.j];
            this.f14773a.processBlock(bArr2, 0, bArr2, 0);
            long[] jArr = new long[this.j >>> 3];
            Pack.littleEndianToLong(bArr2, 0, jArr);
            this.h.init(jArr);
            Arrays.fill(bArr2, (byte) 0);
            Arrays.fill(jArr, 0L);
            int size2 = this.k.size();
            if (size2 > 0) {
                c(this.k.a(), 0, size2);
            }
            if (!this.d) {
                int i2 = size - this.c;
                if (bArr.length - i < i2) {
                    throw new OutputLengthException("Output buffer too short");
                }
                a(this.l.a(), 0, i2, size2);
                int processBytes = this.b.processBytes(this.l.a(), 0, i2, bArr, i);
                doFinal = processBytes + this.b.doFinal(bArr, i + processBytes);
            } else if ((bArr.length - i) - this.c < size) {
                throw new OutputLengthException("Output buffer too short");
            } else {
                int processBytes2 = this.b.processBytes(this.l.a(), 0, size, bArr, i);
                doFinal = processBytes2 + this.b.doFinal(bArr, i + processBytes2);
                a(bArr, i, size, size2);
            }
            byte[] bArr3 = this.f;
            if (bArr3 != null) {
                if (this.d) {
                    System.arraycopy(bArr3, 0, bArr, i + doFinal, this.c);
                    reset();
                    return doFinal + this.c;
                }
                byte[] bArr4 = new byte[this.c];
                byte[] a2 = this.l.a();
                int i3 = this.c;
                System.arraycopy(a2, size - i3, bArr4, 0, i3);
                int i4 = this.c;
                byte[] bArr5 = new byte[i4];
                System.arraycopy(this.f, 0, bArr5, 0, i4);
                if (Arrays.constantTimeAreEqual(bArr4, bArr5)) {
                    reset();
                    return doFinal;
                }
                throw new InvalidCipherTextException("mac verification failed");
            }
            throw new IllegalStateException("mac is not calculated");
        }
        throw new InvalidCipherTextException("data too short");
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public String getAlgorithmName() {
        return this.f14773a.getAlgorithmName() + "/KGCM";
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public byte[] getMac() {
        int i = this.c;
        byte[] bArr = new byte[i];
        System.arraycopy(this.f, 0, bArr, 0, i);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getOutputSize(int i) {
        int size = i + this.l.size();
        if (this.d) {
            return size + this.c;
        }
        int i2 = this.c;
        if (size < i2) {
            return 0;
        }
        return size - i2;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.f14773a;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getUpdateOutputSize(int i) {
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        KeyParameter keyParameter;
        this.d = z;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            byte[] nonce = aEADParameters.getNonce();
            byte[] bArr = this.g;
            Arrays.fill(bArr, (byte) 0);
            System.arraycopy(nonce, 0, this.g, bArr.length - nonce.length, nonce.length);
            this.e = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize < 64 || macSize > (this.j << 3) || (macSize & 7) != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
            this.c = macSize >>> 3;
            keyParameter = aEADParameters.getKey();
            byte[] bArr2 = this.e;
            if (bArr2 != null) {
                processAADBytes(bArr2, 0, bArr2.length);
            }
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Invalid parameter passed");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            byte[] bArr3 = this.g;
            Arrays.fill(bArr3, (byte) 0);
            System.arraycopy(iv, 0, this.g, bArr3.length - iv.length, iv.length);
            this.e = null;
            this.c = this.j;
            keyParameter = (KeyParameter) parametersWithIV.getParameters();
        }
        this.f = new byte[this.j];
        this.b.init(true, new ParametersWithIV(keyParameter, this.g));
        this.f14773a.init(true, keyParameter);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADByte(byte b) {
        this.k.write(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        this.k.write(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        this.l.write(b);
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException, IllegalStateException {
        if (bArr.length >= i + i2) {
            this.l.write(bArr, i, i2);
            return 0;
        }
        throw new DataLengthException("input buffer too short");
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void reset() {
        Arrays.fill(this.i, 0L);
        this.f14773a.reset();
        this.l.reset();
        this.k.reset();
        byte[] bArr = this.e;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }
}
