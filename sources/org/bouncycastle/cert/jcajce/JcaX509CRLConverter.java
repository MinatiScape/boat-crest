package org.bouncycastle.cert.jcajce;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.X509CRL;
import org.bouncycastle.cert.X509CRLHolder;
/* loaded from: classes12.dex */
public class JcaX509CRLConverter {

    /* renamed from: a  reason: collision with root package name */
    public org.bouncycastle.cert.jcajce.a f14486a;

    /* loaded from: classes12.dex */
    public class a extends CRLException {
        private Throwable cause;

        public a(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    public JcaX509CRLConverter() {
        this.f14486a = new b();
        this.f14486a = new b();
    }

    public X509CRL getCRL(X509CRLHolder x509CRLHolder) throws CRLException {
        try {
            return (X509CRL) this.f14486a.b("X.509").generateCRL(new ByteArrayInputStream(x509CRLHolder.getEncoded()));
        } catch (IOException e) {
            throw new a("exception parsing certificate: " + e.getMessage(), e);
        } catch (NoSuchProviderException e2) {
            throw new a("cannot find required provider:" + e2.getMessage(), e2);
        } catch (CertificateException e3) {
            throw new a("cannot create factory: " + e3.getMessage(), e3);
        }
    }

    public JcaX509CRLConverter setProvider(String str) {
        this.f14486a = new c(str);
        return this;
    }

    public JcaX509CRLConverter setProvider(Provider provider) {
        this.f14486a = new d(provider);
        return this;
    }
}
