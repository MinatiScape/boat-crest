package org.bouncycastle.cert.jcajce;

import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameStyle;
/* loaded from: classes12.dex */
public class JcaX500NameUtil {
    public static X500Name getIssuer(X509Certificate x509Certificate) {
        return X500Name.getInstance(x509Certificate.getIssuerX500Principal().getEncoded());
    }

    public static X500Name getIssuer(X500NameStyle x500NameStyle, X509Certificate x509Certificate) {
        return X500Name.getInstance(x500NameStyle, x509Certificate.getIssuerX500Principal().getEncoded());
    }

    public static X500Name getSubject(X509Certificate x509Certificate) {
        return X500Name.getInstance(x509Certificate.getSubjectX500Principal().getEncoded());
    }

    public static X500Name getSubject(X500NameStyle x500NameStyle, X509Certificate x509Certificate) {
        return X500Name.getInstance(x500NameStyle, x509Certificate.getSubjectX500Principal().getEncoded());
    }
}
