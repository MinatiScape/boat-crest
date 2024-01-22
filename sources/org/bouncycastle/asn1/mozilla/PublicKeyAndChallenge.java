package org.bouncycastle.asn1.mozilla;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
/* loaded from: classes12.dex */
public class PublicKeyAndChallenge extends ASN1Object {
    public ASN1Sequence h;
    public SubjectPublicKeyInfo i;
    public DERIA5String j;

    public PublicKeyAndChallenge(ASN1Sequence aSN1Sequence) {
        this.h = aSN1Sequence;
        this.i = SubjectPublicKeyInfo.getInstance(aSN1Sequence.getObjectAt(0));
        this.j = DERIA5String.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static PublicKeyAndChallenge getInstance(Object obj) {
        if (obj instanceof PublicKeyAndChallenge) {
            return (PublicKeyAndChallenge) obj;
        }
        if (obj != null) {
            return new PublicKeyAndChallenge(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DERIA5String getChallenge() {
        return this.j;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }
}
