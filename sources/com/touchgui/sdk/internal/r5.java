package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGAlarm;
import java.nio.ByteBuffer;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class r5 extends h5 {
    public r5() {
        super((short) 15);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.position(11);
        wrap.get();
        int i = wrap.get() & 255;
        for (int i2 = 0; i2 < i; i2++) {
            TGAlarm tGAlarm = new TGAlarm();
            tGAlarm.setId(wrap.get() & 255);
            tGAlarm.setShow(wrap.get() == 85);
            tGAlarm.setType(wrap.get() & 255);
            tGAlarm.setHour(wrap.get() & 255);
            tGAlarm.setMinute(wrap.get() & 255);
            tGAlarm.setRepeat(wrap.get() & 255);
            tGAlarm.setSnooze(wrap.get() & 255);
            wrap.position(wrap.position() + 6);
            arrayList.add(tGAlarm);
        }
        return arrayList;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0};
    }
}
