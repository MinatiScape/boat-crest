package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class FileBackedOutputStream extends OutputStream {
    public final int h;
    public final boolean i;
    public final ByteSource j;
    @NullableDecl
    public final File k;
    @GuardedBy("this")
    public OutputStream l;
    @GuardedBy("this")
    public c m;
    @NullableDecl
    @GuardedBy("this")
    public File n;

    /* loaded from: classes10.dex */
    public class a extends ByteSource {
        public a() {
        }

        public void finalize() {
            try {
                FileBackedOutputStream.this.reset();
            } catch (Throwable th) {
                th.printStackTrace(System.err);
            }
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return FileBackedOutputStream.this.b();
        }
    }

    /* loaded from: classes10.dex */
    public class b extends ByteSource {
        public b() {
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return FileBackedOutputStream.this.b();
        }
    }

    /* loaded from: classes10.dex */
    public static class c extends ByteArrayOutputStream {
        public c() {
        }

        public byte[] a() {
            return ((ByteArrayOutputStream) this).buf;
        }

        public int getCount() {
            return ((ByteArrayOutputStream) this).count;
        }

        public /* synthetic */ c(a aVar) {
            this();
        }
    }

    public FileBackedOutputStream(int i) {
        this(i, false);
    }

    public ByteSource asByteSource() {
        return this.j;
    }

    public final synchronized InputStream b() throws IOException {
        if (this.n != null) {
            return new FileInputStream(this.n);
        }
        return new ByteArrayInputStream(this.m.a(), 0, this.m.getCount());
    }

    @GuardedBy("this")
    public final void c(int i) throws IOException {
        if (this.n != null || this.m.getCount() + i <= this.h) {
            return;
        }
        File createTempFile = File.createTempFile("FileBackedOutputStream", null, this.k);
        if (this.i) {
            createTempFile.deleteOnExit();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
        fileOutputStream.write(this.m.a(), 0, this.m.getCount());
        fileOutputStream.flush();
        this.l = fileOutputStream;
        this.n = createTempFile;
        this.m = null;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.l.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public synchronized void flush() throws IOException {
        this.l.flush();
    }

    public synchronized void reset() throws IOException {
        close();
        c cVar = this.m;
        if (cVar == null) {
            this.m = new c(null);
        } else {
            cVar.reset();
        }
        this.l = this.m;
        File file = this.n;
        if (file != null) {
            this.n = null;
            if (!file.delete()) {
                String valueOf = String.valueOf(file);
                StringBuilder sb = new StringBuilder(valueOf.length() + 18);
                sb.append("Could not delete: ");
                sb.append(valueOf);
                throw new IOException(sb.toString());
            }
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(int i) throws IOException {
        c(1);
        this.l.write(i);
    }

    public FileBackedOutputStream(int i, boolean z) {
        this(i, z, null);
    }

    public FileBackedOutputStream(int i, boolean z, @NullableDecl File file) {
        this.h = i;
        this.i = z;
        this.k = file;
        c cVar = new c(null);
        this.m = cVar;
        this.l = cVar;
        if (z) {
            this.j = new a();
        } else {
            this.j = new b();
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) throws IOException {
        c(i2);
        this.l.write(bArr, i, i2);
    }
}
