package org.bouncycastle.crypto.prng.drbg;

import java.util.Hashtable;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
/* loaded from: classes13.dex */
public class HashSP800DRBG implements SP80090DRBG {
    public static final byte[] h = {1};
    public static final Hashtable i;

    /* renamed from: a  reason: collision with root package name */
    public Digest f14826a;
    public byte[] b;
    public byte[] c;
    public long d;
    public EntropySource e;
    public int f;
    public int g;

    static {
        Hashtable hashtable = new Hashtable();
        i = hashtable;
        hashtable.put("SHA-1", Integers.valueOf(440));
        hashtable.put("SHA-224", Integers.valueOf(440));
        hashtable.put("SHA-256", Integers.valueOf(440));
        hashtable.put(MessageDigestAlgorithms.SHA_512_256, Integers.valueOf(440));
        hashtable.put(MessageDigestAlgorithms.SHA_512_224, Integers.valueOf(440));
        hashtable.put("SHA-384", Integers.valueOf(888));
        hashtable.put("SHA-512", Integers.valueOf(888));
    }

    public HashSP800DRBG(Digest digest, int i2, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        if (i2 > a.a(digest)) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        }
        if (entropySource.entropySize() < i2) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        this.f14826a = digest;
        this.e = entropySource;
        this.f = i2;
        this.g = ((Integer) i.get(digest.getAlgorithmName())).intValue();
        byte[] c = a.c(this.f14826a, Arrays.concatenate(c(), bArr2, bArr), this.g);
        this.b = c;
        byte[] bArr3 = new byte[c.length + 1];
        System.arraycopy(c, 0, bArr3, 1, c.length);
        this.c = a.c(this.f14826a, bArr3, this.g);
        this.d = 1L;
    }

    public final void a(byte[] bArr, byte[] bArr2) {
        int i2 = 0;
        for (int i3 = 1; i3 <= bArr2.length; i3++) {
            int i4 = (bArr[bArr.length - i3] & 255) + (bArr2[bArr2.length - i3] & 255) + i2;
            i2 = i4 > 255 ? 1 : 0;
            bArr[bArr.length - i3] = (byte) i4;
        }
        for (int length = bArr2.length + 1; length <= bArr.length; length++) {
            int i5 = (bArr[bArr.length - length] & 255) + i2;
            i2 = i5 > 255 ? 1 : 0;
            bArr[bArr.length - length] = (byte) i5;
        }
    }

    public final void b(byte[] bArr, byte[] bArr2) {
        this.f14826a.update(bArr, 0, bArr.length);
        this.f14826a.doFinal(bArr2, 0);
    }

    public final byte[] c() {
        byte[] entropy = this.e.getEntropy();
        if (entropy.length >= (this.f + 7) / 8) {
            return entropy;
        }
        throw new IllegalStateException("Insufficient entropy provided by entropy source");
    }

    public final byte[] d(byte[] bArr) {
        byte[] bArr2 = new byte[this.f14826a.getDigestSize()];
        b(bArr, bArr2);
        return bArr2;
    }

    public final byte[] e(byte[] bArr, int i2) {
        int i3 = i2 / 8;
        int digestSize = i3 / this.f14826a.getDigestSize();
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        byte[] bArr3 = new byte[i3];
        int digestSize2 = this.f14826a.getDigestSize();
        byte[] bArr4 = new byte[digestSize2];
        for (int i4 = 0; i4 <= digestSize; i4++) {
            b(bArr2, bArr4);
            int i5 = i4 * digestSize2;
            int i6 = i3 - i5;
            if (i6 > digestSize2) {
                i6 = digestSize2;
            }
            System.arraycopy(bArr4, 0, bArr3, i5, i6);
            a(bArr2, h);
        }
        return bArr3;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int generate(byte[] bArr, byte[] bArr2, boolean z) {
        long j;
        int length = bArr.length * 8;
        if (length <= 262144) {
            if (this.d > 140737488355328L) {
                return -1;
            }
            if (z) {
                reseed(bArr2);
                bArr2 = null;
            }
            if (bArr2 != null) {
                byte[] bArr3 = this.b;
                byte[] bArr4 = new byte[bArr3.length + 1 + bArr2.length];
                bArr4[0] = 2;
                System.arraycopy(bArr3, 0, bArr4, 1, bArr3.length);
                System.arraycopy(bArr2, 0, bArr4, this.b.length + 1, bArr2.length);
                a(this.b, d(bArr4));
            }
            byte[] e = e(this.b, length);
            byte[] bArr5 = this.b;
            byte[] bArr6 = new byte[bArr5.length + 1];
            System.arraycopy(bArr5, 0, bArr6, 1, bArr5.length);
            bArr6[0] = 3;
            a(this.b, d(bArr6));
            a(this.b, this.c);
            a(this.b, new byte[]{(byte) (j >> 24), (byte) (j >> 16), (byte) (j >> 8), (byte) this.d});
            this.d++;
            System.arraycopy(e, 0, bArr, 0, bArr.length);
            return length;
        }
        throw new IllegalArgumentException("Number of bits per request limited to 262144");
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int getBlockSize() {
        return this.f14826a.getDigestSize() * 8;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public void reseed(byte[] bArr) {
        byte[] c = a.c(this.f14826a, Arrays.concatenate(h, this.b, c(), bArr), this.g);
        this.b = c;
        byte[] bArr2 = new byte[c.length + 1];
        bArr2[0] = 0;
        System.arraycopy(c, 0, bArr2, 1, c.length);
        this.c = a.c(this.f14826a, bArr2, this.g);
        this.d = 1L;
    }
}
