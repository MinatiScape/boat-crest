package org.bouncycastle.asn1.cmp;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class PKIStatusInfo extends ASN1Object {
    public ASN1Integer h;
    public PKIFreeText i;
    public DERBitString j;

    public PKIStatusInfo(ASN1Sequence aSN1Sequence) {
        ASN1Encodable objectAt;
        this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = null;
        this.j = null;
        if (aSN1Sequence.size() > 2) {
            this.i = PKIFreeText.getInstance(aSN1Sequence.getObjectAt(1));
            objectAt = aSN1Sequence.getObjectAt(2);
        } else if (aSN1Sequence.size() <= 1) {
            return;
        } else {
            objectAt = aSN1Sequence.getObjectAt(1);
            if (!(objectAt instanceof DERBitString)) {
                this.i = PKIFreeText.getInstance(objectAt);
                return;
            }
        }
        this.j = DERBitString.getInstance(objectAt);
    }

    public PKIStatusInfo(PKIStatus pKIStatus) {
        this.h = ASN1Integer.getInstance(pKIStatus.toASN1Primitive());
    }

    public PKIStatusInfo(PKIStatus pKIStatus, PKIFreeText pKIFreeText) {
        this.h = ASN1Integer.getInstance(pKIStatus.toASN1Primitive());
        this.i = pKIFreeText;
    }

    public PKIStatusInfo(PKIStatus pKIStatus, PKIFreeText pKIFreeText, PKIFailureInfo pKIFailureInfo) {
        this.h = ASN1Integer.getInstance(pKIStatus.toASN1Primitive());
        this.i = pKIFreeText;
        this.j = pKIFailureInfo;
    }

    public static PKIStatusInfo getInstance(Object obj) {
        if (obj instanceof PKIStatusInfo) {
            return (PKIStatusInfo) obj;
        }
        if (obj != null) {
            return new PKIStatusInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static PKIStatusInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public DERBitString getFailInfo() {
        return this.j;
    }

    public BigInteger getStatus() {
        return this.h.getValue();
    }

    public PKIFreeText getStatusString() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        PKIFreeText pKIFreeText = this.i;
        if (pKIFreeText != null) {
            aSN1EncodableVector.add(pKIFreeText);
        }
        DERBitString dERBitString = this.j;
        if (dERBitString != null) {
            aSN1EncodableVector.add(dERBitString);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
