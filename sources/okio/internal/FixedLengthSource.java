package okio.internal;

import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ForwardingSource;
import okio.Source;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class FixedLengthSource extends ForwardingSource {
    public final long i;
    public final boolean j;
    public long k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FixedLengthSource(@NotNull Source delegate, long j, boolean z) {
        super(delegate);
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.i = j;
        this.j = z;
    }

    public final void a(Buffer buffer, long j) {
        Buffer buffer2 = new Buffer();
        buffer2.writeAll(buffer);
        buffer.write(buffer2, j);
        buffer2.clear();
    }

    @Override // okio.ForwardingSource, okio.Source
    public long read(@NotNull Buffer sink, long j) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long j2 = this.k;
        long j3 = this.i;
        if (j2 > j3) {
            j = 0;
        } else if (this.j) {
            long j4 = j3 - j2;
            if (j4 == 0) {
                return -1L;
            }
            j = Math.min(j, j4);
        }
        long read = super.read(sink, j);
        int i = (read > (-1L) ? 1 : (read == (-1L) ? 0 : -1));
        if (i != 0) {
            this.k += read;
        }
        long j5 = this.k;
        long j6 = this.i;
        if ((j5 >= j6 || i != 0) && j5 <= j6) {
            return read;
        }
        if (read > 0 && j5 > j6) {
            a(sink, sink.size() - (this.k - this.i));
        }
        throw new IOException("expected " + this.i + " bytes but got " + this.k);
    }
}
