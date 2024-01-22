package okio;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class e implements Sink {
    @NotNull
    public final OutputStream h;
    @NotNull
    public final Timeout i;

    public e(@NotNull OutputStream out, @NotNull Timeout timeout) {
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        this.h = out;
        this.i = timeout;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.h.close();
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        this.h.flush();
    }

    @Override // okio.Sink
    @NotNull
    public Timeout timeout() {
        return this.i;
    }

    @NotNull
    public String toString() {
        return "sink(" + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // okio.Sink
    public void write(@NotNull Buffer source, long j) {
        Intrinsics.checkNotNullParameter(source, "source");
        _UtilKt.checkOffsetAndCount(source.size(), 0L, j);
        while (j > 0) {
            this.i.throwIfReached();
            Segment segment = source.head;
            Intrinsics.checkNotNull(segment);
            int min = (int) Math.min(j, segment.limit - segment.pos);
            this.h.write(segment.data, segment.pos, min);
            segment.pos += min;
            long j2 = min;
            j -= j2;
            source.setSize$okio(source.size() - j2);
            if (segment.pos == segment.limit) {
                source.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }
}
