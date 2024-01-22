package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public abstract class z8 implements d8 {

    /* renamed from: a  reason: collision with root package name */
    public final byte f13852a;
    public final byte b;
    public final int c;
    public boolean d;

    public z8(byte b, byte b2) {
        this(b, b2, 2);
    }

    public byte[] a(byte b, byte b2, int i) {
        return new byte[]{b, b2};
    }

    @Override // com.touchgui.sdk.internal.d8
    public byte[] a(int i) {
        return a(this.f13852a, this.b, i);
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int c() {
        return this.c;
    }

    public z8(byte b, byte b2, int i) {
        this.d = false;
        this.f13852a = b;
        this.b = b2;
        this.c = i;
    }

    public z8(int i) {
        this((byte) ((65280 & i) >> 8), (byte) (i & 255));
    }

    @Override // com.touchgui.sdk.internal.d8
    public boolean a(byte[] bArr) {
        return bArr[0] == this.f13852a && bArr.length > 1 && bArr[1] == this.b;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final String a() {
        return s.a(new byte[]{this.f13852a, this.b}, 0, 2);
    }
}
