package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGNightModeConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class m7 extends y8 {
    public m7() {
        super((byte) 2, (byte) -71);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGNightModeConfig tGNightModeConfig = new TGNightModeConfig();
        tGNightModeConfig.setOn((wrap.get() & 255) == 1);
        tGNightModeConfig.setStartHour(wrap.get() & 255);
        tGNightModeConfig.setStartMinute(wrap.get() & 255);
        tGNightModeConfig.setStopHour(wrap.get() & 255);
        tGNightModeConfig.setStopMinute(wrap.get() & 255);
        return tGNightModeConfig;
    }
}
