package com.bumptech.glide.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;
@Deprecated
/* loaded from: classes2.dex */
public class ExceptionCatchingInputStream extends InputStream {
    public static final Queue<ExceptionCatchingInputStream> j = Util.createQueue(0);
    public InputStream h;
    public IOException i;

    @NonNull
    public static ExceptionCatchingInputStream obtain(@NonNull InputStream inputStream) {
        ExceptionCatchingInputStream poll;
        Queue<ExceptionCatchingInputStream> queue = j;
        synchronized (queue) {
            poll = queue.poll();
        }
        if (poll == null) {
            poll = new ExceptionCatchingInputStream();
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
    public int read(byte[] bArr) {
        try {
            return this.h.read(bArr);
        } catch (IOException e) {
            this.i = e;
            return -1;
        }
    }

    public void release() {
        this.i = null;
        this.h = null;
        Queue<ExceptionCatchingInputStream> queue = j;
        synchronized (queue) {
            queue.offer(this);
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.h.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j2) {
        try {
            return this.h.skip(j2);
        } catch (IOException e) {
            this.i = e;
            return 0L;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        try {
            return this.h.read(bArr, i, i2);
        } catch (IOException e) {
            this.i = e;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            return this.h.read();
        } catch (IOException e) {
            this.i = e;
            return -1;
        }
    }
}
