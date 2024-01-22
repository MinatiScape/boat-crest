package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public class f extends Reader {
    public final Iterator<? extends CharSource> h;
    @NullableDecl
    public Reader i;

    public f(Iterator<? extends CharSource> it) throws IOException {
        this.h = it;
        a();
    }

    public final void a() throws IOException {
        close();
        if (this.h.hasNext()) {
            this.i = this.h.next().openStream();
        }
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Reader reader = this.i;
        if (reader != null) {
            try {
                reader.close();
            } finally {
                this.i = null;
            }
        }
    }

    @Override // java.io.Reader
    public int read(@NullableDecl char[] cArr, int i, int i2) throws IOException {
        Reader reader = this.i;
        if (reader == null) {
            return -1;
        }
        int read = reader.read(cArr, i, i2);
        if (read == -1) {
            a();
            return read(cArr, i, i2);
        }
        return read;
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        Reader reader = this.i;
        return reader != null && reader.ready();
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        Preconditions.checkArgument(i >= 0, "n is negative");
        if (i > 0) {
            while (true) {
                Reader reader = this.i;
                if (reader == null) {
                    break;
                }
                long skip = reader.skip(j);
                if (skip > 0) {
                    return skip;
                }
                a();
            }
        }
        return 0L;
    }
}
