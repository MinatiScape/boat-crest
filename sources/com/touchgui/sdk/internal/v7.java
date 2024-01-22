package com.touchgui.sdk.internal;

import java.util.Locale;
/* loaded from: classes12.dex */
public final class v7 extends y8 {
    public v7() {
        super((byte) 2, (byte) 4);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        return bArr.length < 8 ? "00:00:00:00:00:00" : String.format(Locale.getDefault(), "%02x:%02x:%02x:%02x:%02x:%02x", Byte.valueOf(bArr[2]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[5]), Byte.valueOf(bArr[6]), Byte.valueOf(bArr[7])).toUpperCase();
    }
}
