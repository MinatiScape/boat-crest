package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class g9 extends y8 {
    public final /* synthetic */ boolean g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g9(boolean z) {
        super((byte) 32, (byte) 5, 4);
        this.g = z;
    }

    @Override // com.touchgui.sdk.internal.y8, com.touchgui.sdk.internal.z8
    public final byte[] a(byte b, byte b2, int i) {
        return new byte[]{b, b2, (byte) (1 ^ (this.g ? 1 : 0))};
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        return new f8(bArr[2] & 255, null);
    }
}
