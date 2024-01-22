package com.realsil.sdk.dfu.m;

import java.util.Locale;
/* loaded from: classes12.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    public int f13623a;
    public int b;
    public int c;
    public byte d;

    /* loaded from: classes12.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public int f13624a;
        public int b;
        public int c;
        public byte d;

        public b(int i, int i2) {
            this.f13624a = i;
            this.b = i2;
        }

        public b a(int i) {
            this.c = i;
            return this;
        }

        public b a(byte b) {
            this.d = b;
            return this;
        }

        public h a() {
            return new h(this.f13624a, this.b, this.c, this.d);
        }
    }

    public byte[] a() {
        if (this.f13623a == 20) {
            return b();
        }
        if (this.b >= 2) {
            int i = this.c;
            return new byte[]{c(), (byte) (i & 255), (byte) ((i >> 8) & 255), this.d};
        }
        int i2 = this.c;
        return new byte[]{c(), (byte) (i2 & 255), (byte) ((i2 >> 8) & 255)};
    }

    public final byte[] b() {
        int i = this.c;
        return new byte[]{c(), (byte) (i & 255), (byte) ((i >> 8) & 255), this.d};
    }

    public byte c() {
        return (byte) 3;
    }

    public String toString() {
        return String.format("ValidateFwImageCmd(0x%04X-0x%02X:0x%02X) {", Integer.valueOf(this.f13623a), Integer.valueOf(this.b), Byte.valueOf(c())) + String.format(Locale.US, "\n\timageId=0x%04X, flag=0x%02X", Integer.valueOf(this.c), Byte.valueOf(this.d)) + "\n}";
    }

    public h(int i, int i2, int i3, byte b2) {
        this.f13623a = i;
        this.b = i2;
        this.c = i3;
        this.d = b2;
    }
}
