package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSyncHealth;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class t4 extends y8 {
    public t4() {
        super((byte) 8, (byte) 1, 1);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(4);
        TGSyncHealth tGSyncHealth = new TGSyncHealth();
        tGSyncHealth.setStepDay(wrap.get() & 255);
        tGSyncHealth.setSleepDay(wrap.get() & 255);
        tGSyncHealth.setHeartRateDay(wrap.get() & 255);
        tGSyncHealth.setBpDay(wrap.get() & 255);
        return tGSyncHealth;
    }
}
