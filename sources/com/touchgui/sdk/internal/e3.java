package com.touchgui.sdk.internal;

import com.realsil.sdk.dfu.DfuException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class e3 {

    /* renamed from: a  reason: collision with root package name */
    public int f13757a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 368;
    public int f = 448;
    public int g = 220;
    public int h = DfuException.ERROR_SEND_COMMAND_REACH_MAX_RETRY_TIMES;
    public final int[] i = new int[16];
    public final int[] j = new int[16];
    public final byte[] k;

    public e3(byte[] bArr) {
        this.k = bArr;
        a();
    }

    public final void a() {
        ByteBuffer wrap = ByteBuffer.wrap(this.k);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        int i = wrap.getInt();
        this.f13757a = i & 7;
        this.b = (i & 112) >> 3;
        this.c = (268435328 & i) >> 7;
        this.d = (i & (-268435456)) >> 28;
        wrap.getInt();
        for (int i2 = 0; i2 < 16; i2++) {
            this.i[i2] = wrap.getInt();
        }
        for (int i3 = 0; i3 < 16; i3++) {
            this.j[i3] = wrap.getInt();
        }
        this.e = wrap.getShort() & UShort.MAX_VALUE;
        this.f = wrap.getShort() & UShort.MAX_VALUE;
        this.g = wrap.getShort() & UShort.MAX_VALUE;
        this.h = wrap.getShort() & UShort.MAX_VALUE;
    }

    public final String toString() {
        return "Info{type=" + this.f13757a + ", style=" + this.b + ", maxSize=" + this.c + ", picNum=" + this.d + ", width=" + this.e + ", height=" + this.f + ", thumbWidth=" + this.g + ", thumbHeight=" + this.h + ", picOffset=" + Arrays.toString(this.i) + ", picSize=" + Arrays.toString(this.j) + '}';
    }
}
