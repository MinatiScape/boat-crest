package org.bouncycastle.asn1.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class NoticeReference extends ASN1Object {
    public DisplayText h;
    public ASN1Sequence i;

    public NoticeReference(String str, Vector vector) {
        this(str, a(vector));
    }

    public NoticeReference(String str, ASN1EncodableVector aSN1EncodableVector) {
        this(new DisplayText(str), aSN1EncodableVector);
    }

    public NoticeReference(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.h = DisplayText.getInstance(aSN1Sequence.getObjectAt(0));
            this.i = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }

    public NoticeReference(DisplayText displayText, ASN1EncodableVector aSN1EncodableVector) {
        this.h = displayText;
        this.i = new DERSequence(aSN1EncodableVector);
    }

    public static ASN1EncodableVector a(Vector vector) {
        ASN1Integer aSN1Integer;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            Object nextElement = elements.nextElement();
            if (nextElement instanceof BigInteger) {
                aSN1Integer = new ASN1Integer((BigInteger) nextElement);
            } else if (!(nextElement instanceof Integer)) {
                throw new IllegalArgumentException();
            } else {
                aSN1Integer = new ASN1Integer(((Integer) nextElement).intValue());
            }
            aSN1EncodableVector.add(aSN1Integer);
        }
        return aSN1EncodableVector;
    }

    public static NoticeReference getInstance(Object obj) {
        if (obj instanceof NoticeReference) {
            return (NoticeReference) obj;
        }
        if (obj != null) {
            return new NoticeReference(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Integer[] getNoticeNumbers() {
        ASN1Integer[] aSN1IntegerArr = new ASN1Integer[this.i.size()];
        for (int i = 0; i != this.i.size(); i++) {
            aSN1IntegerArr[i] = ASN1Integer.getInstance(this.i.getObjectAt(i));
        }
        return aSN1IntegerArr;
    }

    public DisplayText getOrganization() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        return new DERSequence(aSN1EncodableVector);
    }
}
