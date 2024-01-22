package com.touchgui.sdk.internal;

import android.os.Build;
import com.touchgui.sdk.bean.TGPhysiologicalCycle;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Calendar;
import java.util.Date;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class q7 extends y8 {
    public q7() {
        super((byte) 2, (byte) -65);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGPhysiologicalCycle tGPhysiologicalCycle = new TGPhysiologicalCycle();
        tGPhysiologicalCycle.setEnable((wrap.get() & 255) == 1);
        int i = wrap.getShort() & UShort.MAX_VALUE;
        byte b = wrap.get();
        byte b2 = wrap.get();
        tGPhysiologicalCycle.setLastDate(Build.VERSION.SDK_INT >= 26 ? new Calendar.Builder().setDate(i, b - 1, b2).build().getTime() : new Date(i - 1900, b - 1, b2));
        tGPhysiologicalCycle.setMenstrualDuration(wrap.get() & 255);
        tGPhysiologicalCycle.setMenstrualCycleDays(wrap.get() & 255);
        tGPhysiologicalCycle.setRemindMenstrual(wrap.get() & 255);
        tGPhysiologicalCycle.setRemindOvulation(wrap.get() & 255);
        tGPhysiologicalCycle.setRemindHour(wrap.get() & 255);
        tGPhysiologicalCycle.setRemindMinute(wrap.get() & 255);
        return tGPhysiologicalCycle;
    }
}
