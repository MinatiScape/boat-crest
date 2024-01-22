package com.touchgui.sdk.internal;

import java.util.Locale;
/* loaded from: classes12.dex */
public final class w7 extends y8 {
    public w7() {
        super((byte) 2, (byte) 4);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        return bArr.length < 14 ? "00:00:00:00:00:00" : String.format(Locale.getDefault(), "%02x:%02x:%02x:%02x:%02x:%02x", Byte.valueOf(bArr[8]), Byte.valueOf(bArr[9]), Byte.valueOf(bArr[10]), Byte.valueOf(bArr[11]), Byte.valueOf(bArr[12]), Byte.valueOf(bArr[13])).toUpperCase();
    }
}
