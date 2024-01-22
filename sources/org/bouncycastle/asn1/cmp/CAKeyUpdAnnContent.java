package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class CAKeyUpdAnnContent extends ASN1Object {
    public CMPCertificate h;
    public CMPCertificate i;
    public CMPCertificate j;

    public CAKeyUpdAnnContent(ASN1Sequence aSN1Sequence) {
        this.h = CMPCertificate.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = CMPCertificate.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = CMPCertificate.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public CAKeyUpdAnnContent(CMPCertificate cMPCertificate, CMPCertificate cMPCertificate2, CMPCertificate cMPCertificate3) {
        this.h = cMPCertificate;
        this.i = cMPCertificate2;
        this.j = cMPCertificate3;
    }

    public static CAKeyUpdAnnContent getInstance(Object obj) {
        if (obj instanceof CAKeyUpdAnnContent) {
            return (CAKeyUpdAnnContent) obj;
        }
        if (obj != null) {
            return new CAKeyUpdAnnContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CMPCertificate getNewWithNew() {
        return this.j;
    }

    public CMPCertificate getNewWithOld() {
        return this.i;
    }

    public CMPCertificate getOldWithNew() {
        return this.h;
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
