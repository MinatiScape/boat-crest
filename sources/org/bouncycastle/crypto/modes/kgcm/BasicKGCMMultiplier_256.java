package org.bouncycastle.crypto.modes.kgcm;
/* loaded from: classes13.dex */
public class BasicKGCMMultiplier_256 implements KGCMMultiplier {

    /* renamed from: a  reason: collision with root package name */
    public final long[] f14787a = new long[4];

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void init(long[] jArr) {
        KGCMUtil_256.copy(jArr, this.f14787a);
    }

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void multiplyH(long[] jArr) {
        KGCMUtil_256.multiply(jArr, this.f14787a, jArr);
    }
}
