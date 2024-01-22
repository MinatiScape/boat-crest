package org.bouncycastle.asn1.eac;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class UnsignedInteger extends ASN1Object {
    public int h;
    public BigInteger i;

    public UnsignedInteger(int i, BigInteger bigInteger) {
        this.h = i;
        this.i = bigInteger;
    }

    public UnsignedInteger(ASN1TaggedObject aSN1TaggedObject) {
        this.h = aSN1TaggedObject.getTagNo();
        this.i = new BigInteger(1, ASN1OctetString.getInstance(aSN1TaggedObject, false).getOctets());
    }

    public static UnsignedInteger getInstance(Object obj) {
        if (obj instanceof UnsignedInteger) {
            return (UnsignedInteger) obj;
        }
        if (obj != null) {
            return new UnsignedInteger(ASN1TaggedObject.getInstance(obj));
        }
        return null;
    }

    public final byte[] a() {
        byte[] byteArray = this.i.toByteArray();
        if (byteArray[0] == 0) {
            int length = byteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(byteArray, 1, bArr, 0, length);
            return bArr;
        }
        return byteArray;
    }

    public int getTagNo() {
        return this.h;
    }

    public BigInteger getValue() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(false, this.h, new DEROctetString(a()));
    }
}
