package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
/* loaded from: classes12.dex */
public abstract class t3 {
    public static o3 a(int i, long j, String str) {
        TGLogger.d("file name=" + str + ", file size=" + j + ", type=" + i);
        o3 o3Var = new o3();
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        ByteBuffer b = o3Var.b(bytes.length + 6);
        b.order(ByteOrder.LITTLE_ENDIAN);
        b.put((byte) i).putInt((int) j).put((byte) 0);
        b.put(bytes);
        return o3Var;
    }
}
