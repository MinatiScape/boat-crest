package org.bouncycastle.asn1.cms;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class RsaKemParameters extends ASN1Object {
    public final AlgorithmIdentifier h;
    public final BigInteger i;

    public RsaKemParameters(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("ASN.1 SEQUENCE should be of length 2");
        }
        this.h = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue();
    }

    public RsaKemParameters(AlgorithmIdentifier algorithmIdentifier, int i) {
        this.h = algorithmIdentifier;
        this.i = BigInteger.valueOf(i);
    }

    public static RsaKemParameters getInstance(Object obj) {
        if (obj instanceof RsaKemParameters) {
            return (RsaKemParameters) obj;
        }
        if (obj != null) {
            return new RsaKemParameters(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getKeyDerivationFunction() {
        return this.h;
    }

    public BigInteger getKeyLength() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(new ASN1Integer(this.i));
        return new DERSequence(aSN1EncodableVector);
    }
}
