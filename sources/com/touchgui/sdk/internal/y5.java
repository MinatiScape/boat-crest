package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGAlarm;
import java.nio.ByteBuffer;
import java.util.List;
/* loaded from: classes12.dex */
public final class y5 extends h5 {
    public final /* synthetic */ List l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y5(List list) {
        super((short) 14);
        this.l = list;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        return Integer.valueOf(bArr[11] & 255);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        ByteBuffer allocate = ByteBuffer.allocate((this.l.size() * 13) + 2);
        allocate.put((byte) 0);
        allocate.put((byte) this.l.size());
        for (TGAlarm tGAlarm : this.l) {
            allocate.put(tGAlarm.toBytes());
        }
        return allocate.array();
    }
}
