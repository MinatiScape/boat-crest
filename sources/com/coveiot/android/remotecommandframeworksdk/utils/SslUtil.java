package com.coveiot.android.remotecommandframeworksdk.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
/* loaded from: classes6.dex */
public class SslUtil {
    public static SSLSocketFactory getSocketFactory(InputStream inputStream, InputStream inputStream2, InputStream inputStream3, String str) {
        Security.addProvider(new BouncyCastleProvider());
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509Certificate x509Certificate = null;
        while (bufferedInputStream.available() > 0) {
            x509Certificate = (X509Certificate) certificateFactory.generateCertificate(bufferedInputStream);
        }
        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream2);
        X509Certificate x509Certificate2 = null;
        while (bufferedInputStream2.available() > 0) {
            x509Certificate2 = (X509Certificate) certificateFactory.generateCertificate(bufferedInputStream2);
        }
        KeyPair keyPair = new JcaPEMKeyConverter().getKeyPair((PEMKeyPair) new PEMParser(new InputStreamReader(inputStream3)).readObject());
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setCertificateEntry("cert-certificate", x509Certificate);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        KeyStore keyStore2 = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore2.load(null, null);
        keyStore2.setCertificateEntry("certificate", x509Certificate2);
        keyStore2.setKeyEntry("private-cert", keyPair.getPrivate(), str.toCharArray(), new Certificate[]{x509Certificate2});
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore2, str.toCharArray());
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
        sSLContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        return sSLContext.getSocketFactory();
    }
}
