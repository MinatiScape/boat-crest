package com.realsil.sdk.dfu.u;

import com.realsil.sdk.core.utility.DataConverter;
import java.util.Locale;
/* loaded from: classes12.dex */
public class q extends d {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f13662a;
    public byte b;

    /* loaded from: classes12.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f13663a;
        public byte b = 0;

        public b a(byte[] bArr) {
            this.f13663a = bArr;
            return this;
        }

        public b a(byte b) {
            this.b = b;
            return this;
        }

        public q a() {
            return new q(this.f13663a, this.b);
        }
    }

    public byte[] a() {
        byte[] bArr = new byte[16];
        byte[] bArr2 = this.f13662a;
        if (bArr2 != null && bArr2.length >= 12) {
            System.arraycopy(bArr2, 0, bArr, 0, 12);
        }
        bArr[12] = this.b;
        return bArr;
    }

    public short b() {
        return (short) 1538;
    }

    public String toString() {
        return String.format("StartDfuReq(0x%04X) {", Short.valueOf(b())) + String.format(Locale.US, "\n\tmode=0x%02X, imageHeader=%s", Byte.valueOf(this.b), DataConverter.bytes2Hex(this.f13662a)) + "\n}";
    }

    public q(byte[] bArr, byte b2) {
        this.f13662a = bArr;
        this.b = b2;
    }
}
