package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSosConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class j8 extends y8 {
    public j8() {
        super((byte) 2, (byte) 12);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGSosConfig tGSosConfig = new TGSosConfig();
        int i = wrap.get() & 255;
        int i2 = wrap.get() & 255;
        if (i > 0) {
            byte[] bArr2 = new byte[i];
            wrap.get(bArr2, 0, i);
            int i3 = 0;
            for (int i4 = 0; i4 < i && bArr2[i4] != 0; i4++) {
                i3++;
            }
            tGSosConfig.setName(new String(bArr2, 0, i3));
        }
        if (i2 > 0) {
            byte[] bArr3 = new byte[i2];
            wrap.get(bArr3, 0, i2);
            int i5 = 0;
            for (int i6 = 0; i6 < i2 && bArr3[i6] != 0; i6++) {
                i5++;
            }
            tGSosConfig.setPhoneNum(new String(bArr3, 0, i5));
        }
        return tGSosConfig;
    }
}
