package com.realsil.sdk.dfu.m;

import com.realsil.sdk.core.utility.DataConverter;
import java.util.Locale;
/* loaded from: classes12.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public int f13621a;
    public byte[] b;

    /* loaded from: classes12.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public int f13622a;
        public byte[] b;

        public b(int i) {
            this.f13622a = i;
        }

        public b a(byte[] bArr) {
            this.b = bArr;
            return this;
        }

        public g a() {
            return new g(this.f13622a, this.b);
        }
    }

    public byte[] a() {
        byte[] bArr = new byte[17];
        bArr[0] = b();
        byte[] bArr2 = this.b;
        if (bArr2 != null && bArr2.length >= 16) {
            System.arraycopy(bArr2, 0, bArr, 1, 16);
        }
        return bArr;
    }

    public byte b() {
        return (byte) 1;
    }

    public String toString() {
        return String.format("StartDfuCmd(0x%02X) {", Byte.valueOf(b())) + String.format(Locale.US, "\n\tparams=%s", DataConverter.bytes2Hex(this.b)) + "\n}";
    }

    public g(int i, byte[] bArr) {
        this.f13621a = i;
        this.b = bArr;
    }
}
