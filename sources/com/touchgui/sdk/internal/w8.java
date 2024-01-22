package com.touchgui.sdk.internal;

import com.realsil.sdk.dfu.DfuException;
/* loaded from: classes12.dex */
public final class w8 extends z8 {
    public byte[] e;
    public int f;
    public int g;
    public int h;

    public w8(byte b, byte b2) {
        super(b, b2);
        this.f = 0;
        this.g = 0;
        this.h = 0;
    }

    @Override // com.touchgui.sdk.internal.z8
    public final byte[] a(byte b, byte b2, int i) {
        byte[] bArr = this.e;
        if (bArr != null) {
            int i2 = i - 4;
            byte[] bArr2 = new byte[i];
            bArr2[0] = b;
            bArr2[1] = b2;
            int length = ((bArr.length + i2) - 1) / i2;
            this.f = length;
            bArr2[2] = (byte) length;
            int i3 = this.g;
            bArr2[3] = (byte) (i3 + 1);
            int i4 = i3 * i2;
            if (i4 + i2 > bArr.length) {
                i2 = bArr.length - i4;
            }
            System.arraycopy(bArr, i4, bArr2, 4, i2);
            this.g++;
            return bArr2;
        }
        return super.a(b, b2, i);
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int b(byte[] bArr) {
        return !a(bArr) ? 0 : 2;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final /* bridge */ /* synthetic */ Object b() {
        return null;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int onResponse(byte[] bArr) {
        int i;
        if (!a(bArr) || this.d || (i = this.h + 1) > this.g) {
            return 0;
        }
        this.h = i;
        if (this.f == i) {
            this.d = true;
            return DfuException.ERROR_CONNECTION_GATT_CONN_TIMEOUT;
        }
        return 512;
    }
}
