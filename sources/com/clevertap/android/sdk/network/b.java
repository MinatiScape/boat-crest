package com.clevertap.android.sdk.network;

import com.clevertap.android.sdk.Logger;
import java.io.BufferedInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
/* loaded from: classes2.dex */
public final class b {
    public SSLContext a() {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("AmazonRootCA1", (X509Certificate) certificateFactory.generateCertificate(new BufferedInputStream(b.class.getClassLoader().getResourceAsStream("com/clevertap/android/sdk/certificates/AmazonRootCA1.cer"))));
            trustManagerFactory.init(keyStore);
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, trustManagerFactory.getTrustManagers(), null);
            Logger.d("SSL Context built");
            return sSLContext;
        } catch (Throwable th) {
            Logger.i("Error building SSL Context", th);
            return null;
        }
    }
}
