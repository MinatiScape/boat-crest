package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.dvcs.TargetEtcChain;
/* loaded from: classes13.dex */
public class TargetChain {

    /* renamed from: a  reason: collision with root package name */
    public final TargetEtcChain f14890a;

    public TargetChain(TargetEtcChain targetEtcChain) {
        this.f14890a = targetEtcChain;
    }

    public TargetEtcChain toASN1Structure() {
        return this.f14890a;
    }
}
