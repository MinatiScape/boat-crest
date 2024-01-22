package org.bouncycastle.pkix.jcajce;

import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.pkix.PKIXIdentity;
/* loaded from: classes13.dex */
public class JcaPKIXIdentity extends PKIXIdentity {
    public final PrivateKey c;
    public final X509Certificate[] d;

    public JcaPKIXIdentity(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
        super(c(privateKey), b(x509CertificateArr));
        this.c = privateKey;
        X509Certificate[] x509CertificateArr2 = new X509Certificate[x509CertificateArr.length];
        this.d = x509CertificateArr2;
        System.arraycopy(x509CertificateArr, 0, x509CertificateArr2, 0, x509CertificateArr.length);
    }

    public static X509CertificateHolder[] b(X509Certificate[] x509CertificateArr) {
        int length = x509CertificateArr.length;
        X509CertificateHolder[] x509CertificateHolderArr = new X509CertificateHolder[length];
        for (int i = 0; i != length; i++) {
            try {
                x509CertificateHolderArr[i] = new JcaX509CertificateHolder(x509CertificateArr[i]);
            } catch (CertificateEncodingException e) {
                throw new IllegalArgumentException("Unable to process certificates: " + e.getMessage());
            }
        }
        return x509CertificateHolderArr;
    }

    public static PrivateKeyInfo c(PrivateKey privateKey) {
        try {
            return PrivateKeyInfo.getInstance(privateKey.getEncoded());
        } catch (Exception unused) {
            return null;
        }
    }

    public PrivateKey getPrivateKey() {
        return this.c;
    }

    public X509Certificate getX509Certificate() {
        return this.d[0];
    }
}
