package org.bouncycastle.pqc.crypto.gmss;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public class GMSSKeyGenerationParameters extends KeyGenerationParameters {
    public GMSSParameters c;

    public GMSSKeyGenerationParameters(SecureRandom secureRandom, GMSSParameters gMSSParameters) {
        super(secureRandom, 1);
        this.c = gMSSParameters;
    }

    public GMSSParameters getParameters() {
        return this.c;
    }
}
