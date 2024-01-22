package org.bouncycastle.asn1.cmp;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class CertStatus extends ASN1Object {
    public ASN1OctetString h;
    public ASN1Integer i;
    public PKIStatusInfo j;

    public CertStatus(ASN1Sequence aSN1Sequence) {
        this.h = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() > 2) {
            this.j = PKIStatusInfo.getInstance(aSN1Sequence.getObjectAt(2));
        }
    }

    public CertStatus(byte[] bArr, BigInteger bigInteger) {
        this.h = new DEROctetString(bArr);
        this.i = new ASN1Integer(bigInteger);
    }

    public CertStatus(byte[] bArr, BigInteger bigInteger, PKIStatusInfo pKIStatusInfo) {
        this.h = new DEROctetString(bArr);
        this.i = new ASN1Integer(bigInteger);
        this.j = pKIStatusInfo;
    }

    public static CertStatus getInstance(Object obj) {
        if (obj instanceof CertStatus) {
            return (CertStatus) obj;
        }
        if (obj != null) {
            return new CertStatus(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1OctetString getCertHash() {
        return this.h;
    }

    public ASN1Integer getCertReqId() {
        return this.i;
    }

    public PKIStatusInfo getStatusInfo() {
        return this.j;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        PKIStatusInfo pKIStatusInfo = this.j;
        if (pKIStatusInfo != null) {
            aSN1EncodableVector.add(pKIStatusInfo);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
