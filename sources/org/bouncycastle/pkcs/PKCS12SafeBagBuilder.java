package org.bouncycastle.pkcs;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.pkcs.CertBag;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.SafeBag;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.OutputEncryptor;
/* loaded from: classes13.dex */
public class PKCS12SafeBagBuilder {

    /* renamed from: a  reason: collision with root package name */
    public ASN1ObjectIdentifier f15258a;
    public ASN1Encodable b;
    public ASN1EncodableVector c;

    public PKCS12SafeBagBuilder(PrivateKeyInfo privateKeyInfo) {
        this.c = new ASN1EncodableVector();
        this.f15258a = PKCSObjectIdentifiers.keyBag;
        this.b = privateKeyInfo;
    }

    public PKCS12SafeBagBuilder(PrivateKeyInfo privateKeyInfo, OutputEncryptor outputEncryptor) {
        this.c = new ASN1EncodableVector();
        this.f15258a = PKCSObjectIdentifiers.pkcs8ShroudedKeyBag;
        this.b = new PKCS8EncryptedPrivateKeyInfoBuilder(privateKeyInfo).build(outputEncryptor).toASN1Structure();
    }

    public PKCS12SafeBagBuilder(Certificate certificate) throws IOException {
        this.c = new ASN1EncodableVector();
        this.f15258a = PKCSObjectIdentifiers.certBag;
        this.b = new CertBag(PKCSObjectIdentifiers.x509Certificate, new DEROctetString(certificate.getEncoded()));
    }

    public PKCS12SafeBagBuilder(CertificateList certificateList) throws IOException {
        this.c = new ASN1EncodableVector();
        this.f15258a = PKCSObjectIdentifiers.crlBag;
        this.b = new CertBag(PKCSObjectIdentifiers.x509Crl, new DEROctetString(certificateList.getEncoded()));
    }

    public PKCS12SafeBagBuilder(X509CRLHolder x509CRLHolder) throws IOException {
        this(x509CRLHolder.toASN1Structure());
    }

    public PKCS12SafeBagBuilder(X509CertificateHolder x509CertificateHolder) throws IOException {
        this(x509CertificateHolder.toASN1Structure());
    }

    public PKCS12SafeBagBuilder addBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.c.add(new Attribute(aSN1ObjectIdentifier, new DERSet(aSN1Encodable)));
        return this;
    }

    public PKCS12SafeBag build() {
        return new PKCS12SafeBag(new SafeBag(this.f15258a, this.b, new DERSet(this.c)));
    }
}
