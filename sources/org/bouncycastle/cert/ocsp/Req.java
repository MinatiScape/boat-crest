package org.bouncycastle.cert.ocsp;

import org.bouncycastle.asn1.ocsp.Request;
import org.bouncycastle.asn1.x509.Extensions;
/* loaded from: classes12.dex */
public class Req {

    /* renamed from: a  reason: collision with root package name */
    public Request f14499a;

    public Req(Request request) {
        this.f14499a = request;
    }

    public CertificateID getCertID() {
        return new CertificateID(this.f14499a.getReqCert());
    }

    public Extensions getSingleRequestExtensions() {
        return this.f14499a.getSingleRequestExtensions();
    }
}
