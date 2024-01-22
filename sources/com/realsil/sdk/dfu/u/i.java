package com.realsil.sdk.dfu.u;

import java.util.Locale;
/* loaded from: classes12.dex */
public class i extends d {

    /* renamed from: a  reason: collision with root package name */
    public int f13658a;

    /* loaded from: classes12.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public int f13659a;

        public b(int i) {
            this.f13659a = i;
        }

        public i a() {
            return new i(this.f13659a);
        }
    }

    public byte[] a() {
        int i = this.f13658a;
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255)};
    }

    public short b() {
        return (short) 1545;
    }

    public String toString() {
        return String.format("GeTargetImageInfoReq(0x%04X) {", Short.valueOf(b())) + String.format(Locale.US, "\n\timageId=0x%02X", Integer.valueOf(this.f13658a)) + "\n}";
    }

    public i(int i) {
        this.f13658a = i;
    }
}
