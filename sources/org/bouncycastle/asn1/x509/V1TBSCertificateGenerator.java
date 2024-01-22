package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes12.dex */
public class V1TBSCertificateGenerator {

    /* renamed from: a  reason: collision with root package name */
    public ASN1Integer f14432a;
    public AlgorithmIdentifier b;
    public X500Name c;
    public Time d;
    public Time e;
    public X500Name f;
    public SubjectPublicKeyInfo g;

    public V1TBSCertificateGenerator() {
        new DERTaggedObject(true, 0, new ASN1Integer(0L));
    }

    public TBSCertificate generateTBSCertificate() {
        if (this.f14432a == null || this.b == null || this.c == null || this.d == null || this.e == null || this.f == null || this.g == null) {
            throw new IllegalStateException("not all mandatory fields set in V1 TBScertificate generator");
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f14432a);
        aSN1EncodableVector.add(this.b);
        aSN1EncodableVector.add(this.c);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        aSN1EncodableVector2.add(this.d);
        aSN1EncodableVector2.add(this.e);
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector.add(this.f);
        aSN1EncodableVector.add(this.g);
        return TBSCertificate.getInstance(new DERSequence(aSN1EncodableVector));
    }

    public void setEndDate(ASN1UTCTime aSN1UTCTime) {
        this.e = new Time(aSN1UTCTime);
    }

    public void setEndDate(Time time) {
        this.e = time;
    }

    public void setIssuer(X500Name x500Name) {
        this.c = x500Name;
    }

    public void setIssuer(X509Name x509Name) {
        this.c = X500Name.getInstance(x509Name.toASN1Primitive());
    }

    public void setSerialNumber(ASN1Integer aSN1Integer) {
        this.f14432a = aSN1Integer;
    }

    public void setSignature(AlgorithmIdentifier algorithmIdentifier) {
        this.b = algorithmIdentifier;
    }

    public void setStartDate(ASN1UTCTime aSN1UTCTime) {
        this.d = new Time(aSN1UTCTime);
    }

    public void setStartDate(Time time) {
        this.d = time;
    }

    public void setSubject(X500Name x500Name) {
        this.f = x500Name;
    }

    public void setSubject(X509Name x509Name) {
        this.f = X500Name.getInstance(x509Name.toASN1Primitive());
    }

    public void setSubjectPublicKeyInfo(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.g = subjectPublicKeyInfo;
    }
}
