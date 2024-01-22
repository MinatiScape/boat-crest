package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public class ECKeyGenerationParameters extends KeyGenerationParameters {
    public ECDomainParameters c;

    public ECKeyGenerationParameters(ECDomainParameters eCDomainParameters, SecureRandom secureRandom) {
        super(secureRandom, eCDomainParameters.getN().bitLength());
        this.c = eCDomainParameters;
    }

    public ECDomainParameters getDomainParameters() {
        return this.c;
    }
}
