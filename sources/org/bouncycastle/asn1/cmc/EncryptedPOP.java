package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class EncryptedPOP extends ASN1Object {
    public final TaggedRequest h;
    public final ContentInfo i;
    public final AlgorithmIdentifier j;
    public final AlgorithmIdentifier k;
    public final byte[] l;

    public EncryptedPOP(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 5) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.h = TaggedRequest.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = ContentInfo.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(2));
        this.k = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(3));
        this.l = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(4)).getOctets());
    }

    public EncryptedPOP(TaggedRequest taggedRequest, ContentInfo contentInfo, AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) {
        this.h = taggedRequest;
        this.i = contentInfo;
        this.j = algorithmIdentifier;
        this.k = algorithmIdentifier2;
        this.l = Arrays.clone(bArr);
    }

    public static EncryptedPOP getInstance(Object obj) {
        if (obj instanceof EncryptedPOP) {
            return (EncryptedPOP) obj;
        }
        if (obj != null) {
            return new EncryptedPOP(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ContentInfo getCms() {
        return this.i;
    }

    public TaggedRequest getRequest() {
        return this.h;
    }

    public AlgorithmIdentifier getThePOPAlgID() {
        return this.j;
    }

    public byte[] getWitness() {
        return Arrays.clone(this.l);
    }

    public AlgorithmIdentifier getWitnessAlgID() {
        return this.k;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        aSN1EncodableVector.add(this.k);
        aSN1EncodableVector.add(new DEROctetString(this.l));
        return new DERSequence(aSN1EncodableVector);
    }
}
