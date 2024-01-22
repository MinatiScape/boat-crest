package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class AttributeCertificateInfo extends ASN1Object {
    public ASN1Integer h;
    public Holder i;
    public AttCertIssuer j;
    public AlgorithmIdentifier k;
    public ASN1Integer l;
    public AttCertValidityPeriod m;
    public ASN1Sequence n;
    public DERBitString o;
    public Extensions p;

    public AttributeCertificateInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 6 || aSN1Sequence.size() > 9) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        int i = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
            i = 1;
        } else {
            this.h = new ASN1Integer(0L);
        }
        this.i = Holder.getInstance(aSN1Sequence.getObjectAt(i));
        this.j = AttCertIssuer.getInstance(aSN1Sequence.getObjectAt(i + 1));
        this.k = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i + 2));
        this.l = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i + 3));
        this.m = AttCertValidityPeriod.getInstance(aSN1Sequence.getObjectAt(i + 4));
        this.n = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(i + 5));
        for (int i2 = i + 6; i2 < aSN1Sequence.size(); i2++) {
            ASN1Encodable objectAt = aSN1Sequence.getObjectAt(i2);
            if (objectAt instanceof DERBitString) {
                this.o = DERBitString.getInstance(aSN1Sequence.getObjectAt(i2));
            } else if ((objectAt instanceof ASN1Sequence) || (objectAt instanceof Extensions)) {
                this.p = Extensions.getInstance(aSN1Sequence.getObjectAt(i2));
            }
        }
    }

    public static AttributeCertificateInfo getInstance(Object obj) {
        if (obj instanceof AttributeCertificateInfo) {
            return (AttributeCertificateInfo) obj;
        }
        if (obj != null) {
            return new AttributeCertificateInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static AttributeCertificateInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public AttCertValidityPeriod getAttrCertValidityPeriod() {
        return this.m;
    }

    public ASN1Sequence getAttributes() {
        return this.n;
    }

    public Extensions getExtensions() {
        return this.p;
    }

    public Holder getHolder() {
        return this.i;
    }

    public AttCertIssuer getIssuer() {
        return this.j;
    }

    public DERBitString getIssuerUniqueID() {
        return this.o;
    }

    public ASN1Integer getSerialNumber() {
        return this.l;
    }

    public AlgorithmIdentifier getSignature() {
        return this.k;
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.h.getValue().intValue() != 0) {
            aSN1EncodableVector.add(this.h);
        }
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        aSN1EncodableVector.add(this.k);
        aSN1EncodableVector.add(this.l);
        aSN1EncodableVector.add(this.m);
        aSN1EncodableVector.add(this.n);
        DERBitString dERBitString = this.o;
        if (dERBitString != null) {
            aSN1EncodableVector.add(dERBitString);
        }
        Extensions extensions = this.p;
        if (extensions != null) {
            aSN1EncodableVector.add(extensions);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
