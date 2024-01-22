package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class x7 extends y8 {
    public x7() {
        super((byte) 2, (byte) 6);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        byte b;
        int i = 0;
        for (int i2 = 2; i2 < bArr.length && (b = bArr[i2]) != 0 && b != -1; i2++) {
            i++;
        }
        return new String(bArr, 2, i);
    }
}
