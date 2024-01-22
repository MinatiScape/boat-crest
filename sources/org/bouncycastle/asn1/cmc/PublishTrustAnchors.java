package org.bouncycastle.asn1.cmc;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class PublishTrustAnchors extends ASN1Object {
    public final ASN1Integer h;
    public final AlgorithmIdentifier i;
    public final ASN1Sequence j;

    public PublishTrustAnchors(BigInteger bigInteger, AlgorithmIdentifier algorithmIdentifier, byte[][] bArr) {
        this.h = new ASN1Integer(bigInteger);
        this.i = algorithmIdentifier;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != bArr.length; i++) {
            aSN1EncodableVector.add(new DEROctetString(Arrays.clone(bArr[i])));
        }
        this.j = new DERSequence(aSN1EncodableVector);
    }

    public PublishTrustAnchors(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static PublishTrustAnchors getInstance(Object obj) {
        if (obj instanceof PublishTrustAnchors) {
            return (PublishTrustAnchors) obj;
        }
        if (obj != null) {
            return new PublishTrustAnchors(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[][] getAnchorHashes() {
        int size = this.j.size();
        byte[][] bArr = new byte[size];
        for (int i = 0; i != size; i++) {
            bArr[i] = Arrays.clone(ASN1OctetString.getInstance(this.j.getObjectAt(i)).getOctets());
        }
        return bArr;
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        return this.i;
    }

    public BigInteger getSeqNumber() {
        return this.h.getValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        return new DERSequence(aSN1EncodableVector);
    }
}
