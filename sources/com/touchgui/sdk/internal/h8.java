package com.touchgui.sdk.internal;

import com.realsil.sdk.dfu.DfuException;
import com.touchgui.sdk.TGLogger;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class h8 implements d8 {

    /* renamed from: a  reason: collision with root package name */
    public final byte f13771a;
    public final byte b;
    public final int c;
    public ByteBuffer d;
    public boolean e;

    public h8(byte b, byte b2) {
        this(b, b2, 2);
    }

    @Override // com.touchgui.sdk.internal.d8
    public final byte[] a(int i) {
        ByteBuffer byteBuffer = this.d;
        if (byteBuffer != null) {
            byte[] array = byteBuffer.array();
            byte[] bArr = new byte[array.length + 2];
            bArr[0] = this.f13771a;
            bArr[1] = this.b;
            System.arraycopy(array, 0, bArr, 2, array.length);
            return bArr;
        }
        return new byte[]{this.f13771a, this.b};
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int b(byte[] bArr) {
        if (!a(bArr) || this.e) {
            return 0;
        }
        this.e = true;
        return DfuException.ERROR_CONNECTION_GATT_CONN_TIMEOUT;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final /* bridge */ /* synthetic */ Object b() {
        return null;
    }

    @Override // com.touchgui.sdk.internal.d8
    public int c() {
        return this.c;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int onResponse(byte[] bArr) {
        TGLogger.e("don't call this method, this protocol only send data.");
        return 0;
    }

    public h8(byte b, byte b2, int i) {
        this.e = false;
        this.f13771a = b;
        this.b = b2;
        this.c = i;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final boolean a(byte[] bArr) {
        return bArr[0] == this.f13771a && bArr.length > 1 && bArr[1] == this.b;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final String a() {
        return s.a(new byte[]{this.f13771a, this.b}, 0, 2);
    }
}
