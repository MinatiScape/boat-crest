package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public class ElGamalKeyGenerationParameters extends KeyGenerationParameters {
    public ElGamalParameters c;

    public ElGamalKeyGenerationParameters(SecureRandom secureRandom, ElGamalParameters elGamalParameters) {
        super(secureRandom, a(elGamalParameters));
        this.c = elGamalParameters;
    }

    public static int a(ElGamalParameters elGamalParameters) {
        return elGamalParameters.getL() != 0 ? elGamalParameters.getL() : elGamalParameters.getP().bitLength();
    }

    public ElGamalParameters getParameters() {
        return this.c;
    }
}
