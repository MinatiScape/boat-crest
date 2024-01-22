package org.bouncycastle.pqc.asn1;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class XMSSMTPrivateKey extends ASN1Object {
    public final int h;
    public final byte[] i;
    public final byte[] j;
    public final byte[] k;
    public final byte[] l;
    public final byte[] m;

    public XMSSMTPrivateKey(int i, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        this.h = i;
        this.i = Arrays.clone(bArr);
        this.j = Arrays.clone(bArr2);
        this.k = Arrays.clone(bArr3);
        this.l = Arrays.clone(bArr4);
        this.m = Arrays.clone(bArr5);
    }

    public XMSSMTPrivateKey(ASN1Sequence aSN1Sequence) {
        if (!ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue().equals(BigInteger.valueOf(0L))) {
            throw new IllegalArgumentException("unknown version of sequence");
        }
        if (aSN1Sequence.size() != 2 && aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("key sequence wrong size");
        }
        ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        this.h = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(0)).getValue().intValue();
        this.i = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(1)).getOctets());
        this.j = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(2)).getOctets());
        this.k = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(3)).getOctets());
        this.l = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(4)).getOctets());
        if (aSN1Sequence.size() == 3) {
            this.m = Arrays.clone(ASN1OctetString.getInstance(ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(2)), true).getOctets());
        } else {
            this.m = null;
        }
    }

    public static XMSSMTPrivateKey getInstance(Object obj) {
        if (obj instanceof XMSSMTPrivateKey) {
            return (XMSSMTPrivateKey) obj;
        }
        if (obj != null) {
            return new XMSSMTPrivateKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[] getBdsState() {
        return Arrays.clone(this.m);
    }

    public int getIndex() {
        return this.h;
    }

    public byte[] getPublicSeed() {
        return Arrays.clone(this.k);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.l);
    }

    public byte[] getSecretKeyPRF() {
        return Arrays.clone(this.j);
    }

    public byte[] getSecretKeySeed() {
        return Arrays.clone(this.i);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(0L));
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        aSN1EncodableVector2.add(new ASN1Integer(this.h));
        aSN1EncodableVector2.add(new DEROctetString(this.i));
        aSN1EncodableVector2.add(new DEROctetString(this.j));
        aSN1EncodableVector2.add(new DEROctetString(this.k));
        aSN1EncodableVector2.add(new DEROctetString(this.l));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector.add(new DERTaggedObject(true, 0, new DEROctetString(this.m)));
        return new DERSequence(aSN1EncodableVector);
    }
}
