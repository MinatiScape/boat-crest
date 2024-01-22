package org.bouncycastle.crypto.prng;

import org.bouncycastle.crypto.Digest;
/* loaded from: classes13.dex */
public class DigestRandomGenerator implements RandomGenerator {
    public static long f = 10;
    public Digest c;
    public byte[] d;
    public byte[] e;
    public long b = 1;

    /* renamed from: a  reason: collision with root package name */
    public long f14813a = 1;

    public DigestRandomGenerator(Digest digest) {
        this.c = digest;
        this.e = new byte[digest.getDigestSize()];
        this.d = new byte[digest.getDigestSize()];
    }

    public final void a() {
        d(this.e);
        long j = this.b;
        this.b = 1 + j;
        b(j);
        c(this.e);
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(long j) {
        synchronized (this) {
            b(j);
            d(this.e);
            c(this.e);
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(byte[] bArr) {
        synchronized (this) {
            d(bArr);
            d(this.e);
            c(this.e);
        }
    }

    public final void b(long j) {
        for (int i = 0; i != 8; i++) {
            this.c.update((byte) j);
            j >>>= 8;
        }
    }

    public final void c(byte[] bArr) {
        this.c.doFinal(bArr, 0);
    }

    public final void d(byte[] bArr) {
        this.c.update(bArr, 0, bArr.length);
    }

    public final void e() {
        long j = this.f14813a;
        this.f14813a = 1 + j;
        b(j);
        d(this.d);
        d(this.e);
        c(this.d);
        if (this.f14813a % f == 0) {
            a();
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr) {
        nextBytes(bArr, 0, bArr.length);
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr, int i, int i2) {
        synchronized (this) {
            e();
            int i3 = i2 + i;
            int i4 = 0;
            while (i != i3) {
                if (i4 == this.d.length) {
                    e();
                    i4 = 0;
                }
                bArr[i] = this.d[i4];
                i++;
                i4++;
            }
        }
    }
}
