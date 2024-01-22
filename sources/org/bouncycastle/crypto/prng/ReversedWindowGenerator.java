package org.bouncycastle.crypto.prng;
/* loaded from: classes13.dex */
public class ReversedWindowGenerator implements RandomGenerator {

    /* renamed from: a  reason: collision with root package name */
    public final RandomGenerator f14814a;
    public byte[] b;
    public int c;

    public ReversedWindowGenerator(RandomGenerator randomGenerator, int i) {
        if (randomGenerator == null) {
            throw new IllegalArgumentException("generator cannot be null");
        }
        if (i < 2) {
            throw new IllegalArgumentException("windowSize must be at least 2");
        }
        this.f14814a = randomGenerator;
        this.b = new byte[i];
    }

    public final void a(byte[] bArr, int i, int i2) {
        synchronized (this) {
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.c < 1) {
                    RandomGenerator randomGenerator = this.f14814a;
                    byte[] bArr2 = this.b;
                    randomGenerator.nextBytes(bArr2, 0, bArr2.length);
                    this.c = this.b.length;
                }
                byte[] bArr3 = this.b;
                int i4 = this.c - 1;
                this.c = i4;
                bArr[i3 + i] = bArr3[i4];
            }
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(long j) {
        synchronized (this) {
            this.c = 0;
            this.f14814a.addSeedMaterial(j);
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(byte[] bArr) {
        synchronized (this) {
            this.c = 0;
            this.f14814a.addSeedMaterial(bArr);
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr) {
        a(bArr, 0, bArr.length);
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr, int i, int i2) {
        a(bArr, i, i2);
    }
}
