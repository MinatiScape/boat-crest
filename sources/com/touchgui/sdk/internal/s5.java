package com.touchgui.sdk.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class s5 extends h5 {
    public s5() {
        super((short) 17);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(11);
        int i = wrap.get() & 255;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            byte[] bArr2 = new byte[64];
            wrap.get(bArr2, 0, 64);
            arrayList.add(new String(bArr2));
        }
        return arrayList;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[0];
    }
}
