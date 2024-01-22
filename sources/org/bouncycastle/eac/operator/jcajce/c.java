package org.bouncycastle.eac.operator.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
/* loaded from: classes13.dex */
public class c extends b {
    public final String b;

    public c(String str) {
        this.b = str;
    }

    @Override // org.bouncycastle.eac.operator.jcajce.b
    public Signature a(String str) throws NoSuchProviderException, NoSuchAlgorithmException {
        return Signature.getInstance(str, this.b);
    }
}
