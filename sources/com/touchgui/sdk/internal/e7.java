package com.touchgui.sdk.internal;

import com.coveiot.sdk.ble.api.BleUUID;
import com.touchgui.sdk.bean.TGHeartRateRangeConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class e7 extends y8 {
    public e7() {
        super((byte) 2, BleUUID.CMD_ID_B4);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGHeartRateRangeConfig tGHeartRateRangeConfig = new TGHeartRateRangeConfig();
        wrap.position(5);
        tGHeartRateRangeConfig.setMaxHr(wrap.get() & 255);
        tGHeartRateRangeConfig.setRange1(wrap.get() & 255);
        tGHeartRateRangeConfig.setRange2(wrap.get() & 255);
        tGHeartRateRangeConfig.setRange3(wrap.get() & 255);
        tGHeartRateRangeConfig.setRange4(wrap.get() & 255);
        tGHeartRateRangeConfig.setRange5(wrap.get() & 255);
        tGHeartRateRangeConfig.setMinHr(wrap.get() & 255);
        tGHeartRateRangeConfig.setEnableMax((wrap.get() & 255) == 1);
        tGHeartRateRangeConfig.setEnableMin((wrap.get() & 255) == 1);
        return tGHeartRateRangeConfig;
    }
}
