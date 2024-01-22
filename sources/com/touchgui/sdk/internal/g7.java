package com.touchgui.sdk.internal;

import com.coveiot.sdk.ble.api.BleUUID;
import com.touchgui.sdk.bean.TGFindPhoneConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class g7 extends y8 {
    public g7() {
        super((byte) 2, BleUUID.CMD_ID_B6);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGFindPhoneConfig tGFindPhoneConfig = new TGFindPhoneConfig();
        tGFindPhoneConfig.setEnable((wrap.get() & 255) == 170);
        tGFindPhoneConfig.setTimeout(wrap.get() & 255);
        return tGFindPhoneConfig;
    }
}
