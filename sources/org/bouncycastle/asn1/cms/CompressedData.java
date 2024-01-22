package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class CompressedData extends ASN1Object {
    public ASN1Integer h;
    public AlgorithmIdentifier i;
    public ContentInfo j;

    public CompressedData(ASN1Sequence aSN1Sequence) {
        this.h = (ASN1Integer) aSN1Sequence.getObjectAt(0);
        this.i = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = ContentInfo.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public CompressedData(AlgorithmIdentifier algorithmIdentifier, ContentInfo contentInfo) {
        this.h = new ASN1Integer(0L);
        this.i = algorithmIdentifier;
        this.j = contentInfo;
    }

    public static CompressedData getInstance(Object obj) {
        if (obj instanceof CompressedData) {
            return (CompressedData) obj;
        }
        if (obj != null) {
            return new CompressedData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static CompressedData getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public AlgorithmIdentifier getCompressionAlgorithmIdentifier() {
        return this.i;
    }

    public ContentInfo getEncapContentInfo() {
        return this.j;
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        return new BERSequence(aSN1EncodableVector);
    }
}
