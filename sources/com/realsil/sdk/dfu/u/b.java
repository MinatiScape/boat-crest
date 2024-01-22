package com.realsil.sdk.dfu.u;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;
/* loaded from: classes12.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public short f13654a = 1544;
    public byte b;
    public int c;

    public b(byte b, int i) {
        this.b = b;
        this.c = i;
    }

    public static b a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = bArr.length;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        return new b(bArr[0], length >= 5 ? wrap.getInt(1) : 0);
    }

    public String toString() {
        return String.format("BufferCheckEvent(0x%04X) {", Short.valueOf(this.f13654a)) + String.format(Locale.US, "\n\tstatus=0x%02X, updateOffset=0x%08X(%d)", Byte.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.c)) + "\n}";
    }
}
