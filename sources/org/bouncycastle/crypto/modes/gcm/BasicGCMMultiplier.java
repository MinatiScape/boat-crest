package org.bouncycastle.crypto.modes.gcm;
/* loaded from: classes13.dex */
public class BasicGCMMultiplier implements GCMMultiplier {

    /* renamed from: a  reason: collision with root package name */
    public long[] f14781a;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        this.f14781a = GCMUtil.asLongs(bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        long[] asLongs = GCMUtil.asLongs(bArr);
        GCMUtil.multiply(asLongs, this.f14781a);
        GCMUtil.asBytes(asLongs, bArr);
    }
}
