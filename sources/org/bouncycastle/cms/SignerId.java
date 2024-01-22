package org.bouncycastle.cms;

import java.math.BigInteger;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.selector.X509CertificateHolderSelector;
import org.bouncycastle.util.Selector;
/* loaded from: classes12.dex */
public class SignerId implements Selector {
    public X509CertificateHolderSelector h;

    public SignerId(X500Name x500Name, BigInteger bigInteger) {
        this(x500Name, bigInteger, null);
    }

    public SignerId(X500Name x500Name, BigInteger bigInteger, byte[] bArr) {
        this(new X509CertificateHolderSelector(x500Name, bigInteger, bArr));
    }

    public SignerId(X509CertificateHolderSelector x509CertificateHolderSelector) {
        this.h = x509CertificateHolderSelector;
    }

    public SignerId(byte[] bArr) {
        this(null, null, bArr);
    }

    @Override // org.bouncycastle.util.Selector
    public Object clone() {
        return new SignerId(this.h);
    }

    public boolean equals(Object obj) {
        if (obj instanceof SignerId) {
            return this.h.equals(((SignerId) obj).h);
        }
        return false;
    }

    public X500Name getIssuer() {
        return this.h.getIssuer();
    }

    public BigInteger getSerialNumber() {
        return this.h.getSerialNumber();
    }

    public byte[] getSubjectKeyIdentifier() {
        return this.h.getSubjectKeyIdentifier();
    }

    public int hashCode() {
        return this.h.hashCode();
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        return obj instanceof SignerInformation ? ((SignerInformation) obj).getSID().equals(this) : this.h.match(obj);
    }
}
