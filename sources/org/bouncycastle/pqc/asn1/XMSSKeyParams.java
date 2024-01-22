package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes13.dex */
public class XMSSKeyParams extends ASN1Object {
    public final ASN1Integer h;
    public final int i;
    public final AlgorithmIdentifier j;

    public XMSSKeyParams(int i, AlgorithmIdentifier algorithmIdentifier) {
        this.h = new ASN1Integer(0L);
        this.i = i;
        this.j = algorithmIdentifier;
    }

    public XMSSKeyParams(ASN1Sequence aSN1Sequence) {
        this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue().intValue();
        this.j = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static XMSSKeyParams getInstance(Object obj) {
        if (obj instanceof XMSSKeyParams) {
            return (XMSSKeyParams) obj;
        }
        if (obj != null) {
            return new XMSSKeyParams(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public int getHeight() {
        return this.i;
    }

    public AlgorithmIdentifier getTreeDigest() {
        return this.j;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(new ASN1Integer(this.i));
        aSN1EncodableVector.add(this.j);
        return new DERSequence(aSN1EncodableVector);
    }
}
