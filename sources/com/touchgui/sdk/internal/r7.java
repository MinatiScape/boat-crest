package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSpo2Config;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class r7 extends y8 {
    public r7() {
        super((byte) 2, (byte) 18);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGSpo2Config tGSpo2Config = new TGSpo2Config();
        tGSpo2Config.setMode(wrap.get() & 255);
        tGSpo2Config.setHasRange((wrap.get() & 255) == 1);
        tGSpo2Config.setStartHour(wrap.get() & 255);
        tGSpo2Config.setStartMinute(wrap.get() & 255);
        tGSpo2Config.setStopHour(wrap.get() & 255);
        tGSpo2Config.setStopMinute(wrap.get() & 255);
        tGSpo2Config.setInterval(wrap.get() & 255);
        return tGSpo2Config;
    }
}
