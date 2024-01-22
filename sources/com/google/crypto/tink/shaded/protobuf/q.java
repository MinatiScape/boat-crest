package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
/* loaded from: classes10.dex */
public class q extends InputStream {
    public Iterator<ByteBuffer> h;
    public ByteBuffer i;
    public int j = 0;
    public int k;
    public int l;
    public boolean m;
    public byte[] n;
    public int o;
    public long p;

    public q(Iterable<ByteBuffer> iterable) {
        this.h = iterable.iterator();
        for (ByteBuffer byteBuffer : iterable) {
            this.j++;
        }
        this.k = -1;
        if (a()) {
            return;
        }
        this.i = Internal.EMPTY_BYTE_BUFFER;
        this.k = 0;
        this.l = 0;
        this.p = 0L;
    }

    public final boolean a() {
        this.k++;
        if (this.h.hasNext()) {
            ByteBuffer next = this.h.next();
            this.i = next;
            this.l = next.position();
            if (this.i.hasArray()) {
                this.m = true;
                this.n = this.i.array();
                this.o = this.i.arrayOffset();
            } else {
                this.m = false;
                this.p = u0.i(this.i);
                this.n = null;
            }
            return true;
        }
        return false;
    }

    public final void b(int i) {
        int i2 = this.l + i;
        this.l = i2;
        if (i2 == this.i.limit()) {
            a();
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.k == this.j) {
            return -1;
        }
        if (this.m) {
            int i = this.n[this.l + this.o] & 255;
            b(1);
            return i;
        }
        int v = u0.v(this.l + this.p) & 255;
        b(1);
        return v;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.k == this.j) {
            return -1;
        }
        int limit = this.i.limit();
        int i3 = this.l;
        int i4 = limit - i3;
        if (i2 > i4) {
            i2 = i4;
        }
        if (this.m) {
            System.arraycopy(this.n, i3 + this.o, bArr, i, i2);
            b(i2);
        } else {
            int position = this.i.position();
            this.i.position(this.l);
            this.i.get(bArr, i, i2);
            this.i.position(position);
            b(i2);
        }
        return i2;
    }
}
