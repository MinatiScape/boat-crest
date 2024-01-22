package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class p3 extends y8 {
    public final /* synthetic */ byte g = 10;

    public p3() {
        super((byte) -47, (byte) 5, 1);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        int i;
        if (bArr.length < 3 || (i = bArr[2] & 255) <= 0) {
            i = this.g;
        }
        return Integer.valueOf(i);
    }
}
