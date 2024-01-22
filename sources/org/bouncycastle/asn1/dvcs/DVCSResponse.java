package org.bouncycastle.asn1.dvcs;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class DVCSResponse extends ASN1Object implements ASN1Choice {
    public DVCSCertInfo h;
    public DVCSErrorNotice i;

    public DVCSResponse(DVCSCertInfo dVCSCertInfo) {
        this.h = dVCSCertInfo;
    }

    public DVCSResponse(DVCSErrorNotice dVCSErrorNotice) {
        this.i = dVCSErrorNotice;
    }

    public static DVCSResponse getInstance(Object obj) {
        if (obj == null || (obj instanceof DVCSResponse)) {
            return (DVCSResponse) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct sequence from byte[]: " + e.getMessage());
            }
        } else if (obj instanceof ASN1Sequence) {
            return new DVCSResponse(DVCSCertInfo.getInstance(obj));
        } else {
            if (obj instanceof ASN1TaggedObject) {
                return new DVCSResponse(DVCSErrorNotice.getInstance(ASN1TaggedObject.getInstance(obj), false));
            }
            throw new IllegalArgumentException("Couldn't convert from object to DVCSResponse: " + obj.getClass().getName());
        }
    }

    public static DVCSResponse getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public DVCSCertInfo getCertInfo() {
        return this.h;
    }

    public DVCSErrorNotice getErrorNotice() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        DVCSCertInfo dVCSCertInfo = this.h;
        return dVCSCertInfo != null ? dVCSCertInfo.toASN1Primitive() : new DERTaggedObject(false, 0, this.i);
    }

    public String toString() {
        StringBuilder sb;
        String dVCSErrorNotice;
        if (this.h != null) {
            sb = new StringBuilder();
            sb.append("DVCSResponse {\ndvCertInfo: ");
            dVCSErrorNotice = this.h.toString();
        } else {
            sb = new StringBuilder();
            sb.append("DVCSResponse {\ndvErrorNote: ");
            dVCSErrorNotice = this.i.toString();
        }
        sb.append(dVCSErrorNotice);
        sb.append("}\n");
        return sb.toString();
    }
}
