package org.bouncycastle.util.test;

import java.security.SecureRandom;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.EntropySourceProvider;
/* loaded from: classes13.dex */
public class TestRandomEntropySourceProvider implements EntropySourceProvider {

    /* renamed from: a  reason: collision with root package name */
    public final SecureRandom f15409a = new SecureRandom();
    public final boolean b;

    /* loaded from: classes13.dex */
    public class a implements EntropySource {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f15410a;

        public a(int i) {
            this.f15410a = i;
        }

        @Override // org.bouncycastle.crypto.prng.EntropySource
        public int entropySize() {
            return this.f15410a;
        }

        @Override // org.bouncycastle.crypto.prng.EntropySource
        public byte[] getEntropy() {
            byte[] bArr = new byte[(this.f15410a + 7) / 8];
            TestRandomEntropySourceProvider.this.f15409a.nextBytes(bArr);
            return bArr;
        }

        @Override // org.bouncycastle.crypto.prng.EntropySource
        public boolean isPredictionResistant() {
            return TestRandomEntropySourceProvider.this.b;
        }
    }

    public TestRandomEntropySourceProvider(boolean z) {
        this.b = z;
    }

    @Override // org.bouncycastle.crypto.prng.EntropySourceProvider
    public EntropySource get(int i) {
        return new a(i);
    }
}
