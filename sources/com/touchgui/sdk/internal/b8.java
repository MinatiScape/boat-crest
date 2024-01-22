package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGBatteryInfo;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class b8 extends y8 {
    public b8() {
        super((byte) 2, (byte) 5);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGBatteryInfo tGBatteryInfo = new TGBatteryInfo();
        tGBatteryInfo.setType(wrap.get() & 255);
        tGBatteryInfo.setVoltage(wrap.getShort() & UShort.MAX_VALUE);
        tGBatteryInfo.setStatus(wrap.get() & 255);
        tGBatteryInfo.setLevel(wrap.get() & 255);
        int i = wrap.getShort() & UShort.MAX_VALUE;
        int i2 = wrap.get() & 255;
        int i3 = wrap.get() & 255;
        int i4 = wrap.get() & 255;
        int i5 = wrap.get() & 255;
        int i6 = wrap.get() & 255;
        tGBatteryInfo.setLastCharging((i == 0 && i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0 && i6 == 0) ? null : h2.a(i, i2, i3, i4, i5, i6));
        if (wrap.position() + 1 <= wrap.array().length) {
            tGBatteryInfo.setFull((wrap.get() & 255) == 1);
        }
        return tGBatteryInfo;
    }
}
