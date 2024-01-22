package org.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes12.dex */
public class ASN1Exception extends IOException {
    private Throwable cause;

    public ASN1Exception(String str) {
        super(str);
    }

    public ASN1Exception(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
