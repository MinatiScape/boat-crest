package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGEventReminder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class k3 extends h5 {
    public final /* synthetic */ boolean l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k3(boolean z) {
        super((short) 24);
        this.l = z;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(11);
        int i = wrap.get() & 255;
        for (int i2 = 0; i2 < i; i2++) {
            TGEventReminder tGEventReminder = new TGEventReminder();
            tGEventReminder.setId(wrap.get() & 255);
            tGEventReminder.setType(wrap.get() & 255);
            tGEventReminder.setYear((wrap.get() & 255) + 2000);
            tGEventReminder.setMonth((wrap.get() & 255) - 1);
            tGEventReminder.setDay(wrap.get() & 255);
            tGEventReminder.setHour(wrap.get() & 255);
            tGEventReminder.setMinute(wrap.get() & 255);
            tGEventReminder.setRepeat(wrap.getShort() & UShort.MAX_VALUE);
            int i3 = wrap.getShort();
            if (i3 > 0) {
                byte[] bArr2 = new byte[i3];
                wrap.get(bArr2, 0, i3);
                if (this.l) {
                    bArr2 = s.b(bArr2);
                }
                tGEventReminder.setContent(new String(bArr2));
            }
            arrayList.add(tGEventReminder);
        }
        return arrayList;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0};
    }
}
