package com.abupdate.iot_libs.utils;

import com.abupdate.iot_libs.OtaAgentPolicy;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
/* loaded from: classes.dex */
public class d {
    public static SSLContext a(String str, String str2) throws Exception {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        KeyStore b = b(str, str2);
        keyManagerFactory.init(b, str.toCharArray());
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(b);
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        return sSLContext;
    }

    public static KeyStore b(String str, String str2) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        KeyStore keyStore = KeyStore.getInstance("BKS");
        InputStream open = OtaAgentPolicy.sCx.getAssets().open(str2);
        keyStore.load(open, str.toCharArray());
        open.close();
        return keyStore;
    }
}
