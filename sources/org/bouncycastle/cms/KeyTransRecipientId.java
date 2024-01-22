package org.bouncycastle.cms;

import java.math.BigInteger;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.selector.X509CertificateHolderSelector;
/* loaded from: classes12.dex */
public class KeyTransRecipientId extends RecipientId {
    public X509CertificateHolderSelector i;

    public KeyTransRecipientId(X500Name x500Name, BigInteger bigInteger) {
        this(x500Name, bigInteger, null);
    }

    public KeyTransRecipientId(X500Name x500Name, BigInteger bigInteger, byte[] bArr) {
        this(new X509CertificateHolderSelector(x500Name, bigInteger, bArr));
    }

    public KeyTransRecipientId(X509CertificateHolderSelector x509CertificateHolderSelector) {
        super(0);
        this.i = x509CertificateHolderSelector;
    }

    public KeyTransRecipientId(byte[] bArr) {
        this(null, null, bArr);
    }

    @Override // org.bouncycastle.cms.RecipientId, org.bouncycastle.util.Selector
    public Object clone() {
        return new KeyTransRecipientId(this.i);
    }

    public boolean equals(Object obj) {
        if (obj instanceof KeyTransRecipientId) {
            return this.i.equals(((KeyTransRecipientId) obj).i);
        }
        return false;
    }

    public X500Name getIssuer() {
        return this.i.getIssuer();
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
        return obj instanceof KeyTransRecipientInformation ? ((KeyTransRecipientInformation) obj).getRID().equals(this) : this.i.match(obj);
    }
}
