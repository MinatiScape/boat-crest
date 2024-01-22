package com.touchgui.sdk.internal;

import com.realsil.sdk.dfu.DfuException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public class y8 extends z8 {
    public ByteBuffer e;
    public Object f;

    public y8(byte b, byte b2) {
        super(b, b2);
    }

    @Override // com.touchgui.sdk.internal.z8
    public byte[] a(byte b, byte b2, int i) {
        ByteBuffer byteBuffer = this.e;
        if (byteBuffer != null) {
            byte[] array = byteBuffer.array();
            byte[] bArr = new byte[array.length + 2];
            bArr[0] = b;
            bArr[1] = b2;
            System.arraycopy(array, 0, bArr, 2, array.length);
            return bArr;
        }
        return super.a(b, b2, i);
    }

    @Override // com.touchgui.sdk.internal.d8
    public final Object b() {
        return this.f;
    }

    public Object c(byte[] bArr) {
        return null;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int onResponse(byte[] bArr) {
        if (!a(bArr) || this.d) {
            return 0;
        }
        this.f = c(bArr);
        this.d = true;
        return DfuException.ERROR_CONNECTION_GATT_CONN_TIMEOUT;
    }

    public y8(byte b, byte b2, int i) {
        super(b, b2, i);
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int b(byte[] bArr) {
        return !a(bArr) ? 0 : 2;
    }

    public y8(int i) {
        super(i);
    }

    public final ByteBuffer b(int i) {
        if (this.e == null) {
            ByteBuffer allocate = ByteBuffer.allocate(i);
            this.e = allocate;
            allocate.order(ByteOrder.LITTLE_ENDIAN);
        }
        return this.e;
    }
}
