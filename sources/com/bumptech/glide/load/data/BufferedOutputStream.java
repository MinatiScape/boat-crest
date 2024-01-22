package com.bumptech.glide.load.data;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes2.dex */
public final class BufferedOutputStream extends OutputStream {
    @NonNull
    public final OutputStream h;
    public byte[] i;
    public ArrayPool j;
    public int k;

    public BufferedOutputStream(@NonNull OutputStream outputStream, @NonNull ArrayPool arrayPool) {
        this(outputStream, arrayPool, 65536);
    }

    public final void a() throws IOException {
        int i = this.k;
        if (i > 0) {
            this.h.write(this.i, 0, i);
            this.k = 0;
        }
    }

    public final void b() throws IOException {
        if (this.k == this.i.length) {
            a();
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            flush();
            this.h.close();
            release();
        } catch (Throwable th) {
            this.h.close();
            throw th;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        a();
        this.h.flush();
    }

    public final void release() {
        byte[] bArr = this.i;
        if (bArr != null) {
            this.j.put(bArr);
            this.i = null;
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.i;
        int i2 = this.k;
        this.k = i2 + 1;
        bArr[i2] = (byte) i;
        b();
    }

    @VisibleForTesting
    public BufferedOutputStream(@NonNull OutputStream outputStream, ArrayPool arrayPool, int i) {
        this.h = outputStream;
        this.j = arrayPool;
        this.i = (byte[]) arrayPool.get(i, byte[].class);
    }

    @Override // java.io.OutputStream
    public void write(@NonNull byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(@NonNull byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        do {
            int i4 = i2 - i3;
            int i5 = i + i3;
            int i6 = this.k;
            if (i6 == 0 && i4 >= this.i.length) {
                this.h.write(bArr, i5, i4);
                return;
            }
            int min = Math.min(i4, this.i.length - i6);
            System.arraycopy(bArr, i5, this.i, this.k, min);
            this.k += min;
            i3 += min;
            b();
        } while (i3 < i2);
    }
}
