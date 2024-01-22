package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class n3 extends y8 {
    public n3() {
        super((byte) -47, (byte) 7, 1);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        return Integer.valueOf((bArr.length < 3 || (r3 = bArr[2] & 255) <= 0) ? 0 : 0);
    }
}
