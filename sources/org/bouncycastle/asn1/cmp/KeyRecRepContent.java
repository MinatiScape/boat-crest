package org.bouncycastle.asn1.cmp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class KeyRecRepContent extends ASN1Object {
    public PKIStatusInfo h;
    public CMPCertificate i;
    public ASN1Sequence j;
    public ASN1Sequence k;

    public KeyRecRepContent(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = PKIStatusInfo.getInstance(objects.nextElement());
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objects.nextElement());
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                this.i = CMPCertificate.getInstance(aSN1TaggedObject.getObject());
            } else if (tagNo == 1) {
                this.j = ASN1Sequence.getInstance(aSN1TaggedObject.getObject());
            } else if (tagNo != 2) {
                throw new IllegalArgumentException("unknown tag number: " + aSN1TaggedObject.getTagNo());
            } else {
                this.k = ASN1Sequence.getInstance(aSN1TaggedObject.getObject());
            }
        }
    }

    public static KeyRecRepContent getInstance(Object obj) {
        if (obj instanceof KeyRecRepContent) {
            return (KeyRecRepContent) obj;
        }
        if (obj != null) {
            return new KeyRecRepContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public final void a(ASN1EncodableVector aSN1EncodableVector, int i, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, i, aSN1Encodable));
        }
    }

    public CMPCertificate[] getCaCerts() {
        ASN1Sequence aSN1Sequence = this.j;
        if (aSN1Sequence == null) {
            return null;
        }
        int size = aSN1Sequence.size();
        CMPCertificate[] cMPCertificateArr = new CMPCertificate[size];
        for (int i = 0; i != size; i++) {
            cMPCertificateArr[i] = CMPCertificate.getInstance(this.j.getObjectAt(i));
        }
        return cMPCertificateArr;
    }

    public CertifiedKeyPair[] getKeyPairHist() {
        ASN1Sequence aSN1Sequence = this.k;
        if (aSN1Sequence == null) {
            return null;
        }
        int size = aSN1Sequence.size();
        CertifiedKeyPair[] certifiedKeyPairArr = new CertifiedKeyPair[size];
        for (int i = 0; i != size; i++) {
            certifiedKeyPairArr[i] = CertifiedKeyPair.getInstance(this.k.getObjectAt(i));
        }
        return certifiedKeyPairArr;
    }

    public CMPCertificate getNewSigCert() {
        return this.i;
    }

    public PKIStatusInfo getStatus() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        a(aSN1EncodableVector, 0, this.i);
        a(aSN1EncodableVector, 1, this.j);
        a(aSN1EncodableVector, 2, this.k);
        return new DERSequence(aSN1EncodableVector);
    }
}
