package com.realsil.sdk.dfu.m;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;
/* loaded from: classes12.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public int f13615a;
    public int b;
    public int c;
    public int d;
    public boolean e;
    public int f;

    public d(int i, int i2, int i3, int i4, boolean z, int i5) {
        this.f13615a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = z;
        this.f = i5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static d a(int i, int i2, byte[] bArr) {
        byte b;
        byte b2;
        byte b3;
        short s;
        short s2;
        short s3;
        boolean z;
        boolean z2;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = bArr.length;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        if (i == 16) {
            byte b4 = wrap.get();
            byte b5 = wrap.get();
            byte b6 = wrap.get();
            short s4 = wrap.getShort();
            if (i2 >= 4 || length < 7) {
                b2 = b5;
                z2 = false;
                s3 = 0;
            } else {
                s3 = wrap.getShort();
                b2 = b5;
                z2 = true;
            }
            b3 = b6;
            s = s4;
            b = b4;
            z = z2;
        } else {
            if (i == 20) {
                byte b7 = wrap.get();
                byte b8 = wrap.get();
                byte b9 = wrap.get();
                b = b7;
                b2 = b8;
                s = wrap.getShort();
                b3 = b9;
                s2 = 0;
            } else {
                b = 0;
                b2 = 0;
                b3 = 0;
                s = 0;
                s2 = 0;
            }
            s3 = s2;
            z = s2;
        }
        return new d(b, b2, b3, s, z, s3);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("EnableBufferCheckEvent(0x%04X) {", Integer.valueOf(this.b)));
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\n\tmode=0x%02X, maxBufferCheckSize=0x%08X(%d), bufferCheckMutSizeSupported=%b)", Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.d), Boolean.valueOf(this.e)));
        if (this.e) {
            sb.append(String.format(locale, "\n\tbufferCheckMutSize=0x%08X(%d)", Integer.valueOf(this.f), Integer.valueOf(this.f)));
        }
        sb.append("\n}");
        return sb.toString();
    }

    public boolean a() {
        return this.c == 1;
    }
}
