package com.touchgui.sdk.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class y2 {

    /* renamed from: a  reason: collision with root package name */
    public int f13845a;
    public short b;
    public short c;
    public final byte[] d;
    public int e;

    public y2(byte[] bArr) {
        this.d = bArr;
        a(bArr);
    }

    public final void a(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        this.f13845a = wrap.getShort();
        this.b = wrap.getShort();
        this.c = wrap.getShort();
        wrap.getShort();
        wrap.getShort();
        wrap.getShort();
        this.e = wrap.getInt();
    }
}
