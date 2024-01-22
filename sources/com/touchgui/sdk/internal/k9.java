package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGStock;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
/* loaded from: classes12.dex */
public final class k9 extends h5 {
    public final /* synthetic */ List l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k9(List list) {
        super((short) 35);
        this.l = list;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        return Integer.valueOf(bArr[11] & 255);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        int i = 1;
        int i2 = 1;
        for (TGStock tGStock : this.l) {
            i2 = tGStock.getNameLen() + 1 + tGStock.getCodeLen() + 1 + i2 + 13;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i2);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.put((byte) this.l.size());
        for (TGStock tGStock2 : this.l) {
            allocate.put((byte) (i & 255));
            allocate.putInt((int) (tGStock2.getCurrent() * 1000.0f));
            allocate.putInt((int) (tGStock2.getFluctuates() * 1000.0f));
            allocate.putInt((int) (tGStock2.getPercent() * 10000.0f));
            int codeLen = tGStock2.getCodeLen();
            allocate.put((byte) (codeLen & 255));
            if (codeLen > 0) {
                allocate.put(tGStock2.getCodeBytes());
            }
            int nameLen = tGStock2.getNameLen();
            allocate.put((byte) (nameLen & 255));
            if (nameLen > 0) {
                allocate.put(tGStock2.getNameBytes());
            }
            i++;
        }
        return allocate.array();
    }
}
