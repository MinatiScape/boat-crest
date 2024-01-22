package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGWorldClock;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class rb extends h5 {
    public rb() {
        super((short) 34);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(11);
        int i = wrap.get() & 255;
        for (int i2 = 0; i2 < i; i2++) {
            wrap.get();
            TGWorldClock tGWorldClock = new TGWorldClock();
            tGWorldClock.setTimezone(wrap.getShort());
            int i3 = wrap.get() & 255;
            if (i3 > 0) {
                byte[] bArr2 = new byte[i3];
                wrap.get(bArr2, 0, i3);
                tGWorldClock.setCityName(new String(bArr2));
            }
            arrayList.add(tGWorldClock);
        }
        return arrayList;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0};
    }
}
