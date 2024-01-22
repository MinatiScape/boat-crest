package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSportRealTimeData;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class p5 extends h5 {
    public final /* synthetic */ boolean l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p5(boolean z) {
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
        TGSportRealTimeData tGSportRealTimeData = null;
        if (i2 > 0 && bArr.length >= i4 + i3 + 14) {
            tGSportRealTimeData = new TGSportRealTimeData();
            tGSportRealTimeData.setOffset(i);
            tGSportRealTimeData.setHaveMoreData(z);
            wrap.get();
            tGSportRealTimeData.setDate(h2.a(wrap.getShort() & UShort.MAX_VALUE, wrap.get(), wrap.get(), wrap.get(), wrap.get(), wrap.get()));
            wrap.position(wrap.position() + 2);
            ArrayList arrayList = new ArrayList();
            for (int i5 = 0; i5 < i2; i5++) {
                TGSportRealTimeData.ItemBean itemBean = new TGSportRealTimeData.ItemBean();
                itemBean.setHeartRate(wrap.get() & 255);
                itemBean.setPace(wrap.getShort() & UShort.MAX_VALUE);
                itemBean.setSpeed(wrap.getShort() & UShort.MAX_VALUE);
                itemBean.setSwolf(wrap.getShort() & UShort.MAX_VALUE);
                arrayList.add(itemBean);
            }
            tGSportRealTimeData.setItems(arrayList);
        }
        return tGSportRealTimeData;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0, 10, (byte) (!this.l ? 1 : 0), 0, 0};
    }
}
