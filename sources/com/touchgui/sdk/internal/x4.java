package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGBreathTrain;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class x4 extends h5 {
    public x4() {
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
        wrap.get();
        int i = wrap.getInt();
        int i2 = wrap.getInt();
        wrap.get();
        wrap.get();
        int i3 = wrap.getShort() & UShort.MAX_VALUE;
        TGBreathTrain tGBreathTrain = new TGBreathTrain();
        tGBreathTrain.setStartUtcTime(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 1000);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        tGBreathTrain.setStartTime((int) (h2.a(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13)).getTime() / 1000));
        tGBreathTrain.setDuration(i2 - i);
        tGBreathTrain.setAvgStress(wrap.get() & 255);
        tGBreathTrain.setAvgHr(wrap.get() & 255);
        if (i3 > 0) {
            ArrayList arrayList = new ArrayList();
            int i4 = 0;
            int i5 = 0;
            while (i4 < i3) {
                TGBreathTrain.ItemBean itemBean = new TGBreathTrain.ItemBean();
                int i6 = wrap.getShort() & UShort.MAX_VALUE;
                itemBean.setOffset(i6 - i5);
                itemBean.setStressValue(wrap.get() & 255);
                itemBean.setHeartRate(wrap.get() & 255);
                arrayList.add(itemBean);
                i4++;
                i5 = i6;
            }
            tGBreathTrain.setItems(arrayList);
        }
        return tGBreathTrain;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0, 17, 0, 0, 0};
    }
}
