package okhttp3;

import android.support.v4.media.session.PlaybackStateCompat;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http1.HeadersReader;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class MultipartReader implements Closeable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final Options p;
    @NotNull
    public final BufferedSource h;
    @NotNull
    public final String i;
    @NotNull
    public final ByteString j;
    @NotNull
    public final ByteString k;
    public int l;
    public boolean m;
    public boolean n;
    @Nullable
    public a o;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Options getAfterBoundaryOptions() {
            return MultipartReader.p;
        }
    }

    /* loaded from: classes12.dex */
    public static final class Part implements Closeable {
        @NotNull
        public final Headers h;
        @NotNull
        public final BufferedSource i;

        public Part(@NotNull Headers headers, @NotNull BufferedSource body) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            Intrinsics.checkNotNullParameter(body, "body");
            this.h = headers;
            this.i = body;
        }

        @JvmName(name = "body")
        @NotNull
        public final BufferedSource body() {
            return this.i;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.i.close();
        }

        @JvmName(name = "headers")
        @NotNull
        public final Headers headers() {
            return this.h;
        }
    }

    /* loaded from: classes12.dex */
    public final class a implements Source {
        @NotNull
        public final Timeout h;
        public final /* synthetic */ MultipartReader i;

        public a(MultipartReader this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.i = this$0;
            this.h = new Timeout();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (Intrinsics.areEqual(this.i.o, this)) {
                this.i.o = null;
            }
        }

        @Override // okio.Source
        public long read(@NotNull Buffer sink, long j) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (j >= 0) {
                if (Intrinsics.areEqual(this.i.o, this)) {
                    Timeout timeout = this.i.h.timeout();
                    Timeout timeout2 = this.h;
                    MultipartReader multipartReader = this.i;
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
                            long a2 = multipartReader.a(j);
                            long read = a2 == 0 ? -1L : multipartReader.h.read(sink, a2);
                            timeout.timeout(timeoutNanos, timeUnit);
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(deadlineNanoTime);
                            }
                            return read;
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
                        long a3 = multipartReader.a(j);
                        long read2 = a3 == 0 ? -1L : multipartReader.h.read(sink, a3);
                        timeout.timeout(timeoutNanos, timeUnit);
                        if (timeout2.hasDeadline()) {
                            timeout.clearDeadline();
                        }
                        return read2;
                    } catch (Throwable th2) {
                        timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                        if (timeout2.hasDeadline()) {
                            timeout.clearDeadline();
                        }
                        throw th2;
                    }
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount < 0: ", Long.valueOf(j)).toString());
        }

        @Override // okio.Source
        @NotNull
        public Timeout timeout() {
            return this.h;
        }
    }

    static {
        Options.Companion companion = Options.Companion;
        ByteString.Companion companion2 = ByteString.Companion;
        p = companion.of(companion2.encodeUtf8("\r\n"), companion2.encodeUtf8("--"), companion2.encodeUtf8(HexStringBuilder.DEFAULT_SEPARATOR), companion2.encodeUtf8("\t"));
    }

    public MultipartReader(@NotNull BufferedSource source, @NotNull String boundary) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(boundary, "boundary");
        this.h = source;
        this.i = boundary;
        this.j = new Buffer().writeUtf8("--").writeUtf8(boundary).readByteString();
        this.k = new Buffer().writeUtf8("\r\n--").writeUtf8(boundary).readByteString();
    }

    public final long a(long j) {
        this.h.require(this.k.size());
        long indexOf = this.h.getBuffer().indexOf(this.k);
        if (indexOf == -1) {
            return Math.min(j, (this.h.getBuffer().size() - this.k.size()) + 1);
        }
        return Math.min(j, indexOf);
    }

    @JvmName(name = "boundary")
    @NotNull
    public final String boundary() {
        return this.i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.m) {
            return;
        }
        this.m = true;
        this.o = null;
        this.h.close();
    }

    @Nullable
    public final Part nextPart() throws IOException {
        if (!this.m) {
            if (this.n) {
                return null;
            }
            if (this.l == 0 && this.h.rangeEquals(0L, this.j)) {
                this.h.skip(this.j.size());
            } else {
                while (true) {
                    long a2 = a(PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                    if (a2 == 0) {
                        break;
                    }
                    this.h.skip(a2);
                }
                this.h.skip(this.k.size());
            }
            boolean z = false;
            while (true) {
                int select = this.h.select(p);
                if (select == -1) {
                    throw new ProtocolException("unexpected characters after boundary");
                }
                if (select == 0) {
                    this.l++;
                    Headers readHeaders = new HeadersReader(this.h).readHeaders();
                    a aVar = new a(this);
                    this.o = aVar;
                    return new Part(readHeaders, Okio.buffer(aVar));
                } else if (select == 1) {
                    if (!z) {
                        if (this.l != 0) {
                            this.n = true;
                            return null;
                        }
                        throw new ProtocolException("expected at least 1 part");
                    }
                    throw new ProtocolException("unexpected characters after boundary");
                } else if (select == 2 || select == 3) {
                    z = true;
                }
            }
        } else {
            throw new IllegalStateException("closed".toString());
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MultipartReader(@org.jetbrains.annotations.NotNull okhttp3.ResponseBody r3) throws java.io.IOException {
        /*
            r2 = this;
            java.lang.String r0 = "response"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            okio.BufferedSource r0 = r3.source()
            okhttp3.MediaType r3 = r3.contentType()
            if (r3 != 0) goto L11
            r3 = 0
            goto L17
        L11:
            java.lang.String r1 = "boundary"
            java.lang.String r3 = r3.parameter(r1)
        L17:
            if (r3 == 0) goto L1d
            r2.<init>(r0, r3)
            return
        L1d:
            java.net.ProtocolException r3 = new java.net.ProtocolException
            java.lang.String r0 = "expected the Content-Type to have a boundary parameter"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.MultipartReader.<init>(okhttp3.ResponseBody):void");
    }
}
