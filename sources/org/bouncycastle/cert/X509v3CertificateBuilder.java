package org.bouncycastle.cert;

import java.math.BigInteger;
import java.util.Date;
import java.util.Locale;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.asn1.x509.V3TBSCertificateGenerator;
import org.bouncycastle.operator.ContentSigner;
/* loaded from: classes12.dex */
public class X509v3CertificateBuilder {

    /* renamed from: a  reason: collision with root package name */
    public V3TBSCertificateGenerator f14446a;
    public ExtensionsGenerator b;

    public X509v3CertificateBuilder(X500Name x500Name, BigInteger bigInteger, Date date, Date date2, Locale locale, X500Name x500Name2, SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this(x500Name, bigInteger, new Time(date, locale), new Time(date2, locale), x500Name2, subjectPublicKeyInfo);
    }

    public X509v3CertificateBuilder(X500Name x500Name, BigInteger bigInteger, Date date, Date date2, X500Name x500Name2, SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this(x500Name, bigInteger, new Time(date), new Time(date2), x500Name2, subjectPublicKeyInfo);
    }

    public X509v3CertificateBuilder(X500Name x500Name, BigInteger bigInteger, Time time, Time time2, X500Name x500Name2, SubjectPublicKeyInfo subjectPublicKeyInfo) {
        V3TBSCertificateGenerator v3TBSCertificateGenerator = new V3TBSCertificateGenerator();
        this.f14446a = v3TBSCertificateGenerator;
        v3TBSCertificateGenerator.setSerialNumber(new ASN1Integer(bigInteger));
        this.f14446a.setIssuer(x500Name);
        this.f14446a.setStartDate(time);
        this.f14446a.setEndDate(time2);
        this.f14446a.setSubject(x500Name2);
        this.f14446a.setSubjectPublicKeyInfo(subjectPublicKeyInfo);
        this.b = new ExtensionsGenerator();
    }

    public X509v3CertificateBuilder addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, ASN1Encodable aSN1Encodable) throws CertIOException {
        a.a(this.b, aSN1ObjectIdentifier, z, aSN1Encodable);
        return this;
    }

    public X509v3CertificateBuilder addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, byte[] bArr) throws CertIOException {
        this.b.addExtension(aSN1ObjectIdentifier, z, bArr);
        return this;
    }

    public X509v3CertificateBuilder addExtension(Extension extension) throws CertIOException {
        this.b.addExtension(extension);
        return this;
    }

    public X509CertificateHolder build(ContentSigner contentSigner) {
        this.f14446a.setSignature(contentSigner.getAlgorithmIdentifier());
        if (!this.b.isEmpty()) {
            this.f14446a.setExtensions(this.b.generate());
        }
        return a.h(contentSigner, this.f14446a.generateTBSCertificate());
    }

    public X509v3CertificateBuilder copyAndAddExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, X509CertificateHolder x509CertificateHolder) {
        Extension extension = x509CertificateHolder.toASN1Structure().getTBSCertificate().getExtensions().getExtension(aSN1ObjectIdentifier);
        if (extension != null) {
            this.b.addExtension(aSN1ObjectIdentifier, z, extension.getExtnValue().getOctets());
            return this;
        }
        throw new NullPointerException("extension " + aSN1ObjectIdentifier + " not present");
    }

    public X509v3CertificateBuilder setIssuerUniqueID(boolean[] zArr) {
        this.f14446a.setIssuerUniqueID(a.c(zArr));
        return this;
    }

    public X509v3CertificateBuilder setSubjectUniqueID(boolean[] zArr) {
        this.f14446a.setSubjectUniqueID(a.c(zArr));
        return this;
    }
}
