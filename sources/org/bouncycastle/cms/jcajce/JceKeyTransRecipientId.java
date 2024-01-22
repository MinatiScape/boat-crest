package org.bouncycastle.cms.jcajce;

import java.math.BigInteger;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cms.KeyTransRecipientId;
/* loaded from: classes12.dex */
public class JceKeyTransRecipientId extends KeyTransRecipientId {
    public JceKeyTransRecipientId(X509Certificate x509Certificate) {
        super(a(x509Certificate.getIssuerX500Principal()), x509Certificate.getSerialNumber(), a.e(x509Certificate));
    }

    public JceKeyTransRecipientId(X500Principal x500Principal, BigInteger bigInteger) {
        super(a(x500Principal), bigInteger);
    }

    public JceKeyTransRecipientId(X500Principal x500Principal, BigInteger bigInteger, byte[] bArr) {
        super(a(x500Principal), bigInteger, bArr);
    }

    public static X500Name a(X500Principal x500Principal) {
        if (x500Principal == null) {
            return null;
        }
        return X500Name.getInstance(x500Principal.getEncoded());
    }
}
