package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class MacData extends ASN1Object {
    public static final BigInteger k = BigInteger.valueOf(1);
    public DigestInfo h;
    public byte[] i;
    public BigInteger j;

    public MacData(ASN1Sequence aSN1Sequence) {
        this.h = DigestInfo.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = Arrays.clone(((ASN1OctetString) aSN1Sequence.getObjectAt(1)).getOctets());
        this.j = aSN1Sequence.size() == 3 ? ((ASN1Integer) aSN1Sequence.getObjectAt(2)).getValue() : k;
    }

    public MacData(DigestInfo digestInfo, byte[] bArr, int i) {
        this.h = digestInfo;
        this.i = Arrays.clone(bArr);
        this.j = BigInteger.valueOf(i);
    }

    public static MacData getInstance(Object obj) {
        if (obj instanceof MacData) {
            return (MacData) obj;
        }
        if (obj != null) {
            return new MacData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getIterationCount() {
        return this.j;
    }

    public DigestInfo getMac() {
        return this.h;
    }

    public byte[] getSalt() {
        return Arrays.clone(this.i);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(new DEROctetString(this.i));
        if (!this.j.equals(k)) {
            aSN1EncodableVector.add(new ASN1Integer(this.j));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
