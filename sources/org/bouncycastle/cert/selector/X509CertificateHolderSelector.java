package org.bouncycastle.cert.selector;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;
/* loaded from: classes12.dex */
public class X509CertificateHolderSelector implements Selector {
    public byte[] h;
    public X500Name i;
    public BigInteger j;

    public X509CertificateHolderSelector(X500Name x500Name, BigInteger bigInteger) {
        this(x500Name, bigInteger, null);
    }

    public X509CertificateHolderSelector(X500Name x500Name, BigInteger bigInteger, byte[] bArr) {
        this.i = x500Name;
        this.j = bigInteger;
        this.h = bArr;
    }

    public X509CertificateHolderSelector(byte[] bArr) {
        this(null, null, bArr);
    }

    public final boolean a(Object obj, Object obj2) {
        return obj != null ? obj.equals(obj2) : obj2 == null;
    }

    @Override // org.bouncycastle.util.Selector
    public Object clone() {
        return new X509CertificateHolderSelector(this.i, this.j, this.h);
    }

    public boolean equals(Object obj) {
        if (obj instanceof X509CertificateHolderSelector) {
            X509CertificateHolderSelector x509CertificateHolderSelector = (X509CertificateHolderSelector) obj;
            return Arrays.areEqual(this.h, x509CertificateHolderSelector.h) && a(this.j, x509CertificateHolderSelector.j) && a(this.i, x509CertificateHolderSelector.i);
        }
        return false;
    }

    public X500Name getIssuer() {
        return this.i;
    }

    public BigInteger getSerialNumber() {
        return this.j;
    }

    public byte[] getSubjectKeyIdentifier() {
        return Arrays.clone(this.h);
    }

    public int hashCode() {
        int hashCode = Arrays.hashCode(this.h);
        BigInteger bigInteger = this.j;
        if (bigInteger != null) {
            hashCode ^= bigInteger.hashCode();
        }
        X500Name x500Name = this.i;
        return x500Name != null ? hashCode ^ x500Name.hashCode() : hashCode;
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        if (obj instanceof X509CertificateHolder) {
            X509CertificateHolder x509CertificateHolder = (X509CertificateHolder) obj;
            if (getSerialNumber() != null) {
                IssuerAndSerialNumber issuerAndSerialNumber = new IssuerAndSerialNumber(x509CertificateHolder.toASN1Structure());
                return issuerAndSerialNumber.getName().equals(this.i) && issuerAndSerialNumber.getSerialNumber().getValue().equals(this.j);
            } else if (this.h != null) {
                Extension extension = x509CertificateHolder.getExtension(Extension.subjectKeyIdentifier);
                if (extension == null) {
                    return Arrays.areEqual(this.h, a.a(x509CertificateHolder.getSubjectPublicKeyInfo()));
                }
                return Arrays.areEqual(this.h, ASN1OctetString.getInstance(extension.getParsedValue()).getOctets());
            }
        } else if (obj instanceof byte[]) {
            return Arrays.areEqual(this.h, (byte[]) obj);
        }
        return false;
    }
}
