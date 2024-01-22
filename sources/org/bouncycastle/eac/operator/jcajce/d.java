package org.bouncycastle.eac.operator.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Signature;
/* loaded from: classes13.dex */
public class d extends b {
    public final Provider b;

    public d(Provider provider) {
        this.b = provider;
    }

    @Override // org.bouncycastle.eac.operator.jcajce.b
    public Signature a(String str) throws NoSuchAlgorithmException {
        return Signature.getInstance(str, this.b);
    }
}
