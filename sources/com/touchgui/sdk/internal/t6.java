package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGGpsInfo;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class t6 extends y8 {
    public t6() {
        super((byte) 2, (byte) -93);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGGpsInfo tGGpsInfo = new TGGpsInfo();
        tGGpsInfo.setErrCode(wrap.get() & 255);
        tGGpsInfo.setFwVersion(wrap.getInt());
        tGGpsInfo.setAgpsInfo(wrap.getInt());
        tGGpsInfo.setAgpsErrorCode(wrap.get() & 255);
        tGGpsInfo.setDate(h2.a(wrap.get() + 2000, wrap.get(), wrap.get(), wrap.get(), wrap.get(), 0));
        tGGpsInfo.setStartMode(wrap.get() & 255);
        tGGpsInfo.setGns(wrap.get() & 255);
        return tGGpsInfo;
    }
}
