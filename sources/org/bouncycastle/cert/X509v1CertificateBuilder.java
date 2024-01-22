package org.bouncycastle.cert;

import java.math.BigInteger;
import java.util.Date;
import java.util.Locale;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.asn1.x509.V1TBSCertificateGenerator;
import org.bouncycastle.operator.ContentSigner;
/* loaded from: classes12.dex */
public class X509v1CertificateBuilder {

    /* renamed from: a  reason: collision with root package name */
    public V1TBSCertificateGenerator f14443a;

    public X509v1CertificateBuilder(X500Name x500Name, BigInteger bigInteger, Date date, Date date2, Locale locale, X500Name x500Name2, SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this(x500Name, bigInteger, new Time(date, locale), new Time(date2, locale), x500Name2, subjectPublicKeyInfo);
    }

    public X509v1CertificateBuilder(X500Name x500Name, BigInteger bigInteger, Date date, Date date2, X500Name x500Name2, SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this(x500Name, bigInteger, new Time(date), new Time(date2), x500Name2, subjectPublicKeyInfo);
    }

    public X509v1CertificateBuilder(X500Name x500Name, BigInteger bigInteger, Time time, Time time2, X500Name x500Name2, SubjectPublicKeyInfo subjectPublicKeyInfo) {
        if (x500Name == null) {
            throw new IllegalArgumentException("issuer must not be null");
        }
        if (subjectPublicKeyInfo == null) {
            throw new IllegalArgumentException("publicKeyInfo must not be null");
        }
        V1TBSCertificateGenerator v1TBSCertificateGenerator = new V1TBSCertificateGenerator();
        this.f14443a = v1TBSCertificateGenerator;
        v1TBSCertificateGenerator.setSerialNumber(new ASN1Integer(bigInteger));
        this.f14443a.setIssuer(x500Name);
        this.f14443a.setStartDate(time);
        this.f14443a.setEndDate(time2);
        this.f14443a.setSubject(x500Name2);
        this.f14443a.setSubjectPublicKeyInfo(subjectPublicKeyInfo);
    }

    public X509CertificateHolder build(ContentSigner contentSigner) {
        this.f14443a.setSignature(contentSigner.getAlgorithmIdentifier());
        return a.h(contentSigner, this.f14443a.generateTBSCertificate());
    }
}
