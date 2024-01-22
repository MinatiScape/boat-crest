package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSyncGps;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class d6 extends h5 {
    public final /* synthetic */ boolean l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d6(boolean z) {
        super((short) 4);
        this.l = z;
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
        TGSyncGps tGSyncGps = null;
        if (i2 > 0 && bArr.length >= i4 + i3 + 14) {
            tGSyncGps = new TGSyncGps();
            tGSyncGps.setOffset(i);
            tGSyncGps.setHaveMoreData(z);
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
        }
        return tGSyncGps;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0, 5, (byte) (!this.l ? 1 : 0), 0, 0};
    }
}
