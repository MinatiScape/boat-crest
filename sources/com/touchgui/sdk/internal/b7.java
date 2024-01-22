package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGDeviceInfo;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class b7 extends y8 {
    public b7() {
        super((byte) 2, (byte) 1);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGDeviceInfo tGDeviceInfo = new TGDeviceInfo();
        tGDeviceInfo.setId(wrap.getShort() & UShort.MAX_VALUE);
        tGDeviceInfo.setVersionCode(wrap.get() & 255);
        tGDeviceInfo.setRunMode(wrap.get() & 255);
        tGDeviceInfo.setBatteryStatus(wrap.get() & 255);
        tGDeviceInfo.setEnerge(wrap.get() & 255);
        tGDeviceInfo.setPairFlag(wrap.get() & 255);
        tGDeviceInfo.setRebootFlag(wrap.get() & 255);
        tGDeviceInfo.setHasDetailVersion(wrap.get() == 1);
        tGDeviceInfo.setBindConfirm(wrap.get() & 255);
        tGDeviceInfo.setPlatform(wrap.get() & 255);
        tGDeviceInfo.setResFlag(wrap.get() & 255);
        if (wrap.position() + 2 <= wrap.array().length) {
            tGDeviceInfo.setProtocolVersion(wrap.getShort() & UShort.MAX_VALUE);
            if (wrap.position() + 1 <= wrap.array().length) {
                tGDeviceInfo.setBtStatus(wrap.get() & 255);
            }
        }
        return tGDeviceInfo;
    }
}
