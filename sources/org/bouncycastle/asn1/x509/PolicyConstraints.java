package org.bouncycastle.asn1.x509;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class PolicyConstraints extends ASN1Object {
    public BigInteger h;
    public BigInteger i;

    public PolicyConstraints(BigInteger bigInteger, BigInteger bigInteger2) {
        this.h = bigInteger;
        this.i = bigInteger2;
    }

    public PolicyConstraints(ASN1Sequence aSN1Sequence) {
        for (int i = 0; i != aSN1Sequence.size(); i++) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i));
            if (aSN1TaggedObject.getTagNo() == 0) {
                this.h = ASN1Integer.getInstance(aSN1TaggedObject, false).getValue();
            } else if (aSN1TaggedObject.getTagNo() != 1) {
                throw new IllegalArgumentException("Unknown tag encountered.");
            } else {
                this.i = ASN1Integer.getInstance(aSN1TaggedObject, false).getValue();
            }
        }
    }

    public static PolicyConstraints fromExtensions(Extensions extensions) {
        return getInstance(extensions.getExtensionParsedValue(Extension.policyConstraints));
    }

    public static PolicyConstraints getInstance(Object obj) {
        if (obj instanceof PolicyConstraints) {
            return (PolicyConstraints) obj;
        }
        if (obj != null) {
            return new PolicyConstraints(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getInhibitPolicyMapping() {
        return this.i;
    }

    public BigInteger getRequireExplicitPolicyMapping() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.h != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, new ASN1Integer(this.h)));
        }
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, new ASN1Integer(this.i)));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
