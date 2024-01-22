package org.bouncycastle.cert.jcajce;

import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
/* loaded from: classes12.dex */
public class d extends a {

    /* renamed from: a  reason: collision with root package name */
    public final Provider f14491a;

    public d(Provider provider) {
        this.f14491a = provider;
    }

    @Override // org.bouncycastle.cert.jcajce.a
    public CertificateFactory a(String str) throws CertificateException {
        return CertificateFactory.getInstance(str, this.f14491a);
    }
}
