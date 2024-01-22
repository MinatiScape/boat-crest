package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.X509Extensions;
/* loaded from: classes12.dex */
public class TBSRequest extends ASN1Object {
    public static final ASN1Integer m = new ASN1Integer(0);
    public ASN1Integer h;
    public GeneralName i;
    public ASN1Sequence j;
    public Extensions k;
    public boolean l;

    public TBSRequest(ASN1Sequence aSN1Sequence) {
        int i = 0;
        if ((aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) && ((ASN1TaggedObject) aSN1Sequence.getObjectAt(0)).getTagNo() == 0) {
            this.l = true;
            this.h = ASN1Integer.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(0), true);
            i = 1;
        } else {
            this.h = m;
        }
        if (aSN1Sequence.getObjectAt(i) instanceof ASN1TaggedObject) {
            this.i = GeneralName.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(i), true);
            i++;
        }
        int i2 = i + 1;
        this.j = (ASN1Sequence) aSN1Sequence.getObjectAt(i);
        if (aSN1Sequence.size() == i2 + 1) {
            this.k = Extensions.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(i2), true);
        }
    }

    public TBSRequest(GeneralName generalName, ASN1Sequence aSN1Sequence, Extensions extensions) {
        this.h = m;
        this.i = generalName;
        this.j = aSN1Sequence;
        this.k = extensions;
    }

    public TBSRequest(GeneralName generalName, ASN1Sequence aSN1Sequence, X509Extensions x509Extensions) {
        this.h = m;
        this.i = generalName;
        this.j = aSN1Sequence;
        this.k = Extensions.getInstance(x509Extensions);
    }

    public static TBSRequest getInstance(Object obj) {
        if (obj instanceof TBSRequest) {
            return (TBSRequest) obj;
        }
        if (obj != null) {
            return new TBSRequest(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TBSRequest getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Extensions getRequestExtensions() {
        return this.k;
    }

    public ASN1Sequence getRequestList() {
        return this.j;
    }

    public GeneralName getRequestorName() {
        return this.i;
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (!this.h.equals(m) || this.l) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.h));
        }
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.i));
        }
        aSN1EncodableVector.add(this.j);
        if (this.k != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, this.k));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
