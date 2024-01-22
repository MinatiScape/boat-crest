package org.bouncycastle.asn1.misc;

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
/* loaded from: classes12.dex */
public class ScryptParams extends ASN1Object {
    public final byte[] h;
    public final BigInteger i;
    public final BigInteger j;
    public final BigInteger k;
    public final BigInteger l;

    public ScryptParams(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 4 && aSN1Sequence.size() != 5) {
            throw new IllegalArgumentException("invalid sequence: size = " + aSN1Sequence.size());
        }
        this.h = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0)).getOctets());
        this.i = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue();
        this.j = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(2)).getValue();
        this.k = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(3)).getValue();
        this.l = aSN1Sequence.size() == 5 ? ASN1Integer.getInstance(aSN1Sequence.getObjectAt(4)).getValue() : null;
    }

    public ScryptParams(byte[] bArr, int i, int i2, int i3) {
        this(bArr, BigInteger.valueOf(i), BigInteger.valueOf(i2), BigInteger.valueOf(i3), (BigInteger) null);
    }

    public ScryptParams(byte[] bArr, int i, int i2, int i3, int i4) {
        this(bArr, BigInteger.valueOf(i), BigInteger.valueOf(i2), BigInteger.valueOf(i3), BigInteger.valueOf(i4));
    }

    public ScryptParams(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.h = Arrays.clone(bArr);
        this.i = bigInteger;
        this.j = bigInteger2;
        this.k = bigInteger3;
        this.l = bigInteger4;
    }

    public static ScryptParams getInstance(Object obj) {
        if (obj instanceof ScryptParams) {
            return (ScryptParams) obj;
        }
        if (obj != null) {
            return new ScryptParams(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getBlockSize() {
        return this.j;
    }

    public BigInteger getCostParameter() {
        return this.i;
    }

    public BigInteger getKeyLength() {
        return this.l;
    }

    public BigInteger getParallelizationParameter() {
        return this.k;
    }

    public byte[] getSalt() {
        return Arrays.clone(this.h);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new DEROctetString(this.h));
        aSN1EncodableVector.add(new ASN1Integer(this.i));
        aSN1EncodableVector.add(new ASN1Integer(this.j));
        aSN1EncodableVector.add(new ASN1Integer(this.k));
        BigInteger bigInteger = this.l;
        if (bigInteger != null) {
            aSN1EncodableVector.add(new ASN1Integer(bigInteger));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
