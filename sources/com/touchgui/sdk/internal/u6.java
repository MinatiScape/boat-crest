package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGGpsStatus;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class u6 extends y8 {
    public u6() {
        super((byte) 2, (byte) -91);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGGpsStatus tGGpsStatus = new TGGpsStatus();
        tGGpsStatus.setRunStatus(wrap.get() & 255);
        tGGpsStatus.setValidHours(wrap.getShort() & UShort.MAX_VALUE);
        return tGGpsStatus;
    }
}
