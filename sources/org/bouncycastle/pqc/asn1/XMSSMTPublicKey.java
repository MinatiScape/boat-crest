package org.bouncycastle.pqc.asn1;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class XMSSMTPublicKey extends ASN1Object {
    public final byte[] h;
    public final byte[] i;

    public XMSSMTPublicKey(ASN1Sequence aSN1Sequence) {
        if (!ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue().equals(BigInteger.valueOf(0L))) {
            throw new IllegalArgumentException("unknown version of sequence");
        }
        this.h = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets());
        this.i = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(2)).getOctets());
    }

    public XMSSMTPublicKey(byte[] bArr, byte[] bArr2) {
        this.h = Arrays.clone(bArr);
        this.i = Arrays.clone(bArr2);
    }

    public static XMSSMTPublicKey getInstance(Object obj) {
        if (obj instanceof XMSSMTPublicKey) {
            return (XMSSMTPublicKey) obj;
        }
        if (obj != null) {
            return new XMSSMTPublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[] getPublicSeed() {
        return Arrays.clone(this.h);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.i);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(0L));
        aSN1EncodableVector.add(new DEROctetString(this.h));
        aSN1EncodableVector.add(new DEROctetString(this.i));
        return new DERSequence(aSN1EncodableVector);
    }
}
