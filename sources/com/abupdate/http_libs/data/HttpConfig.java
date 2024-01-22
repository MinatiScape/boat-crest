package com.abupdate.http_libs.data;

import android.content.Context;
import com.abupdate.http_libs.HttpIotUtils;
import com.abupdate.http_libs.engine.HttpManager;
import java.io.InputStream;
import java.security.KeyStore;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;
/* loaded from: classes.dex */
public class HttpConfig {
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final int DEFAULT_TIMEOUT = 20000;

    /* renamed from: a  reason: collision with root package name */
    public Context f1866a;
    public int b = 20000;
    public int c = 20000;
    public int d = 3;
    public int e = 3;
    public HostnameVerifier f = new a(this);

    /* loaded from: classes.dex */
    public class a implements HostnameVerifier {
        public a(HttpConfig httpConfig) {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    public HttpConfig(Context context) {
        if (context != null) {
            this.f1866a = context.getApplicationContext();
        }
    }

    public final KeyStore a(String str, String str2) {
        KeyStore keyStore = KeyStore.getInstance("BKS");
        InputStream resourceAsStream = getClass().getResourceAsStream(str2);
        keyStore.load(resourceAsStream, str.toCharArray());
        resourceAsStream.close();
        return keyStore;
    }

    public final SSLContext b(String str, String str2) {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        KeyStore a2 = a(str, str2);
        keyManagerFactory.init(a2, str.toCharArray());
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(a2);
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        return sSLContext;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(java.lang.String r1, java.lang.String r2, javax.net.ssl.HostnameVerifier r3) {
        /*
            r0 = this;
            javax.net.ssl.SSLContext r1 = r0.b(r1, r2)     // Catch: java.lang.Exception -> L5 java.security.GeneralSecurityException -> La
            goto Lf
        L5:
            r1 = move-exception
            r1.printStackTrace()
            goto Le
        La:
            r1 = move-exception
            r1.printStackTrace()
        Le:
            r1 = 0
        Lf:
            if (r1 == 0) goto L18
            javax.net.ssl.SSLSocketFactory r1 = r1.getSocketFactory()
            javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r1)
        L18:
            javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.abupdate.http_libs.data.HttpConfig.c(java.lang.String, java.lang.String, javax.net.ssl.HostnameVerifier):void");
    }

    public void create() {
        HttpIotUtils.init(new HttpManager(this));
    }

    public int getConnectTimeout() {
        return this.c;
    }

    public int getRedirectTimes() {
        return this.e;
    }

    public int getRetryTimes() {
        return this.d;
    }

    public int getSocketTimeout() {
        return this.b;
    }

    public HttpConfig setConnectTimeout(int i) {
        this.c = i;
        return this;
    }

    public HttpConfig setRedirectTimes(int i) {
        this.e = i;
        return this;
    }

    public HttpConfig setRetryTimes(int i) {
        this.d = i;
        return this;
    }

    public HttpConfig setSSL(String str, String str2) {
        c(str, str2, this.f);
        return this;
    }

    public HttpConfig setSSL(String str, String str2, HostnameVerifier hostnameVerifier) {
        c(str, str2, hostnameVerifier);
        return this;
    }

    public HttpConfig setSocketTimeout(int i) {
        this.b = i;
        return this;
    }

    public String toString() {
        return "HttpConfig{context=" + this.f1866a + ", connectTimeout=" + this.c + ", socketTimeout=" + this.b + '}';
    }
}
