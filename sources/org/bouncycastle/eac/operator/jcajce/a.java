package org.bouncycastle.eac.operator.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.Signature;
/* loaded from: classes13.dex */
public class a extends b {
    @Override // org.bouncycastle.eac.operator.jcajce.b
    public Signature a(String str) throws NoSuchAlgorithmException {
        return Signature.getInstance(str);
    }
}
