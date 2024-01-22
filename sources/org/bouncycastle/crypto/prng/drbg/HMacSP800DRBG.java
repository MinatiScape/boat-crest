package org.bouncycastle.crypto.prng.drbg;

import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class HMacSP800DRBG implements SP80090DRBG {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14825a;
    public byte[] b;
    public long c;
    public EntropySource d;
    public Mac e;
    public int f;

    public HMacSP800DRBG(Mac mac, int i, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        if (i > a.b(mac)) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        }
        if (entropySource.entropySize() < i) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        this.f = i;
        this.d = entropySource;
        this.e = mac;
        byte[] concatenate = Arrays.concatenate(a(), bArr2, bArr);
        byte[] bArr3 = new byte[mac.getMacSize()];
        this.f14825a = bArr3;
        byte[] bArr4 = new byte[bArr3.length];
        this.b = bArr4;
        Arrays.fill(bArr4, (byte) 1);
        b(concatenate);
        this.c = 1L;
    }

    public final byte[] a() {
        byte[] entropy = this.d.getEntropy();
        if (entropy.length >= (this.f + 7) / 8) {
            return entropy;
        }
        throw new IllegalStateException("Insufficient entropy provided by entropy source");
    }

    public final void b(byte[] bArr) {
        c(bArr, (byte) 0);
        if (bArr != null) {
            c(bArr, (byte) 1);
        }
    }

    public final void c(byte[] bArr, byte b) {
        this.e.init(new KeyParameter(this.f14825a));
        Mac mac = this.e;
        byte[] bArr2 = this.b;
        mac.update(bArr2, 0, bArr2.length);
        this.e.update(b);
        if (bArr != null) {
            this.e.update(bArr, 0, bArr.length);
        }
        this.e.doFinal(this.f14825a, 0);
        this.e.init(new KeyParameter(this.f14825a));
        Mac mac2 = this.e;
        byte[] bArr3 = this.b;
        mac2.update(bArr3, 0, bArr3.length);
        this.e.doFinal(this.b, 0);
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int generate(byte[] bArr, byte[] bArr2, boolean z) {
        int length = bArr.length * 8;
        if (length <= 262144) {
            if (this.c > 140737488355328L) {
                return -1;
            }
            if (z) {
                reseed(bArr2);
                bArr2 = null;
            }
            if (bArr2 != null) {
                b(bArr2);
            }
            int length2 = bArr.length;
            byte[] bArr3 = new byte[length2];
            int length3 = bArr.length / this.b.length;
            this.e.init(new KeyParameter(this.f14825a));
            for (int i = 0; i < length3; i++) {
                Mac mac = this.e;
                byte[] bArr4 = this.b;
                mac.update(bArr4, 0, bArr4.length);
                this.e.doFinal(this.b, 0);
                byte[] bArr5 = this.b;
                System.arraycopy(bArr5, 0, bArr3, bArr5.length * i, bArr5.length);
            }
            byte[] bArr6 = this.b;
            if (bArr6.length * length3 < length2) {
                this.e.update(bArr6, 0, bArr6.length);
                this.e.doFinal(this.b, 0);
                byte[] bArr7 = this.b;
                System.arraycopy(bArr7, 0, bArr3, bArr7.length * length3, length2 - (length3 * bArr7.length));
            }
            b(bArr2);
            this.c++;
            System.arraycopy(bArr3, 0, bArr, 0, bArr.length);
            return length;
        }
        throw new IllegalArgumentException("Number of bits per request limited to 262144");
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int getBlockSize() {
        return this.b.length * 8;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public void reseed(byte[] bArr) {
        b(Arrays.concatenate(a(), bArr));
        this.c = 1L;
    }
}
