package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class b extends Reader {
    public CharSequence h;
    public int i;
    public int j;

    public b(CharSequence charSequence) {
        this.h = (CharSequence) Preconditions.checkNotNull(charSequence);
    }

    public final void a() throws IOException {
        if (this.h == null) {
            throw new IOException("reader closed");
        }
    }

    public final boolean b() {
        return c() > 0;
    }

    public final int c() {
        return this.h.length() - this.i;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.h = null;
    }

    @Override // java.io.Reader
    public synchronized void mark(int i) throws IOException {
        Preconditions.checkArgument(i >= 0, "readAheadLimit (%s) may not be negative", i);
        a();
        this.j = this.i;
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.Reader, java.lang.Readable
    public synchronized int read(CharBuffer charBuffer) throws IOException {
        Preconditions.checkNotNull(charBuffer);
        a();
        if (b()) {
            int min = Math.min(charBuffer.remaining(), c());
            for (int i = 0; i < min; i++) {
                CharSequence charSequence = this.h;
                int i2 = this.i;
                this.i = i2 + 1;
                charBuffer.put(charSequence.charAt(i2));
            }
            return min;
        }
        return -1;
    }

    @Override // java.io.Reader
    public synchronized boolean ready() throws IOException {
        a();
        return true;
    }

    @Override // java.io.Reader
    public synchronized void reset() throws IOException {
        a();
        this.i = this.j;
    }

    @Override // java.io.Reader
    public synchronized long skip(long j) throws IOException {
        int min;
        Preconditions.checkArgument(j >= 0, "n (%s) may not be negative", j);
        a();
        min = (int) Math.min(c(), j);
        this.i += min;
        return min;
    }

    @Override // java.io.Reader
    public synchronized int read() throws IOException {
        char c;
        a();
        if (b()) {
            CharSequence charSequence = this.h;
            int i = this.i;
            this.i = i + 1;
            c = charSequence.charAt(i);
        } else {
            c = 65535;
        }
        return c;
    }

    @Override // java.io.Reader
    public synchronized int read(char[] cArr, int i, int i2) throws IOException {
        Preconditions.checkPositionIndexes(i, i + i2, cArr.length);
        a();
        if (b()) {
            int min = Math.min(i2, c());
            for (int i3 = 0; i3 < min; i3++) {
                CharSequence charSequence = this.h;
                int i4 = this.i;
                this.i = i4 + 1;
                cArr[i + i3] = charSequence.charAt(i4);
            }
            return min;
        }
        return -1;
    }
}
