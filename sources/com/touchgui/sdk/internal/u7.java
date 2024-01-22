package com.touchgui.sdk.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class u7 extends y8 {
    public u7() {
        super((byte) 2, (byte) 10);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        return bArr.length > 3 ? new String(bArr, 3, Math.min(wrap.get() & 255, bArr.length - 3)) : "";
    }
}
