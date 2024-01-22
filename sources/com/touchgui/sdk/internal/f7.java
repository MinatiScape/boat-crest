package com.touchgui.sdk.internal;

import com.coveiot.sdk.ble.api.BleUUID;
import com.touchgui.sdk.bean.TGHeartRateMonitoringModeConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class f7 extends y8 {
    public f7() {
        super((byte) 2, BleUUID.CMD_ID_B5);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGHeartRateMonitoringModeConfig tGHeartRateMonitoringModeConfig = new TGHeartRateMonitoringModeConfig();
        tGHeartRateMonitoringModeConfig.setMode(wrap.get() & 255);
        tGHeartRateMonitoringModeConfig.setHasRange((wrap.get() & 255) == 1);
        tGHeartRateMonitoringModeConfig.setStartHour(wrap.get() & 255);
        tGHeartRateMonitoringModeConfig.setStartMinute(wrap.get() & 255);
        tGHeartRateMonitoringModeConfig.setStopHour(wrap.get() & 255);
        tGHeartRateMonitoringModeConfig.setStopMinute(wrap.get() & 255);
        tGHeartRateMonitoringModeConfig.setInterval(wrap.get() & 255);
        return tGHeartRateMonitoringModeConfig;
    }
}
