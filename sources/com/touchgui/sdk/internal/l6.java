package com.touchgui.sdk.internal;

import android.text.TextUtils;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
/* loaded from: classes12.dex */
public abstract class l6 {
    public static ByteBuffer a(String str, String str2, boolean z) {
        byte[] bytes;
        byte[] bytes2;
        if (TextUtils.isEmpty(str)) {
            bytes = new byte[0];
        } else {
            bytes = s.a(64, str).getBytes(StandardCharsets.UTF_8);
            if (z) {
                bytes = s.b(bytes);
            }
        }
        if (TextUtils.isEmpty(str2)) {
            bytes2 = new byte[0];
        } else {
            bytes2 = s.a(64, str2).getBytes(StandardCharsets.UTF_8);
            if (z) {
                bytes2 = s.b(bytes2);
            }
        }
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + bytes2.length + 2);
        allocate.put((byte) bytes.length);
        allocate.put((byte) bytes2.length);
        if (bytes.length > 0) {
            allocate.put(bytes);
        }
        if (bytes2.length > 0) {
            allocate.put(bytes2);
        }
        return allocate;
    }

    public static w8 a(String str, String str2, String str3, int i) {
        w8 w8Var = new w8((byte) 5, (byte) 3);
        byte[] bytes = TextUtils.isEmpty(str) ? new byte[0] : str.getBytes(StandardCharsets.UTF_8);
        byte[] bytes2 = TextUtils.isEmpty(str2) ? new byte[0] : str2.getBytes(StandardCharsets.UTF_8);
        byte[] bytes3 = TextUtils.isEmpty(str3) ? new byte[0] : str3.getBytes(StandardCharsets.UTF_8);
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + bytes2.length + bytes3.length + 4);
        allocate.put((byte) i);
        allocate.put((byte) bytes3.length);
        allocate.put((byte) bytes2.length);
        allocate.put((byte) bytes.length);
        if (bytes.length > 0) {
            allocate.put(bytes);
        }
        if (bytes2.length > 0) {
            allocate.put(bytes2);
        }
        if (bytes3.length > 0) {
            allocate.put(bytes3);
        }
        w8Var.e = allocate.array();
        return w8Var;
    }
}
