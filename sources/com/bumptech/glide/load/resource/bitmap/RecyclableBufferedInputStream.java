package com.bumptech.glide.load.resource.bitmap;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class RecyclableBufferedInputStream extends FilterInputStream {
    public volatile byte[] h;
    public int i;
    public int j;
    public int k;
    public int l;
    public final ArrayPool m;

    /* loaded from: classes2.dex */
    public static class a extends IOException {
        private static final long serialVersionUID = -4338378848813561757L;

        public a(String str) {
            super(str);
        }
    }

    public RecyclableBufferedInputStream(@NonNull InputStream inputStream, @NonNull ArrayPool arrayPool) {
        this(inputStream, arrayPool, 65536);
    }

    public static IOException b() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    public final int a(InputStream inputStream, byte[] bArr) throws IOException {
        int i = this.k;
        if (i != -1) {
            int i2 = this.l - i;
            int i3 = this.j;
            if (i2 < i3) {
                if (i == 0 && i3 > bArr.length && this.i == bArr.length) {
                    int length = bArr.length * 2;
                    if (length <= i3) {
                        i3 = length;
                    }
                    byte[] bArr2 = (byte[]) this.m.get(i3, byte[].class);
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    this.h = bArr2;
                    this.m.put(bArr);
                    bArr = bArr2;
                } else if (i > 0) {
                    System.arraycopy(bArr, i, bArr, 0, bArr.length - i);
                }
                int i4 = this.l - this.k;
                this.l = i4;
                this.k = 0;
                this.i = 0;
                int read = inputStream.read(bArr, i4, bArr.length - i4);
                int i5 = this.l;
                if (read > 0) {
                    i5 += read;
                }
                this.i = i5;
                return read;
            }
        }
        int read2 = inputStream.read(bArr);
        if (read2 > 0) {
            this.k = -1;
            this.l = 0;
            this.i = read2;
        }
        return read2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() throws IOException {
        InputStream inputStream;
        inputStream = ((FilterInputStream) this).in;
        if (this.h != null && inputStream != null) {
        } else {
            throw b();
        }
        return (this.i - this.l) + inputStream.available();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.h != null) {
            this.m.put(this.h);
            this.h = null;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        ((FilterInputStream) this).in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public synchronized void fixMarkLimit() {
        this.j = this.h.length;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        this.j = Math.max(this.j, i);
        this.k = this.l;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() throws IOException {
        byte[] bArr = this.h;
        InputStream inputStream = ((FilterInputStream) this).in;
        if (bArr != null && inputStream != null) {
            if (this.l < this.i || a(inputStream, bArr) != -1) {
                if (bArr != this.h && (bArr = this.h) == null) {
                    throw b();
                }
                int i = this.i;
                int i2 = this.l;
                if (i - i2 > 0) {
                    this.l = i2 + 1;
                    return bArr[i2] & 255;
                }
                return -1;
            }
            return -1;
        }
        throw b();
    }

    public synchronized void release() {
        if (this.h != null) {
            this.m.put(this.h);
            this.h = null;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this.h != null) {
            int i = this.k;
            if (-1 != i) {
                this.l = i;
            } else {
                throw new a("Mark has been invalidated, pos: " + this.l + " markLimit: " + this.j);
            }
        } else {
            throw new IOException("Stream is closed");
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j) throws IOException {
        if (j < 1) {
            return 0L;
        }
        byte[] bArr = this.h;
        if (bArr != null) {
            InputStream inputStream = ((FilterInputStream) this).in;
            if (inputStream != null) {
                int i = this.i;
                int i2 = this.l;
                if (i - i2 >= j) {
                    this.l = (int) (i2 + j);
                    return j;
                }
                long j2 = i - i2;
                this.l = i;
                if (this.k != -1 && j <= this.j) {
                    if (a(inputStream, bArr) == -1) {
                        return j2;
                    }
                    int i3 = this.i;
                    int i4 = this.l;
                    if (i3 - i4 >= j - j2) {
                        this.l = (int) ((i4 + j) - j2);
                        return j;
                    }
                    long j3 = (j2 + i3) - i4;
                    this.l = i3;
                    return j3;
                }
                long skip = inputStream.skip(j - j2);
                if (skip > 0) {
                    this.k = -1;
                }
                return j2 + skip;
            }
            throw b();
        }
        throw b();
    }

    @VisibleForTesting
    public RecyclableBufferedInputStream(@NonNull InputStream inputStream, @NonNull ArrayPool arrayPool, int i) {
        super(inputStream);
        this.k = -1;
        this.m = arrayPool;
        this.h = (byte[]) arrayPool.get(i, byte[].class);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(@NonNull byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        byte[] bArr2 = this.h;
        if (bArr2 == null) {
            throw b();
        }
        if (i2 == 0) {
            return 0;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        if (inputStream != null) {
            int i5 = this.l;
            int i6 = this.i;
            if (i5 < i6) {
                int i7 = i6 - i5 >= i2 ? i2 : i6 - i5;
                System.arraycopy(bArr2, i5, bArr, i, i7);
                this.l += i7;
                if (i7 == i2 || inputStream.available() == 0) {
                    return i7;
                }
                i += i7;
                i3 = i2 - i7;
            } else {
                i3 = i2;
            }
            while (true) {
                if (this.k == -1 && i3 >= bArr2.length) {
                    i4 = inputStream.read(bArr, i, i3);
                    if (i4 == -1) {
                        return i3 != i2 ? i2 - i3 : -1;
                    }
                } else if (a(inputStream, bArr2) == -1) {
                    return i3 != i2 ? i2 - i3 : -1;
                } else {
                    if (bArr2 != this.h && (bArr2 = this.h) == null) {
                        throw b();
                    }
                    int i8 = this.i;
                    int i9 = this.l;
                    i4 = i8 - i9 >= i3 ? i3 : i8 - i9;
                    System.arraycopy(bArr2, i9, bArr, i, i4);
                    this.l += i4;
                }
                i3 -= i4;
                if (i3 == 0) {
                    return i2;
                }
                if (inputStream.available() == 0) {
                    return i2 - i3;
                }
                i += i4;
            }
        } else {
            throw b();
        }
    }
}
