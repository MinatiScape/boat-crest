package okio;

import java.io.IOException;
import javax.crypto.Cipher;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class CipherSink implements Sink {
    @NotNull
    public final BufferedSink h;
    @NotNull
    public final Cipher i;
    public final int j;
    public boolean k;

    public CipherSink(@NotNull BufferedSink sink, @NotNull Cipher cipher) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(cipher, "cipher");
        this.h = sink;
        this.i = cipher;
        int blockSize = cipher.getBlockSize();
        this.j = blockSize;
        if (blockSize > 0) {
            return;
        }
        throw new IllegalArgumentException(("Block cipher required " + cipher).toString());
    }

    public final Throwable a() {
        int outputSize = this.i.getOutputSize(0);
        Throwable th = null;
        if (outputSize == 0) {
            return null;
        }
        if (outputSize > 8192) {
            try {
                BufferedSink bufferedSink = this.h;
                byte[] doFinal = this.i.doFinal();
                Intrinsics.checkNotNullExpressionValue(doFinal, "cipher.doFinal()");
                bufferedSink.write(doFinal);
                return null;
            } catch (Throwable th2) {
                return th2;
            }
        }
        Buffer buffer = this.h.getBuffer();
        Segment writableSegment$okio = buffer.writableSegment$okio(outputSize);
        try {
            int doFinal2 = this.i.doFinal(writableSegment$okio.data, writableSegment$okio.limit);
            writableSegment$okio.limit += doFinal2;
            buffer.setSize$okio(buffer.size() + doFinal2);
        } catch (Throwable th3) {
            th = th3;
        }
        if (writableSegment$okio.pos == writableSegment$okio.limit) {
            buffer.head = writableSegment$okio.pop();
            SegmentPool.recycle(writableSegment$okio);
        }
        return th;
    }

    public final int b(Buffer buffer, long j) {
        Segment segment = buffer.head;
        Intrinsics.checkNotNull(segment);
        int min = (int) Math.min(j, segment.limit - segment.pos);
        Buffer buffer2 = this.h.getBuffer();
        int outputSize = this.i.getOutputSize(min);
        while (outputSize > 8192) {
            int i = this.j;
            if (min <= i) {
                BufferedSink bufferedSink = this.h;
                byte[] update = this.i.update(buffer.readByteArray(j));
                Intrinsics.checkNotNullExpressionValue(update, "cipher.update(source.readByteArray(remaining))");
                bufferedSink.write(update);
                return (int) j;
            }
            min -= i;
            outputSize = this.i.getOutputSize(min);
        }
        Segment writableSegment$okio = buffer2.writableSegment$okio(outputSize);
        int update2 = this.i.update(segment.data, segment.pos, min, writableSegment$okio.data, writableSegment$okio.limit);
        writableSegment$okio.limit += update2;
        buffer2.setSize$okio(buffer2.size() + update2);
        if (writableSegment$okio.pos == writableSegment$okio.limit) {
            buffer2.head = writableSegment$okio.pop();
            SegmentPool.recycle(writableSegment$okio);
        }
        this.h.emitCompleteSegments();
        buffer.setSize$okio(buffer.size() - min);
        int i2 = segment.pos + min;
        segment.pos = i2;
        if (i2 == segment.limit) {
            buffer.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.k) {
            return;
        }
        this.k = true;
        Throwable a2 = a();
        try {
            this.h.close();
        } catch (Throwable th) {
            if (a2 == null) {
                a2 = th;
            }
        }
        if (a2 != null) {
            throw a2;
        }
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        this.h.flush();
    }

    @NotNull
    public final Cipher getCipher() {
        return this.i;
    }

    @Override // okio.Sink
    @NotNull
    public Timeout timeout() {
        return this.h.timeout();
    }

    @Override // okio.Sink
    public void write(@NotNull Buffer source, long j) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        _UtilKt.checkOffsetAndCount(source.size(), 0L, j);
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        while (j > 0) {
            j -= b(source, j);
        }
    }
}
