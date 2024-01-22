package org.bouncycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public final class XMSSKeyGenerationParameters extends KeyGenerationParameters {
    public final XMSSParameters c;

    public XMSSKeyGenerationParameters(XMSSParameters xMSSParameters, SecureRandom secureRandom) {
        super(secureRandom, -1);
        this.c = xMSSParameters;
    }

    public XMSSParameters getParameters() {
        return this.c;
    }
}
