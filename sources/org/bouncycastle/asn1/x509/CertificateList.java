package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.TBSCertList;
/* loaded from: classes12.dex */
public class CertificateList extends ASN1Object {
    public TBSCertList h;
    public AlgorithmIdentifier i;
    public DERBitString j;
    public boolean k = false;
    public int l;

    public CertificateList(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("sequence wrong size for CertificateList");
        }
        this.h = TBSCertList.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = DERBitString.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static CertificateList getInstance(Object obj) {
        if (obj instanceof CertificateList) {
            return (CertificateList) obj;
        }
        if (obj != null) {
            return new CertificateList(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static CertificateList getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public X500Name getIssuer() {
        return this.h.getIssuer();
    }

    public Time getNextUpdate() {
        return this.h.getNextUpdate();
    }

    public Enumeration getRevokedCertificateEnumeration() {
        return this.h.getRevokedCertificateEnumeration();
    }

    public TBSCertList.CRLEntry[] getRevokedCertificates() {
        return this.h.getRevokedCertificates();
    }

    public DERBitString getSignature() {
        return this.j;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.i;
    }

    public TBSCertList getTBSCertList() {
        return this.h;
    }

    public Time getThisUpdate() {
        return this.h.getThisUpdate();
    }

    public int getVersionNumber() {
        return this.h.getVersionNumber();
    }

    @Override // org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        if (!this.k) {
            this.l = super.hashCode();
            this.k = true;
        }
        return this.l;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        return new DERSequence(aSN1EncodableVector);
    }
}
