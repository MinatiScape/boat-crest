package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
@CanIgnoreReturnValue
/* loaded from: classes10.dex */
public abstract class a extends d {

    /* renamed from: a  reason: collision with root package name */
    public final ByteBuffer f10651a = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

    public final Hasher a(int i) {
        try {
            e(this.f10651a.array(), 0, i);
            return this;
        } finally {
            l.a(this.f10651a);
        }
    }

    public abstract void b(byte b);

    public void c(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            e(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            l.c(byteBuffer, byteBuffer.limit());
            return;
        }
        for (int remaining = byteBuffer.remaining(); remaining > 0; remaining--) {
            b(byteBuffer.get());
        }
    }

    public void d(byte[] bArr) {
        e(bArr, 0, bArr.length);
    }

    public abstract void e(byte[] bArr, int i, int i2);

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putByte(byte b) {
        b(b);
        return this;
    }

    @Override // com.google.common.hash.d, com.google.common.hash.PrimitiveSink
    public Hasher putChar(char c) {
        this.f10651a.putChar(c);
        return a(2);
    }

    @Override // com.google.common.hash.d, com.google.common.hash.PrimitiveSink
    public Hasher putInt(int i) {
        this.f10651a.putInt(i);
        return a(4);
    }

    @Override // com.google.common.hash.d, com.google.common.hash.PrimitiveSink
    public Hasher putLong(long j) {
        this.f10651a.putLong(j);
        return a(8);
    }

    @Override // com.google.common.hash.d, com.google.common.hash.PrimitiveSink
    public Hasher putShort(short s) {
        this.f10651a.putShort(s);
        return a(2);
    }

    @Override // com.google.common.hash.d, com.google.common.hash.PrimitiveSink
    public Hasher putBytes(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        d(bArr);
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putBytes(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        e(bArr, i, i2);
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putBytes(ByteBuffer byteBuffer) {
        c(byteBuffer);
        return this;
    }
}
