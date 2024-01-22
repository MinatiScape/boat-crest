package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGWashConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class i8 extends y8 {
    public i8() {
        super((byte) 2, (byte) -48);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGWashConfig tGWashConfig = new TGWashConfig();
        tGWashConfig.setEnable((wrap.get() & 255) == 170);
        tGWashConfig.setStartHour(wrap.get() & 255);
        tGWashConfig.setStartMinute(wrap.get() & 255);
        tGWashConfig.setStopHour(wrap.get() & 255);
        tGWashConfig.setStopMinute(wrap.get() & 255);
        tGWashConfig.setInterval(wrap.getShort() & UShort.MAX_VALUE);
        return tGWashConfig;
    }
}
