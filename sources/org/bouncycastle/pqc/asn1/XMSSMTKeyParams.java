package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes13.dex */
public class XMSSMTKeyParams extends ASN1Object {
    public final ASN1Integer h;
    public final int i;
    public final int j;
    public final AlgorithmIdentifier k;

    public XMSSMTKeyParams(int i, int i2, AlgorithmIdentifier algorithmIdentifier) {
        this.h = new ASN1Integer(0L);
        this.i = i;
        this.j = i2;
        this.k = algorithmIdentifier;
    }

    public XMSSMTKeyParams(ASN1Sequence aSN1Sequence) {
        this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue().intValue();
        this.j = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(2)).getValue().intValue();
        this.k = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(3));
    }

    public static XMSSMTKeyParams getInstance(Object obj) {
        if (obj instanceof XMSSMTKeyParams) {
            return (XMSSMTKeyParams) obj;
        }
        if (obj != null) {
            return new XMSSMTKeyParams(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public int getHeight() {
        return this.i;
    }

    public int getLayers() {
        return this.j;
    }

    public AlgorithmIdentifier getTreeDigest() {
        return this.k;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(new ASN1Integer(this.i));
        aSN1EncodableVector.add(new ASN1Integer(this.j));
        aSN1EncodableVector.add(this.k);
        return new DERSequence(aSN1EncodableVector);
    }
}
