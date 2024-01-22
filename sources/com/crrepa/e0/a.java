package com.crrepa.e0;

import com.crrepa.i0.e;
/* loaded from: classes9.dex */
public class a {
    public static final int c = 65535;

    /* renamed from: a  reason: collision with root package name */
    public byte[] f7703a;
    public int b = 0;

    public a(byte[] bArr) {
        this.f7703a = bArr;
    }

    public int a() {
        return this.b;
    }

    public int b() {
        byte[] bArr = this.f7703a;
        if (bArr == null || bArr.length < 2) {
            return -1;
        }
        if (bArr.length == 4) {
            this.b = e.b(bArr[2], bArr[3]);
        }
        byte[] bArr2 = this.f7703a;
        return e.b(bArr2[0], bArr2[1]);
    }
}
