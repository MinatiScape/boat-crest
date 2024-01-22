package org.bouncycastle.asn1.dvcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.PolicyInformation;
/* loaded from: classes12.dex */
public class DVCSRequestInformation extends ASN1Object {
    public int h;
    public ServiceType i;
    public BigInteger j;
    public DVCSTime k;
    public GeneralNames l;
    public PolicyInformation m;
    public GeneralNames n;
    public GeneralNames o;
    public Extensions p;

    public DVCSRequestInformation(ASN1Sequence aSN1Sequence) {
        int i;
        this.h = 1;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue().intValue();
            i = 1;
        } else {
            this.h = 1;
            i = 0;
        }
        this.i = ServiceType.getInstance(aSN1Sequence.getObjectAt(i));
        for (int i2 = i + 1; i2 < aSN1Sequence.size(); i2++) {
            ASN1Encodable objectAt = aSN1Sequence.getObjectAt(i2);
            if (objectAt instanceof ASN1Integer) {
                this.j = ASN1Integer.getInstance(objectAt).getValue();
            } else if (!(objectAt instanceof ASN1GeneralizedTime) && (objectAt instanceof ASN1TaggedObject)) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objectAt);
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    this.l = GeneralNames.getInstance(aSN1TaggedObject, false);
                } else if (tagNo == 1) {
                    this.m = PolicyInformation.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, false));
                } else if (tagNo == 2) {
                    this.n = GeneralNames.getInstance(aSN1TaggedObject, false);
                } else if (tagNo == 3) {
                    this.o = GeneralNames.getInstance(aSN1TaggedObject, false);
                } else if (tagNo != 4) {
                    throw new IllegalArgumentException("unknown tag number encountered: " + tagNo);
                } else {
                    this.p = Extensions.getInstance(aSN1TaggedObject, false);
                }
            } else {
                this.k = DVCSTime.getInstance(objectAt);
            }
        }
    }

    public static DVCSRequestInformation getInstance(Object obj) {
        if (obj instanceof DVCSRequestInformation) {
            return (DVCSRequestInformation) obj;
        }
        if (obj != null) {
            return new DVCSRequestInformation(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static DVCSRequestInformation getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public GeneralNames getDVCS() {
        return this.n;
    }

    public GeneralNames getDataLocations() {
        return this.o;
    }

    public Extensions getExtensions() {
        return this.p;
    }

    public BigInteger getNonce() {
        return this.j;
    }

    public PolicyInformation getRequestPolicy() {
        return this.m;
    }

    public DVCSTime getRequestTime() {
        return this.k;
    }

    public GeneralNames getRequester() {
        return this.l;
    }

    public ServiceType getService() {
        return this.i;
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
        BigInteger bigInteger = this.j;
        if (bigInteger != null) {
            aSN1EncodableVector.add(new ASN1Integer(bigInteger));
        }
        DVCSTime dVCSTime = this.k;
        if (dVCSTime != null) {
            aSN1EncodableVector.add(dVCSTime);
        }
        int[] iArr = {0, 1, 2, 3, 4};
        ASN1Encodable[] aSN1EncodableArr = {this.l, this.m, this.n, this.o, this.p};
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            ASN1Encodable aSN1Encodable = aSN1EncodableArr[i2];
            if (aSN1Encodable != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, i3, aSN1Encodable));
            }
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DVCSRequestInformation {\n");
        if (this.h != 1) {
            stringBuffer.append("version: " + this.h + "\n");
        }
        stringBuffer.append("service: " + this.i + "\n");
        if (this.j != null) {
            stringBuffer.append("nonce: " + this.j + "\n");
        }
        if (this.k != null) {
            stringBuffer.append("requestTime: " + this.k + "\n");
        }
        if (this.l != null) {
            stringBuffer.append("requester: " + this.l + "\n");
        }
        if (this.m != null) {
            stringBuffer.append("requestPolicy: " + this.m + "\n");
        }
        if (this.n != null) {
            stringBuffer.append("dvcs: " + this.n + "\n");
        }
        if (this.o != null) {
            stringBuffer.append("dataLocations: " + this.o + "\n");
        }
        if (this.p != null) {
            stringBuffer.append("extensions: " + this.p + "\n");
        }
        stringBuffer.append("}\n");
        return stringBuffer.toString();
    }
}
