package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class UserNotice extends ASN1Object {
    public final NoticeReference h;
    public final DisplayText i;

    public UserNotice(ASN1Sequence aSN1Sequence) {
        ASN1Encodable objectAt;
        if (aSN1Sequence.size() != 2) {
            if (aSN1Sequence.size() == 1) {
                if (aSN1Sequence.getObjectAt(0).toASN1Primitive() instanceof ASN1Sequence) {
                    this.h = NoticeReference.getInstance(aSN1Sequence.getObjectAt(0));
                } else {
                    this.h = null;
                    objectAt = aSN1Sequence.getObjectAt(0);
                }
            } else if (aSN1Sequence.size() != 0) {
                throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
            } else {
                this.h = null;
            }
            this.i = null;
            return;
        }
        this.h = NoticeReference.getInstance(aSN1Sequence.getObjectAt(0));
        objectAt = aSN1Sequence.getObjectAt(1);
        this.i = DisplayText.getInstance(objectAt);
    }

    public UserNotice(NoticeReference noticeReference, String str) {
        this(noticeReference, new DisplayText(str));
    }

    public UserNotice(NoticeReference noticeReference, DisplayText displayText) {
        this.h = noticeReference;
        this.i = displayText;
    }

    public static UserNotice getInstance(Object obj) {
        if (obj instanceof UserNotice) {
            return (UserNotice) obj;
        }
        if (obj != null) {
            return new UserNotice(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DisplayText getExplicitText() {
        return this.i;
    }

    public NoticeReference getNoticeRef() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        NoticeReference noticeReference = this.h;
        if (noticeReference != null) {
            aSN1EncodableVector.add(noticeReference);
        }
        DisplayText displayText = this.i;
        if (displayText != null) {
            aSN1EncodableVector.add(displayText);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
