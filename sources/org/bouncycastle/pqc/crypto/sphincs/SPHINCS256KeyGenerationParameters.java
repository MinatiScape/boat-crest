package org.bouncycastle.pqc.crypto.sphincs;

import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public class SPHINCS256KeyGenerationParameters extends KeyGenerationParameters {
    public final Digest c;

    public SPHINCS256KeyGenerationParameters(SecureRandom secureRandom, Digest digest) {
        super(secureRandom, com.veryfit.multi.nativeprotocol.b.q5);
        this.c = digest;
    }

    public Digest getTreeDigest() {
        return this.c;
    }
}
