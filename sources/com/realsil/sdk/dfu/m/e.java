package com.realsil.sdk.dfu.m;

import java.util.Locale;
/* loaded from: classes12.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public int f13616a;
    public int b;
    public int c;

    /* loaded from: classes12.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public int f13617a;
        public int b;
        public int c;

        public b(int i) {
            this.f13617a = i;
        }

        public b a(int i) {
            this.b = i;
            return this;
        }

        public b b(int i) {
            this.c = i;
            return this;
        }

        public e a() {
            return new e(this.f13617a, this.b, this.c);
        }
    }

    public byte[] a() {
        int i = this.b;
        int i2 = this.c;
        return new byte[]{b(), (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)};
    }

    public byte b() {
        return (byte) 2;
    }

    public String toString() {
        return String.format("ValidateFwImageCmd(0x%02X) {", Byte.valueOf(b())) + String.format(Locale.US, "\n\timageId=0x%04X, offset=0x%08X(%d)", Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.c)) + "\n}";
    }

    public e(int i, int i2, int i3) {
        this.f13616a = i;
        this.b = i2;
        this.c = i3;
    }
}
