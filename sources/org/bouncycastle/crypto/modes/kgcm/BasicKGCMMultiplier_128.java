package org.bouncycastle.crypto.modes.kgcm;
/* loaded from: classes13.dex */
public class BasicKGCMMultiplier_128 implements KGCMMultiplier {

    /* renamed from: a  reason: collision with root package name */
    public final long[] f14786a = new long[2];

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void init(long[] jArr) {
        KGCMUtil_128.copy(jArr, this.f14786a);
    }

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void multiplyH(long[] jArr) {
        KGCMUtil_128.multiply(jArr, this.f14786a, jArr);
    }
}
