package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSyncSwim;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class f6 extends h5 {
    public final /* synthetic */ boolean l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f6(boolean z) {
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
        if (i2 <= 0 || bArr.length < i4 + i3 + 14) {
            return null;
        }
        TGSyncSwim tGSyncSwim = new TGSyncSwim();
        tGSyncSwim.setOffset(i);
        tGSyncSwim.setHaveMoreData(z);
        tGSyncSwim.setDate(h2.a(wrap.getShort() & UShort.MAX_VALUE, wrap.get(), wrap.get(), wrap.get(), wrap.get(), wrap.get()));
        wrap.get();
        tGSyncSwim.setType(wrap.get() & 255);
        tGSyncSwim.setCalories(wrap.getShort() & UShort.MAX_VALUE);
        tGSyncSwim.setDistance(wrap.getShort() & UShort.MAX_VALUE);
        tGSyncSwim.setConfirmDistance(wrap.getShort() & UShort.MAX_VALUE);
        tGSyncSwim.setTrips(wrap.getShort() & UShort.MAX_VALUE);
        tGSyncSwim.setDuration(wrap.getShort() & UShort.MAX_VALUE);
        tGSyncSwim.setAvgSwolf(wrap.getShort() & UShort.MAX_VALUE);
        tGSyncSwim.setTotalStrokes(wrap.getShort() & UShort.MAX_VALUE);
        tGSyncSwim.setPosture(wrap.get() & 255);
        tGSyncSwim.setPoolDistance(wrap.getShort() & UShort.MAX_VALUE);
        wrap.position(wrap.position() + 4);
        ArrayList arrayList = new ArrayList();
        for (int i5 = 0; i5 < i2; i5++) {
            TGSyncSwim.ItemBean itemBean = new TGSyncSwim.ItemBean();
            itemBean.setSwolf(wrap.get() & 255);
            itemBean.setPosture(wrap.get() & 255);
            itemBean.setStrokesNum(wrap.getShort() & UShort.MAX_VALUE);
            itemBean.setDuration(wrap.getShort() & UShort.MAX_VALUE);
            itemBean.setDistance(wrap.getShort() & UShort.MAX_VALUE);
            arrayList.add(itemBean);
        }
        tGSyncSwim.setItems(arrayList);
        return tGSyncSwim;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0, 6, (byte) (!this.l ? 1 : 0), 0, 0};
    }
}
