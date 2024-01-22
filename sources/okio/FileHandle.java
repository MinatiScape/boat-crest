package okio;

import java.io.Closeable;
import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public abstract class FileHandle implements Closeable {
    public final boolean h;
    public boolean i;
    public int j;

    /* loaded from: classes12.dex */
    public static final class a implements Sink {
        @NotNull
        public final FileHandle h;
        public long i;
        public boolean j;

        public a(@NotNull FileHandle fileHandle, long j) {
            Intrinsics.checkNotNullParameter(fileHandle, "fileHandle");
            this.h = fileHandle;
            this.i = j;
        }

        public final boolean a() {
            return this.j;
        }

        @NotNull
        public final FileHandle b() {
            return this.h;
        }

        public final long c() {
            return this.i;
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.j) {
                return;
            }
            this.j = true;
            synchronized (this.h) {
                FileHandle fileHandle = this.h;
                fileHandle.j--;
                if (this.h.j == 0 && this.h.i) {
                    Unit unit = Unit.INSTANCE;
                    this.h.protectedClose();
                }
            }
        }

        public final void d(long j) {
            this.i = j;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            if (!this.j) {
                this.h.protectedFlush();
                return;
            }
            throw new IllegalStateException("closed".toString());
        }

        @Override // okio.Sink
        @NotNull
        public Timeout timeout() {
            return Timeout.NONE;
        }

        @Override // okio.Sink
        public void write(@NotNull Buffer source, long j) {
            Intrinsics.checkNotNullParameter(source, "source");
            if (!this.j) {
                this.h.b(this.i, source, j);
                this.i += j;
                return;
            }
            throw new IllegalStateException("closed".toString());
        }
    }

    /* loaded from: classes12.dex */
    public static final class b implements Source {
        @NotNull
        public final FileHandle h;
        public long i;
        public boolean j;

        public b(@NotNull FileHandle fileHandle, long j) {
            Intrinsics.checkNotNullParameter(fileHandle, "fileHandle");
            this.h = fileHandle;
            this.i = j;
        }

        public final boolean a() {
            return this.j;
        }

        @NotNull
        public final FileHandle b() {
            return this.h;
        }

        public final long c() {
            return this.i;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.j) {
                return;
            }
            this.j = true;
            synchronized (this.h) {
                FileHandle fileHandle = this.h;
                fileHandle.j--;
                if (this.h.j == 0 && this.h.i) {
                    Unit unit = Unit.INSTANCE;
                    this.h.protectedClose();
                }
            }
        }

        public final void d(long j) {
            this.i = j;
        }

        @Override // okio.Source
        public long read(@NotNull Buffer sink, long j) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (!this.j) {
                long a2 = this.h.a(this.i, sink, j);
                if (a2 != -1) {
                    this.i += a2;
                }
                return a2;
            }
            throw new IllegalStateException("closed".toString());
        }

        @Override // okio.Source
        @NotNull
        public Timeout timeout() {
            return Timeout.NONE;
        }
    }

    public FileHandle(boolean z) {
        this.h = z;
    }

    public static /* synthetic */ Sink sink$default(FileHandle fileHandle, long j, int i, Object obj) throws IOException {
        if (obj == null) {
            if ((i & 1) != 0) {
                j = 0;
            }
            return fileHandle.sink(j);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sink");
    }

    public static /* synthetic */ Source source$default(FileHandle fileHandle, long j, int i, Object obj) throws IOException {
        if (obj == null) {
            if ((i & 1) != 0) {
                j = 0;
            }
            return fileHandle.source(j);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: source");
    }

    public final long a(long j, Buffer buffer, long j2) {
        int i;
        if (!(j2 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j2).toString());
        }
        long j3 = j + j2;
        long j4 = j;
        while (true) {
            if (j4 >= j3) {
                break;
            }
            Segment writableSegment$okio = buffer.writableSegment$okio(1);
            int protectedRead = protectedRead(j4, writableSegment$okio.data, writableSegment$okio.limit, (int) Math.min(j3 - j4, 8192 - i));
            if (protectedRead == -1) {
                if (writableSegment$okio.pos == writableSegment$okio.limit) {
                    buffer.head = writableSegment$okio.pop();
                    SegmentPool.recycle(writableSegment$okio);
                }
                if (j == j4) {
                    return -1L;
                }
            } else {
                writableSegment$okio.limit += protectedRead;
                long j5 = protectedRead;
                j4 += j5;
                buffer.setSize$okio(buffer.size() + j5);
            }
        }
        return j4 - j;
    }

    @NotNull
    public final Sink appendingSink() throws IOException {
        return sink(size());
    }

    public final void b(long j, Buffer buffer, long j2) {
        _UtilKt.checkOffsetAndCount(buffer.size(), 0L, j2);
        long j3 = j2 + j;
        while (j < j3) {
            Segment segment = buffer.head;
            Intrinsics.checkNotNull(segment);
            int min = (int) Math.min(j3 - j, segment.limit - segment.pos);
            protectedWrite(j, segment.data, segment.pos, min);
            segment.pos += min;
            long j4 = min;
            j += j4;
            buffer.setSize$okio(buffer.size() - j4);
            if (segment.pos == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        synchronized (this) {
            if (this.i) {
                return;
            }
            this.i = true;
            if (this.j != 0) {
                return;
            }
            Unit unit = Unit.INSTANCE;
            protectedClose();
        }
    }

    public final void flush() throws IOException {
        if (this.h) {
            synchronized (this) {
                if (!this.i) {
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("closed".toString());
                }
            }
            protectedFlush();
            return;
        }
        throw new IllegalStateException("file handle is read-only".toString());
    }

    public final boolean getReadWrite() {
        return this.h;
    }

    public final long position(@NotNull Source source) throws IOException {
        long j;
        Intrinsics.checkNotNullParameter(source, "source");
        if (source instanceof RealBufferedSource) {
            RealBufferedSource realBufferedSource = (RealBufferedSource) source;
            j = realBufferedSource.bufferField.size();
            source = realBufferedSource.source;
        } else {
            j = 0;
        }
        if ((source instanceof b) && ((b) source).b() == this) {
            b bVar = (b) source;
            if (!bVar.a()) {
                return bVar.c() - j;
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException("source was not created by this FileHandle".toString());
    }

    public abstract void protectedClose() throws IOException;

    public abstract void protectedFlush() throws IOException;

    public abstract int protectedRead(long j, @NotNull byte[] bArr, int i, int i2) throws IOException;

    public abstract void protectedResize(long j) throws IOException;

    public abstract long protectedSize() throws IOException;

    public abstract void protectedWrite(long j, @NotNull byte[] bArr, int i, int i2) throws IOException;

    public final int read(long j, @NotNull byte[] array, int i, int i2) throws IOException {
        Intrinsics.checkNotNullParameter(array, "array");
        synchronized (this) {
            if (!this.i) {
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("closed".toString());
            }
        }
        return protectedRead(j, array, i, i2);
    }

    public final void reposition(@NotNull Source source, long j) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        boolean z = false;
        if (source instanceof RealBufferedSource) {
            RealBufferedSource realBufferedSource = (RealBufferedSource) source;
            Source source2 = realBufferedSource.source;
            if ((source2 instanceof b) && ((b) source2).b() == this) {
                b bVar = (b) source2;
                if (!bVar.a()) {
                    long size = realBufferedSource.bufferField.size();
                    long c = j - (bVar.c() - size);
                    if (0 <= c && c < size) {
                        z = true;
                    }
                    if (z) {
                        realBufferedSource.skip(c);
                        return;
                    }
                    realBufferedSource.bufferField.clear();
                    bVar.d(j);
                    return;
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException("source was not created by this FileHandle".toString());
        }
        if ((source instanceof b) && ((b) source).b() == this) {
            z = true;
        }
        if (z) {
            b bVar2 = (b) source;
            if (!bVar2.a()) {
                bVar2.d(j);
                return;
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException("source was not created by this FileHandle".toString());
    }

    public final void resize(long j) throws IOException {
        if (this.h) {
            synchronized (this) {
                if (!this.i) {
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("closed".toString());
                }
            }
            protectedResize(j);
            return;
        }
        throw new IllegalStateException("file handle is read-only".toString());
    }

    @NotNull
    public final Sink sink(long j) throws IOException {
        if (this.h) {
            synchronized (this) {
                if (!this.i) {
                    this.j++;
                } else {
                    throw new IllegalStateException("closed".toString());
                }
            }
            return new a(this, j);
        }
        throw new IllegalStateException("file handle is read-only".toString());
    }

    public final long size() throws IOException {
        synchronized (this) {
            if (!this.i) {
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("closed".toString());
            }
        }
        return protectedSize();
    }

    @NotNull
    public final Source source(long j) throws IOException {
        synchronized (this) {
            if (!this.i) {
                this.j++;
            } else {
                throw new IllegalStateException("closed".toString());
            }
        }
        return new b(this, j);
    }

    public final void write(long j, @NotNull byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (this.h) {
            synchronized (this) {
                if (!this.i) {
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("closed".toString());
                }
            }
            protectedWrite(j, array, i, i2);
            return;
        }
        throw new IllegalStateException("file handle is read-only".toString());
    }

    public final long read(long j, @NotNull Buffer sink, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        synchronized (this) {
            if (!this.i) {
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("closed".toString());
            }
        }
        return a(j, sink, j2);
    }

    public final void write(long j, @NotNull Buffer source, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (this.h) {
            synchronized (this) {
                if (!this.i) {
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("closed".toString());
                }
            }
            b(j, source, j2);
            return;
        }
        throw new IllegalStateException("file handle is read-only".toString());
    }

    public final long position(@NotNull Sink sink) throws IOException {
        long j;
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (sink instanceof RealBufferedSink) {
            RealBufferedSink realBufferedSink = (RealBufferedSink) sink;
            j = realBufferedSink.bufferField.size();
            sink = realBufferedSink.sink;
        } else {
            j = 0;
        }
        if ((sink instanceof a) && ((a) sink).b() == this) {
            a aVar = (a) sink;
            if (!aVar.a()) {
                return aVar.c() + j;
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException("sink was not created by this FileHandle".toString());
    }

    public final void reposition(@NotNull Sink sink, long j) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        boolean z = false;
        if (sink instanceof RealBufferedSink) {
            RealBufferedSink realBufferedSink = (RealBufferedSink) sink;
            Sink sink2 = realBufferedSink.sink;
            if ((sink2 instanceof a) && ((a) sink2).b() == this) {
                z = true;
            }
            if (z) {
                a aVar = (a) sink2;
                if (!aVar.a()) {
                    realBufferedSink.emit();
                    aVar.d(j);
                    return;
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException("sink was not created by this FileHandle".toString());
        }
        if ((sink instanceof a) && ((a) sink).b() == this) {
            z = true;
        }
        if (z) {
            a aVar2 = (a) sink;
            if (!aVar2.a()) {
                aVar2.d(j);
                return;
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException("sink was not created by this FileHandle".toString());
    }
}
