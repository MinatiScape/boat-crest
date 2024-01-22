package org.bouncycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public class McElieceCCA2KeyGenerationParameters extends KeyGenerationParameters {
    public McElieceCCA2Parameters c;

    public McElieceCCA2KeyGenerationParameters(SecureRandom secureRandom, McElieceCCA2Parameters mcElieceCCA2Parameters) {
        super(secureRandom, 128);
        this.c = mcElieceCCA2Parameters;
    }

    public McElieceCCA2Parameters getParameters() {
        return this.c;
    }
}
