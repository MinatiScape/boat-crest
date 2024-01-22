package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSedentaryConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class d7 extends y8 {
    public d7() {
        super((byte) 2, (byte) -78);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGSedentaryConfig tGSedentaryConfig = new TGSedentaryConfig();
        tGSedentaryConfig.setStartHour(wrap.get() & 255);
        tGSedentaryConfig.setStartMinute(wrap.get() & 255);
        tGSedentaryConfig.setStopHour(wrap.get() & 255);
        tGSedentaryConfig.setStopMinute(wrap.get() & 255);
        tGSedentaryConfig.setInterval(wrap.getShort() & UShort.MAX_VALUE);
        tGSedentaryConfig.setRepeat(wrap.get() & 255);
        tGSedentaryConfig.setNoontimeOnOff((wrap.get() & 255) == 1);
        tGSedentaryConfig.setNoontimeStartHour(wrap.get() & 255);
        tGSedentaryConfig.setNoontimeStartMinute(wrap.get() & 255);
        tGSedentaryConfig.setNoontimeStopHour(wrap.get() & 255);
        tGSedentaryConfig.setNoontimeStopMinute(wrap.get() & 255);
        return tGSedentaryConfig;
    }
}
