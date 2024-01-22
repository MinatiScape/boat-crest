package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGIotFunction;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public abstract class f5 {
    public static void a(TGIotFunction tGIotFunction, ByteBuffer byteBuffer) {
        int i = byteBuffer.get() & 255;
        byte[] bArr = new byte[255];
        byteBuffer.get(bArr, 0, 255);
        tGIotFunction.setName(new String(bArr, 0, i));
        int min = Math.min(byteBuffer.get() & 255, (255 - i) - 1);
        if (min > 0) {
            tGIotFunction.setNameEnum(new String(bArr, i + 1, min));
        }
    }
}
