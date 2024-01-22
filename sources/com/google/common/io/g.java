package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class g extends InputStream {
    public final Reader h;
    public final CharsetEncoder i;
    public final byte[] j;
    public CharBuffer k;
    public ByteBuffer l;
    public boolean m;
    public boolean n;
    public boolean o;

    public g(Reader reader, Charset charset, int i) {
        this(reader, charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE), i);
    }

    public static int a(Buffer buffer) {
        return buffer.capacity() - buffer.limit();
    }

    public static CharBuffer c(CharBuffer charBuffer) {
        CharBuffer wrap = CharBuffer.wrap(Arrays.copyOf(charBuffer.array(), charBuffer.capacity() * 2));
        c.d(wrap, charBuffer.position());
        c.c(wrap, charBuffer.limit());
        return wrap;
    }

    public final int b(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, this.l.remaining());
        this.l.get(bArr, i, min);
        return min;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h.close();
    }

    public final void d() throws IOException {
        if (a(this.k) == 0) {
            if (this.k.position() > 0) {
                c.b(this.k.compact());
            } else {
                this.k = c(this.k);
            }
        }
        int limit = this.k.limit();
        int read = this.h.read(this.k.array(), limit, a(this.k));
        if (read == -1) {
            this.m = true;
        } else {
            c.c(this.k, limit + read);
        }
    }

    public final void e(boolean z) {
        c.b(this.l);
        if (z && this.l.remaining() == 0) {
            this.l = ByteBuffer.allocate(this.l.capacity() * 2);
        } else {
            this.n = true;
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.j) == 1) {
            return UnsignedBytes.toInt(this.j[0]);
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0029, code lost:
        if (r2 <= 0) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:?, code lost:
        return r2;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int read(byte[] r8, int r9, int r10) throws java.io.IOException {
        /*
            r7 = this;
            int r0 = r9 + r10
            int r1 = r8.length
            com.google.common.base.Preconditions.checkPositionIndexes(r9, r0, r1)
            r0 = 0
            if (r10 != 0) goto La
            return r0
        La:
            boolean r1 = r7.m
            r2 = r0
        Ld:
            boolean r3 = r7.n
            if (r3 == 0) goto L2e
            int r3 = r9 + r2
            int r4 = r10 - r2
            int r3 = r7.b(r8, r3, r4)
            int r2 = r2 + r3
            if (r2 == r10) goto L29
            boolean r3 = r7.o
            if (r3 == 0) goto L21
            goto L29
        L21:
            r7.n = r0
            java.nio.ByteBuffer r3 = r7.l
            com.google.common.io.c.a(r3)
            goto L2e
        L29:
            if (r2 <= 0) goto L2c
            goto L2d
        L2c:
            r2 = -1
        L2d:
            return r2
        L2e:
            boolean r3 = r7.o
            if (r3 == 0) goto L35
            java.nio.charset.CoderResult r3 = java.nio.charset.CoderResult.UNDERFLOW
            goto L4c
        L35:
            if (r1 == 0) goto L40
            java.nio.charset.CharsetEncoder r3 = r7.i
            java.nio.ByteBuffer r4 = r7.l
            java.nio.charset.CoderResult r3 = r3.flush(r4)
            goto L4c
        L40:
            java.nio.charset.CharsetEncoder r3 = r7.i
            java.nio.CharBuffer r4 = r7.k
            java.nio.ByteBuffer r5 = r7.l
            boolean r6 = r7.m
            java.nio.charset.CoderResult r3 = r3.encode(r4, r5, r6)
        L4c:
            boolean r4 = r3.isOverflow()
            r5 = 1
            if (r4 == 0) goto L57
            r7.e(r5)
            goto Ld
        L57:
            boolean r4 = r3.isUnderflow()
            if (r4 == 0) goto L6f
            if (r1 == 0) goto L65
            r7.o = r5
            r7.e(r0)
            goto Ld
        L65:
            boolean r3 = r7.m
            if (r3 == 0) goto L6b
            r1 = r5
            goto L2e
        L6b:
            r7.d()
            goto L2e
        L6f:
            boolean r4 = r3.isError()
            if (r4 == 0) goto L2e
            r3.throwException()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.g.read(byte[], int, int):int");
    }

    public g(Reader reader, CharsetEncoder charsetEncoder, int i) {
        this.j = new byte[1];
        this.h = (Reader) Preconditions.checkNotNull(reader);
        this.i = (CharsetEncoder) Preconditions.checkNotNull(charsetEncoder);
        Preconditions.checkArgument(i > 0, "bufferSize must be positive: %s", i);
        charsetEncoder.reset();
        CharBuffer allocate = CharBuffer.allocate(i);
        this.k = allocate;
        c.b(allocate);
        this.l = ByteBuffer.allocate(i);
    }
}
