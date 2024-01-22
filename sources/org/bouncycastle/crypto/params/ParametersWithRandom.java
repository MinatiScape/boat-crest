package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class ParametersWithRandom implements CipherParameters {
    public SecureRandom h;
    public CipherParameters i;

    public ParametersWithRandom(CipherParameters cipherParameters) {
        this(cipherParameters, new SecureRandom());
    }

    public ParametersWithRandom(CipherParameters cipherParameters, SecureRandom secureRandom) {
        this.h = secureRandom;
        this.i = cipherParameters;
    }

    public CipherParameters getParameters() {
        return this.i;
    }

    public SecureRandom getRandom() {
        return this.h;
    }
}
