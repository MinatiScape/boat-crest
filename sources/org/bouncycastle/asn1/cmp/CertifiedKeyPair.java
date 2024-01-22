package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.crmf.EncryptedValue;
import org.bouncycastle.asn1.crmf.PKIPublicationInfo;
/* loaded from: classes12.dex */
public class CertifiedKeyPair extends ASN1Object {
    public CertOrEncCert h;
    public EncryptedValue i;
    public PKIPublicationInfo j;

    public CertifiedKeyPair(ASN1Sequence aSN1Sequence) {
        ASN1Primitive aSN1TaggedObject;
        this.h = CertOrEncCert.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() >= 2) {
            if (aSN1Sequence.size() == 2) {
                ASN1TaggedObject aSN1TaggedObject2 = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(1));
                int tagNo = aSN1TaggedObject2.getTagNo();
                aSN1TaggedObject = aSN1TaggedObject2.getObject();
                if (tagNo == 0) {
                    this.i = EncryptedValue.getInstance(aSN1TaggedObject);
                    return;
                }
            } else {
                this.i = EncryptedValue.getInstance(ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(1)));
                aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(2));
            }
            this.j = PKIPublicationInfo.getInstance(aSN1TaggedObject);
        }
    }

    public CertifiedKeyPair(CertOrEncCert certOrEncCert) {
        this(certOrEncCert, null, null);
    }

    public CertifiedKeyPair(CertOrEncCert certOrEncCert, EncryptedValue encryptedValue, PKIPublicationInfo pKIPublicationInfo) {
        if (certOrEncCert == null) {
            throw new IllegalArgumentException("'certOrEncCert' cannot be null");
        }
        this.h = certOrEncCert;
        this.i = encryptedValue;
        this.j = pKIPublicationInfo;
    }

    public static CertifiedKeyPair getInstance(Object obj) {
        if (obj instanceof CertifiedKeyPair) {
            return (CertifiedKeyPair) obj;
        }
        if (obj != null) {
            return new CertifiedKeyPair(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CertOrEncCert getCertOrEncCert() {
        return this.h;
    }

    public EncryptedValue getPrivateKey() {
        return this.i;
    }

    public PKIPublicationInfo getPublicationInfo() {
        return this.j;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.i));
        }
        if (this.j != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.j));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
