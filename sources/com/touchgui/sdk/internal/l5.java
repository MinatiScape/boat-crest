package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGFootballFieldGps;
import com.touchgui.sdk.bean.TGSyncGps;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class l5 extends h5 {
    public l5() {
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
        TGFootballFieldGps tGFootballFieldGps = null;
        if (i2 > 0 && bArr.length >= i4 + i3 + 14) {
            tGFootballFieldGps = new TGFootballFieldGps();
            tGFootballFieldGps.setOffset(i);
            tGFootballFieldGps.setHaveMoreData(z);
            wrap.get();
            tGFootballFieldGps.setDate(h2.a(wrap.getShort() & UShort.MAX_VALUE, wrap.get(), wrap.get(), wrap.get(), wrap.get(), wrap.get()));
            wrap.position(wrap.position() + 2);
            ArrayList arrayList = new ArrayList();
            for (int i5 = 0; i5 < i2; i5++) {
                TGSyncGps.ItemBean itemBean = new TGSyncGps.ItemBean();
                itemBean.setOffset(wrap.getShort() & UShort.MAX_VALUE);
                itemBean.setLongitude(wrap.getDouble());
                itemBean.setLatitude(wrap.getDouble());
                arrayList.add(itemBean);
            }
            tGFootballFieldGps.setItems(arrayList);
        }
        return tGFootballFieldGps;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0, 8, 0, 0, 0};
    }
}
