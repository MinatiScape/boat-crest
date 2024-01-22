package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSyncBlockGps;
import com.touchgui.sdk.bean.TGSyncGps;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class j5 extends h5 {
    public j5() {
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
        TGSyncBlockGps tGSyncBlockGps = new TGSyncBlockGps();
        if (i2 > 0 && bArr.length >= i4 + i3 + 14) {
            TGSyncGps tGSyncGps = new TGSyncGps();
            tGSyncGps.setOffset(i);
            tGSyncGps.setHaveMoreData(z);
            tGSyncBlockGps.setTotalPkg(wrap.getShort() & UShort.MAX_VALUE);
            tGSyncBlockGps.setPkgIndex(wrap.getShort() & UShort.MAX_VALUE);
            wrap.get();
            tGSyncGps.setDate(h2.a(wrap.getShort() & UShort.MAX_VALUE, wrap.get(), wrap.get(), wrap.get(), wrap.get(), wrap.get()));
            wrap.position(wrap.position() + 2);
            ArrayList arrayList = new ArrayList();
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                TGSyncGps.ItemBean itemBean = new TGSyncGps.ItemBean();
                i5 += wrap.getShort() & UShort.MAX_VALUE;
                itemBean.setOffset(i5);
                itemBean.setLongitude(wrap.getDouble());
                itemBean.setLatitude(wrap.getDouble());
                arrayList.add(itemBean);
            }
            tGSyncGps.setItems(arrayList);
            tGSyncBlockGps.setGpsData(tGSyncGps);
        }
        return tGSyncBlockGps;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0, 7, 0, 0, 0};
    }
}
