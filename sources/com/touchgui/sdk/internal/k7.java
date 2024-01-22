package com.touchgui.sdk.internal;

import com.coveiot.sdk.ble.api.BleUUID;
/* loaded from: classes12.dex */
public final class k7 extends y8 {
    public k7() {
        super((byte) 2, BleUUID.CMD_ID_B8);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        boolean z = true;
        return Boolean.valueOf((bArr.length <= 2 || (bArr[2] & 255) != 1) ? false : false);
    }
}
