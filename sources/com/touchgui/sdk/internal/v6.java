package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSportStatus;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class v6 extends y8 {
    public v6() {
        super((byte) 2, (byte) -90);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGSportStatus tGSportStatus = new TGSportStatus();
        tGSportStatus.setSportType(wrap.get() & 255);
        tGSportStatus.setNeedAppGpsData(wrap.get() == 1);
        return tGSportStatus;
    }
}
