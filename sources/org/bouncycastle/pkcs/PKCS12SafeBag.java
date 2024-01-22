package org.bouncycastle.pkcs;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.pkcs.CRLBag;
import org.bouncycastle.asn1.pkcs.CertBag;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.SafeBag;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
/* loaded from: classes13.dex */
public class PKCS12SafeBag {
    public static final ASN1ObjectIdentifier friendlyNameAttribute = PKCSObjectIdentifiers.pkcs_9_at_friendlyName;
    public static final ASN1ObjectIdentifier localKeyIdAttribute = PKCSObjectIdentifiers.pkcs_9_at_localKeyId;

    /* renamed from: a  reason: collision with root package name */
    public SafeBag f15257a;

    public PKCS12SafeBag(SafeBag safeBag) {
        this.f15257a = safeBag;
    }

    public Attribute[] getAttributes() {
        ASN1Set bagAttributes = this.f15257a.getBagAttributes();
        if (bagAttributes == null) {
            return null;
        }
        Attribute[] attributeArr = new Attribute[bagAttributes.size()];
        for (int i = 0; i != bagAttributes.size(); i++) {
            attributeArr[i] = Attribute.getInstance(bagAttributes.getObjectAt(i));
        }
        return attributeArr;
    }

    public Object getBagValue() {
        return getType().equals(PKCSObjectIdentifiers.pkcs8ShroudedKeyBag) ? new PKCS8EncryptedPrivateKeyInfo(EncryptedPrivateKeyInfo.getInstance(this.f15257a.getBagValue())) : getType().equals(PKCSObjectIdentifiers.certBag) ? new X509CertificateHolder(Certificate.getInstance(ASN1OctetString.getInstance(CertBag.getInstance(this.f15257a.getBagValue()).getCertValue()).getOctets())) : getType().equals(PKCSObjectIdentifiers.keyBag) ? PrivateKeyInfo.getInstance(this.f15257a.getBagValue()) : getType().equals(PKCSObjectIdentifiers.crlBag) ? new X509CRLHolder(CertificateList.getInstance(ASN1OctetString.getInstance(CRLBag.getInstance(this.f15257a.getBagValue()).getCrlValue()).getOctets())) : this.f15257a.getBagValue();
    }

    public ASN1ObjectIdentifier getType() {
        return this.f15257a.getBagId();
    }

    public SafeBag toASN1Structure() {
        return this.f15257a;
    }
}
