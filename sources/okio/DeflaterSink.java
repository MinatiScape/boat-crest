package okio;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.IOException;
import java.util.zip.Deflater;
import kotlin.jvm.internal.Intrinsics;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class DeflaterSink implements Sink {
    @NotNull
    public final BufferedSink h;
    @NotNull
    public final Deflater i;
    public boolean j;

    public DeflaterSink(@NotNull BufferedSink sink, @NotNull Deflater deflater) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(deflater, "deflater");
        this.h = sink;
        this.i = deflater;
    }

    @IgnoreJRERequirement
    public final void a(boolean z) {
        Segment writableSegment$okio;
        int deflate;
        Buffer buffer = this.h.getBuffer();
        while (true) {
            writableSegment$okio = buffer.writableSegment$okio(1);
            if (z) {
                Deflater deflater = this.i;
                byte[] bArr = writableSegment$okio.data;
                int i = writableSegment$okio.limit;
                deflate = deflater.deflate(bArr, i, 8192 - i, 2);
            } else {
                Deflater deflater2 = this.i;
                byte[] bArr2 = writableSegment$okio.data;
                int i2 = writableSegment$okio.limit;
                deflate = deflater2.deflate(bArr2, i2, 8192 - i2);
            }
            if (deflate > 0) {
                writableSegment$okio.limit += deflate;
                buffer.setSize$okio(buffer.size() + deflate);
                this.h.emitCompleteSegments();
            } else if (this.i.needsInput()) {
                break;
            }
        }
        if (writableSegment$okio.pos == writableSegment$okio.limit) {
            buffer.head = writableSegment$okio.pop();
            SegmentPool.recycle(writableSegment$okio);
        }
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.j) {
            return;
        }
        Throwable th = null;
        try {
            finishDeflate$okio();
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.i.end();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        try {
            this.h.close();
        } catch (Throwable th4) {
            if (th == null) {
                th = th4;
            }
        }
        this.j = true;
        if (th != null) {
            throw th;
        }
    }

    public final void finishDeflate$okio() {
        this.i.finish();
        a(false);
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        a(true);
        this.h.flush();
    }

    @Override // okio.Sink
    @NotNull
    public Timeout timeout() {
        return this.h.timeout();
    }

    @NotNull
    public String toString() {
        return "DeflaterSink(" + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // okio.Sink
    public void write(@NotNull Buffer source, long j) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        _UtilKt.checkOffsetAndCount(source.size(), 0L, j);
        while (j > 0) {
            Segment segment = source.head;
            Intrinsics.checkNotNull(segment);
            int min = (int) Math.min(j, segment.limit - segment.pos);
            this.i.setInput(segment.data, segment.pos, min);
            a(false);
            long j2 = min;
            source.setSize$okio(source.size() - j2);
            int i = segment.pos + min;
            segment.pos = i;
            if (i == segment.limit) {
                source.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            j -= j2;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeflaterSink(@NotNull Sink sink, @NotNull Deflater deflater) {
        this(Okio.buffer(sink), deflater);
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(deflater, "deflater");
    }
}
