package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class k6 extends y8 {
    public final /* synthetic */ byte g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k6(byte b) {
        super((byte) 5, (byte) 2);
        this.g = b;
    }

    @Override // com.touchgui.sdk.internal.y8, com.touchgui.sdk.internal.z8
    public final byte[] a(byte b, byte b2, int i) {
        return new byte[]{b, b2, this.g};
    }
}
