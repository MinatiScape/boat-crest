package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public class DHKeyGenerationParameters extends KeyGenerationParameters {
    public DHParameters c;

    public DHKeyGenerationParameters(SecureRandom secureRandom, DHParameters dHParameters) {
        super(secureRandom, a(dHParameters));
        this.c = dHParameters;
    }

    public static int a(DHParameters dHParameters) {
        return dHParameters.getL() != 0 ? dHParameters.getL() : dHParameters.getP().bitLength();
    }

    public DHParameters getParameters() {
        return this.c;
    }
}
