package org.bouncycastle.crypto.tls;

import java.security.SecureRandom;
/* loaded from: classes13.dex */
public class m extends a implements TlsClientContext {
    public m(SecureRandom secureRandom, SecurityParameters securityParameters) {
        super(secureRandom, securityParameters);
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public boolean isServer() {
        return false;
    }
}
