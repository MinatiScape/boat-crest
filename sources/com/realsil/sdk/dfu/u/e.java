package com.realsil.sdk.dfu.u;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;
/* loaded from: classes12.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public short f13657a = 1541;
    public byte b;
    public int c;
    public int d;

    public e(byte b, int i, int i2) {
        this.b = b;
        this.c = i;
        this.d = i2;
    }

    public static e a(byte[] bArr) {
        if (bArr != null && bArr.length >= 1) {
            int length = bArr.length;
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.order(ByteOrder.LITTLE_ENDIAN);
            return new e(bArr[0], length >= 3 ? (((short) (wrap.get(2) & 255)) << 8) | ((short) (wrap.get(1) & 255)) : 0, length >= 4 ? (((short) (wrap.get(4) & 255)) << 8) | ((short) (wrap.get(3) & 255)) : 0);
        }
        return new e((byte) 2, 0, 0);
    }

    public String toString() {
        return String.format("EnableBufferCheckRsp(0x%04X) {", Short.valueOf(this.f13657a)) + String.format(Locale.US, "\n\tstatus=0x%02X,maxBufferSize=0x%04X(%d), bufferCheckMtuSize=0x%04X(%d)", Byte.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.d)) + "\n}";
    }
}
