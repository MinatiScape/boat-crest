package org.bouncycastle.asn1.mozilla;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class SignedPublicKeyAndChallenge extends ASN1Object {
    public final PublicKeyAndChallenge h;
    public final ASN1Sequence i;

    public SignedPublicKeyAndChallenge(ASN1Sequence aSN1Sequence) {
        this.i = aSN1Sequence;
        this.h = PublicKeyAndChallenge.getInstance(aSN1Sequence.getObjectAt(0));
    }

    public static SignedPublicKeyAndChallenge getInstance(Object obj) {
        if (obj instanceof SignedPublicKeyAndChallenge) {
            return (SignedPublicKeyAndChallenge) obj;
        }
        if (obj != null) {
            return new SignedPublicKeyAndChallenge(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public PublicKeyAndChallenge getPublicKeyAndChallenge() {
        return this.h;
    }

    public DERBitString getSignature() {
        return DERBitString.getInstance(this.i.getObjectAt(2));
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return AlgorithmIdentifier.getInstance(this.i.getObjectAt(1));
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.i;
    }
}
