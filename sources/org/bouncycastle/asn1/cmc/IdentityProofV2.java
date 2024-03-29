package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class IdentityProofV2 extends ASN1Object {
    public final AlgorithmIdentifier h;
    public final AlgorithmIdentifier i;
    public final byte[] j;

    public IdentityProofV2(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.h = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(2)).getOctets());
    }

    public IdentityProofV2(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) {
        this.h = algorithmIdentifier;
        this.i = algorithmIdentifier2;
        this.j = Arrays.clone(bArr);
    }

    public static IdentityProofV2 getInstance(Object obj) {
        if (obj instanceof IdentityProofV2) {
            return (IdentityProofV2) obj;
        }
        if (obj != null) {
            return new IdentityProofV2(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getMacAlgId() {
        return this.i;
    }

    public AlgorithmIdentifier getProofAlgID() {
        return this.h;
    }

    public byte[] getWitness() {
        return Arrays.clone(this.j);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(new DEROctetString(getWitness()));
        return new DERSequence(aSN1EncodableVector);
    }
}
