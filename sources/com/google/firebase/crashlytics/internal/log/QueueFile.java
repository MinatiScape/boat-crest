package com.google.firebase.crashlytics.internal.log;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes10.dex */
public class QueueFile implements Closeable {
    public static final Logger n = Logger.getLogger(QueueFile.class.getName());
    public final RandomAccessFile h;
    public int i;
    public int j;
    public b k;
    public b l;
    public final byte[] m = new byte[16];

    /* loaded from: classes10.dex */
    public interface ElementReader {
        void read(InputStream inputStream, int i) throws IOException;
    }

    /* loaded from: classes10.dex */
    public class a implements ElementReader {

        /* renamed from: a  reason: collision with root package name */
        public boolean f11165a = true;
        public final /* synthetic */ StringBuilder b;

        public a(QueueFile queueFile, StringBuilder sb) {
            this.b = sb;
        }

        @Override // com.google.firebase.crashlytics.internal.log.QueueFile.ElementReader
        public void read(InputStream inputStream, int i) throws IOException {
            if (this.f11165a) {
                this.f11165a = false;
            } else {
                this.b.append(", ");
            }
            this.b.append(i);
        }
    }

    /* loaded from: classes10.dex */
    public static class b {
        public static final b c = new b(0, 0);

        /* renamed from: a  reason: collision with root package name */
        public final int f11166a;
        public final int b;

        public b(int i, int i2) {
            this.f11166a = i;
            this.b = i2;
        }

        public String toString() {
            return b.class.getSimpleName() + "[position = " + this.f11166a + ", length = " + this.b + "]";
        }
    }

    /* loaded from: classes10.dex */
    public final class c extends InputStream {
        public int h;
        public int i;

        public /* synthetic */ c(QueueFile queueFile, b bVar, a aVar) {
            this(bVar);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            QueueFile.k(bArr, "buffer");
            if ((i | i2) >= 0 && i2 <= bArr.length - i) {
                int i3 = this.i;
                if (i3 > 0) {
                    if (i2 > i3) {
                        i2 = i3;
                    }
                    QueueFile.this.r(this.h, bArr, i, i2);
                    this.h = QueueFile.this.v(this.h + i2);
                    this.i -= i2;
                    return i2;
                }
                return -1;
            }
            throw new ArrayIndexOutOfBoundsException();
        }

        public c(b bVar) {
            this.h = QueueFile.this.v(bVar.f11166a + 4);
            this.i = bVar.b;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.i == 0) {
                return -1;
            }
            QueueFile.this.h.seek(this.h);
            int read = QueueFile.this.h.read();
            this.h = QueueFile.this.v(this.h + 1);
            this.i--;
            return read;
        }
    }

    public QueueFile(File file) throws IOException {
        if (!file.exists()) {
            i(file);
        }
        this.h = l(file);
        n();
    }

    public static void i(File file) throws IOException {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile l = l(file2);
        try {
            l.setLength(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
            l.seek(0L);
            byte[] bArr = new byte[16];
            y(bArr, 4096, 0, 0, 0);
            l.write(bArr);
            l.close();
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } catch (Throwable th) {
            l.close();
            throw th;
        }
    }

    public static <T> T k(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    public static RandomAccessFile l(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    public static int o(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16) + ((bArr[i + 2] & 255) << 8) + (bArr[i + 3] & 255);
    }

    public static void x(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    public static void y(byte[] bArr, int... iArr) {
        int i = 0;
        for (int i2 : iArr) {
            x(bArr, i, i2);
            i += 4;
        }
    }

    public synchronized void clear() throws IOException {
        w(4096, 0, 0, 0);
        this.j = 0;
        b bVar = b.c;
        this.k = bVar;
        this.l = bVar;
        if (this.i > 4096) {
            t(4096);
        }
        this.i = 4096;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.h.close();
    }

    public void e(byte[] bArr) throws IOException {
        f(bArr, 0, bArr.length);
    }

    public synchronized void f(byte[] bArr, int i, int i2) throws IOException {
        int v;
        k(bArr, "buffer");
        if ((i | i2) >= 0 && i2 <= bArr.length - i) {
            g(i2);
            boolean j = j();
            if (j) {
                v = 16;
            } else {
                b bVar = this.l;
                v = v(bVar.f11166a + 4 + bVar.b);
            }
            b bVar2 = new b(v, i2);
            x(this.m, 0, i2);
            s(bVar2.f11166a, this.m, 0, 4);
            s(bVar2.f11166a + 4, bArr, i, i2);
            w(this.i, this.j + 1, j ? bVar2.f11166a : this.k.f11166a, bVar2.f11166a);
            this.l = bVar2;
            this.j++;
            if (j) {
                this.k = bVar2;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public final void g(int i) throws IOException {
        int i2 = i + 4;
        int p = p();
        if (p >= i2) {
            return;
        }
        int i3 = this.i;
        do {
            p += i3;
            i3 <<= 1;
        } while (p < i2);
        t(i3);
        b bVar = this.l;
        int v = v(bVar.f11166a + 4 + bVar.b);
        if (v < this.k.f11166a) {
            FileChannel channel = this.h.getChannel();
            channel.position(this.i);
            long j = v - 4;
            if (channel.transferTo(16L, j, channel) != j) {
                throw new AssertionError("Copied insufficient number of bytes!");
            }
        }
        int i4 = this.l.f11166a;
        int i5 = this.k.f11166a;
        if (i4 < i5) {
            int i6 = (this.i + i4) - 16;
            w(i3, this.j, i5, i6);
            this.l = new b(i6, this.l.b);
        } else {
            w(i3, this.j, i5, i4);
        }
        this.i = i3;
    }

    public synchronized void h(ElementReader elementReader) throws IOException {
        int i = this.k.f11166a;
        for (int i2 = 0; i2 < this.j; i2++) {
            b m = m(i);
            elementReader.read(new c(this, m, null), m.b);
            i = v(m.f11166a + 4 + m.b);
        }
    }

    public synchronized boolean j() {
        return this.j == 0;
    }

    public final b m(int i) throws IOException {
        if (i == 0) {
            return b.c;
        }
        this.h.seek(i);
        return new b(i, this.h.readInt());
    }

    public final void n() throws IOException {
        this.h.seek(0L);
        this.h.readFully(this.m);
        int o = o(this.m, 0);
        this.i = o;
        if (o <= this.h.length()) {
            this.j = o(this.m, 4);
            int o2 = o(this.m, 8);
            int o3 = o(this.m, 12);
            this.k = m(o2);
            this.l = m(o3);
            return;
        }
        throw new IOException("File is truncated. Expected length: " + this.i + ", Actual length: " + this.h.length());
    }

    public final int p() {
        return this.i - u();
    }

    public synchronized void q() throws IOException {
        if (!j()) {
            if (this.j == 1) {
                clear();
            } else {
                b bVar = this.k;
                int v = v(bVar.f11166a + 4 + bVar.b);
                r(v, this.m, 0, 4);
                int o = o(this.m, 0);
                w(this.i, this.j - 1, v, this.l.f11166a);
                this.j--;
                this.k = new b(v, o);
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    public final void r(int i, byte[] bArr, int i2, int i3) throws IOException {
        int v = v(i);
        int i4 = v + i3;
        int i5 = this.i;
        if (i4 <= i5) {
            this.h.seek(v);
            this.h.readFully(bArr, i2, i3);
            return;
        }
        int i6 = i5 - v;
        this.h.seek(v);
        this.h.readFully(bArr, i2, i6);
        this.h.seek(16L);
        this.h.readFully(bArr, i2 + i6, i3 - i6);
    }

    public final void s(int i, byte[] bArr, int i2, int i3) throws IOException {
        int v = v(i);
        int i4 = v + i3;
        int i5 = this.i;
        if (i4 <= i5) {
            this.h.seek(v);
            this.h.write(bArr, i2, i3);
            return;
        }
        int i6 = i5 - v;
        this.h.seek(v);
        this.h.write(bArr, i2, i6);
        this.h.seek(16L);
        this.h.write(bArr, i2 + i6, i3 - i6);
    }

    public final void t(int i) throws IOException {
        this.h.setLength(i);
        this.h.getChannel().force(true);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(QueueFile.class.getSimpleName());
        sb.append('[');
        sb.append("fileLength=");
        sb.append(this.i);
        sb.append(", size=");
        sb.append(this.j);
        sb.append(", first=");
        sb.append(this.k);
        sb.append(", last=");
        sb.append(this.l);
        sb.append(", element lengths=[");
        try {
            h(new a(this, sb));
        } catch (IOException e) {
            n.log(Level.WARNING, "read error", (Throwable) e);
        }
        sb.append("]]");
        return sb.toString();
    }

    public int u() {
        if (this.j == 0) {
            return 16;
        }
        b bVar = this.l;
        int i = bVar.f11166a;
        int i2 = this.k.f11166a;
        if (i >= i2) {
            return (i - i2) + 4 + bVar.b + 16;
        }
        return (((i + 4) + bVar.b) + this.i) - i2;
    }

    public final int v(int i) {
        int i2 = this.i;
        return i < i2 ? i : (i + 16) - i2;
    }

    public final void w(int i, int i2, int i3, int i4) throws IOException {
        y(this.m, i, i2, i3, i4);
        this.h.seek(0L);
        this.h.write(this.m);
    }
}
