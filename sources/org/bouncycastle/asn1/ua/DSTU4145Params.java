package org.bouncycastle.asn1.ua;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.c.a;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class DSTU4145Params extends ASN1Object {
    public static final byte[] k = {-87, -42, -21, a.E0, a.J1, 60, com.htsmart.wristband2.a.a.a.J1, -126, Byte.MIN_VALUE, -60, BleUUID.CMD_ID_96, 123, 35, 31, com.htsmart.wristband2.a.a.a.u1, BleUUID.CMD_ID_AD, -10, com.htsmart.wristband2.a.a.a.o1, -21, -92, -64, 55, 41, 29, 56, -39, 107, -16, 37, -54, com.htsmart.wristband2.a.a.a.c1, 23, -8, -23, 114, 13, -58, 21, BleUUID.CMD_ID_B4, 58, 40, BleUUID.CMD_ID_97, 95, 11, -63, -34, -93, 100, 56, BleUUID.CMD_ID_B5, 100, a.A, 44, 23, -97, -48, 18, 62, 109, BleUUID.CMD_ID_B8, -6, -59, com.htsmart.wristband2.a.a.a.U1, 4};
    public ASN1ObjectIdentifier h;
    public DSTU4145ECBinary i;
    public byte[] j;

    public DSTU4145Params(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.j = k;
        this.h = aSN1ObjectIdentifier;
    }

    public DSTU4145Params(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) {
        this.j = k;
        this.h = aSN1ObjectIdentifier;
        this.j = Arrays.clone(bArr);
    }

    public DSTU4145Params(DSTU4145ECBinary dSTU4145ECBinary) {
        this.j = k;
        this.i = dSTU4145ECBinary;
    }

    public static byte[] getDefaultDKE() {
        return k;
    }

    public static DSTU4145Params getInstance(Object obj) {
        if (obj instanceof DSTU4145Params) {
            return (DSTU4145Params) obj;
        }
        if (obj != null) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(obj);
            DSTU4145Params dSTU4145Params = aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier ? new DSTU4145Params(ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0))) : new DSTU4145Params(DSTU4145ECBinary.getInstance(aSN1Sequence.getObjectAt(0)));
            if (aSN1Sequence.size() == 2) {
                byte[] octets = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets();
                dSTU4145Params.j = octets;
                if (octets.length != k.length) {
                    throw new IllegalArgumentException("object parse error");
                }
            }
            return dSTU4145Params;
        }
        throw new IllegalArgumentException("object parse error");
    }

    public byte[] getDKE() {
        return this.j;
    }

    public DSTU4145ECBinary getECBinary() {
        return this.i;
    }

    public ASN1ObjectIdentifier getNamedCurve() {
        return this.h;
    }

    public boolean isNamedCurve() {
        return this.h != null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Encodable aSN1Encodable = this.h;
        if (aSN1Encodable == null) {
            aSN1Encodable = this.i;
        }
        aSN1EncodableVector.add(aSN1Encodable);
        if (!Arrays.areEqual(this.j, k)) {
            aSN1EncodableVector.add(new DEROctetString(this.j));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
