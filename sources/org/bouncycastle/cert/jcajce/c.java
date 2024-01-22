package org.bouncycastle.cert.jcajce;

import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
/* loaded from: classes12.dex */
public class c extends a {

    /* renamed from: a  reason: collision with root package name */
    public final String f14490a;

    public c(String str) {
        this.f14490a = str;
    }

    @Override // org.bouncycastle.cert.jcajce.a
    public CertificateFactory a(String str) throws CertificateException, NoSuchProviderException {
        return CertificateFactory.getInstance(str, this.f14490a);
    }
}
