package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class GzipSource implements Source {
    public byte h;
    @NotNull
    public final RealBufferedSource i;
    @NotNull
    public final Inflater j;
    @NotNull
    public final InflaterSource k;
    @NotNull
    public final CRC32 l;

    public GzipSource(@NotNull Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        RealBufferedSource realBufferedSource = new RealBufferedSource(source);
        this.i = realBufferedSource;
        Inflater inflater = new Inflater(true);
        this.j = inflater;
        this.k = new InflaterSource((BufferedSource) realBufferedSource, inflater);
        this.l = new CRC32();
    }

    public final void a(String str, int i, int i2) {
        if (i2 == i) {
            return;
        }
        String format = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(this, *args)");
        throw new IOException(format);
    }

    public final void b() throws IOException {
        this.i.require(10L);
        byte b = this.i.bufferField.getByte(3L);
        boolean z = ((b >> 1) & 1) == 1;
        if (z) {
            d(this.i.bufferField, 0L, 10L);
        }
        a("ID1ID2", 8075, this.i.readShort());
        this.i.skip(8L);
        if (((b >> 2) & 1) == 1) {
            this.i.require(2L);
            if (z) {
                d(this.i.bufferField, 0L, 2L);
            }
            long readShortLe = this.i.bufferField.readShortLe();
            this.i.require(readShortLe);
            if (z) {
                d(this.i.bufferField, 0L, readShortLe);
            }
            this.i.skip(readShortLe);
        }
        if (((b >> 3) & 1) == 1) {
            long indexOf = this.i.indexOf((byte) 0);
            if (indexOf != -1) {
                if (z) {
                    d(this.i.bufferField, 0L, indexOf + 1);
                }
                this.i.skip(indexOf + 1);
            } else {
                throw new EOFException();
            }
        }
        if (((b >> 4) & 1) == 1) {
            long indexOf2 = this.i.indexOf((byte) 0);
            if (indexOf2 != -1) {
                if (z) {
                    d(this.i.bufferField, 0L, indexOf2 + 1);
                }
                this.i.skip(indexOf2 + 1);
            } else {
                throw new EOFException();
            }
        }
        if (z) {
            a("FHCRC", this.i.readShortLe(), (short) this.l.getValue());
            this.l.reset();
        }
    }

    public final void c() throws IOException {
        a("CRC", this.i.readIntLe(), (int) this.l.getValue());
        a("ISIZE", this.i.readIntLe(), (int) this.j.getBytesWritten());
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.k.close();
    }

    public final void d(Buffer buffer, long j, long j2) {
        int i;
        Segment segment = buffer.head;
        Intrinsics.checkNotNull(segment);
        while (true) {
            int i2 = segment.limit;
            int i3 = segment.pos;
            if (j < i2 - i3) {
                break;
            }
            j -= i2 - i3;
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
        }
        while (j2 > 0) {
            int min = (int) Math.min(segment.limit - i, j2);
            this.l.update(segment.data, (int) (segment.pos + j), min);
            j2 -= min;
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
            j = 0;
        }
    }

    @Override // okio.Source
    public long read(@NotNull Buffer sink, long j) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        } else if (i == 0) {
            return 0L;
        } else {
            if (this.h == 0) {
                b();
                this.h = (byte) 1;
            }
            if (this.h == 1) {
                long size = sink.size();
                long read = this.k.read(sink, j);
                if (read != -1) {
                    d(sink, size, read);
                    return read;
                }
                this.h = (byte) 2;
            }
            if (this.h == 2) {
                c();
                this.h = (byte) 3;
                if (!this.i.exhausted()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1L;
        }
    }

    @Override // okio.Source
    @NotNull
    public Timeout timeout() {
        return this.i.timeout();
    }
}
