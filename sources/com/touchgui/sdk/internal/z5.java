package com.touchgui.sdk.internal;

import android.text.TextUtils;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
/* loaded from: classes12.dex */
public final class z5 extends h5 {
    public final /* synthetic */ boolean l;
    public final /* synthetic */ String m;
    public final /* synthetic */ int n;
    public final /* synthetic */ int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z5(boolean z, String str, int i, int i2) {
        super((short) 10);
        this.l = z;
        this.m = str;
        this.n = i;
        this.o = i2;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        return Integer.valueOf(bArr[11] & 255);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        ByteBuffer allocate = ByteBuffer.allocate(73);
        allocate.put(this.l ? (byte) 1 : (byte) 0);
        int i = 0;
        allocate.putShort((short) 0);
        allocate.putShort((short) 0);
        byte[] bArr = new byte[64];
        if (!TextUtils.isEmpty(this.m)) {
            byte[] bytes = this.m.getBytes(StandardCharsets.UTF_8);
            int min = Math.min(bytes.length, 64);
            System.arraycopy(bytes, 0, bArr, 0, min);
            i = min;
        }
        allocate.putShort((short) i);
        allocate.put(bArr);
        allocate.put((byte) this.n);
        allocate.put((byte) this.o);
        return allocate.array();
    }
}
