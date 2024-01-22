package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSyncData;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class x6 extends y8 {
    public x6() {
        super((byte) 2, com.crrepa.c.a.L1, 1);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGSyncData tGSyncData = new TGSyncData();
        tGSyncData.setTotalSize(wrap.getInt());
        tGSyncData.setActivityCount(wrap.getShort() & UShort.MAX_VALUE);
        tGSyncData.setGpsCount(wrap.getShort() & UShort.MAX_VALUE);
        tGSyncData.setSpo2Count(wrap.getShort() & UShort.MAX_VALUE);
        tGSyncData.setSwimCount(wrap.getShort() & UShort.MAX_VALUE);
        tGSyncData.setPressCount(wrap.getShort() & UShort.MAX_VALUE);
        tGSyncData.setHeartRateCount(wrap.getShort() & UShort.MAX_VALUE);
        if (wrap.position() + 2 <= wrap.array().length) {
            tGSyncData.setBreathTrainCount(wrap.getShort() & UShort.MAX_VALUE);
        }
        return tGSyncData;
    }
}
