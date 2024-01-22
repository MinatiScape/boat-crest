package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class f9 extends y8 {
    public final /* synthetic */ int g;
    public final /* synthetic */ boolean h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f9(int i, boolean z) {
        super((byte) 32, (byte) 4, 4);
        this.g = i;
        this.h = z;
    }

    @Override // com.touchgui.sdk.internal.y8, com.touchgui.sdk.internal.z8
    public final byte[] a(byte b, byte b2, int i) {
        return new byte[]{b, b2, (byte) this.g, (byte) (1 ^ (this.h ? 1 : 0))};
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        return new f8(bArr[2], null);
    }
}
