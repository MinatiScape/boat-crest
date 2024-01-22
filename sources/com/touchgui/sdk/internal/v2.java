package com.touchgui.sdk.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class v2 {

    /* renamed from: a  reason: collision with root package name */
    public short f13833a;
    public short b;
    public short c;
    public final byte[] d;
    public byte[] e;

    public v2(byte[] bArr) {
        this.d = bArr;
        a(bArr);
    }

    public final void a(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(8);
        this.f13833a = wrap.getShort();
        this.b = wrap.getShort();
        wrap.position(20);
        this.c = wrap.getShort();
    }
}
