package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
/* loaded from: classes12.dex */
public class CMCStatusInfoV2Builder {

    /* renamed from: a  reason: collision with root package name */
    public final CMCStatus f14395a;
    public final ASN1Sequence b;
    public DERUTF8String c;
    public OtherStatusInfo d;

    public CMCStatusInfoV2Builder(CMCStatus cMCStatus, BodyPartID bodyPartID) {
        this.f14395a = cMCStatus;
        this.b = new DERSequence(bodyPartID);
    }

    public CMCStatusInfoV2Builder(CMCStatus cMCStatus, BodyPartID[] bodyPartIDArr) {
        this.f14395a = cMCStatus;
        this.b = new DERSequence(bodyPartIDArr);
    }

    public CMCStatusInfoV2 build() {
        return new CMCStatusInfoV2(this.f14395a, this.b, this.c, this.d);
    }

    public CMCStatusInfoV2Builder setOtherInfo(CMCFailInfo cMCFailInfo) {
        this.d = new OtherStatusInfo(cMCFailInfo);
        return this;
    }

    public CMCStatusInfoV2Builder setOtherInfo(ExtendedFailInfo extendedFailInfo) {
        this.d = new OtherStatusInfo(extendedFailInfo);
        return this;
    }

    public CMCStatusInfoV2Builder setOtherInfo(PendInfo pendInfo) {
        this.d = new OtherStatusInfo(pendInfo);
        return this;
    }

    public CMCStatusInfoV2Builder setStatusString(String str) {
        this.c = new DERUTF8String(str);
        return this;
    }
}
