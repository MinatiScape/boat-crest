package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGRaiseWristConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class h7 extends y8 {
    public h7() {
        super((byte) 2, com.crrepa.c.a.J1);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGRaiseWristConfig tGRaiseWristConfig = new TGRaiseWristConfig();
        tGRaiseWristConfig.setOn((wrap.get() & 255) == 170);
        wrap.get();
        tGRaiseWristConfig.setHasRange((wrap.get() & 255) == 1);
        tGRaiseWristConfig.setStartHour(wrap.get() & 255);
        tGRaiseWristConfig.setStartMinute(wrap.get() & 255);
        tGRaiseWristConfig.setStopHour(wrap.get() & 255);
        tGRaiseWristConfig.setStopMinute(wrap.get() & 255);
        return tGRaiseWristConfig;
    }
}
