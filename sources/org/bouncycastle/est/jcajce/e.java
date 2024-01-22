package org.bouncycastle.est.jcajce;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Objects;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
/* loaded from: classes13.dex */
public class e {
    public Provider b;
    public KeyManager[] c;
    public X509TrustManager[] d;

    /* renamed from: a  reason: collision with root package name */
    public String f14922a = "TLS";
    public SecureRandom e = new SecureRandom();

    /* loaded from: classes13.dex */
    public class a implements SSLSocketFactoryCreator {
        public a() {
        }

        @Override // org.bouncycastle.est.jcajce.SSLSocketFactoryCreator
        public SSLSocketFactory createFactory() throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
            e eVar = e.this;
            Provider provider = eVar.b;
            String str = eVar.f14922a;
            SSLContext sSLContext = provider != null ? SSLContext.getInstance(str, provider) : SSLContext.getInstance(str);
            e eVar2 = e.this;
            sSLContext.init(eVar2.c, eVar2.d, eVar2.e);
            return sSLContext.getSocketFactory();
        }

        @Override // org.bouncycastle.est.jcajce.SSLSocketFactoryCreator
        public boolean isTrusted() {
            int i = 0;
            while (true) {
                X509TrustManager[] x509TrustManagerArr = e.this.d;
                if (i == x509TrustManagerArr.length) {
                    return false;
                }
                if (x509TrustManagerArr[i].getAcceptedIssuers().length > 0) {
                    return true;
                }
                i++;
            }
        }
    }

    public e(X509TrustManager x509TrustManager) {
        Objects.requireNonNull(x509TrustManager, "Trust managers can not be null");
        this.d = new X509TrustManager[]{x509TrustManager};
    }

    public e(X509TrustManager[] x509TrustManagerArr) {
        Objects.requireNonNull(x509TrustManagerArr, "Trust managers can not be null");
        this.d = x509TrustManagerArr;
    }

    public SSLSocketFactoryCreator a() {
        return new a();
    }

    public e b(KeyManager keyManager) {
        if (keyManager == null) {
            this.c = null;
        } else {
            this.c = new KeyManager[]{keyManager};
        }
        return this;
    }

    public e c(KeyManager[] keyManagerArr) {
        this.c = keyManagerArr;
        return this;
    }

    public e d(String str) throws NoSuchProviderException {
        Provider provider = Security.getProvider(str);
        this.b = provider;
        if (provider != null) {
            return this;
        }
        throw new NoSuchProviderException("JSSE provider not found: " + str);
    }

    public e e(Provider provider) {
        this.b = provider;
        return this;
    }

    public e f(SecureRandom secureRandom) {
        this.e = secureRandom;
        return this;
    }

    public e g(String str) {
        this.f14922a = str;
        return this;
    }
}
