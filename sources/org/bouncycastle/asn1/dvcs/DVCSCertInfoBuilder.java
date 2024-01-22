package org.bouncycastle.asn1.dvcs;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.PolicyInformation;
/* loaded from: classes12.dex */
public class DVCSCertInfoBuilder {

    /* renamed from: a  reason: collision with root package name */
    public int f14410a = 1;
    public DVCSRequestInformation b;
    public DigestInfo c;
    public ASN1Integer d;
    public DVCSTime e;
    public PKIStatusInfo f;
    public PolicyInformation g;
    public ASN1Set h;
    public ASN1Sequence i;
    public Extensions j;

    public DVCSCertInfoBuilder(DVCSRequestInformation dVCSRequestInformation, DigestInfo digestInfo, ASN1Integer aSN1Integer, DVCSTime dVCSTime) {
        this.b = dVCSRequestInformation;
        this.c = digestInfo;
        this.d = aSN1Integer;
        this.e = dVCSTime;
    }

    public DVCSCertInfo build() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        int i = this.f14410a;
        if (i != 1) {
            aSN1EncodableVector.add(new ASN1Integer(i));
        }
        aSN1EncodableVector.add(this.b);
        aSN1EncodableVector.add(this.c);
        aSN1EncodableVector.add(this.d);
        aSN1EncodableVector.add(this.e);
        if (this.f != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.f));
        }
        if (this.g != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.g));
        }
        if (this.h != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, this.h));
        }
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 3, this.i));
        }
        Extensions extensions = this.j;
        if (extensions != null) {
            aSN1EncodableVector.add(extensions);
        }
        return DVCSCertInfo.getInstance(new DERSequence(aSN1EncodableVector));
    }

    public void setCerts(TargetEtcChain[] targetEtcChainArr) {
        this.i = new DERSequence(targetEtcChainArr);
    }

    public void setDvReqInfo(DVCSRequestInformation dVCSRequestInformation) {
        this.b = dVCSRequestInformation;
    }

    public void setDvStatus(PKIStatusInfo pKIStatusInfo) {
        this.f = pKIStatusInfo;
    }

    public void setExtensions(Extensions extensions) {
        this.j = extensions;
    }

    public void setMessageImprint(DigestInfo digestInfo) {
        this.c = digestInfo;
    }

    public void setPolicy(PolicyInformation policyInformation) {
        this.g = policyInformation;
    }

    public void setReqSignature(ASN1Set aSN1Set) {
        this.h = aSN1Set;
    }

    public void setResponseTime(DVCSTime dVCSTime) {
        this.e = dVCSTime;
    }

    public void setSerialNumber(ASN1Integer aSN1Integer) {
        this.d = aSN1Integer;
    }

    public void setVersion(int i) {
        this.f14410a = i;
    }
}
