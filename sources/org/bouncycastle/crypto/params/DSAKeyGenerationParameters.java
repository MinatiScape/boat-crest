package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public class DSAKeyGenerationParameters extends KeyGenerationParameters {
    public DSAParameters c;

    public DSAKeyGenerationParameters(SecureRandom secureRandom, DSAParameters dSAParameters) {
        super(secureRandom, dSAParameters.getP().bitLength() - 1);
        this.c = dSAParameters;
    }

    public DSAParameters getParameters() {
        return this.c;
    }
}
