package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSportStatusEvent;
/* loaded from: classes12.dex */
public final class k2 extends e8 {
    public k2() {
        super((byte) 3);
    }

    @Override // com.touchgui.sdk.internal.e8
    public final Object c(byte[] bArr) {
        TGSportStatusEvent tGSportStatusEvent = new TGSportStatusEvent();
        tGSportStatusEvent.setEvent(bArr[2] & 255);
        tGSportStatusEvent.setSportType(bArr[3] & 255);
        tGSportStatusEvent.setNeedAppGpsData(bArr[4] == 1);
        return tGSportStatusEvent;
    }
}
