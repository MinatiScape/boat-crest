package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGTargetConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class y6 extends y8 {
    public y6() {
        super((byte) 2, (byte) -80);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGTargetConfig tGTargetConfig = new TGTargetConfig();
        wrap.get();
        tGTargetConfig.setStep(wrap.getInt());
        tGTargetConfig.setSleepHour(wrap.get() & 255);
        tGTargetConfig.setSleepMinute(wrap.get() & 255);
        tGTargetConfig.setCalorie(wrap.getInt());
        tGTargetConfig.setDistance(wrap.getInt());
        if (wrap.position() + 8 < wrap.array().length) {
            tGTargetConfig.setStandingTime(wrap.getInt());
            tGTargetConfig.setWorkoutTime(wrap.getInt());
        }
        return tGTargetConfig;
    }
}
