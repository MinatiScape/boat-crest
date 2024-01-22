package org.bouncycastle.crypto.prng;

import java.security.SecureRandom;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public class X931SecureRandomBuilder {

    /* renamed from: a  reason: collision with root package name */
    public SecureRandom f14821a;
    public EntropySourceProvider b;
    public byte[] c;

    public X931SecureRandomBuilder() {
        this(new SecureRandom(), false);
    }

    public X931SecureRandomBuilder(SecureRandom secureRandom, boolean z) {
        this.f14821a = secureRandom;
        this.b = new BasicEntropySourceProvider(secureRandom, z);
    }

    public X931SecureRandomBuilder(EntropySourceProvider entropySourceProvider) {
        this.f14821a = null;
        this.b = entropySourceProvider;
    }

    public X931SecureRandom build(BlockCipher blockCipher, KeyParameter keyParameter, boolean z) {
        if (this.c == null) {
            this.c = new byte[blockCipher.getBlockSize()];
            Pack.longToBigEndian(System.currentTimeMillis(), this.c, 0);
        }
        blockCipher.init(true, keyParameter);
        return new X931SecureRandom(this.f14821a, new X931RNG(blockCipher, this.c, this.b.get(blockCipher.getBlockSize() * 8)), z);
    }

    public X931SecureRandomBuilder setDateTimeVector(byte[] bArr) {
        this.c = bArr;
        return this;
    }
}
