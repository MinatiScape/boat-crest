package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGUnitConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class c7 extends y8 {
    public c7() {
        super((byte) 2, (byte) 8);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGUnitConfig tGUnitConfig = new TGUnitConfig();
        tGUnitConfig.setDistance(wrap.get() & 255);
        tGUnitConfig.setWeight(wrap.get() & 255);
        tGUnitConfig.setTemp(wrap.get() & 255);
        tGUnitConfig.setStrideWalk(wrap.get() & 255);
        tGUnitConfig.setLanguage(wrap.get() & 255);
        tGUnitConfig.setTimeMode(wrap.get() & 255);
        tGUnitConfig.setStrideRun(wrap.get() & 255);
        return tGUnitConfig;
    }
}
