package okio;

import java.io.IOException;
import javax.crypto.Cipher;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class CipherSource implements Source {
    @NotNull
    public final BufferedSource h;
    @NotNull
    public final Cipher i;
    public final int j;
    @NotNull
    public final Buffer k;
    public boolean l;
    public boolean m;

    public CipherSource(@NotNull BufferedSource source, @NotNull Cipher cipher) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(cipher, "cipher");
        this.h = source;
        this.i = cipher;
        int blockSize = cipher.getBlockSize();
        this.j = blockSize;
        this.k = new Buffer();
        if (blockSize > 0) {
            return;
        }
        throw new IllegalArgumentException(("Block cipher required " + cipher).toString());
    }

    public final void a() {
        int outputSize = this.i.getOutputSize(0);
        if (outputSize == 0) {
            return;
        }
        Segment writableSegment$okio = this.k.writableSegment$okio(outputSize);
        int doFinal = this.i.doFinal(writableSegment$okio.data, writableSegment$okio.pos);
        writableSegment$okio.limit += doFinal;
        Buffer buffer = this.k;
        buffer.setSize$okio(buffer.size() + doFinal);
        if (writableSegment$okio.pos == writableSegment$okio.limit) {
            this.k.head = writableSegment$okio.pop();
            SegmentPool.recycle(writableSegment$okio);
        }
    }

    public final void b() {
        while (this.k.size() == 0 && !this.l) {
            if (this.h.exhausted()) {
                this.l = true;
                a();
                return;
            }
            c();
        }
    }

    public final void c() {
        Segment segment = this.h.getBuffer().head;
        Intrinsics.checkNotNull(segment);
        int i = segment.limit - segment.pos;
        int outputSize = this.i.getOutputSize(i);
        while (outputSize > 8192) {
            int i2 = this.j;
            if (i <= i2) {
                this.l = true;
                Buffer buffer = this.k;
                byte[] doFinal = this.i.doFinal(this.h.readByteArray());
                Intrinsics.checkNotNullExpressionValue(doFinal, "cipher.doFinal(source.readByteArray())");
                buffer.write(doFinal);
                return;
            }
            i -= i2;
            outputSize = this.i.getOutputSize(i);
        }
        Segment writableSegment$okio = this.k.writableSegment$okio(outputSize);
        int update = this.i.update(segment.data, segment.pos, i, writableSegment$okio.data, writableSegment$okio.pos);
        this.h.skip(i);
        writableSegment$okio.limit += update;
        Buffer buffer2 = this.k;
        buffer2.setSize$okio(buffer2.size() + update);
        if (writableSegment$okio.pos == writableSegment$okio.limit) {
            this.k.head = writableSegment$okio.pop();
            SegmentPool.recycle(writableSegment$okio);
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.m = true;
        this.h.close();
    }

    @NotNull
    public final Cipher getCipher() {
        return this.i;
    }

    @Override // okio.Source
    public long read(@NotNull Buffer sink, long j) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i >= 0) {
            if (true ^ this.m) {
                if (i == 0) {
                    return 0L;
                }
                b();
                return this.k.read(sink, j);
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
    }

    @Override // okio.Source
    @NotNull
    public Timeout timeout() {
        return this.h.timeout();
    }
}
