package com.touchgui.sdk.internal;

import com.realsil.sdk.dfu.DfuException;
/* loaded from: classes12.dex */
public abstract class x8 extends z8 {
    public x8(byte b, byte b2, int i) {
        super(b, b2, i);
    }

    @Override // com.touchgui.sdk.internal.z8, com.touchgui.sdk.internal.d8
    public boolean a(byte[] bArr) {
        return super.a(bArr) || c(bArr);
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int b(byte[] bArr) {
        return !a(bArr) ? 0 : 2;
    }

    public abstract boolean c(byte[] bArr);

    public void d() {
    }

    public abstract void d(byte[] bArr);

    @Override // com.touchgui.sdk.internal.d8
    public final int onResponse(byte[] bArr) {
        if (!a(bArr) || this.d) {
            return 0;
        }
        if (!c(bArr)) {
            d(bArr);
            return 1024;
        }
        this.d = true;
        d();
        return DfuException.ERROR_CONNECTION_GATT_CONN_TIMEOUT;
    }
}
