package org.bouncycastle.crypto.prng.drbg;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;
import org.jose4j.keys.AesKey;
/* loaded from: classes13.dex */
public class CTRSP800DRBG implements SP80090DRBG {
    public static final byte[] j = Hex.decode("000102030405060708090A0B0C0D0E0F101112131415161718191A1B1C1D1E1F");

    /* renamed from: a  reason: collision with root package name */
    public EntropySource f14822a;
    public BlockCipher b;
    public int c;
    public int d;
    public int e;
    public byte[] f;
    public byte[] g;
    public long h = 0;
    public boolean i;

    public CTRSP800DRBG(BlockCipher blockCipher, int i, int i2, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        this.i = false;
        this.f14822a = entropySource;
        this.b = blockCipher;
        this.c = i;
        this.e = i2;
        this.d = (blockCipher.getBlockSize() * 8) + i;
        this.i = l(blockCipher);
        if (i2 > 256) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        }
        if (k(blockCipher, i) < i2) {
            throw new IllegalArgumentException("Requested security strength is not supported by block cipher and key size");
        }
        if (entropySource.entropySize() < i2) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        c(j(), bArr2, bArr);
    }

    public final void a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int blockSize = this.b.getBlockSize();
        byte[] bArr5 = new byte[blockSize];
        int length = bArr4.length / blockSize;
        byte[] bArr6 = new byte[blockSize];
        this.b.init(true, new KeyParameter(i(bArr2)));
        this.b.processBlock(bArr3, 0, bArr5, 0);
        for (int i = 0; i < length; i++) {
            f(bArr6, bArr5, bArr4, i * blockSize);
            this.b.processBlock(bArr6, 0, bArr5, 0);
        }
        System.arraycopy(bArr5, 0, bArr, 0, bArr.length);
    }

    public final byte[] b(byte[] bArr, int i) {
        int blockSize = this.b.getBlockSize();
        int length = bArr.length;
        int i2 = i / 8;
        int i3 = length + 8;
        byte[] bArr2 = new byte[((((i3 + 1) + blockSize) - 1) / blockSize) * blockSize];
        h(bArr2, length, 0);
        h(bArr2, i2, 4);
        System.arraycopy(bArr, 0, bArr2, 8, length);
        bArr2[i3] = Byte.MIN_VALUE;
        int i4 = this.c;
        int i5 = (i4 / 8) + blockSize;
        byte[] bArr3 = new byte[i5];
        byte[] bArr4 = new byte[blockSize];
        byte[] bArr5 = new byte[blockSize];
        int i6 = i4 / 8;
        byte[] bArr6 = new byte[i6];
        System.arraycopy(j, 0, bArr6, 0, i6);
        int i7 = 0;
        while (true) {
            int i8 = i7 * blockSize;
            if (i8 * 8 >= this.c + (blockSize * 8)) {
                break;
            }
            h(bArr5, i7, 0);
            a(bArr4, bArr6, bArr5, bArr2);
            int i9 = i5 - i8;
            if (i9 > blockSize) {
                i9 = blockSize;
            }
            System.arraycopy(bArr4, 0, bArr3, i8, i9);
            i7++;
        }
        byte[] bArr7 = new byte[blockSize];
        System.arraycopy(bArr3, 0, bArr6, 0, i6);
        System.arraycopy(bArr3, i6, bArr7, 0, blockSize);
        byte[] bArr8 = new byte[i2];
        this.b.init(true, new KeyParameter(i(bArr6)));
        int i10 = 0;
        while (true) {
            int i11 = i10 * blockSize;
            if (i11 >= i2) {
                return bArr8;
            }
            this.b.processBlock(bArr7, 0, bArr7, 0);
            int i12 = i2 - i11;
            if (i12 > blockSize) {
                i12 = blockSize;
            }
            System.arraycopy(bArr7, 0, bArr8, i11, i12);
            i10++;
        }
    }

    public final void c(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] b = b(Arrays.concatenate(bArr, bArr2, bArr3), this.d);
        int blockSize = this.b.getBlockSize();
        byte[] bArr4 = new byte[(this.c + 7) / 8];
        this.f = bArr4;
        byte[] bArr5 = new byte[blockSize];
        this.g = bArr5;
        e(b, bArr4, bArr5);
        this.h = 1L;
    }

    public final void d(byte[] bArr) {
        e(b(Arrays.concatenate(j(), bArr), this.d), this.f, this.g);
        this.h = 1L;
    }

    public final void e(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int length = bArr.length;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[this.b.getBlockSize()];
        int blockSize = this.b.getBlockSize();
        this.b.init(true, new KeyParameter(i(bArr2)));
        int i = 0;
        while (true) {
            int i2 = i * blockSize;
            if (i2 >= bArr.length) {
                f(bArr4, bArr, bArr4, 0);
                System.arraycopy(bArr4, 0, bArr2, 0, bArr2.length);
                System.arraycopy(bArr4, bArr2.length, bArr3, 0, bArr3.length);
                return;
            }
            g(bArr3);
            this.b.processBlock(bArr3, 0, bArr5, 0);
            int i3 = length - i2;
            if (i3 > blockSize) {
                i3 = blockSize;
            }
            System.arraycopy(bArr5, 0, bArr4, i2, i3);
            i++;
        }
    }

    public final void f(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) (bArr2[i2] ^ bArr3[i2 + i]);
        }
    }

    public final void g(byte[] bArr) {
        int i = 1;
        for (int i2 = 1; i2 <= bArr.length; i2++) {
            int i3 = (bArr[bArr.length - i2] & 255) + i;
            i = i3 > 255 ? 1 : 0;
            bArr[bArr.length - i2] = (byte) i3;
        }
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int generate(byte[] bArr, byte[] bArr2, boolean z) {
        byte[] bArr3;
        boolean z2 = this.i;
        long j2 = this.h;
        if (z2) {
            if (j2 > 2147483648L) {
                return -1;
            }
            if (a.d(bArr, 512)) {
                throw new IllegalArgumentException("Number of bits per request limited to 4096");
            }
        } else if (j2 > 140737488355328L) {
            return -1;
        } else {
            if (a.d(bArr, 32768)) {
                throw new IllegalArgumentException("Number of bits per request limited to 262144");
            }
        }
        if (z) {
            d(bArr2);
            bArr2 = null;
        }
        if (bArr2 != null) {
            bArr3 = b(bArr2, this.d);
            e(bArr3, this.f, this.g);
        } else {
            bArr3 = new byte[this.d];
        }
        int length = this.g.length;
        byte[] bArr4 = new byte[length];
        this.b.init(true, new KeyParameter(i(this.f)));
        for (int i = 0; i <= bArr.length / length; i++) {
            int i2 = i * length;
            int length2 = bArr.length - i2 > length ? length : bArr.length - (this.g.length * i);
            if (length2 != 0) {
                g(this.g);
                this.b.processBlock(this.g, 0, bArr4, 0);
                System.arraycopy(bArr4, 0, bArr, i2, length2);
            }
        }
        e(bArr3, this.f, this.g);
        this.h++;
        return bArr.length * 8;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int getBlockSize() {
        return this.g.length * 8;
    }

    public final void h(byte[] bArr, int i, int i2) {
        bArr[i2 + 0] = (byte) (i >> 24);
        bArr[i2 + 1] = (byte) (i >> 16);
        bArr[i2 + 2] = (byte) (i >> 8);
        bArr[i2 + 3] = (byte) i;
    }

    public byte[] i(byte[] bArr) {
        if (this.i) {
            byte[] bArr2 = new byte[24];
            m(bArr, 0, bArr2, 0);
            m(bArr, 7, bArr2, 8);
            m(bArr, 14, bArr2, 16);
            return bArr2;
        }
        return bArr;
    }

    public final byte[] j() {
        byte[] entropy = this.f14822a.getEntropy();
        if (entropy.length >= (this.e + 7) / 8) {
            return entropy;
        }
        throw new IllegalStateException("Insufficient entropy provided by entropy source");
    }

    public final int k(BlockCipher blockCipher, int i) {
        if (l(blockCipher) && i == 168) {
            return 112;
        }
        if (blockCipher.getAlgorithmName().equals(AesKey.ALGORITHM)) {
            return i;
        }
        return -1;
    }

    public final boolean l(BlockCipher blockCipher) {
        return blockCipher.getAlgorithmName().equals("DESede") || blockCipher.getAlgorithmName().equals("TDEA");
    }

    public final void m(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = i + 0;
        bArr2[i2 + 0] = (byte) (bArr[i3] & 254);
        int i4 = i + 1;
        bArr2[i2 + 1] = (byte) ((bArr[i3] << 7) | ((bArr[i4] & 252) >>> 1));
        int i5 = i + 2;
        bArr2[i2 + 2] = (byte) ((bArr[i4] << 6) | ((bArr[i5] & 248) >>> 2));
        int i6 = i + 3;
        bArr2[i2 + 3] = (byte) ((bArr[i5] << 5) | ((bArr[i6] & 240) >>> 3));
        int i7 = i + 4;
        bArr2[i2 + 4] = (byte) ((bArr[i6] << 4) | ((bArr[i7] & 224) >>> 4));
        int i8 = i + 5;
        bArr2[i2 + 5] = (byte) ((bArr[i7] << 3) | ((bArr[i8] & 192) >>> 5));
        int i9 = i + 6;
        bArr2[i2 + 6] = (byte) ((bArr[i8] << 2) | ((bArr[i9] & 128) >>> 6));
        int i10 = i2 + 7;
        bArr2[i10] = (byte) (bArr[i9] << 1);
        while (i2 <= i10) {
            byte b = bArr2[i2];
            bArr2[i2] = (byte) (((((b >> 7) ^ ((((((b >> 1) ^ (b >> 2)) ^ (b >> 3)) ^ (b >> 4)) ^ (b >> 5)) ^ (b >> 6))) ^ 1) & 1) | (b & 254));
            i2++;
        }
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public void reseed(byte[] bArr) {
        d(bArr);
    }
}
