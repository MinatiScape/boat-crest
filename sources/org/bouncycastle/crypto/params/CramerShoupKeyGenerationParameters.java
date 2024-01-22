package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public class CramerShoupKeyGenerationParameters extends KeyGenerationParameters {
    public CramerShoupParameters c;

    public CramerShoupKeyGenerationParameters(SecureRandom secureRandom, CramerShoupParameters cramerShoupParameters) {
        super(secureRandom, a(cramerShoupParameters));
        this.c = cramerShoupParameters;
    }

    public static int a(CramerShoupParameters cramerShoupParameters) {
        return cramerShoupParameters.getP().bitLength();
    }

    public CramerShoupParameters getParameters() {
        return this.c;
    }
}
