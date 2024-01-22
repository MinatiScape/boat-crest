package org.bouncycastle.crypto.tls;

import java.security.SecureRandom;
/* loaded from: classes13.dex */
public class p extends a implements TlsServerContext {
    public p(SecureRandom secureRandom, SecurityParameters securityParameters) {
        super(secureRandom, securityParameters);
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public boolean isServer() {
        return true;
    }
}
