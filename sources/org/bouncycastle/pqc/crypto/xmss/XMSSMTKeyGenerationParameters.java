package org.bouncycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public final class XMSSMTKeyGenerationParameters extends KeyGenerationParameters {
    public final XMSSMTParameters c;

    public XMSSMTKeyGenerationParameters(XMSSMTParameters xMSSMTParameters, SecureRandom secureRandom) {
        super(secureRandom, -1);
        this.c = xMSSMTParameters;
    }

    public XMSSMTParameters getParameters() {
        return this.c;
    }
}
