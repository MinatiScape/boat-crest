package org.bouncycastle.cert.jcajce;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.cert.X509CertificateHolder;
/* loaded from: classes12.dex */
public class JcaX509CertificateConverter {

    /* renamed from: a  reason: collision with root package name */
    public org.bouncycastle.cert.jcajce.a f14487a;

    /* loaded from: classes12.dex */
    public class a extends CertificateException {
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

    /* loaded from: classes12.dex */
    public class b extends CertificateParsingException {
        private Throwable cause;

        public b(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    public JcaX509CertificateConverter() {
        this.f14487a = new org.bouncycastle.cert.jcajce.b();
        this.f14487a = new org.bouncycastle.cert.jcajce.b();
    }

    public X509Certificate getCertificate(X509CertificateHolder x509CertificateHolder) throws CertificateException {
        try {
            return (X509Certificate) this.f14487a.b("X.509").generateCertificate(new ByteArrayInputStream(x509CertificateHolder.getEncoded()));
        } catch (IOException e) {
            throw new b("exception parsing certificate: " + e.getMessage(), e);
        } catch (NoSuchProviderException e2) {
            throw new a("cannot find required provider:" + e2.getMessage(), e2);
        }
    }

    public JcaX509CertificateConverter setProvider(String str) {
        this.f14487a = new c(str);
        return this;
    }

    public JcaX509CertificateConverter setProvider(Provider provider) {
        this.f14487a = new d(provider);
        return this;
    }
}
