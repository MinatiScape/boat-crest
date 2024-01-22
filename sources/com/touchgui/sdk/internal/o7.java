package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class o7 extends y8 {
    public o7() {
        super((byte) 2, (byte) -67);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        boolean z = true;
        return Boolean.valueOf((bArr.length <= 2 || (bArr[2] & 255) != 1) ? false : false);
    }
}
