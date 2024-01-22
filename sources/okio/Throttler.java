package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class Throttler {

    /* renamed from: a  reason: collision with root package name */
    public long f14317a;
    public long b;
    public long c;
    public long d;

    public Throttler(long j) {
        this.f14317a = j;
        this.c = PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        this.d = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
    }

    public static /* synthetic */ void bytesPerSecond$default(Throttler throttler, long j, long j2, long j3, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = throttler.c;
        }
        long j4 = j2;
        if ((i & 4) != 0) {
            j3 = throttler.d;
        }
        throttler.bytesPerSecond(j, j4, j3);
    }

    public final long a(long j) {
        return (j * 1000000000) / this.b;
    }

    public final long b(long j) {
        return (j * this.b) / 1000000000;
    }

    public final long byteCountOrWaitNanos$okio(long j, long j2) {
        if (this.b == 0) {
            return j2;
        }
        long max = Math.max(this.f14317a - j, 0L);
        long b = this.d - b(max);
        if (b >= j2) {
            this.f14317a = j + max + a(j2);
            return j2;
        }
        long j3 = this.c;
        if (b >= j3) {
            this.f14317a = j + a(this.d);
            return b;
        }
        long min = Math.min(j3, j2);
        long a2 = max + a(min - this.d);
        if (a2 == 0) {
            this.f14317a = j + a(this.d);
            return min;
        }
        return -a2;
    }

    @JvmOverloads
    public final void bytesPerSecond(long j) {
        bytesPerSecond$default(this, j, 0L, 0L, 6, null);
    }

    @JvmOverloads
    public final void bytesPerSecond(long j, long j2) {
        bytesPerSecond$default(this, j, j2, 0L, 4, null);
    }

    @JvmOverloads
    public final void bytesPerSecond(long j, long j2, long j3) {
        synchronized (this) {
            if (!(j >= 0)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (!(j2 > 0)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (j3 >= j2) {
                this.b = j;
                this.c = j2;
                this.d = j3;
                notifyAll();
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }
    }

    public final void c(long j) {
        long j2 = j / 1000000;
        wait(j2, (int) (j - (1000000 * j2)));
    }

    @NotNull
    public final Sink sink(@NotNull final Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return new ForwardingSink(sink) { // from class: okio.Throttler$sink$1
            @Override // okio.ForwardingSink, okio.Sink
            public void write(@NotNull Buffer source, long j) throws IOException {
                Intrinsics.checkNotNullParameter(source, "source");
                while (j > 0) {
                    try {
                        long take$okio = this.take$okio(j);
                        super.write(source, take$okio);
                        j -= take$okio;
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException("interrupted");
                    }
                }
            }
        };
    }

    @NotNull
    public final Source source(@NotNull final Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new ForwardingSource(source) { // from class: okio.Throttler$source$1
            @Override // okio.ForwardingSource, okio.Source
            public long read(@NotNull Buffer sink, long j) {
                Intrinsics.checkNotNullParameter(sink, "sink");
                try {
                    return super.read(sink, this.take$okio(j));
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    throw new InterruptedIOException("interrupted");
                }
            }
        };
    }

    public final long take$okio(long j) {
        long byteCountOrWaitNanos$okio;
        if (j > 0) {
            synchronized (this) {
                while (true) {
                    byteCountOrWaitNanos$okio = byteCountOrWaitNanos$okio(System.nanoTime(), j);
                    if (byteCountOrWaitNanos$okio < 0) {
                        c(-byteCountOrWaitNanos$okio);
                    }
                }
            }
            return byteCountOrWaitNanos$okio;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public Throttler() {
        this(System.nanoTime());
    }
}
