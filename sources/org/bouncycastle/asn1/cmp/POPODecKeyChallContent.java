package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
/* loaded from: classes12.dex */
public class POPODecKeyChallContent extends ASN1Object {
    public ASN1Sequence h;

    public POPODecKeyChallContent(ASN1Sequence aSN1Sequence) {
        this.h = aSN1Sequence;
    }

    public static POPODecKeyChallContent getInstance(Object obj) {
        if (obj instanceof POPODecKeyChallContent) {
            return (POPODecKeyChallContent) obj;
        }
        if (obj != null) {
            return new POPODecKeyChallContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }

    public Challenge[] toChallengeArray() {
        int size = this.h.size();
        Challenge[] challengeArr = new Challenge[size];
        for (int i = 0; i != size; i++) {
            challengeArr[i] = Challenge.getInstance(this.h.getObjectAt(i));
        }
        return challengeArr;
    }
}
