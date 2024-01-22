package com.touchgui.sdk.internal;

import com.realsil.sdk.dfu.DfuException;
/* loaded from: classes12.dex */
public abstract class e8 extends z8 {
    public Object e;

    public e8(byte b) {
        super((byte) 7, b);
    }

    @Override // com.touchgui.sdk.internal.z8, com.touchgui.sdk.internal.d8
    public final byte[] a(int i) {
        throw new IllegalStateException("do not call the method");
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int b(byte[] bArr) {
        return 0;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final Object b() {
        return this.e;
    }

    public abstract Object c(byte[] bArr);

    @Override // com.touchgui.sdk.internal.d8
    public final int onResponse(byte[] bArr) {
        if (a(bArr)) {
            this.e = c(bArr);
            return DfuException.ERROR_CONNECTION_GATT_CONN_TIMEOUT;
        }
        return 0;
    }

    @Override // com.touchgui.sdk.internal.z8, com.touchgui.sdk.internal.d8
    public final boolean a(byte[] bArr) {
        return bArr[0] == this.f13852a && bArr[1] == this.b;
    }
}
