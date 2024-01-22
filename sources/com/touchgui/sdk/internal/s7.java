package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGStressConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class s7 extends y8 {
    public s7() {
        super((byte) 2, (byte) 19);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGStressConfig tGStressConfig = new TGStressConfig();
        tGStressConfig.setMode(wrap.get() & 255);
        tGStressConfig.setHasRange((wrap.get() & 255) == 1);
        tGStressConfig.setStartHour(wrap.get() & 255);
        tGStressConfig.setStartMinute(wrap.get() & 255);
        tGStressConfig.setStopHour(wrap.get() & 255);
        tGStressConfig.setStopMinute(wrap.get() & 255);
        tGStressConfig.setInterval(wrap.get() & 255);
        return tGStressConfig;
    }
}
