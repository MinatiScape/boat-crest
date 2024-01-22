package org.bouncycastle.crypto.modes.kgcm;
/* loaded from: classes13.dex */
public class BasicKGCMMultiplier_512 implements KGCMMultiplier {

    /* renamed from: a  reason: collision with root package name */
    public final long[] f14788a = new long[8];

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void init(long[] jArr) {
        KGCMUtil_512.copy(jArr, this.f14788a);
    }

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void multiplyH(long[] jArr) {
        KGCMUtil_512.multiply(jArr, this.f14788a, jArr);
    }
}
