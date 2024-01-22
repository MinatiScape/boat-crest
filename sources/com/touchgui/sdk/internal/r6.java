package com.touchgui.sdk.internal;

import com.coveiot.sdk.ble.api.BleUUID;
import com.touchgui.sdk.bean.TGRealTimeData;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class r6 extends y8 {
    public r6() {
        super((byte) 2, BleUUID.CMD_ID_A0);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGRealTimeData tGRealTimeData = new TGRealTimeData();
        tGRealTimeData.setTotalSteps(wrap.getInt());
        tGRealTimeData.setTotalCalories(wrap.getInt());
        tGRealTimeData.setTotalDistances(wrap.getInt());
        tGRealTimeData.setTotalActiveTime(wrap.getInt());
        tGRealTimeData.setHeartRate(wrap.get() & 255);
        return tGRealTimeData;
    }
}
