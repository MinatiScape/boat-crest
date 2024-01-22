package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGStock;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class j9 extends h5 {
    public j9() {
        super((short) 36);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(11);
        int i = wrap.get() & 255;
        for (int i2 = 0; i2 < i; i2++) {
            TGStock tGStock = new TGStock();
            wrap.get();
            tGStock.setCurrent(wrap.getInt() / 1000.0f);
            tGStock.setFluctuates(wrap.getInt() / 1000.0f);
            tGStock.setPercent(wrap.getInt() / 10000.0f);
            int i3 = wrap.get() & 255;
            if (i3 > 0) {
                byte[] bArr2 = new byte[i3];
                wrap.get(bArr2, 0, i3);
                tGStock.setCode(new String(bArr2));
            }
            int i4 = wrap.get() & 255;
            if (i4 > 0) {
                byte[] bArr3 = new byte[i4];
                wrap.get(bArr3, 0, i4);
                tGStock.setName(new String(bArr3));
            }
            arrayList.add(tGStock);
        }
        return arrayList;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0};
    }
}
