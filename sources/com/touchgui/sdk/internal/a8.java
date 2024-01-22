package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGVersionInfo;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class a8 extends y8 {
    public a8() {
        super((byte) 2, (byte) 17);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        byte b;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGVersionInfo tGVersionInfo = new TGVersionInfo();
        tGVersionInfo.setSdkVersion(wrap.get() & 255);
        tGVersionInfo.setHrAlgorithmVersion(wrap.get() & 255);
        tGVersionInfo.setSleepAlgorithmVersion(wrap.get() & 255);
        tGVersionInfo.setStepAlgorithmVersion(wrap.get() & 255);
        tGVersionInfo.setGestureAlgorithmVersion(wrap.get() & 255);
        tGVersionInfo.setPcbAlgorithmVersion(wrap.get() & 255);
        int i = 0;
        for (int i2 = 8; i2 < bArr.length && (b = bArr[i2]) != 0 && b != -1; i2++) {
            i++;
        }
        tGVersionInfo.setDetailVersion(new String(bArr, 8, i));
        return tGVersionInfo;
    }
}
