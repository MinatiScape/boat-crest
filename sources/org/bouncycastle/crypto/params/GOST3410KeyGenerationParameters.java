package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public class GOST3410KeyGenerationParameters extends KeyGenerationParameters {
    public GOST3410Parameters c;

    public GOST3410KeyGenerationParameters(SecureRandom secureRandom, GOST3410Parameters gOST3410Parameters) {
        super(secureRandom, gOST3410Parameters.getP().bitLength() - 1);
        this.c = gOST3410Parameters;
    }

    public GOST3410Parameters getParameters() {
        return this.c;
    }
}
