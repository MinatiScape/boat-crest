package org.bouncycastle.crypto.prng;

import java.security.SecureRandom;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.prng.drbg.CTRSP800DRBG;
import org.bouncycastle.crypto.prng.drbg.HMacSP800DRBG;
import org.bouncycastle.crypto.prng.drbg.HashSP800DRBG;
import org.bouncycastle.crypto.prng.drbg.SP80090DRBG;
/* loaded from: classes13.dex */
public class SP800SecureRandomBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final SecureRandom f14815a;
    public final EntropySourceProvider b;
    public byte[] c;
    public int d;
    public int e;

    /* loaded from: classes13.dex */
    public static class a implements org.bouncycastle.crypto.prng.a {

        /* renamed from: a  reason: collision with root package name */
        public final BlockCipher f14816a;
        public final int b;
        public final byte[] c;
        public final byte[] d;
        public final int e;

        public a(BlockCipher blockCipher, int i, byte[] bArr, byte[] bArr2, int i2) {
            this.f14816a = blockCipher;
            this.b = i;
            this.c = bArr;
            this.d = bArr2;
            this.e = i2;
        }

        @Override // org.bouncycastle.crypto.prng.a
        public SP80090DRBG a(EntropySource entropySource) {
            return new CTRSP800DRBG(this.f14816a, this.b, this.e, entropySource, this.d, this.c);
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements org.bouncycastle.crypto.prng.a {

        /* renamed from: a  reason: collision with root package name */
        public final Mac f14817a;
        public final byte[] b;
        public final byte[] c;
        public final int d;

        public b(Mac mac, byte[] bArr, byte[] bArr2, int i) {
            this.f14817a = mac;
            this.b = bArr;
            this.c = bArr2;
            this.d = i;
        }

        @Override // org.bouncycastle.crypto.prng.a
        public SP80090DRBG a(EntropySource entropySource) {
            return new HMacSP800DRBG(this.f14817a, this.d, entropySource, this.c, this.b);
        }
    }

    /* loaded from: classes13.dex */
    public static class c implements org.bouncycastle.crypto.prng.a {

        /* renamed from: a  reason: collision with root package name */
        public final Digest f14818a;
        public final byte[] b;
        public final byte[] c;
        public final int d;

        public c(Digest digest, byte[] bArr, byte[] bArr2, int i) {
            this.f14818a = digest;
            this.b = bArr;
            this.c = bArr2;
            this.d = i;
        }

        @Override // org.bouncycastle.crypto.prng.a
        public SP80090DRBG a(EntropySource entropySource) {
            return new HashSP800DRBG(this.f14818a, this.d, entropySource, this.c, this.b);
        }
    }

    public SP800SecureRandomBuilder() {
        this(new SecureRandom(), false);
    }

    public SP800SecureRandomBuilder(SecureRandom secureRandom, boolean z) {
        this.d = 256;
        this.e = 256;
        this.f14815a = secureRandom;
        this.b = new BasicEntropySourceProvider(secureRandom, z);
    }

    public SP800SecureRandomBuilder(EntropySourceProvider entropySourceProvider) {
        this.d = 256;
        this.e = 256;
        this.f14815a = null;
        this.b = entropySourceProvider;
    }

    public SP800SecureRandom buildCTR(BlockCipher blockCipher, int i, byte[] bArr, boolean z) {
        return new SP800SecureRandom(this.f14815a, this.b.get(this.e), new a(blockCipher, i, bArr, this.c, this.d), z);
    }

    public SP800SecureRandom buildHMAC(Mac mac, byte[] bArr, boolean z) {
        return new SP800SecureRandom(this.f14815a, this.b.get(this.e), new b(mac, bArr, this.c, this.d), z);
    }

    public SP800SecureRandom buildHash(Digest digest, byte[] bArr, boolean z) {
        return new SP800SecureRandom(this.f14815a, this.b.get(this.e), new c(digest, bArr, this.c, this.d), z);
    }

    public SP800SecureRandomBuilder setEntropyBitsRequired(int i) {
        this.e = i;
        return this;
    }

    public SP800SecureRandomBuilder setPersonalizationString(byte[] bArr) {
        this.c = bArr;
        return this;
    }

    public SP800SecureRandomBuilder setSecurityStrength(int i) {
        this.d = i;
        return this;
    }
}
