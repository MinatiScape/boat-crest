package org.bouncycastle.crypto.modes.gcm;

import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class BasicGCMExponentiator implements GCMExponentiator {

    /* renamed from: a  reason: collision with root package name */
    public long[] f14780a;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j, byte[] bArr) {
        long[] oneAsLongs = GCMUtil.oneAsLongs();
        if (j <= 0) {
            GCMUtil.asBytes(oneAsLongs, bArr);
        }
        long[] clone = Arrays.clone(this.f14780a);
        do {
            if ((1 & j) != 0) {
                GCMUtil.multiply(oneAsLongs, clone);
            }
            GCMUtil.square(clone, clone);
            j >>>= 1;
        } while (j > 0);
        GCMUtil.asBytes(oneAsLongs, bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        this.f14780a = GCMUtil.asLongs(bArr);
    }
}
