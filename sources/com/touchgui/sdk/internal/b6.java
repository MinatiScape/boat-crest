package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSyncSpo2;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class b6 extends h5 {
    public final /* synthetic */ boolean l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b6(boolean z) {
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
        wrap.getShort();
        int i = wrap.getShort() & UShort.MAX_VALUE;
        int i2 = wrap.getShort() & UShort.MAX_VALUE;
        int i3 = wrap.getInt() & 65535;
        boolean z = wrap.get() == 1;
        if (i <= 0 || bArr.length < i3 + i2 + 14) {
            return null;
        }
        TGSyncSpo2 tGSyncSpo2 = new TGSyncSpo2();
        tGSyncSpo2.setHaveMoreData(z);
        tGSyncSpo2.setDate(h2.a(65535 & wrap.getShort(), wrap.get(), wrap.get()));
        wrap.getInt();
        wrap.position(wrap.position() + 5);
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < i; i4++) {
            TGSyncSpo2.ItemBean itemBean = new TGSyncSpo2.ItemBean();
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
        tGSyncSpo2.setItems(arrayList);
        return tGSyncSpo2;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0, 1, (byte) (1 ^ (this.l ? 1 : 0)), 0, 0};
    }
}
