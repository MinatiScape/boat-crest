package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509Extensions;
/* loaded from: classes12.dex */
public class CertTemplateBuilder {

    /* renamed from: a  reason: collision with root package name */
    public ASN1Integer f14407a;
    public ASN1Integer b;
    public AlgorithmIdentifier c;
    public X500Name d;
    public OptionalValidity e;
    public X500Name f;
    public SubjectPublicKeyInfo g;
    public DERBitString h;
    public DERBitString i;
    public Extensions j;

    public final void a(ASN1EncodableVector aSN1EncodableVector, int i, boolean z, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(new DERTaggedObject(z, i, aSN1Encodable));
        }
    }

    public CertTemplate build() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        a(aSN1EncodableVector, 0, false, this.f14407a);
        a(aSN1EncodableVector, 1, false, this.b);
        a(aSN1EncodableVector, 2, false, this.c);
        a(aSN1EncodableVector, 3, true, this.d);
        a(aSN1EncodableVector, 4, false, this.e);
        a(aSN1EncodableVector, 5, true, this.f);
        a(aSN1EncodableVector, 6, false, this.g);
        a(aSN1EncodableVector, 7, false, this.h);
        a(aSN1EncodableVector, 8, false, this.i);
        a(aSN1EncodableVector, 9, false, this.j);
        return CertTemplate.getInstance(new DERSequence(aSN1EncodableVector));
    }

    public CertTemplateBuilder setExtensions(Extensions extensions) {
        this.j = extensions;
        return this;
    }

    public CertTemplateBuilder setExtensions(X509Extensions x509Extensions) {
        return setExtensions(Extensions.getInstance(x509Extensions));
    }

    public CertTemplateBuilder setIssuer(X500Name x500Name) {
        this.d = x500Name;
        return this;
    }

    public CertTemplateBuilder setIssuerUID(DERBitString dERBitString) {
        this.h = dERBitString;
        return this;
    }

    public CertTemplateBuilder setPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.g = subjectPublicKeyInfo;
        return this;
    }

    public CertTemplateBuilder setSerialNumber(ASN1Integer aSN1Integer) {
        this.b = aSN1Integer;
        return this;
    }

    public CertTemplateBuilder setSigningAlg(AlgorithmIdentifier algorithmIdentifier) {
        this.c = algorithmIdentifier;
        return this;
    }

    public CertTemplateBuilder setSubject(X500Name x500Name) {
        this.f = x500Name;
        return this;
    }

    public CertTemplateBuilder setSubjectUID(DERBitString dERBitString) {
        this.i = dERBitString;
        return this;
    }

    public CertTemplateBuilder setValidity(OptionalValidity optionalValidity) {
        this.e = optionalValidity;
        return this;
    }

    public CertTemplateBuilder setVersion(int i) {
        this.f14407a = new ASN1Integer(i);
        return this;
    }
}
