package org.bouncycastle.est.jcajce;

import java.net.Socket;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.est.ESTClientProvider;
import org.bouncycastle.est.ESTService;
import org.bouncycastle.est.ESTServiceBuilder;
/* loaded from: classes13.dex */
public class JsseESTServiceBuilder extends ESTServiceBuilder {
    public Long absoluteLimit;
    public ChannelBindingProvider bindingProvider;
    public boolean filterCipherSuites;
    public JsseHostnameAuthorizer hostNameAuthorizer;
    public SSLSocketFactoryCreator socketFactoryCreator;
    public e sslSocketFactoryCreatorBuilder;
    public Set<String> supportedSuites;
    public int timeoutMillis;

    /* loaded from: classes13.dex */
    public class a implements ChannelBindingProvider {
        public a(JsseESTServiceBuilder jsseESTServiceBuilder) {
        }

        @Override // org.bouncycastle.est.jcajce.ChannelBindingProvider
        public boolean canAccessChannelBinding(Socket socket) {
            return false;
        }

        @Override // org.bouncycastle.est.jcajce.ChannelBindingProvider
        public byte[] getChannelBinding(Socket socket, String str) {
            return null;
        }
    }

    public JsseESTServiceBuilder(String str) {
        super(str);
        this.hostNameAuthorizer = new JsseDefaultHostnameAuthorizer(null);
        this.timeoutMillis = 0;
        this.supportedSuites = new HashSet();
        this.filterCipherSuites = true;
        this.sslSocketFactoryCreatorBuilder = new e(JcaJceUtils.getTrustAllTrustManager());
    }

    public JsseESTServiceBuilder(String str, X509TrustManager x509TrustManager) {
        super(str);
        this.hostNameAuthorizer = new JsseDefaultHostnameAuthorizer(null);
        this.timeoutMillis = 0;
        this.supportedSuites = new HashSet();
        this.filterCipherSuites = true;
        this.sslSocketFactoryCreatorBuilder = new e(x509TrustManager);
    }

    public JsseESTServiceBuilder(String str, SSLSocketFactoryCreator sSLSocketFactoryCreator) {
        super(str);
        this.hostNameAuthorizer = new JsseDefaultHostnameAuthorizer(null);
        this.timeoutMillis = 0;
        this.supportedSuites = new HashSet();
        this.filterCipherSuites = true;
        Objects.requireNonNull(sSLSocketFactoryCreator, "No socket factory creator.");
        this.socketFactoryCreator = sSLSocketFactoryCreator;
    }

    public JsseESTServiceBuilder(String str, X509TrustManager[] x509TrustManagerArr) {
        super(str);
        this.hostNameAuthorizer = new JsseDefaultHostnameAuthorizer(null);
        this.timeoutMillis = 0;
        this.supportedSuites = new HashSet();
        this.filterCipherSuites = true;
        this.sslSocketFactoryCreatorBuilder = new e(x509TrustManagerArr);
    }

    public JsseESTServiceBuilder addCipherSuites(String str) {
        this.supportedSuites.add(str);
        return this;
    }

    public JsseESTServiceBuilder addCipherSuites(String[] strArr) {
        this.supportedSuites.addAll(Arrays.asList(strArr));
        return this;
    }

    @Override // org.bouncycastle.est.ESTServiceBuilder
    public ESTService build() {
        if (this.bindingProvider == null) {
            this.bindingProvider = new a(this);
        }
        if (this.socketFactoryCreator == null) {
            this.socketFactoryCreator = this.sslSocketFactoryCreatorBuilder.a();
        }
        if (this.clientProvider == null) {
            this.clientProvider = new c(this.hostNameAuthorizer, this.socketFactoryCreator, this.timeoutMillis, this.bindingProvider, this.supportedSuites, this.absoluteLimit, this.filterCipherSuites);
        }
        return super.build();
    }

    public JsseESTServiceBuilder withChannelBindingProvider(ChannelBindingProvider channelBindingProvider) {
        this.bindingProvider = channelBindingProvider;
        return this;
    }

    @Override // org.bouncycastle.est.ESTServiceBuilder
    public JsseESTServiceBuilder withClientProvider(ESTClientProvider eSTClientProvider) {
        this.clientProvider = eSTClientProvider;
        return this;
    }

    public JsseESTServiceBuilder withFilterCipherSuites(boolean z) {
        this.filterCipherSuites = z;
        return this;
    }

    public JsseESTServiceBuilder withHostNameAuthorizer(JsseHostnameAuthorizer jsseHostnameAuthorizer) {
        this.hostNameAuthorizer = jsseHostnameAuthorizer;
        return this;
    }

    public JsseESTServiceBuilder withKeyManager(KeyManager keyManager) {
        if (this.socketFactoryCreator == null) {
            this.sslSocketFactoryCreatorBuilder.b(keyManager);
            return this;
        }
        throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
    }

    public JsseESTServiceBuilder withKeyManagers(KeyManager[] keyManagerArr) {
        if (this.socketFactoryCreator == null) {
            this.sslSocketFactoryCreatorBuilder.c(keyManagerArr);
            return this;
        }
        throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
    }

    public JsseESTServiceBuilder withProvider(String str) throws NoSuchProviderException {
        if (this.socketFactoryCreator == null) {
            this.sslSocketFactoryCreatorBuilder.d(str);
            return this;
        }
        throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
    }

    public JsseESTServiceBuilder withProvider(Provider provider) {
        if (this.socketFactoryCreator == null) {
            this.sslSocketFactoryCreatorBuilder.e(provider);
            return this;
        }
        throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
    }

    public JsseESTServiceBuilder withReadLimit(long j) {
        this.absoluteLimit = Long.valueOf(j);
        return this;
    }

    public JsseESTServiceBuilder withSecureRandom(SecureRandom secureRandom) {
        if (this.socketFactoryCreator == null) {
            this.sslSocketFactoryCreatorBuilder.f(secureRandom);
            return this;
        }
        throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
    }

    public JsseESTServiceBuilder withTLSVersion(String str) {
        if (this.socketFactoryCreator == null) {
            this.sslSocketFactoryCreatorBuilder.g(str);
            return this;
        }
        throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
    }

    public JsseESTServiceBuilder withTimeout(int i) {
        this.timeoutMillis = i;
        return this;
    }
}
