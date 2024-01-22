package org.bouncycastle.cms;

import java.math.BigInteger;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.selector.X509CertificateHolderSelector;
/* loaded from: classes12.dex */
public class KeyAgreeRecipientId extends RecipientId {
    public X509CertificateHolderSelector i;

    public KeyAgreeRecipientId(X500Name x500Name, BigInteger bigInteger) {
        this(x500Name, bigInteger, null);
    }

    public KeyAgreeRecipientId(X500Name x500Name, BigInteger bigInteger, byte[] bArr) {
        this(new X509CertificateHolderSelector(x500Name, bigInteger, bArr));
    }

    public KeyAgreeRecipientId(X509CertificateHolderSelector x509CertificateHolderSelector) {
        super(2);
        this.i = x509CertificateHolderSelector;
    }

    public KeyAgreeRecipientId(byte[] bArr) {
        this(null, null, bArr);
    }

    @Override // org.bouncycastle.cms.RecipientId, org.bouncycastle.util.Selector
    public Object clone() {
        return new KeyAgreeRecipientId(this.i);
    }

    public boolean equals(Object obj) {
        if (obj instanceof KeyAgreeRecipientId) {
            return this.i.equals(((KeyAgreeRecipientId) obj).i);
        }
        return false;
    }

    public BigInteger getSerialNumber() {
        return this.i.getSerialNumber();
    }

    public byte[] getSubjectKeyIdentifier() {
        return this.i.getSubjectKeyIdentifier();
    }

    public int hashCode() {
        return this.i.hashCode();
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        return obj instanceof KeyAgreeRecipientInformation ? ((KeyAgreeRecipientInformation) obj).getRID().equals(this) : this.i.match(obj);
    }
}
