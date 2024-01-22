package com.touchgui.sdk.internal;

import com.coveiot.sdk.ble.api.BleUUID;
import com.touchgui.sdk.bean.TGNotDisturbConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class i7 extends y8 {
    public i7() {
        super((byte) 2, BleUUID.CMD_ID_B7);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGNotDisturbConfig tGNotDisturbConfig = new TGNotDisturbConfig();
        tGNotDisturbConfig.setOn((wrap.get() & 255) == 170);
        tGNotDisturbConfig.setStartHour(wrap.get() & 255);
        tGNotDisturbConfig.setStartMinute(wrap.get() & 255);
        tGNotDisturbConfig.setStopHour(wrap.get() & 255);
        tGNotDisturbConfig.setStopMinute(wrap.get() & 255);
        return tGNotDisturbConfig;
    }
}
