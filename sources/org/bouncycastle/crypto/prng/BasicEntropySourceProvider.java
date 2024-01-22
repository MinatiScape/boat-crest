package org.bouncycastle.crypto.prng;

import java.security.SecureRandom;
/* loaded from: classes13.dex */
public class BasicEntropySourceProvider implements EntropySourceProvider {

    /* renamed from: a  reason: collision with root package name */
    public final SecureRandom f14811a;
    public final boolean b;

    /* loaded from: classes13.dex */
    public class a implements EntropySource {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f14812a;

        public a(int i) {
            this.f14812a = i;
        }

        @Override // org.bouncycastle.crypto.prng.EntropySource
        public int entropySize() {
            return this.f14812a;
        }

        @Override // org.bouncycastle.crypto.prng.EntropySource
        public byte[] getEntropy() {
            if ((BasicEntropySourceProvider.this.f14811a instanceof SP800SecureRandom) || (BasicEntropySourceProvider.this.f14811a instanceof X931SecureRandom)) {
                byte[] bArr = new byte[(this.f14812a + 7) / 8];
                BasicEntropySourceProvider.this.f14811a.nextBytes(bArr);
                return bArr;
            }
            return BasicEntropySourceProvider.this.f14811a.generateSeed((this.f14812a + 7) / 8);
        }

        @Override // org.bouncycastle.crypto.prng.EntropySource
        public boolean isPredictionResistant() {
            return BasicEntropySourceProvider.this.b;
        }
    }

    public BasicEntropySourceProvider(SecureRandom secureRandom, boolean z) {
        this.f14811a = secureRandom;
        this.b = z;
    }

    @Override // org.bouncycastle.crypto.prng.EntropySourceProvider
    public EntropySource get(int i) {
        return new a(i);
    }
}
