package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class InflaterSource implements Source {
    @NotNull
    public final BufferedSource h;
    @NotNull
    public final Inflater i;
    public int j;
    public boolean k;

    public InflaterSource(@NotNull BufferedSource source, @NotNull Inflater inflater) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.h = source;
        this.i = inflater;
    }

    public final void a() {
        int i = this.j;
        if (i == 0) {
            return;
        }
        int remaining = i - this.i.getRemaining();
        this.j -= remaining;
        this.h.skip(remaining);
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.k) {
            return;
        }
        this.i.end();
        this.k = true;
        this.h.close();
    }

    @Override // okio.Source
    public long read(@NotNull Buffer sink, long j) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        do {
            long readOrInflate = readOrInflate(sink, j);
            if (readOrInflate > 0) {
                return readOrInflate;
            }
            if (this.i.finished() || this.i.needsDictionary()) {
                return -1L;
            }
        } while (!this.h.exhausted());
        throw new EOFException("source exhausted prematurely");
    }

    public final long readOrInflate(@NotNull Buffer sink, long j) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i >= 0) {
            if (!this.k) {
                if (i == 0) {
                    return 0L;
                }
                try {
                    Segment writableSegment$okio = sink.writableSegment$okio(1);
                    refill();
                    int inflate = this.i.inflate(writableSegment$okio.data, writableSegment$okio.limit, (int) Math.min(j, 8192 - writableSegment$okio.limit));
                    a();
                    if (inflate > 0) {
                        writableSegment$okio.limit += inflate;
                        long j2 = inflate;
                        sink.setSize$okio(sink.size() + j2);
                        return j2;
                    }
                    if (writableSegment$okio.pos == writableSegment$okio.limit) {
                        sink.head = writableSegment$okio.pop();
                        SegmentPool.recycle(writableSegment$okio);
                    }
                    return 0L;
                } catch (DataFormatException e) {
                    throw new IOException(e);
                }
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
    }

    public final boolean refill() throws IOException {
        if (this.i.needsInput()) {
            if (this.h.exhausted()) {
                return true;
            }
            Segment segment = this.h.getBuffer().head;
            Intrinsics.checkNotNull(segment);
            int i = segment.limit;
            int i2 = segment.pos;
            int i3 = i - i2;
            this.j = i3;
            this.i.setInput(segment.data, i2, i3);
            return false;
        }
        return false;
    }

    @Override // okio.Source
    @NotNull
    public Timeout timeout() {
        return this.h.timeout();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public InflaterSource(@NotNull Source source, @NotNull Inflater inflater) {
        this(Okio.buffer(source), inflater);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
    }
}
