package org.bouncycastle.est.jcajce;

import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CRL;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertStore;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Set;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
/* loaded from: classes13.dex */
public class JcaJceUtils {

    /* loaded from: classes13.dex */
    public static class a implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements X509TrustManager {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Set f14916a;
        public final /* synthetic */ CRL[] b;
        public final /* synthetic */ X509Certificate[] c;

        public b(Set set, CRL[] crlArr, X509Certificate[] x509CertificateArr) {
            this.f14916a = set;
            this.b = crlArr;
            this.c = x509CertificateArr;
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            try {
                CertStore certStore = CertStore.getInstance("Collection", new CollectionCertStoreParameters(Arrays.asList(x509CertificateArr)), BouncyCastleProvider.PROVIDER_NAME);
                CertPathBuilder certPathBuilder = CertPathBuilder.getInstance("PKIX", BouncyCastleProvider.PROVIDER_NAME);
                X509CertSelector x509CertSelector = new X509CertSelector();
                x509CertSelector.setCertificate(x509CertificateArr[0]);
                PKIXBuilderParameters pKIXBuilderParameters = new PKIXBuilderParameters(this.f14916a, x509CertSelector);
                pKIXBuilderParameters.addCertStore(certStore);
                if (this.b != null) {
                    pKIXBuilderParameters.setRevocationEnabled(true);
                    pKIXBuilderParameters.addCertStore(CertStore.getInstance("Collection", new CollectionCertStoreParameters(Arrays.asList(this.b))));
                } else {
                    pKIXBuilderParameters.setRevocationEnabled(false);
                }
                PKIXCertPathValidatorResult pKIXCertPathValidatorResult = (PKIXCertPathValidatorResult) certPathBuilder.build(pKIXBuilderParameters);
                JcaJceUtils.validateServerCertUsage(x509CertificateArr[0]);
            } catch (CertificateException e) {
                throw e;
            } catch (GeneralSecurityException e2) {
                throw new CertificateException("unable to process certificates: " + e2.getMessage(), e2);
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            X509Certificate[] x509CertificateArr = this.c;
            int length = x509CertificateArr.length;
            X509Certificate[] x509CertificateArr2 = new X509Certificate[length];
            System.arraycopy(x509CertificateArr, 0, x509CertificateArr2, 0, length);
            return x509CertificateArr2;
        }
    }

    public static KeyManagerFactory createKeyManagerFactory(String str, String str2, KeyStore keyStore, char[] cArr) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException {
        KeyManagerFactory keyManagerFactory;
        if (str == null && str2 == null) {
            str = KeyManagerFactory.getDefaultAlgorithm();
        } else if (str2 != null) {
            keyManagerFactory = KeyManagerFactory.getInstance(str, str2);
            keyManagerFactory.init(keyStore, cArr);
            return keyManagerFactory;
        }
        keyManagerFactory = KeyManagerFactory.getInstance(str);
        keyManagerFactory.init(keyStore, cArr);
        return keyManagerFactory;
    }

    public static X509TrustManager[] getCertPathTrustManager(Set<TrustAnchor> set, CRL[] crlArr) {
        X509Certificate[] x509CertificateArr = new X509Certificate[set.size()];
        int i = 0;
        for (TrustAnchor trustAnchor : set) {
            x509CertificateArr[i] = trustAnchor.getTrustedCert();
            i++;
        }
        return new X509TrustManager[]{new b(set, crlArr, x509CertificateArr)};
    }

    public static X509TrustManager getTrustAllTrustManager() {
        return new a();
    }

    public static void validateServerCertUsage(X509Certificate x509Certificate) throws CertificateException {
        try {
            X509CertificateHolder x509CertificateHolder = new X509CertificateHolder(x509Certificate.getEncoded());
            KeyUsage fromExtensions = KeyUsage.fromExtensions(x509CertificateHolder.getExtensions());
            if (fromExtensions != null) {
                if (fromExtensions.hasUsages(4)) {
                    throw new CertificateException("Key usage must not contain keyCertSign");
                }
                if (!fromExtensions.hasUsages(128) && !fromExtensions.hasUsages(32)) {
                    throw new CertificateException("Key usage must be none, digitalSignature or keyEncipherment");
                }
            }
            ExtendedKeyUsage fromExtensions2 = ExtendedKeyUsage.fromExtensions(x509CertificateHolder.getExtensions());
            if (fromExtensions2 != null && !fromExtensions2.hasKeyPurposeId(KeyPurposeId.id_kp_serverAuth) && !fromExtensions2.hasKeyPurposeId(KeyPurposeId.id_kp_msSGC) && !fromExtensions2.hasKeyPurposeId(KeyPurposeId.id_kp_nsSGC)) {
                throw new CertificateException("Certificate extended key usage must include serverAuth, msSGC or nsSGC");
            }
        } catch (CertificateException e) {
            throw e;
        } catch (Exception e2) {
            throw new CertificateException(e2.getMessage(), e2);
        }
    }
}
