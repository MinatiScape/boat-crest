package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGDataUpdated;
/* loaded from: classes12.dex */
public final class l2 extends e8 {
    public l2() {
        super((byte) 64);
    }

    @Override // com.touchgui.sdk.internal.e8
    public final Object c(byte[] bArr) {
        TGDataUpdated tGDataUpdated = new TGDataUpdated();
        tGDataUpdated.setType(s.b(bArr, 2, 2));
        tGDataUpdated.setAlarmUpdated1((bArr[4] & 1) > 0);
        tGDataUpdated.setFirmwareError((bArr[4] & 1) > 0);
        tGDataUpdated.setAlarmUpdated2((bArr[4] & 4) > 0);
        tGDataUpdated.setRequestSyncWeather((bArr[4] & 8) > 0);
        tGDataUpdated.setNotifyType(bArr[4] & 255);
        if (bArr.length >= 6) {
            tGDataUpdated.setRaiseWristUpdated((bArr[5] & 1) > 0);
            tGDataUpdated.setNotifyType2(bArr[5] & 255);
        }
        return tGDataUpdated;
    }
}
