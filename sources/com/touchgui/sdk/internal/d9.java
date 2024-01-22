package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class d9 extends y8 {
    public d9() {
        super((byte) 32, (byte) 1, 4);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        return new f8(bArr[2], Integer.valueOf(bArr[3] & 255));
    }
}
