package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGMtuInfo;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class w6 extends y8 {
    public w6() {
        super((byte) 2, (byte) -16);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(3);
        TGMtuInfo tGMtuInfo = new TGMtuInfo();
        tGMtuInfo.setTxMtu(wrap.getShort() & UShort.MAX_VALUE);
        tGMtuInfo.setRxMtu(wrap.getShort() & UShort.MAX_VALUE);
        tGMtuInfo.setPhySpeed(wrap.getShort() & UShort.MAX_VALUE);
        tGMtuInfo.setDleLength(wrap.getShort() & UShort.MAX_VALUE);
        return tGMtuInfo;
    }
}
