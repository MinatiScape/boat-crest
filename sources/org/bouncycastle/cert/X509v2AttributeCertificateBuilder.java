package org.bouncycastle.cert;

import java.math.BigInteger;
import java.util.Date;
import java.util.Locale;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.x509.AttCertIssuer;
import org.bouncycastle.asn1.x509.Attribute;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.V2AttributeCertificateInfoGenerator;
import org.bouncycastle.operator.ContentSigner;
/* loaded from: classes12.dex */
public class X509v2AttributeCertificateBuilder {

    /* renamed from: a  reason: collision with root package name */
    public V2AttributeCertificateInfoGenerator f14444a = new V2AttributeCertificateInfoGenerator();
    public ExtensionsGenerator b = new ExtensionsGenerator();

    public X509v2AttributeCertificateBuilder(AttributeCertificateHolder attributeCertificateHolder, AttributeCertificateIssuer attributeCertificateIssuer, BigInteger bigInteger, Date date, Date date2) {
        this.f14444a.setHolder(attributeCertificateHolder.h);
        this.f14444a.setIssuer(AttCertIssuer.getInstance(attributeCertificateIssuer.h));
        this.f14444a.setSerialNumber(new ASN1Integer(bigInteger));
        this.f14444a.setStartDate(new ASN1GeneralizedTime(date));
        this.f14444a.setEndDate(new ASN1GeneralizedTime(date2));
    }

    public X509v2AttributeCertificateBuilder(AttributeCertificateHolder attributeCertificateHolder, AttributeCertificateIssuer attributeCertificateIssuer, BigInteger bigInteger, Date date, Date date2, Locale locale) {
        this.f14444a.setHolder(attributeCertificateHolder.h);
        this.f14444a.setIssuer(AttCertIssuer.getInstance(attributeCertificateIssuer.h));
        this.f14444a.setSerialNumber(new ASN1Integer(bigInteger));
        this.f14444a.setStartDate(new ASN1GeneralizedTime(date, locale));
        this.f14444a.setEndDate(new ASN1GeneralizedTime(date2, locale));
    }

    public X509v2AttributeCertificateBuilder addAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.f14444a.addAttribute(new Attribute(aSN1ObjectIdentifier, new DERSet(aSN1Encodable)));
        return this;
    }

    public X509v2AttributeCertificateBuilder addAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable[] aSN1EncodableArr) {
        this.f14444a.addAttribute(new Attribute(aSN1ObjectIdentifier, new DERSet(aSN1EncodableArr)));
        return this;
    }

    public X509v2AttributeCertificateBuilder addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, ASN1Encodable aSN1Encodable) throws CertIOException {
        a.a(this.b, aSN1ObjectIdentifier, z, aSN1Encodable);
        return this;
    }

    public X509v2AttributeCertificateBuilder addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, byte[] bArr) throws CertIOException {
        this.b.addExtension(aSN1ObjectIdentifier, z, bArr);
        return this;
    }

    public X509v2AttributeCertificateBuilder addExtension(Extension extension) throws CertIOException {
        this.b.addExtension(extension);
        return this;
    }

    public X509AttributeCertificateHolder build(ContentSigner contentSigner) {
        this.f14444a.setSignature(contentSigner.getAlgorithmIdentifier());
        if (!this.b.isEmpty()) {
            this.f14444a.setExtensions(this.b.generate());
        }
        return a.f(contentSigner, this.f14444a.generateAttributeCertificateInfo());
    }

    public void setIssuerUniqueId(boolean[] zArr) {
        this.f14444a.setIssuerUniqueID(a.c(zArr));
    }
}
