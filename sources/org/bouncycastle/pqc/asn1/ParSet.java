package org.bouncycastle.pqc.asn1;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class ParSet extends ASN1Object {
    public static final BigInteger l = BigInteger.valueOf(0);
    public int h;
    public int[] i;
    public int[] j;
    public int[] k;

    public ParSet(int i, int[] iArr, int[] iArr2, int[] iArr3) {
        this.h = i;
        this.i = iArr;
        this.j = iArr2;
        this.k = iArr3;
    }

    public ParSet(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 4) {
            throw new IllegalArgumentException("sie of seqOfParams = " + aSN1Sequence.size());
        }
        this.h = a(((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue());
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(2);
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
        if (aSN1Sequence2.size() != this.h || aSN1Sequence3.size() != this.h || aSN1Sequence4.size() != this.h) {
            throw new IllegalArgumentException("invalid size of sequences");
        }
        this.i = new int[aSN1Sequence2.size()];
        this.j = new int[aSN1Sequence3.size()];
        this.k = new int[aSN1Sequence4.size()];
        for (int i = 0; i < this.h; i++) {
            this.i[i] = a(((ASN1Integer) aSN1Sequence2.getObjectAt(i)).getValue());
            this.j[i] = a(((ASN1Integer) aSN1Sequence3.getObjectAt(i)).getValue());
            this.k[i] = a(((ASN1Integer) aSN1Sequence4.getObjectAt(i)).getValue());
        }
    }

    public static int a(BigInteger bigInteger) {
        if (bigInteger.compareTo(BigInteger.valueOf(2147483647L)) > 0 || bigInteger.compareTo(l) <= 0) {
            throw new IllegalArgumentException("BigInteger not in Range: " + bigInteger.toString());
        }
        return bigInteger.intValue();
    }

    public static ParSet getInstance(Object obj) {
        if (obj instanceof ParSet) {
            return (ParSet) obj;
        }
        if (obj != null) {
            return new ParSet(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public int[] getH() {
        return Arrays.clone(this.i);
    }

    public int[] getK() {
        return Arrays.clone(this.k);
    }

    public int getT() {
        return this.h;
    }

    public int[] getW() {
        return Arrays.clone(this.j);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        int i = 0;
        while (true) {
            int[] iArr = this.i;
            if (i >= iArr.length) {
                ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
                aSN1EncodableVector4.add(new ASN1Integer(this.h));
                aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector));
                aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector2));
                aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector3));
                return new DERSequence(aSN1EncodableVector4);
            }
            aSN1EncodableVector.add(new ASN1Integer(iArr[i]));
            aSN1EncodableVector2.add(new ASN1Integer(this.j[i]));
            aSN1EncodableVector3.add(new ASN1Integer(this.k[i]));
            i++;
        }
    }
}
