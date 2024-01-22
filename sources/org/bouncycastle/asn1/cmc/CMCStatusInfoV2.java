package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
/* loaded from: classes12.dex */
public class CMCStatusInfoV2 extends ASN1Object {
    public final CMCStatus h;
    public final ASN1Sequence i;
    public final DERUTF8String j;
    public final OtherStatusInfo k;

    public CMCStatusInfoV2(ASN1Sequence aSN1Sequence) {
        ASN1Encodable objectAt;
        if (aSN1Sequence.size() < 2 || aSN1Sequence.size() > 4) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.h = CMCStatus.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() > 2) {
            if (aSN1Sequence.size() == 4) {
                this.j = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(2));
                objectAt = aSN1Sequence.getObjectAt(3);
            } else if (aSN1Sequence.getObjectAt(2) instanceof DERUTF8String) {
                this.j = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(2));
            } else {
                this.j = null;
                objectAt = aSN1Sequence.getObjectAt(2);
            }
            this.k = OtherStatusInfo.getInstance(objectAt);
            return;
        }
        this.j = null;
        this.k = null;
    }

    public CMCStatusInfoV2(CMCStatus cMCStatus, ASN1Sequence aSN1Sequence, DERUTF8String dERUTF8String, OtherStatusInfo otherStatusInfo) {
        this.h = cMCStatus;
        this.i = aSN1Sequence;
        this.j = dERUTF8String;
        this.k = otherStatusInfo;
    }

    public static CMCStatusInfoV2 getInstance(Object obj) {
        if (obj instanceof CMCStatusInfoV2) {
            return (CMCStatusInfoV2) obj;
        }
        if (obj != null) {
            return new CMCStatusInfoV2(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BodyPartID[] getBodyList() {
        return a.c(this.i);
    }

    public OtherStatusInfo getOtherStatusInfo() {
        return this.k;
    }

    public DERUTF8String getStatusString() {
        return this.j;
    }

    public CMCStatus getcMCStatus() {
        return this.h;
    }

    public boolean hasOtherInfo() {
        return this.k != null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        DERUTF8String dERUTF8String = this.j;
        if (dERUTF8String != null) {
            aSN1EncodableVector.add(dERUTF8String);
        }
        OtherStatusInfo otherStatusInfo = this.k;
        if (otherStatusInfo != null) {
            aSN1EncodableVector.add(otherStatusInfo);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
