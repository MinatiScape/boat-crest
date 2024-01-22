package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGEventReminder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
/* loaded from: classes12.dex */
public final class l3 extends h5 {
    public final /* synthetic */ List l;
    public final /* synthetic */ boolean m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l3(List list, boolean z) {
        super((short) 23);
        this.l = list;
        this.m = z;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        return Integer.valueOf(bArr[11] & 255);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        int i = 1;
        for (TGEventReminder tGEventReminder : this.l) {
            i = i + 11 + tGEventReminder.getContentLen();
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.put((byte) this.l.size());
        for (TGEventReminder tGEventReminder2 : this.l) {
            allocate.put((byte) (tGEventReminder2.getId() & 255));
            allocate.put((byte) (tGEventReminder2.getType() & 255));
            allocate.put((byte) ((tGEventReminder2.getYear() - 2000) & 255));
            allocate.put((byte) ((tGEventReminder2.getMonth() & 255) + 1));
            allocate.put((byte) (tGEventReminder2.getDay() & 255));
            allocate.put((byte) (tGEventReminder2.getHour() & 255));
            allocate.put((byte) (tGEventReminder2.getMinute() & 255));
            allocate.putShort((short) (tGEventReminder2.getRepeat() & 65535));
            allocate.putShort((short) (tGEventReminder2.getContentLen() & 65535));
            if (tGEventReminder2.getContentLen() > 0) {
                byte[] contentBytes = tGEventReminder2.getContentBytes();
                if (this.m) {
                    contentBytes = s.b(contentBytes);
                }
                allocate.put(contentBytes);
            }
        }
        return allocate.array();
    }
}
