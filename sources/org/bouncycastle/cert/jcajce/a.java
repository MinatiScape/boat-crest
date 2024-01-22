package org.bouncycastle.cert.jcajce;

import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
/* loaded from: classes12.dex */
public abstract class a {
    public abstract CertificateFactory a(String str) throws CertificateException, NoSuchProviderException;

    public CertificateFactory b(String str) throws NoSuchProviderException, CertificateException {
        return a(str);
    }
}
