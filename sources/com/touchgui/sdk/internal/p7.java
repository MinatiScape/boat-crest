package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class p7 extends y8 {
    public p7() {
        super((byte) 2, (byte) -66);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        return Boolean.valueOf(bArr.length > 2 && (bArr[2] & 255) == 170);
    }
}
