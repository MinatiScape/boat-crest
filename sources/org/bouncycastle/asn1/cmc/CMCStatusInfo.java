package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
/* loaded from: classes12.dex */
public class CMCStatusInfo extends ASN1Object {
    public final CMCStatus h;
    public final ASN1Sequence i;
    public final DERUTF8String j;
    public final OtherInfo k;

    /* loaded from: classes12.dex */
    public static class OtherInfo extends ASN1Object implements ASN1Choice {
        public final CMCFailInfo h;
        public final PendInfo i;

        public OtherInfo(CMCFailInfo cMCFailInfo) {
            this(cMCFailInfo, null);
        }

        public OtherInfo(CMCFailInfo cMCFailInfo, PendInfo pendInfo) {
            this.h = cMCFailInfo;
            this.i = pendInfo;
        }

        public OtherInfo(PendInfo pendInfo) {
            this(null, pendInfo);
        }

        public static OtherInfo b(Object obj) {
            if (obj instanceof OtherInfo) {
                return (OtherInfo) obj;
            }
            if (obj instanceof ASN1Encodable) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1Integer) {
                    return new OtherInfo(CMCFailInfo.getInstance(aSN1Primitive));
                }
                if (aSN1Primitive instanceof ASN1Sequence) {
                    return new OtherInfo(PendInfo.getInstance(aSN1Primitive));
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance(): " + obj.getClass().getName());
        }

        public boolean isFailInfo() {
            return this.h != null;
        }

        @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
        public ASN1Primitive toASN1Primitive() {
            PendInfo pendInfo = this.i;
            return pendInfo != null ? pendInfo.toASN1Primitive() : this.h.toASN1Primitive();
        }
    }

    public CMCStatusInfo(ASN1Sequence aSN1Sequence) {
        ASN1Encodable objectAt;
        if (aSN1Sequence.size() < 2 || aSN1Sequence.size() > 4) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.h = CMCStatus.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() <= 3) {
            if (aSN1Sequence.size() <= 2) {
                this.j = null;
            } else if (aSN1Sequence.getObjectAt(2) instanceof DERUTF8String) {
                this.j = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(2));
            } else {
                this.j = null;
                objectAt = aSN1Sequence.getObjectAt(2);
            }
            this.k = null;
            return;
        }
        this.j = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(2));
        objectAt = aSN1Sequence.getObjectAt(3);
        this.k = OtherInfo.b(objectAt);
    }

    public CMCStatusInfo(CMCStatus cMCStatus, ASN1Sequence aSN1Sequence, DERUTF8String dERUTF8String, OtherInfo otherInfo) {
        this.h = cMCStatus;
        this.i = aSN1Sequence;
        this.j = dERUTF8String;
        this.k = otherInfo;
    }

    public static CMCStatusInfo getInstance(Object obj) {
        if (obj instanceof CMCStatusInfo) {
            return (CMCStatusInfo) obj;
        }
        if (obj != null) {
            return new CMCStatusInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BodyPartID[] getBodyList() {
        return a.c(this.i);
    }

    public CMCStatus getCMCStatus() {
        return this.h;
    }

    public OtherInfo getOtherInfo() {
        return this.k;
    }

    public DERUTF8String getStatusString() {
        return this.j;
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
        OtherInfo otherInfo = this.k;
        if (otherInfo != null) {
            aSN1EncodableVector.add(otherInfo);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
