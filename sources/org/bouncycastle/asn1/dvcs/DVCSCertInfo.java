package org.bouncycastle.asn1.dvcs;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.PolicyInformation;
/* loaded from: classes12.dex */
public class DVCSCertInfo extends ASN1Object {
    public int h;
    public DVCSRequestInformation i;
    public DigestInfo j;
    public ASN1Integer k;
    public DVCSTime l;
    public PKIStatusInfo m;
    public PolicyInformation n;
    public ASN1Set o;
    public ASN1Sequence p;
    public Extensions q;

    public DVCSCertInfo(ASN1Sequence aSN1Sequence) {
        int i;
        this.h = 1;
        ASN1Encodable objectAt = aSN1Sequence.getObjectAt(0);
        try {
            this.h = ASN1Integer.getInstance(objectAt).getValue().intValue();
            try {
                objectAt = aSN1Sequence.getObjectAt(1);
            } catch (IllegalArgumentException unused) {
            }
            i = 2;
        } catch (IllegalArgumentException unused2) {
            i = 1;
        }
        this.i = DVCSRequestInformation.getInstance(objectAt);
        int i2 = i + 1;
        this.j = DigestInfo.getInstance(aSN1Sequence.getObjectAt(i));
        int i3 = i2 + 1;
        this.k = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i2));
        int i4 = i3 + 1;
        this.l = DVCSTime.getInstance(aSN1Sequence.getObjectAt(i3));
        while (i4 < aSN1Sequence.size()) {
            int i5 = i4 + 1;
            ASN1Encodable objectAt2 = aSN1Sequence.getObjectAt(i4);
            if (objectAt2 instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objectAt2);
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    this.m = PKIStatusInfo.getInstance(aSN1TaggedObject, false);
                } else if (tagNo == 1) {
                    this.n = PolicyInformation.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, false));
                } else if (tagNo == 2) {
                    this.o = ASN1Set.getInstance(aSN1TaggedObject, false);
                } else if (tagNo != 3) {
                    throw new IllegalArgumentException("Unknown tag encountered: " + tagNo);
                } else {
                    this.p = ASN1Sequence.getInstance(aSN1TaggedObject, false);
                }
            } else {
                try {
                    this.q = Extensions.getInstance(objectAt2);
                } catch (IllegalArgumentException unused3) {
                }
            }
            i4 = i5;
        }
    }

    public DVCSCertInfo(DVCSRequestInformation dVCSRequestInformation, DigestInfo digestInfo, ASN1Integer aSN1Integer, DVCSTime dVCSTime) {
        this.h = 1;
        this.i = dVCSRequestInformation;
        this.j = digestInfo;
        this.k = aSN1Integer;
        this.l = dVCSTime;
    }

    public static DVCSCertInfo getInstance(Object obj) {
        if (obj instanceof DVCSCertInfo) {
            return (DVCSCertInfo) obj;
        }
        if (obj != null) {
            return new DVCSCertInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static DVCSCertInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public TargetEtcChain[] getCerts() {
        ASN1Sequence aSN1Sequence = this.p;
        if (aSN1Sequence != null) {
            return TargetEtcChain.arrayFromSequence(aSN1Sequence);
        }
        return null;
    }

    public DVCSRequestInformation getDvReqInfo() {
        return this.i;
    }

    public PKIStatusInfo getDvStatus() {
        return this.m;
    }

    public Extensions getExtensions() {
        return this.q;
    }

    public DigestInfo getMessageImprint() {
        return this.j;
    }

    public PolicyInformation getPolicy() {
        return this.n;
    }

    public ASN1Set getReqSignature() {
        return this.o;
    }

    public DVCSTime getResponseTime() {
        return this.l;
    }

    public ASN1Integer getSerialNumber() {
        return this.k;
    }

    public int getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        int i = this.h;
        if (i != 1) {
            aSN1EncodableVector.add(new ASN1Integer(i));
        }
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        aSN1EncodableVector.add(this.k);
        aSN1EncodableVector.add(this.l);
        if (this.m != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.m));
        }
        if (this.n != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.n));
        }
        if (this.o != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, this.o));
        }
        if (this.p != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 3, this.p));
        }
        Extensions extensions = this.q;
        if (extensions != null) {
            aSN1EncodableVector.add(extensions);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DVCSCertInfo {\n");
        if (this.h != 1) {
            stringBuffer.append("version: " + this.h + "\n");
        }
        stringBuffer.append("dvReqInfo: " + this.i + "\n");
        stringBuffer.append("messageImprint: " + this.j + "\n");
        stringBuffer.append("serialNumber: " + this.k + "\n");
        stringBuffer.append("responseTime: " + this.l + "\n");
        if (this.m != null) {
            stringBuffer.append("dvStatus: " + this.m + "\n");
        }
        if (this.n != null) {
            stringBuffer.append("policy: " + this.n + "\n");
        }
        if (this.o != null) {
            stringBuffer.append("reqSignature: " + this.o + "\n");
        }
        if (this.p != null) {
            stringBuffer.append("certs: " + this.p + "\n");
        }
        if (this.q != null) {
            stringBuffer.append("extensions: " + this.q + "\n");
        }
        stringBuffer.append("}\n");
        return stringBuffer.toString();
    }
}
