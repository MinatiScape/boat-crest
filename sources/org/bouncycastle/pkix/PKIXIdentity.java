package org.bouncycastle.pkix;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.KeyTransRecipientId;
import org.bouncycastle.cms.RecipientId;
/* loaded from: classes13.dex */
public class PKIXIdentity {

    /* renamed from: a  reason: collision with root package name */
    public final PrivateKeyInfo f15284a;
    public final X509CertificateHolder[] b;

    public PKIXIdentity(PrivateKeyInfo privateKeyInfo, X509CertificateHolder[] x509CertificateHolderArr) {
        this.f15284a = privateKeyInfo;
        X509CertificateHolder[] x509CertificateHolderArr2 = new X509CertificateHolder[x509CertificateHolderArr.length];
        this.b = x509CertificateHolderArr2;
        System.arraycopy(x509CertificateHolderArr, 0, x509CertificateHolderArr2, 0, x509CertificateHolderArr.length);
    }

    public final byte[] a() {
        SubjectKeyIdentifier fromExtensions = SubjectKeyIdentifier.fromExtensions(this.b[0].getExtensions());
        if (fromExtensions == null) {
            return null;
        }
        return fromExtensions.getKeyIdentifier();
    }

    public X509CertificateHolder getCertificate() {
        return this.b[0];
    }

    public PrivateKeyInfo getPrivateKeyInfo() {
        return this.f15284a;
    }

    public RecipientId getRecipientId() {
        return new KeyTransRecipientId(this.b[0].getIssuer(), this.b[0].getSerialNumber(), a());
    }
}
