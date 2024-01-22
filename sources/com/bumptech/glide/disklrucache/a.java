package com.bumptech.glide.disklrucache;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
/* loaded from: classes.dex */
public class a implements Closeable {
    public final InputStream h;
    public final Charset i;
    public byte[] j;
    public int k;
    public int l;

    /* renamed from: com.bumptech.glide.disklrucache.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0210a extends ByteArrayOutputStream {
        public C0210a(int i) {
            super(i);
        }

        @Override // java.io.ByteArrayOutputStream
        public String toString() {
            int i = ((ByteArrayOutputStream) this).count;
            if (i > 0 && ((ByteArrayOutputStream) this).buf[i - 1] == 13) {
                i--;
            }
            try {
                return new String(((ByteArrayOutputStream) this).buf, 0, i, a.this.i.name());
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
    }

    public a(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    public final void b() throws IOException {
        InputStream inputStream = this.h;
        byte[] bArr = this.j;
        int read = inputStream.read(bArr, 0, bArr.length);
        if (read != -1) {
            this.k = 0;
            this.l = read;
            return;
        }
        throw new EOFException();
    }

    public boolean c() {
        return this.l == -1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.h) {
            if (this.j != null) {
                this.j = null;
                this.h.close();
            }
        }
    }

    public String d() throws IOException {
        int i;
        byte[] bArr;
        int i2;
        synchronized (this.h) {
            if (this.j != null) {
                if (this.k >= this.l) {
                    b();
                }
                for (int i3 = this.k; i3 != this.l; i3++) {
                    byte[] bArr2 = this.j;
                    if (bArr2[i3] == 10) {
                        int i4 = this.k;
                        if (i3 != i4) {
                            i2 = i3 - 1;
                            if (bArr2[i2] == 13) {
                                String str = new String(bArr2, i4, i2 - i4, this.i.name());
                                this.k = i3 + 1;
                                return str;
                            }
                        }
                        i2 = i3;
                        String str2 = new String(bArr2, i4, i2 - i4, this.i.name());
                        this.k = i3 + 1;
                        return str2;
                    }
                }
                C0210a c0210a = new C0210a((this.l - this.k) + 80);
                loop1: while (true) {
                    byte[] bArr3 = this.j;
                    int i5 = this.k;
                    c0210a.write(bArr3, i5, this.l - i5);
                    this.l = -1;
                    b();
                    i = this.k;
                    while (i != this.l) {
                        bArr = this.j;
                        if (bArr[i] == 10) {
                            break loop1;
                        }
                        i++;
                    }
                }
                int i6 = this.k;
                if (i != i6) {
                    c0210a.write(bArr, i6, i - i6);
                }
                this.k = i + 1;
                return c0210a.toString();
            }
            throw new IOException("LineReader is closed");
        }
    }

    public a(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw null;
        }
        if (i >= 0) {
            if (charset.equals(b.f2317a)) {
                this.h = inputStream;
                this.i = charset;
                this.j = new byte[i];
                return;
            }
            throw new IllegalArgumentException("Unsupported encoding");
        }
        throw new IllegalArgumentException("capacity <= 0");
    }
}
