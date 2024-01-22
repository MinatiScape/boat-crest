package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGRemindDrinking;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class j7 extends y8 {
    public j7() {
        super((byte) 2, (byte) -69);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGRemindDrinking tGRemindDrinking = new TGRemindDrinking();
        int i = (wrap.get() & 255) == 170 ? 1 : 0;
        tGRemindDrinking.setStartHour(wrap.get() & 255);
        tGRemindDrinking.setStartMinute(wrap.get() & 255);
        tGRemindDrinking.setStopHour(wrap.get() & 255);
        tGRemindDrinking.setStopMinute(wrap.get() & 255);
        tGRemindDrinking.setRepeat(i | (wrap.get() & 254));
        tGRemindDrinking.setInterval(wrap.getShort() & UShort.MAX_VALUE);
        tGRemindDrinking.setNoontimeOnOff((wrap.get() & 255) == 1);
        tGRemindDrinking.setNoontimeStartHour(wrap.get() & 255);
        tGRemindDrinking.setNoontimeStartMinute(wrap.get() & 255);
        tGRemindDrinking.setNoontimeStopHour(wrap.get() & 255);
        tGRemindDrinking.setNoontimeStopMinute(wrap.get() & 255);
        return tGRemindDrinking;
    }
}
