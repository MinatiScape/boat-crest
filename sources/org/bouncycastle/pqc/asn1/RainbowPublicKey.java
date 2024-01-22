package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.pqc.crypto.rainbow.util.RainbowUtil;
/* loaded from: classes13.dex */
public class RainbowPublicKey extends ASN1Object {
    public ASN1Integer h;
    public ASN1ObjectIdentifier i;
    public ASN1Integer j;
    public byte[][] k;
    public byte[][] l;
    public byte[] m;

    public RainbowPublicKey(int i, short[][] sArr, short[][] sArr2, short[] sArr3) {
        this.h = new ASN1Integer(0L);
        this.j = new ASN1Integer(i);
        this.k = RainbowUtil.convertArray(sArr);
        this.l = RainbowUtil.convertArray(sArr2);
        this.m = RainbowUtil.convertArray(sArr3);
    }

    public RainbowPublicKey(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        } else {
            this.i = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        }
        this.j = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
        ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(2));
        this.k = new byte[aSN1Sequence2.size()];
        for (int i = 0; i < aSN1Sequence2.size(); i++) {
            this.k[i] = ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(i)).getOctets();
        }
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
        this.l = new byte[aSN1Sequence3.size()];
        for (int i2 = 0; i2 < aSN1Sequence3.size(); i2++) {
            this.l[i2] = ASN1OctetString.getInstance(aSN1Sequence3.getObjectAt(i2)).getOctets();
        }
        this.m = ASN1OctetString.getInstance(((ASN1Sequence) aSN1Sequence.getObjectAt(4)).getObjectAt(0)).getOctets();
    }

    public static RainbowPublicKey getInstance(Object obj) {
        if (obj instanceof RainbowPublicKey) {
            return (RainbowPublicKey) obj;
        }
        if (obj != null) {
            return new RainbowPublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public short[][] getCoeffQuadratic() {
        return RainbowUtil.convertArray(this.k);
    }

    public short[] getCoeffScalar() {
        return RainbowUtil.convertArray(this.m);
    }

    public short[][] getCoeffSingular() {
        return RainbowUtil.convertArray(this.l);
    }

    public int getDocLength() {
        return this.j.getValue().intValue();
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Encodable aSN1Encodable = this.h;
        if (aSN1Encodable == null) {
            aSN1Encodable = this.i;
        }
        aSN1EncodableVector.add(aSN1Encodable);
        aSN1EncodableVector.add(this.j);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[][] bArr = this.k;
            if (i2 >= bArr.length) {
                break;
            }
            aSN1EncodableVector2.add(new DEROctetString(bArr[i2]));
            i2++;
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        while (true) {
            byte[][] bArr2 = this.l;
            if (i >= bArr2.length) {
                aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector3));
                ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
                aSN1EncodableVector4.add(new DEROctetString(this.m));
                aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector4));
                return new DERSequence(aSN1EncodableVector);
            }
            aSN1EncodableVector3.add(new DEROctetString(bArr2[i]));
            i++;
        }
    }
}
