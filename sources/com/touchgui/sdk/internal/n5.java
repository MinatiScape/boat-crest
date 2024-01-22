package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGFootballAvgPace;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class n5 extends h5 {
    public n5() {
        super((short) 4);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(11);
        wrap.get();
        wrap.get();
        wrap.get();
        int i = wrap.getShort() & UShort.MAX_VALUE;
        int i2 = wrap.getShort() & UShort.MAX_VALUE;
        int i3 = wrap.getShort() & UShort.MAX_VALUE;
        int i4 = wrap.getInt();
        boolean z = wrap.get() == 1;
        TGFootballAvgPace tGFootballAvgPace = null;
        if (i2 > 0 && bArr.length >= i4 + i3 + 14) {
            tGFootballAvgPace = new TGFootballAvgPace();
            tGFootballAvgPace.setOffset(i);
            tGFootballAvgPace.setHaveMoreData(z);
            wrap.get();
            tGFootballAvgPace.setDate(h2.a(wrap.getShort() & UShort.MAX_VALUE, wrap.get(), wrap.get(), wrap.get(), wrap.get(), wrap.get()));
            wrap.position(wrap.position() + 2);
            ArrayList arrayList = new ArrayList();
            for (int i5 = 0; i5 < i2; i5++) {
                arrayList.add(Integer.valueOf(wrap.getInt()));
            }
            tGFootballAvgPace.setItems(arrayList);
        }
        return tGFootballAvgPace;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0, 9, 0, 0, 0};
    }
}
