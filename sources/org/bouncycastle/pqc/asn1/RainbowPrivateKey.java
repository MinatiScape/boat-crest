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
import org.bouncycastle.pqc.crypto.rainbow.Layer;
import org.bouncycastle.pqc.crypto.rainbow.util.RainbowUtil;
/* loaded from: classes13.dex */
public class RainbowPrivateKey extends ASN1Object {
    public ASN1Integer h;
    public ASN1ObjectIdentifier i;
    public byte[][] j;
    public byte[] k;
    public byte[][] l;
    public byte[] m;
    public byte[] n;
    public Layer[] o;

    public RainbowPrivateKey(ASN1Sequence aSN1Sequence) {
        int i = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        } else {
            this.i = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        }
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
        this.j = new byte[aSN1Sequence2.size()];
        for (int i2 = 0; i2 < aSN1Sequence2.size(); i2++) {
            this.j[i2] = ((ASN1OctetString) aSN1Sequence2.getObjectAt(i2)).getOctets();
        }
        this.k = ((ASN1OctetString) ((ASN1Sequence) aSN1Sequence.getObjectAt(2)).getObjectAt(0)).getOctets();
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
        this.l = new byte[aSN1Sequence3.size()];
        for (int i3 = 0; i3 < aSN1Sequence3.size(); i3++) {
            this.l[i3] = ((ASN1OctetString) aSN1Sequence3.getObjectAt(i3)).getOctets();
        }
        this.m = ((ASN1OctetString) ((ASN1Sequence) aSN1Sequence.getObjectAt(4)).getObjectAt(0)).getOctets();
        this.n = ((ASN1OctetString) ((ASN1Sequence) aSN1Sequence.getObjectAt(5)).getObjectAt(0)).getOctets();
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(6);
        byte[][][][] bArr = new byte[aSN1Sequence4.size()][][];
        byte[][][][] bArr2 = new byte[aSN1Sequence4.size()][][];
        byte[][][] bArr3 = new byte[aSN1Sequence4.size()][];
        byte[][] bArr4 = new byte[aSN1Sequence4.size()];
        int i4 = 0;
        while (i4 < aSN1Sequence4.size()) {
            ASN1Sequence aSN1Sequence5 = (ASN1Sequence) aSN1Sequence4.getObjectAt(i4);
            ASN1Sequence aSN1Sequence6 = (ASN1Sequence) aSN1Sequence5.getObjectAt(i);
            bArr[i4] = new byte[aSN1Sequence6.size()][];
            for (int i5 = i; i5 < aSN1Sequence6.size(); i5++) {
                ASN1Sequence aSN1Sequence7 = (ASN1Sequence) aSN1Sequence6.getObjectAt(i5);
                bArr[i4][i5] = new byte[aSN1Sequence7.size()];
                for (int i6 = 0; i6 < aSN1Sequence7.size(); i6++) {
                    bArr[i4][i5][i6] = ((ASN1OctetString) aSN1Sequence7.getObjectAt(i6)).getOctets();
                }
            }
            ASN1Sequence aSN1Sequence8 = (ASN1Sequence) aSN1Sequence5.getObjectAt(1);
            bArr2[i4] = new byte[aSN1Sequence8.size()][];
            for (int i7 = 0; i7 < aSN1Sequence8.size(); i7++) {
                ASN1Sequence aSN1Sequence9 = (ASN1Sequence) aSN1Sequence8.getObjectAt(i7);
                bArr2[i4][i7] = new byte[aSN1Sequence9.size()];
                for (int i8 = 0; i8 < aSN1Sequence9.size(); i8++) {
                    bArr2[i4][i7][i8] = ((ASN1OctetString) aSN1Sequence9.getObjectAt(i8)).getOctets();
                }
            }
            ASN1Sequence aSN1Sequence10 = (ASN1Sequence) aSN1Sequence5.getObjectAt(2);
            bArr3[i4] = new byte[aSN1Sequence10.size()];
            for (int i9 = 0; i9 < aSN1Sequence10.size(); i9++) {
                bArr3[i4][i9] = ((ASN1OctetString) aSN1Sequence10.getObjectAt(i9)).getOctets();
            }
            bArr4[i4] = ((ASN1OctetString) aSN1Sequence5.getObjectAt(3)).getOctets();
            i4++;
            i = 0;
        }
        int length = this.n.length - 1;
        this.o = new Layer[length];
        int i10 = 0;
        while (i10 < length) {
            byte[] bArr5 = this.n;
            int i11 = i10 + 1;
            this.o[i10] = new Layer(bArr5[i10], bArr5[i11], RainbowUtil.convertArray(bArr[i10]), RainbowUtil.convertArray(bArr2[i10]), RainbowUtil.convertArray(bArr3[i10]), RainbowUtil.convertArray(bArr4[i10]));
            i10 = i11;
        }
    }

    public RainbowPrivateKey(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, Layer[] layerArr) {
        this.h = new ASN1Integer(1L);
        this.j = RainbowUtil.convertArray(sArr);
        this.k = RainbowUtil.convertArray(sArr2);
        this.l = RainbowUtil.convertArray(sArr3);
        this.m = RainbowUtil.convertArray(sArr4);
        this.n = RainbowUtil.convertIntArray(iArr);
        this.o = layerArr;
    }

    public static RainbowPrivateKey getInstance(Object obj) {
        if (obj instanceof RainbowPrivateKey) {
            return (RainbowPrivateKey) obj;
        }
        if (obj != null) {
            return new RainbowPrivateKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public short[] getB1() {
        return RainbowUtil.convertArray(this.k);
    }

    public short[] getB2() {
        return RainbowUtil.convertArray(this.m);
    }

    public short[][] getInvA1() {
        return RainbowUtil.convertArray(this.j);
    }

    public short[][] getInvA2() {
        return RainbowUtil.convertArray(this.l);
    }

    public Layer[] getLayers() {
        return this.o;
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    public int[] getVi() {
        return RainbowUtil.convertArraytoInt(this.n);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Encodable aSN1Encodable = this.h;
        if (aSN1Encodable == null) {
            aSN1Encodable = this.i;
        }
        aSN1EncodableVector.add(aSN1Encodable);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        int i = 0;
        while (true) {
            byte[][] bArr = this.j;
            if (i >= bArr.length) {
                break;
            }
            aSN1EncodableVector2.add(new DEROctetString(bArr[i]));
            i++;
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        aSN1EncodableVector3.add(new DEROctetString(this.k));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector3));
        ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
        int i2 = 0;
        while (true) {
            byte[][] bArr2 = this.l;
            if (i2 >= bArr2.length) {
                break;
            }
            aSN1EncodableVector4.add(new DEROctetString(bArr2[i2]));
            i2++;
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector4));
        ASN1EncodableVector aSN1EncodableVector5 = new ASN1EncodableVector();
        aSN1EncodableVector5.add(new DEROctetString(this.m));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector5));
        ASN1EncodableVector aSN1EncodableVector6 = new ASN1EncodableVector();
        aSN1EncodableVector6.add(new DEROctetString(this.n));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector6));
        ASN1EncodableVector aSN1EncodableVector7 = new ASN1EncodableVector();
        for (int i3 = 0; i3 < this.o.length; i3++) {
            ASN1EncodableVector aSN1EncodableVector8 = new ASN1EncodableVector();
            byte[][][] convertArray = RainbowUtil.convertArray(this.o[i3].getCoeffAlpha());
            ASN1EncodableVector aSN1EncodableVector9 = new ASN1EncodableVector();
            for (int i4 = 0; i4 < convertArray.length; i4++) {
                ASN1EncodableVector aSN1EncodableVector10 = new ASN1EncodableVector();
                for (int i5 = 0; i5 < convertArray[i4].length; i5++) {
                    aSN1EncodableVector10.add(new DEROctetString(convertArray[i4][i5]));
                }
                aSN1EncodableVector9.add(new DERSequence(aSN1EncodableVector10));
            }
            aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector9));
            byte[][][] convertArray2 = RainbowUtil.convertArray(this.o[i3].getCoeffBeta());
            ASN1EncodableVector aSN1EncodableVector11 = new ASN1EncodableVector();
            for (int i6 = 0; i6 < convertArray2.length; i6++) {
                ASN1EncodableVector aSN1EncodableVector12 = new ASN1EncodableVector();
                for (int i7 = 0; i7 < convertArray2[i6].length; i7++) {
                    aSN1EncodableVector12.add(new DEROctetString(convertArray2[i6][i7]));
                }
                aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector12));
            }
            aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector11));
            byte[][] convertArray3 = RainbowUtil.convertArray(this.o[i3].getCoeffGamma());
            ASN1EncodableVector aSN1EncodableVector13 = new ASN1EncodableVector();
            for (byte[] bArr3 : convertArray3) {
                aSN1EncodableVector13.add(new DEROctetString(bArr3));
            }
            aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector13));
            aSN1EncodableVector8.add(new DEROctetString(RainbowUtil.convertArray(this.o[i3].getCoeffEta())));
            aSN1EncodableVector7.add(new DERSequence(aSN1EncodableVector8));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector7));
        return new DERSequence(aSN1EncodableVector);
    }
}