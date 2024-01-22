package com.touchgui.sdk.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public abstract class h5 implements d8 {
    public static final AtomicInteger k = new AtomicInteger(1);

    /* renamed from: a  reason: collision with root package name */
    public final byte f13770a;
    public int b;
    public final short c;
    public short d;
    public int e;
    public ByteBuffer f;
    public ByteBuffer g;
    public boolean h;
    public boolean i;
    public Object j;

    public h5(short s) {
        this(s, 0);
    }

    @Override // com.touchgui.sdk.internal.d8
    public final byte[] a(int i) {
        boolean z;
        int i2;
        if (this.d == 0) {
            this.d = (short) ((k.incrementAndGet() % 65534) + 1);
            byte[] e = e();
            int length = e != null ? e.length : 0;
            ByteBuffer allocate = ByteBuffer.allocate(length + 13);
            this.f = allocate;
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            this.f.putInt(-1378177574);
            this.f.put(this.f13770a);
            this.f.putShort((short) (length + 11));
            this.f.putShort(this.c);
            this.f.putShort(this.d);
            if (length > 0) {
                this.f.put(e);
            }
            ByteBuffer byteBuffer = this.f;
            byteBuffer.putShort(a.a(this.f.position(), byteBuffer.array()));
            z = true;
        } else {
            z = false;
        }
        ByteBuffer allocate2 = ByteBuffer.allocate(i);
        allocate2.order(ByteOrder.LITTLE_ENDIAN);
        allocate2.put((byte) 51);
        if (z) {
            if (this.f.array().length + 1 <= i) {
                allocate2.put(this.f.array());
                this.h = true;
            } else {
                i2 = i - 1;
                allocate2.put(this.f.array(), this.e, i2);
                this.e += i2;
            }
        } else if ((this.f.array().length - this.e) + 1 <= i) {
            int length2 = this.f.array().length - this.e;
            allocate2.put(this.f.array(), this.e, length2);
            this.e += length2;
            this.h = true;
        } else {
            i2 = i - 1;
            allocate2.put(this.f.array(), this.e, i2);
            this.e += i2;
        }
        byte[] bArr = new byte[allocate2.position()];
        System.arraycopy(allocate2.array(), 0, bArr, 0, allocate2.position());
        return bArr;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final Object b() {
        return this.j;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int c() {
        return 1;
    }

    public abstract Object c(byte[] bArr);

    public boolean d() {
        return false;
    }

    public byte[] e() {
        return new byte[0];
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00eb  */
    @Override // com.touchgui.sdk.internal.d8
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int onResponse(byte[] r9) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.touchgui.sdk.internal.h5.onResponse(byte[]):int");
    }

    public h5(short s, int i) {
        this.d = (short) 0;
        this.e = 0;
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.c = s;
        this.f13770a = (byte) 1;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final int b(byte[] bArr) {
        return this.h ? 2 : 4;
    }

    @Override // com.touchgui.sdk.internal.d8
    public boolean a(byte[] bArr) {
        return bArr[0] == 51;
    }

    @Override // com.touchgui.sdk.internal.d8
    public final String a() {
        short s = this.c;
        return s.a(new byte[]{51, (byte) ((65280 & s) >> 8), (byte) (s & 255)}, 0, 3);
    }
}
