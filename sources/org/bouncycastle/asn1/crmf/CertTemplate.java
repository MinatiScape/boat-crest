package org.bouncycastle.asn1.crmf;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
/* loaded from: classes12.dex */
public class CertTemplate extends ASN1Object {
    public ASN1Sequence h;
    public ASN1Integer i;
    public ASN1Integer j;
    public AlgorithmIdentifier k;
    public X500Name l;
    public OptionalValidity m;
    public X500Name n;
    public SubjectPublicKeyInfo o;
    public DERBitString p;
    public DERBitString q;
    public Extensions r;

    public CertTemplate(ASN1Sequence aSN1Sequence) {
        this.h = aSN1Sequence;
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
            switch (aSN1TaggedObject.getTagNo()) {
                case 0:
                    this.i = ASN1Integer.getInstance(aSN1TaggedObject, false);
                    break;
                case 1:
                    this.j = ASN1Integer.getInstance(aSN1TaggedObject, false);
                    break;
                case 2:
                    this.k = AlgorithmIdentifier.getInstance(aSN1TaggedObject, false);
                    break;
                case 3:
                    this.l = X500Name.getInstance(aSN1TaggedObject, true);
                    break;
                case 4:
                    this.m = OptionalValidity.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, false));
                    break;
                case 5:
                    this.n = X500Name.getInstance(aSN1TaggedObject, true);
                    break;
                case 6:
                    this.o = SubjectPublicKeyInfo.getInstance(aSN1TaggedObject, false);
                    break;
                case 7:
                    this.p = DERBitString.getInstance(aSN1TaggedObject, false);
                    break;
                case 8:
                    this.q = DERBitString.getInstance(aSN1TaggedObject, false);
                    break;
                case 9:
                    this.r = Extensions.getInstance(aSN1TaggedObject, false);
                    break;
                default:
                    throw new IllegalArgumentException("unknown tag: " + aSN1TaggedObject.getTagNo());
            }
        }
    }

    public static CertTemplate getInstance(Object obj) {
        if (obj instanceof CertTemplate) {
            return (CertTemplate) obj;
        }
        if (obj != null) {
            return new CertTemplate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public Extensions getExtensions() {
        return this.r;
    }

    public X500Name getIssuer() {
        return this.l;
    }

    public DERBitString getIssuerUID() {
        return this.p;
    }

    public SubjectPublicKeyInfo getPublicKey() {
        return this.o;
    }

    public ASN1Integer getSerialNumber() {
        return this.j;
    }

    public AlgorithmIdentifier getSigningAlg() {
        return this.k;
    }

    public X500Name getSubject() {
        return this.n;
    }

    public DERBitString getSubjectUID() {
        return this.q;
    }

    public OptionalValidity getValidity() {
        return this.m;
    }

    public int getVersion() {
        return this.i.getValue().intValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }
}
