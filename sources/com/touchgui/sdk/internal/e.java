package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGBindResult;
/* loaded from: classes12.dex */
public final class e extends y8 {
    public final /* synthetic */ byte g = 0;

    public e() {
        super((byte) 4, (byte) 1);
    }

    @Override // com.touchgui.sdk.internal.y8, com.touchgui.sdk.internal.z8
    public final byte[] a(byte b, byte b2, int i) {
        return new byte[]{b, b2, com.crrepa.c.a.L1, 0, 0, this.g};
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        TGBindResult tGBindResult = new TGBindResult();
        tGBindResult.setResult(bArr[2]);
        if (bArr[3] != 0) {
            tGBindResult.setEncryptedVersion(bArr[4]);
            tGBindResult.setRandomData(s.a(bArr, 5, bArr[3] & 255));
        }
        return tGBindResult;
    }
}
