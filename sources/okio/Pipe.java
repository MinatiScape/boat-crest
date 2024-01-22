package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Pipe {

    /* renamed from: a  reason: collision with root package name */
    public final long f14315a;
    @NotNull
    public final Buffer b = new Buffer();
    public boolean c;
    public boolean d;
    public boolean e;
    @Nullable
    public Sink f;
    @NotNull
    public final Sink g;
    @NotNull
    public final Source h;

    public Pipe(long j) {
        this.f14315a = j;
        if (j >= 1) {
            this.g = new Sink() { // from class: okio.Pipe$sink$1
                @NotNull
                public final Timeout h = new Timeout();

                @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    Buffer buffer$okio = Pipe.this.getBuffer$okio();
                    Pipe pipe = Pipe.this;
                    synchronized (buffer$okio) {
                        if (pipe.getSinkClosed$okio()) {
                            return;
                        }
                        Sink foldedSink$okio = pipe.getFoldedSink$okio();
                        if (foldedSink$okio == null) {
                            if (pipe.getSourceClosed$okio() && pipe.getBuffer$okio().size() > 0) {
                                throw new IOException("source is closed");
                            }
                            pipe.setSinkClosed$okio(true);
                            pipe.getBuffer$okio().notifyAll();
                            foldedSink$okio = null;
                        }
                        Unit unit = Unit.INSTANCE;
                        if (foldedSink$okio != null) {
                            Pipe pipe2 = Pipe.this;
                            Timeout timeout = foldedSink$okio.timeout();
                            Timeout timeout2 = pipe2.sink().timeout();
                            long timeoutNanos = timeout.timeoutNanos();
                            long minTimeout = Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos());
                            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                            timeout.timeout(minTimeout, timeUnit);
                            if (timeout.hasDeadline()) {
                                long deadlineNanoTime = timeout.deadlineNanoTime();
                                if (timeout2.hasDeadline()) {
                                    timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                                }
                                try {
                                    foldedSink$okio.close();
                                    timeout.timeout(timeoutNanos, timeUnit);
                                    if (timeout2.hasDeadline()) {
                                        timeout.deadlineNanoTime(deadlineNanoTime);
                                        return;
                                    }
                                    return;
                                } catch (Throwable th) {
                                    timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                                    if (timeout2.hasDeadline()) {
                                        timeout.deadlineNanoTime(deadlineNanoTime);
                                    }
                                    throw th;
                                }
                            }
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
                            }
                            try {
                                foldedSink$okio.close();
                                timeout.timeout(timeoutNanos, timeUnit);
                                if (timeout2.hasDeadline()) {
                                    timeout.clearDeadline();
                                }
                            } catch (Throwable th2) {
                                timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                                if (timeout2.hasDeadline()) {
                                    timeout.clearDeadline();
                                }
                                throw th2;
                            }
                        }
                    }
                }

                @Override // okio.Sink, java.io.Flushable
                public void flush() {
                    Sink foldedSink$okio;
                    Buffer buffer$okio = Pipe.this.getBuffer$okio();
                    Pipe pipe = Pipe.this;
                    synchronized (buffer$okio) {
                        if (!pipe.getSinkClosed$okio()) {
                            if (!pipe.getCanceled$okio()) {
                                foldedSink$okio = pipe.getFoldedSink$okio();
                                if (foldedSink$okio == null) {
                                    if (pipe.getSourceClosed$okio() && pipe.getBuffer$okio().size() > 0) {
                                        throw new IOException("source is closed");
                                    }
                                    foldedSink$okio = null;
                                }
                                Unit unit = Unit.INSTANCE;
                            } else {
                                throw new IOException("canceled");
                            }
                        } else {
                            throw new IllegalStateException("closed".toString());
                        }
                    }
                    if (foldedSink$okio != null) {
                        Pipe pipe2 = Pipe.this;
                        Timeout timeout = foldedSink$okio.timeout();
                        Timeout timeout2 = pipe2.sink().timeout();
                        long timeoutNanos = timeout.timeoutNanos();
                        long minTimeout = Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos());
                        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                        timeout.timeout(minTimeout, timeUnit);
                        if (timeout.hasDeadline()) {
                            long deadlineNanoTime = timeout.deadlineNanoTime();
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                            }
                            try {
                                foldedSink$okio.flush();
                                timeout.timeout(timeoutNanos, timeUnit);
                                if (timeout2.hasDeadline()) {
                                    timeout.deadlineNanoTime(deadlineNanoTime);
                                    return;
                                }
                                return;
                            } catch (Throwable th) {
                                timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                                if (timeout2.hasDeadline()) {
                                    timeout.deadlineNanoTime(deadlineNanoTime);
                                }
                                throw th;
                            }
                        }
                        if (timeout2.hasDeadline()) {
                            timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
                        }
                        try {
                            foldedSink$okio.flush();
                            timeout.timeout(timeoutNanos, timeUnit);
                            if (timeout2.hasDeadline()) {
                                timeout.clearDeadline();
                            }
                        } catch (Throwable th2) {
                            timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout2.hasDeadline()) {
                                timeout.clearDeadline();
                            }
                            throw th2;
                        }
                    }
                }

                @Override // okio.Sink
                @NotNull
                public Timeout timeout() {
                    return this.h;
                }

                /* JADX WARN: Code restructure failed: missing block: B:26:0x0075, code lost:
                    r1 = kotlin.Unit.INSTANCE;
                 */
                @Override // okio.Sink
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void write(@org.jetbrains.annotations.NotNull okio.Buffer r13, long r14) {
                    /*
                        Method dump skipped, instructions count: 291
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: okio.Pipe$sink$1.write(okio.Buffer, long):void");
                }
            };
            this.h = new Source() { // from class: okio.Pipe$source$1
                @NotNull
                public final Timeout h = new Timeout();

                @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    Buffer buffer$okio = Pipe.this.getBuffer$okio();
                    Pipe pipe = Pipe.this;
                    synchronized (buffer$okio) {
                        pipe.setSourceClosed$okio(true);
                        pipe.getBuffer$okio().notifyAll();
                        Unit unit = Unit.INSTANCE;
                    }
                }

                @Override // okio.Source
                public long read(@NotNull Buffer sink, long j2) {
                    Intrinsics.checkNotNullParameter(sink, "sink");
                    Buffer buffer$okio = Pipe.this.getBuffer$okio();
                    Pipe pipe = Pipe.this;
                    synchronized (buffer$okio) {
                        if (!pipe.getSourceClosed$okio()) {
                            if (!pipe.getCanceled$okio()) {
                                while (pipe.getBuffer$okio().size() == 0) {
                                    if (pipe.getSinkClosed$okio()) {
                                        return -1L;
                                    }
                                    this.h.waitUntilNotified(pipe.getBuffer$okio());
                                    if (pipe.getCanceled$okio()) {
                                        throw new IOException("canceled");
                                    }
                                }
                                long read = pipe.getBuffer$okio().read(sink, j2);
                                pipe.getBuffer$okio().notifyAll();
                                return read;
                            }
                            throw new IOException("canceled");
                        }
                        throw new IllegalStateException("closed".toString());
                    }
                }

                @Override // okio.Source
                @NotNull
                public Timeout timeout() {
                    return this.h;
                }
            };
            return;
        }
        throw new IllegalArgumentException(("maxBufferSize < 1: " + j).toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sink", imports = {}))
    @JvmName(name = "-deprecated_sink")
    @NotNull
    /* renamed from: -deprecated_sink  reason: not valid java name */
    public final Sink m943deprecated_sink() {
        return this.g;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "source", imports = {}))
    @JvmName(name = "-deprecated_source")
    @NotNull
    /* renamed from: -deprecated_source  reason: not valid java name */
    public final Source m944deprecated_source() {
        return this.h;
    }

    public final void cancel() {
        synchronized (this.b) {
            this.c = true;
            this.b.clear();
            this.b.notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void fold(@NotNull Sink sink) throws IOException {
        boolean z;
        Buffer buffer;
        Intrinsics.checkNotNullParameter(sink, "sink");
        while (true) {
            synchronized (this.b) {
                if (this.f == null) {
                    if (!this.c) {
                        if (this.b.exhausted()) {
                            this.e = true;
                            this.f = sink;
                            return;
                        }
                        z = this.d;
                        buffer = new Buffer();
                        Buffer buffer2 = this.b;
                        buffer.write(buffer2, buffer2.size());
                        this.b.notifyAll();
                        Unit unit = Unit.INSTANCE;
                    } else {
                        this.f = sink;
                        throw new IOException("canceled");
                    }
                } else {
                    throw new IllegalStateException("sink already folded".toString());
                }
            }
            try {
                sink.write(buffer, buffer.size());
                if (z) {
                    sink.close();
                } else {
                    sink.flush();
                }
            } catch (Throwable th) {
                synchronized (this.b) {
                    this.e = true;
                    this.b.notifyAll();
                    Unit unit2 = Unit.INSTANCE;
                    throw th;
                }
            }
        }
    }

    @NotNull
    public final Buffer getBuffer$okio() {
        return this.b;
    }

    public final boolean getCanceled$okio() {
        return this.c;
    }

    @Nullable
    public final Sink getFoldedSink$okio() {
        return this.f;
    }

    public final long getMaxBufferSize$okio() {
        return this.f14315a;
    }

    public final boolean getSinkClosed$okio() {
        return this.d;
    }

    public final boolean getSourceClosed$okio() {
        return this.e;
    }

    public final void setCanceled$okio(boolean z) {
        this.c = z;
    }

    public final void setFoldedSink$okio(@Nullable Sink sink) {
        this.f = sink;
    }

    public final void setSinkClosed$okio(boolean z) {
        this.d = z;
    }

    public final void setSourceClosed$okio(boolean z) {
        this.e = z;
    }

    @JvmName(name = "sink")
    @NotNull
    public final Sink sink() {
        return this.g;
    }

    @JvmName(name = "source")
    @NotNull
    public final Source source() {
        return this.h;
    }
}
