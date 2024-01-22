package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class s8 extends y8 {
    public s8() {
        super((byte) 11, (byte) 6);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        return Integer.valueOf(bArr.length > 2 ? bArr[2] & 255 : 0);
    }
}
