package com.realsil.sdk.dfu.u;

import java.util.Locale;
/* loaded from: classes12.dex */
public class c extends d {

    /* renamed from: a  reason: collision with root package name */
    public int f13655a;
    public int b;

    public static short b(byte[] bArr, int i) {
        short s = 0;
        for (int i2 = 0; i2 < i; i2 += 2) {
            s = (short) (s ^ ((short) ((bArr[i2 + 1] << 8) | (bArr[i2] & 255))));
        }
        return (short) (((s & 255) << 8) | ((65280 & s) >> 8));
    }

    public short b() {
        return (short) 1544;
    }

    public String toString() {
        return String.format("BufferCheckReq(0x%04X) {", Short.valueOf(b())) + String.format(Locale.US, "\n\tbufferSize=%d, crc16=0x%02X", Integer.valueOf(this.f13655a), Integer.valueOf(this.b)) + "\n}";
    }

    /* loaded from: classes12.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f13656a;
        public int b;

        public b a(byte[] bArr, int i) {
            this.f13656a = bArr;
            this.b = i;
            return this;
        }

        public c a() {
            return new c(this.b, c.b(this.f13656a, this.b));
        }
    }

    public c(int i, int i2) {
        this.f13655a = i;
        this.b = i2;
    }

    public byte[] a() {
        int i = this.f13655a;
        int i2 = this.b;
        return new byte[]{(byte) (i & 255), (byte) (i >> 8), (byte) (i2 & 255), (byte) ((i2 >> 8) & 255)};
    }
}
