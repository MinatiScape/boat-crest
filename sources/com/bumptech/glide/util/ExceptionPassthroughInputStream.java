package com.bumptech.glide.util;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;
/* loaded from: classes2.dex */
public final class ExceptionPassthroughInputStream extends InputStream {
    @GuardedBy("POOL")
    public static final Queue<ExceptionPassthroughInputStream> j = Util.createQueue(0);
    public InputStream h;
    public IOException i;

    @NonNull
    public static ExceptionPassthroughInputStream obtain(@NonNull InputStream inputStream) {
        ExceptionPassthroughInputStream poll;
        Queue<ExceptionPassthroughInputStream> queue = j;
        synchronized (queue) {
            poll = queue.poll();
        }
        if (poll == null) {
            poll = new ExceptionPassthroughInputStream();
        }
        poll.a(inputStream);
        return poll;
    }

    public void a(@NonNull InputStream inputStream) {
        this.h = inputStream;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.h.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h.close();
    }

    @Nullable
    public IOException getException() {
        return this.i;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.h.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.h.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        try {
            return this.h.read();
        } catch (IOException e) {
            this.i = e;
            throw e;
        }
    }

    public void release() {
        this.i = null;
        this.h = null;
        Queue<ExceptionPassthroughInputStream> queue = j;
        synchronized (queue) {
            queue.offer(this);
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.h.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j2) throws IOException {
        try {
            return this.h.skip(j2);
        } catch (IOException e) {
            this.i = e;
            throw e;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        try {
            return this.h.read(bArr);
        } catch (IOException e) {
            this.i = e;
            throw e;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            return this.h.read(bArr, i, i2);
        } catch (IOException e) {
            this.i = e;
            throw e;
        }
    }
}
