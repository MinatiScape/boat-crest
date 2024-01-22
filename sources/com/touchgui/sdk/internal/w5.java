package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGStressData;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class w5 extends h5 {
    public final /* synthetic */ boolean l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w5(boolean z) {
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
        int min = Math.min(wrap.getInt(), i2 * 6);
        boolean z = wrap.get() == 1;
        if (i2 <= 0 || bArr.length < min + i3 + 14) {
            return null;
        }
        TGStressData tGStressData = new TGStressData();
        tGStressData.setOffset(i);
        tGStressData.setHaveMoreData(z);
        tGStressData.setDate(h2.a(wrap.getShort() & UShort.MAX_VALUE, wrap.get(), wrap.get()));
        tGStressData.setStartTime(wrap.getInt());
        wrap.position(wrap.position() + 5);
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < i2; i4++) {
            TGStressData.ItemBean itemBean = new TGStressData.ItemBean();
            int i5 = wrap.getInt();
            itemBean.setUtcSeconds(i5);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(i5 * 1000);
            calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
            itemBean.setTimeSeconds((int) (h2.a(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13)).getTime() / 1000));
            itemBean.setValue(wrap.get() & 255);
            itemBean.setHeartRate(wrap.get() & 255);
            arrayList.add(itemBean);
        }
        tGStressData.setItems(arrayList);
        return tGStressData;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0, 16, (byte) (!this.l ? 1 : 0), 0, 0};
    }
}
