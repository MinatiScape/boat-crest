package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class Holder extends ASN1Object {
    public static final int V1_CERTIFICATE_HOLDER = 0;
    public static final int V2_CERTIFICATE_HOLDER = 1;
    public IssuerSerial h;
    public GeneralNames i;
    public ObjectDigestInfo j;
    public int k;

    public Holder(ASN1Sequence aSN1Sequence) {
        this.k = 1;
        if (aSN1Sequence.size() > 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        for (int i = 0; i != aSN1Sequence.size(); i++) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i));
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                this.h = IssuerSerial.getInstance(aSN1TaggedObject, false);
            } else if (tagNo == 1) {
                this.i = GeneralNames.getInstance(aSN1TaggedObject, false);
            } else if (tagNo != 2) {
                throw new IllegalArgumentException("unknown tag in Holder");
            } else {
                this.j = ObjectDigestInfo.getInstance(aSN1TaggedObject, false);
            }
        }
        this.k = 1;
    }

    public Holder(ASN1TaggedObject aSN1TaggedObject) {
        this.k = 1;
        int tagNo = aSN1TaggedObject.getTagNo();
        if (tagNo == 0) {
            this.h = IssuerSerial.getInstance(aSN1TaggedObject, true);
        } else if (tagNo != 1) {
            throw new IllegalArgumentException("unknown tag in Holder");
        } else {
            this.i = GeneralNames.getInstance(aSN1TaggedObject, true);
        }
        this.k = 0;
    }

    public Holder(GeneralNames generalNames) {
        this(generalNames, 1);
    }

    public Holder(GeneralNames generalNames, int i) {
        this.k = 1;
        this.i = generalNames;
        this.k = i;
    }

    public Holder(IssuerSerial issuerSerial) {
        this(issuerSerial, 1);
    }

    public Holder(IssuerSerial issuerSerial, int i) {
        this.k = 1;
        this.h = issuerSerial;
        this.k = i;
    }

    public Holder(ObjectDigestInfo objectDigestInfo) {
        this.k = 1;
        this.j = objectDigestInfo;
    }

    public static Holder getInstance(Object obj) {
        if (obj instanceof Holder) {
            return (Holder) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new Holder(ASN1TaggedObject.getInstance(obj));
        }
        if (obj != null) {
            return new Holder(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public IssuerSerial getBaseCertificateID() {
        return this.h;
    }

    public GeneralNames getEntityName() {
        return this.i;
    }

    public ObjectDigestInfo getObjectDigestInfo() {
        return this.j;
    }

    public int getVersion() {
        return this.k;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        if (this.k != 1) {
            return this.i != null ? new DERTaggedObject(true, 1, this.i) : new DERTaggedObject(true, 0, this.h);
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.h != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.h));
        }
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.i));
        }
        if (this.j != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, this.j));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
