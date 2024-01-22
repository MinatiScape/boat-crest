package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
@CanIgnoreReturnValue
/* loaded from: classes10.dex */
public abstract class f extends d {

    /* renamed from: a  reason: collision with root package name */
    public final ByteBuffer f10654a;
    public final int b;
    public final int c;

    public f(int i) {
        this(i, i);
    }

    public abstract HashCode a();

    public final void b() {
        l.b(this.f10654a);
        while (this.f10654a.remaining() >= this.c) {
            d(this.f10654a);
        }
        this.f10654a.compact();
    }

    public final void c() {
        if (this.f10654a.remaining() < 8) {
            b();
        }
    }

    public abstract void d(ByteBuffer byteBuffer);

    public abstract void e(ByteBuffer byteBuffer);

    public final Hasher f(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() <= this.f10654a.remaining()) {
            this.f10654a.put(byteBuffer);
            c();
            return this;
        }
        int position = this.b - this.f10654a.position();
        for (int i = 0; i < position; i++) {
            this.f10654a.put(byteBuffer.get());
        }
        b();
        while (byteBuffer.remaining() >= this.c) {
            d(byteBuffer);
        }
        this.f10654a.put(byteBuffer);
        return this;
    }

    @Override // com.google.common.hash.Hasher
    public final HashCode hash() {
        b();
        l.b(this.f10654a);
        if (this.f10654a.remaining() > 0) {
            e(this.f10654a);
            ByteBuffer byteBuffer = this.f10654a;
            l.c(byteBuffer, byteBuffer.limit());
        }
        return a();
    }

    public f(int i, int i2) {
        Preconditions.checkArgument(i2 % i == 0);
        this.f10654a = ByteBuffer.allocate(i2 + 7).order(ByteOrder.LITTLE_ENDIAN);
        this.b = i2;
        this.c = i;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putByte(byte b) {
        this.f10654a.put(b);
        c();
        return this;
    }

    @Override // com.google.common.hash.d, com.google.common.hash.PrimitiveSink
    public final Hasher putChar(char c) {
        this.f10654a.putChar(c);
        c();
        return this;
    }

    @Override // com.google.common.hash.d, com.google.common.hash.PrimitiveSink
    public final Hasher putInt(int i) {
        this.f10654a.putInt(i);
        c();
        return this;
    }

    @Override // com.google.common.hash.d, com.google.common.hash.PrimitiveSink
    public final Hasher putLong(long j) {
        this.f10654a.putLong(j);
        c();
        return this;
    }

    @Override // com.google.common.hash.d, com.google.common.hash.PrimitiveSink
    public final Hasher putShort(short s) {
        this.f10654a.putShort(s);
        c();
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putBytes(byte[] bArr, int i, int i2) {
        return f(ByteBuffer.wrap(bArr, i, i2).order(ByteOrder.LITTLE_ENDIAN));
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putBytes(ByteBuffer byteBuffer) {
        ByteOrder order = byteBuffer.order();
        try {
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            return f(byteBuffer);
        } finally {
            byteBuffer.order(order);
        }
    }
}
