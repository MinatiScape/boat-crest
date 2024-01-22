package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGWorldClock;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
/* loaded from: classes12.dex */
public final class sb extends h5 {
    public final /* synthetic */ List l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public sb(List list) {
        super((short) 33);
        this.l = list;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        return Integer.valueOf(bArr[11] & 255);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        int i = 1;
        int i2 = 1;
        for (TGWorldClock tGWorldClock : this.l) {
            i2 = i2 + 3 + tGWorldClock.getCityNameLen() + 1;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i2);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.put((byte) this.l.size());
        for (TGWorldClock tGWorldClock2 : this.l) {
            allocate.put((byte) (i & 255));
            allocate.putShort((short) tGWorldClock2.getTimezone());
            allocate.put((byte) (tGWorldClock2.getCityNameLen() & 255));
            if (tGWorldClock2.getCityNameLen() > 0) {
                allocate.put(tGWorldClock2.getCityNameBytes());
            }
            i++;
        }
        return allocate.array();
    }
}
