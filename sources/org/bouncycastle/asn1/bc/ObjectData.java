package org.bouncycastle.asn1.bc;

import java.math.BigInteger;
import java.util.Date;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERGeneralizedTime;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class ObjectData extends ASN1Object {
    public final BigInteger h;
    public final String i;
    public final ASN1GeneralizedTime j;
    public final ASN1GeneralizedTime k;
    public final ASN1OctetString l;
    public final String m;

    public ObjectData(BigInteger bigInteger, String str, Date date, Date date2, byte[] bArr, String str2) {
        this.h = bigInteger;
        this.i = str;
        this.j = new DERGeneralizedTime(date);
        this.k = new DERGeneralizedTime(date2);
        this.l = new DEROctetString(Arrays.clone(bArr));
        this.m = str2;
    }

    public ObjectData(ASN1Sequence aSN1Sequence) {
        this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue();
        this.i = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(1)).getString();
        this.j = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(2));
        this.k = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(3));
        this.l = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(4));
        this.m = aSN1Sequence.size() == 6 ? DERUTF8String.getInstance(aSN1Sequence.getObjectAt(5)).getString() : null;
    }

    public static ObjectData getInstance(Object obj) {
        if (obj instanceof ObjectData) {
            return (ObjectData) obj;
        }
        if (obj != null) {
            return new ObjectData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public String getComment() {
        return this.m;
    }

    public ASN1GeneralizedTime getCreationDate() {
        return this.j;
    }

    public byte[] getData() {
        return Arrays.clone(this.l.getOctets());
    }

    public String getIdentifier() {
        return this.i;
    }

    public ASN1GeneralizedTime getLastModifiedDate() {
        return this.k;
    }

    public BigInteger getType() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.h));
        aSN1EncodableVector.add(new DERUTF8String(this.i));
        aSN1EncodableVector.add(this.j);
        aSN1EncodableVector.add(this.k);
        aSN1EncodableVector.add(this.l);
        String str = this.m;
        if (str != null) {
            aSN1EncodableVector.add(new DERUTF8String(str));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
