package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.security.cert.CRLException;
/* loaded from: classes13.dex */
public class a extends CRLException {
    public Throwable cause;

    public a(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
