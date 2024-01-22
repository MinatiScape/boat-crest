package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class PollRepContent extends ASN1Object {
    public ASN1Integer[] h;
    public ASN1Integer[] i;
    public PKIFreeText[] j;

    public PollRepContent(ASN1Integer aSN1Integer, ASN1Integer aSN1Integer2) {
        this(aSN1Integer, aSN1Integer2, null);
    }

    public PollRepContent(ASN1Integer aSN1Integer, ASN1Integer aSN1Integer2, PKIFreeText pKIFreeText) {
        this.h = r1;
        this.i = r2;
        this.j = r0;
        ASN1Integer[] aSN1IntegerArr = {aSN1Integer};
        ASN1Integer[] aSN1IntegerArr2 = {aSN1Integer2};
        PKIFreeText[] pKIFreeTextArr = {pKIFreeText};
    }

    public PollRepContent(ASN1Sequence aSN1Sequence) {
        this.h = new ASN1Integer[aSN1Sequence.size()];
        this.i = new ASN1Integer[aSN1Sequence.size()];
        this.j = new PKIFreeText[aSN1Sequence.size()];
        for (int i = 0; i != aSN1Sequence.size(); i++) {
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(i));
            this.h[i] = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(0));
            this.i[i] = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(1));
            if (aSN1Sequence2.size() > 2) {
                this.j[i] = PKIFreeText.getInstance(aSN1Sequence2.getObjectAt(2));
            }
        }
    }

    public static PollRepContent getInstance(Object obj) {
        if (obj instanceof PollRepContent) {
            return (PollRepContent) obj;
        }
        if (obj != null) {
            return new PollRepContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Integer getCertReqId(int i) {
        return this.h[i];
    }

    public ASN1Integer getCheckAfter(int i) {
        return this.i[i];
    }

    public PKIFreeText getReason(int i) {
        return this.j[i];
    }

    public int size() {
        return this.h.length;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.h.length; i++) {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.add(this.h[i]);
            aSN1EncodableVector2.add(this.i[i]);
            PKIFreeText[] pKIFreeTextArr = this.j;
            if (pKIFreeTextArr[i] != null) {
                aSN1EncodableVector2.add(pKIFreeTextArr[i]);
            }
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
