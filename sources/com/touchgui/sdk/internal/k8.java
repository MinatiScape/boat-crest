package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGWorkoutHeartRateConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class k8 extends y8 {
    public k8() {
        super((byte) 2, (byte) 11);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGWorkoutHeartRateConfig tGWorkoutHeartRateConfig = new TGWorkoutHeartRateConfig();
        int i = wrap.get() & 255;
        int i2 = wrap.get() & 255;
        tGWorkoutHeartRateConfig.setEnableAlert(i == 170);
        tGWorkoutHeartRateConfig.setMaxHr(i2);
        return tGWorkoutHeartRateConfig;
    }
}
