package com.realsil.sdk.dfu.t;

import com.realsil.sdk.dfu.u.d;
import java.util.Locale;
/* loaded from: classes12.dex */
public class b extends d {

    /* renamed from: a  reason: collision with root package name */
    public int f13651a;

    /* renamed from: com.realsil.sdk.dfu.t.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static class C0727b {

        /* renamed from: a  reason: collision with root package name */
        public int f13652a;

        public C0727b(int i) {
            this.f13652a = i;
        }

        public b a() {
            return new b(this.f13652a);
        }
    }

    public byte[] a() {
        int i = this.f13651a;
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255)};
    }

    public short b() {
        return (short) 1545;
    }

    public String toString() {
        return String.format("GeTargetImageInfoReq(0x%04X) {", Short.valueOf(b())) + String.format(Locale.US, "\n\timageId=0x%02X", Integer.valueOf(this.f13651a)) + "\n}";
    }

    public b(int i) {
        this.f13651a = i;
    }
}
