package org.bouncycastle.cert.path.validations;

import org.bouncycastle.cert.X509CertificateHolder;
/* loaded from: classes12.dex */
public class a {
    public static boolean a(X509CertificateHolder x509CertificateHolder) {
        return x509CertificateHolder.getSubject().equals(x509CertificateHolder.getIssuer());
    }
}
