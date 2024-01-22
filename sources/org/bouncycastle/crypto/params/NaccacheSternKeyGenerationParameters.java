package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public class NaccacheSternKeyGenerationParameters extends KeyGenerationParameters {
    public int c;
    public int d;
    public boolean e;

    public NaccacheSternKeyGenerationParameters(SecureRandom secureRandom, int i, int i2, int i3) {
        this(secureRandom, i, i2, i3, false);
    }

    public NaccacheSternKeyGenerationParameters(SecureRandom secureRandom, int i, int i2, int i3, boolean z) {
        super(secureRandom, i);
        this.e = false;
        this.c = i2;
        if (i3 % 2 == 1) {
            throw new IllegalArgumentException("cntSmallPrimes must be a multiple of 2");
        }
        if (i3 < 30) {
            throw new IllegalArgumentException("cntSmallPrimes must be >= 30 for security reasons");
        }
        this.d = i3;
        this.e = z;
    }

    public int getCertainty() {
        return this.c;
    }

    public int getCntSmallPrimes() {
        return this.d;
    }

    public boolean isDebug() {
        return this.e;
    }
}
