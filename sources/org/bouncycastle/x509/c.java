package org.bouncycastle.x509;

import java.security.cert.CertificateEncodingException;
/* loaded from: classes13.dex */
public class c extends CertificateEncodingException {
    public Throwable cause;

    public c(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
