package okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class GzipSink implements Sink {
    @NotNull
    public final RealBufferedSink h;
    @NotNull
    public final Deflater i;
    @NotNull
    public final DeflaterSink j;
    public boolean k;
    @NotNull
    public final CRC32 l;

    public GzipSink(@NotNull Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        RealBufferedSink realBufferedSink = new RealBufferedSink(sink);
        this.h = realBufferedSink;
        Deflater deflater = new Deflater(-1, true);
        this.i = deflater;
        this.j = new DeflaterSink((BufferedSink) realBufferedSink, deflater);
        this.l = new CRC32();
        Buffer buffer = realBufferedSink.bufferField;
        buffer.writeShort(8075);
        buffer.writeByte(8);
        buffer.writeByte(0);
        buffer.writeInt(0);
        buffer.writeByte(0);
        buffer.writeByte(0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "deflater", imports = {}))
    @JvmName(name = "-deprecated_deflater")
    @NotNull
    /* renamed from: -deprecated_deflater  reason: not valid java name */
    public final Deflater m940deprecated_deflater() {
        return this.i;
    }

    public final void a(Buffer buffer, long j) {
        Segment segment = buffer.head;
        Intrinsics.checkNotNull(segment);
        while (j > 0) {
            int min = (int) Math.min(j, segment.limit - segment.pos);
            this.l.update(segment.data, segment.pos, min);
            j -= min;
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
        }
    }

    public final void b() {
        this.h.writeIntLe((int) this.l.getValue());
        this.h.writeIntLe((int) this.i.getBytesRead());
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.k) {
            return;
        }
        Throwable th = null;
        try {
            this.j.finishDeflate$okio();
            b();
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
        this.k = true;
        if (th != null) {
            throw th;
        }
    }

    @JvmName(name = "deflater")
    @NotNull
    public final Deflater deflater() {
        return this.i;
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        this.j.flush();
    }

    @Override // okio.Sink
    @NotNull
    public Timeout timeout() {
        return this.h.timeout();
    }

    @Override // okio.Sink
    public void write(@NotNull Buffer source, long j) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        } else if (i == 0) {
        } else {
            a(source, j);
            this.j.write(source, j);
        }
    }
}
