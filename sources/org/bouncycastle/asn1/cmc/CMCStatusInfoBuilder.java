package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.cmc.CMCStatusInfo;
/* loaded from: classes12.dex */
public class CMCStatusInfoBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final CMCStatus f14394a;
    public final ASN1Sequence b;
    public DERUTF8String c;
    public CMCStatusInfo.OtherInfo d;

    public CMCStatusInfoBuilder(CMCStatus cMCStatus, BodyPartID bodyPartID) {
        this.f14394a = cMCStatus;
        this.b = new DERSequence(bodyPartID);
    }

    public CMCStatusInfoBuilder(CMCStatus cMCStatus, BodyPartID[] bodyPartIDArr) {
        this.f14394a = cMCStatus;
        this.b = new DERSequence(bodyPartIDArr);
    }

    public CMCStatusInfo build() {
        return new CMCStatusInfo(this.f14394a, this.b, this.c, this.d);
    }

    public CMCStatusInfoBuilder setOtherInfo(CMCFailInfo cMCFailInfo) {
        this.d = new CMCStatusInfo.OtherInfo(cMCFailInfo);
        return this;
    }

    public CMCStatusInfoBuilder setOtherInfo(PendInfo pendInfo) {
        this.d = new CMCStatusInfo.OtherInfo(pendInfo);
        return this;
    }

    public CMCStatusInfoBuilder setStatusString(String str) {
        this.c = new DERUTF8String(str);
        return this;
    }
}
