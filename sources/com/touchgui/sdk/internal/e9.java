package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class e9 extends y8 {
    public final /* synthetic */ int g;
    public final /* synthetic */ int h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e9(int i, int i2) {
        super((byte) 32, (byte) 2, 4);
        this.g = i;
        this.h = i2;
    }

    @Override // com.touchgui.sdk.internal.y8, com.touchgui.sdk.internal.z8
    public final byte[] a(byte b, byte b2, int i) {
        return new byte[]{b, b2, (byte) this.g, (byte) this.h};
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        return new f8(bArr[2], Integer.valueOf(s.b(bArr, 3, 4)));
    }
}
