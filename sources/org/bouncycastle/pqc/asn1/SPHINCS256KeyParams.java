package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes13.dex */
public class SPHINCS256KeyParams extends ASN1Object {
    public final ASN1Integer h;
    public final AlgorithmIdentifier i;

    public SPHINCS256KeyParams(ASN1Sequence aSN1Sequence) {
        this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public SPHINCS256KeyParams(AlgorithmIdentifier algorithmIdentifier) {
        this.h = new ASN1Integer(0L);
        this.i = algorithmIdentifier;
    }

    public static final SPHINCS256KeyParams getInstance(Object obj) {
        if (obj instanceof SPHINCS256KeyParams) {
            return (SPHINCS256KeyParams) obj;
        }
        if (obj != null) {
            return new SPHINCS256KeyParams(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getTreeDigest() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        return new DERSequence(aSN1EncodableVector);
    }
}
