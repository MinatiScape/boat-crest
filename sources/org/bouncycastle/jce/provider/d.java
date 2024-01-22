package org.bouncycastle.jce.provider;

import java.security.cert.CRLException;
/* loaded from: classes13.dex */
public class d extends CRLException {
    public Throwable cause;

    public d(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
