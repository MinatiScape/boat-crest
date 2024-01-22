package org.bouncycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public class McElieceKeyGenerationParameters extends KeyGenerationParameters {
    public McElieceParameters c;

    public McElieceKeyGenerationParameters(SecureRandom secureRandom, McElieceParameters mcElieceParameters) {
        super(secureRandom, 256);
        this.c = mcElieceParameters;
    }

    public McElieceParameters getParameters() {
        return this.c;
    }
}
