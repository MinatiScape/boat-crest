package okhttp3.internal.http2;

import com.polidea.rxandroidble2.ClientComponent;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Http2Stream {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final long EMIT_BUFFER_SIZE = 16384;

    /* renamed from: a  reason: collision with root package name */
    public final int f14284a;
    @NotNull
    public final Http2Connection b;
    public long c;
    public long d;
    public long e;
    public long f;
    @NotNull
    public final ArrayDeque<Headers> g;
    public boolean h;
    @NotNull
    public final FramingSource i;
    @NotNull
    public final FramingSink j;
    @NotNull
    public final StreamTimeout k;
    @NotNull
    public final StreamTimeout l;
    @Nullable
    public ErrorCode m;
    @Nullable
    public IOException n;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes12.dex */
    public final class FramingSource implements Source {
        public final long h;
        public boolean i;
        @NotNull
        public final Buffer j;
        @NotNull
        public final Buffer k;
        @Nullable
        public Headers l;
        public boolean m;
        public final /* synthetic */ Http2Stream n;

        public FramingSource(Http2Stream this$0, long j, boolean z) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.n = this$0;
            this.h = j;
            this.i = z;
            this.j = new Buffer();
            this.k = new Buffer();
        }

        public final void a(long j) {
            Http2Stream http2Stream = this.n;
            if (Util.assertionsEnabled && Thread.holdsLock(http2Stream)) {
                throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + http2Stream);
            }
            this.n.getConnection().updateConnectionFlowControl$okhttp(j);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            long size;
            Http2Stream http2Stream = this.n;
            synchronized (http2Stream) {
                setClosed$okhttp(true);
                size = getReadBuffer().size();
                getReadBuffer().clear();
                http2Stream.notifyAll();
                Unit unit = Unit.INSTANCE;
            }
            if (size > 0) {
                a(size);
            }
            this.n.cancelStreamIfNecessary$okhttp();
        }

        public final boolean getClosed$okhttp() {
            return this.m;
        }

        public final boolean getFinished$okhttp() {
            return this.i;
        }

        @NotNull
        public final Buffer getReadBuffer() {
            return this.k;
        }

        @NotNull
        public final Buffer getReceiveBuffer() {
            return this.j;
        }

        @Nullable
        public final Headers getTrailers() {
            return this.l;
        }

        /* JADX WARN: Code restructure failed: missing block: B:45:0x00d4, code lost:
            throw new java.io.IOException("stream closed");
         */
        @Override // okio.Source
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long read(@org.jetbrains.annotations.NotNull okio.Buffer r18, long r19) throws java.io.IOException {
            /*
                r17 = this;
                r1 = r17
                r0 = r18
                r2 = r19
                java.lang.String r4 = "sink"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
                r4 = 0
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 < 0) goto L13
                r6 = 1
                goto L14
            L13:
                r6 = 0
            L14:
                if (r6 == 0) goto Le1
            L16:
                r6 = 0
                okhttp3.internal.http2.Http2Stream r9 = r1.n
                monitor-enter(r9)
                okhttp3.internal.http2.Http2Stream$StreamTimeout r10 = r9.getReadTimeout$okhttp()     // Catch: java.lang.Throwable -> Lde
                r10.enter()     // Catch: java.lang.Throwable -> Lde
                okhttp3.internal.http2.ErrorCode r10 = r9.getErrorCode$okhttp()     // Catch: java.lang.Throwable -> Ld5
                if (r10 == 0) goto L3f
                boolean r10 = r17.getFinished$okhttp()     // Catch: java.lang.Throwable -> Ld5
                if (r10 != 0) goto L3f
                java.io.IOException r6 = r9.getErrorException$okhttp()     // Catch: java.lang.Throwable -> Ld5
                if (r6 != 0) goto L3f
                okhttp3.internal.http2.StreamResetException r6 = new okhttp3.internal.http2.StreamResetException     // Catch: java.lang.Throwable -> Ld5
                okhttp3.internal.http2.ErrorCode r10 = r9.getErrorCode$okhttp()     // Catch: java.lang.Throwable -> Ld5
                kotlin.jvm.internal.Intrinsics.checkNotNull(r10)     // Catch: java.lang.Throwable -> Ld5
                r6.<init>(r10)     // Catch: java.lang.Throwable -> Ld5
            L3f:
                boolean r10 = r17.getClosed$okhttp()     // Catch: java.lang.Throwable -> Ld5
                if (r10 != 0) goto Lcd
                okio.Buffer r10 = r17.getReadBuffer()     // Catch: java.lang.Throwable -> Ld5
                long r10 = r10.size()     // Catch: java.lang.Throwable -> Ld5
                int r10 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
                r11 = -1
                if (r10 <= 0) goto La1
                okio.Buffer r10 = r17.getReadBuffer()     // Catch: java.lang.Throwable -> Ld5
                okio.Buffer r13 = r17.getReadBuffer()     // Catch: java.lang.Throwable -> Ld5
                long r13 = r13.size()     // Catch: java.lang.Throwable -> Ld5
                long r13 = java.lang.Math.min(r2, r13)     // Catch: java.lang.Throwable -> Ld5
                long r13 = r10.read(r0, r13)     // Catch: java.lang.Throwable -> Ld5
                long r15 = r9.getReadBytesTotal()     // Catch: java.lang.Throwable -> Ld5
                long r4 = r15 + r13
                r9.setReadBytesTotal$okhttp(r4)     // Catch: java.lang.Throwable -> Ld5
                long r4 = r9.getReadBytesTotal()     // Catch: java.lang.Throwable -> Ld5
                long r15 = r9.getReadBytesAcknowledged()     // Catch: java.lang.Throwable -> Ld5
                long r4 = r4 - r15
                if (r6 != 0) goto Lb0
                okhttp3.internal.http2.Http2Connection r10 = r9.getConnection()     // Catch: java.lang.Throwable -> Ld5
                okhttp3.internal.http2.Settings r10 = r10.getOkHttpSettings()     // Catch: java.lang.Throwable -> Ld5
                int r10 = r10.getInitialWindowSize()     // Catch: java.lang.Throwable -> Ld5
                int r10 = r10 / 2
                long r7 = (long) r10     // Catch: java.lang.Throwable -> Ld5
                int r7 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r7 < 0) goto Lb0
                okhttp3.internal.http2.Http2Connection r7 = r9.getConnection()     // Catch: java.lang.Throwable -> Ld5
                int r8 = r9.getId()     // Catch: java.lang.Throwable -> Ld5
                r7.writeWindowUpdateLater$okhttp(r8, r4)     // Catch: java.lang.Throwable -> Ld5
                long r4 = r9.getReadBytesTotal()     // Catch: java.lang.Throwable -> Ld5
                r9.setReadBytesAcknowledged$okhttp(r4)     // Catch: java.lang.Throwable -> Ld5
                goto Lb0
            La1:
                boolean r4 = r17.getFinished$okhttp()     // Catch: java.lang.Throwable -> Ld5
                if (r4 != 0) goto Laf
                if (r6 != 0) goto Laf
                r9.waitForIo$okhttp()     // Catch: java.lang.Throwable -> Ld5
                r13 = r11
                r4 = 1
                goto Lb1
            Laf:
                r13 = r11
            Lb0:
                r4 = 0
            Lb1:
                okhttp3.internal.http2.Http2Stream$StreamTimeout r5 = r9.getReadTimeout$okhttp()     // Catch: java.lang.Throwable -> Lde
                r5.exitAndThrowIfTimedOut()     // Catch: java.lang.Throwable -> Lde
                kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lde
                monitor-exit(r9)
                if (r4 == 0) goto Lc1
                r4 = 0
                goto L16
            Lc1:
                int r0 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
                if (r0 == 0) goto Lc9
                r1.a(r13)
                return r13
            Lc9:
                if (r6 != 0) goto Lcc
                return r11
            Lcc:
                throw r6
            Lcd:
                java.io.IOException r0 = new java.io.IOException     // Catch: java.lang.Throwable -> Ld5
                java.lang.String r2 = "stream closed"
                r0.<init>(r2)     // Catch: java.lang.Throwable -> Ld5
                throw r0     // Catch: java.lang.Throwable -> Ld5
            Ld5:
                r0 = move-exception
                okhttp3.internal.http2.Http2Stream$StreamTimeout r2 = r9.getReadTimeout$okhttp()     // Catch: java.lang.Throwable -> Lde
                r2.exitAndThrowIfTimedOut()     // Catch: java.lang.Throwable -> Lde
                throw r0     // Catch: java.lang.Throwable -> Lde
            Lde:
                r0 = move-exception
                monitor-exit(r9)
                throw r0
            Le1:
                java.lang.String r0 = "byteCount < 0: "
                java.lang.Long r2 = java.lang.Long.valueOf(r19)
                java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r2)
                java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
                java.lang.String r0 = r0.toString()
                r2.<init>(r0)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSource.read(okio.Buffer, long):long");
        }

        public final void receive$okhttp(@NotNull BufferedSource source, long j) throws IOException {
            boolean finished$okhttp;
            boolean z;
            boolean z2;
            long j2;
            Intrinsics.checkNotNullParameter(source, "source");
            Http2Stream http2Stream = this.n;
            if (Util.assertionsEnabled && Thread.holdsLock(http2Stream)) {
                throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + http2Stream);
            }
            while (j > 0) {
                synchronized (this.n) {
                    finished$okhttp = getFinished$okhttp();
                    z = true;
                    z2 = getReadBuffer().size() + j > this.h;
                    Unit unit = Unit.INSTANCE;
                }
                if (z2) {
                    source.skip(j);
                    this.n.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                } else if (finished$okhttp) {
                    source.skip(j);
                    return;
                } else {
                    long read = source.read(this.j, j);
                    if (read != -1) {
                        j -= read;
                        Http2Stream http2Stream2 = this.n;
                        synchronized (http2Stream2) {
                            if (getClosed$okhttp()) {
                                j2 = getReceiveBuffer().size();
                                getReceiveBuffer().clear();
                            } else {
                                if (getReadBuffer().size() != 0) {
                                    z = false;
                                }
                                getReadBuffer().writeAll(getReceiveBuffer());
                                if (z) {
                                    http2Stream2.notifyAll();
                                }
                                j2 = 0;
                            }
                        }
                        if (j2 > 0) {
                            a(j2);
                        }
                    } else {
                        throw new EOFException();
                    }
                }
            }
        }

        public final void setClosed$okhttp(boolean z) {
            this.m = z;
        }

        public final void setFinished$okhttp(boolean z) {
            this.i = z;
        }

        public final void setTrailers(@Nullable Headers headers) {
            this.l = headers;
        }

        @Override // okio.Source
        @NotNull
        public Timeout timeout() {
            return this.n.getReadTimeout$okhttp();
        }
    }

    /* loaded from: classes12.dex */
    public final class StreamTimeout extends AsyncTimeout {
        public final /* synthetic */ Http2Stream j;

        public StreamTimeout(Http2Stream this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.j = this$0;
        }

        public final void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }

        @Override // okio.AsyncTimeout
        @NotNull
        public IOException newTimeoutException(@Nullable IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException(ClientComponent.NamedSchedulers.TIMEOUT);
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // okio.AsyncTimeout
        public void timedOut() {
            this.j.closeLater(ErrorCode.CANCEL);
            this.j.getConnection().sendDegradedPingLater$okhttp();
        }
    }

    public Http2Stream(int i, @NotNull Http2Connection connection, boolean z, boolean z2, @Nullable Headers headers) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        this.f14284a = i;
        this.b = connection;
        this.f = connection.getPeerSettings().getInitialWindowSize();
        ArrayDeque<Headers> arrayDeque = new ArrayDeque<>();
        this.g = arrayDeque;
        this.i = new FramingSource(this, connection.getOkHttpSettings().getInitialWindowSize(), z2);
        this.j = new FramingSink(this, z);
        this.k = new StreamTimeout(this);
        this.l = new StreamTimeout(this);
        if (headers != null) {
            if (!isLocallyInitiated()) {
                arrayDeque.add(headers);
                return;
            }
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet".toString());
        } else if (!isLocallyInitiated()) {
            throw new IllegalStateException("remotely-initiated streams should have headers".toString());
        }
    }

    public final boolean a(ErrorCode errorCode, IOException iOException) {
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
        }
        synchronized (this) {
            if (getErrorCode$okhttp() != null) {
                return false;
            }
            if (getSource$okhttp().getFinished$okhttp() && getSink$okhttp().getFinished()) {
                return false;
            }
            setErrorCode$okhttp(errorCode);
            setErrorException$okhttp(iOException);
            notifyAll();
            Unit unit = Unit.INSTANCE;
            this.b.removeStream$okhttp(this.f14284a);
            return true;
        }
    }

    public final void addBytesToWriteWindow(long j) {
        this.f += j;
        if (j > 0) {
            notifyAll();
        }
    }

    public final void cancelStreamIfNecessary$okhttp() throws IOException {
        boolean z;
        boolean isOpen;
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
        }
        synchronized (this) {
            z = !getSource$okhttp().getFinished$okhttp() && getSource$okhttp().getClosed$okhttp() && (getSink$okhttp().getFinished() || getSink$okhttp().getClosed());
            isOpen = isOpen();
            Unit unit = Unit.INSTANCE;
        }
        if (z) {
            close(ErrorCode.CANCEL, null);
        } else if (isOpen) {
        } else {
            this.b.removeStream$okhttp(this.f14284a);
        }
    }

    public final void checkOutNotClosed$okhttp() throws IOException {
        if (!this.j.getClosed()) {
            if (!this.j.getFinished()) {
                if (this.m != null) {
                    IOException iOException = this.n;
                    if (iOException != null) {
                        throw iOException;
                    }
                    ErrorCode errorCode = this.m;
                    Intrinsics.checkNotNull(errorCode);
                    throw new StreamResetException(errorCode);
                }
                return;
            }
            throw new IOException("stream finished");
        }
        throw new IOException("stream closed");
    }

    public final void close(@NotNull ErrorCode rstStatusCode, @Nullable IOException iOException) throws IOException {
        Intrinsics.checkNotNullParameter(rstStatusCode, "rstStatusCode");
        if (a(rstStatusCode, iOException)) {
            this.b.writeSynReset$okhttp(this.f14284a, rstStatusCode);
        }
    }

    public final void closeLater(@NotNull ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        if (a(errorCode, null)) {
            this.b.writeSynResetLater$okhttp(this.f14284a, errorCode);
        }
    }

    public final void enqueueTrailers(@NotNull Headers trailers) {
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        synchronized (this) {
            boolean z = true;
            if (!getSink$okhttp().getFinished()) {
                if (trailers.size() == 0) {
                    z = false;
                }
                if (z) {
                    getSink$okhttp().setTrailers(trailers);
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalArgumentException("trailers.size() == 0".toString());
                }
            } else {
                throw new IllegalStateException("already finished".toString());
            }
        }
    }

    @NotNull
    public final Http2Connection getConnection() {
        return this.b;
    }

    @Nullable
    public final synchronized ErrorCode getErrorCode$okhttp() {
        return this.m;
    }

    @Nullable
    public final IOException getErrorException$okhttp() {
        return this.n;
    }

    public final int getId() {
        return this.f14284a;
    }

    public final long getReadBytesAcknowledged() {
        return this.d;
    }

    public final long getReadBytesTotal() {
        return this.c;
    }

    @NotNull
    public final StreamTimeout getReadTimeout$okhttp() {
        return this.k;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0011 A[Catch: all -> 0x0023, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:11:0x0011, B:15:0x0017, B:16:0x0022), top: B:20:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0017 A[Catch: all -> 0x0023, TRY_ENTER, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:11:0x0011, B:15:0x0017, B:16:0x0022), top: B:20:0x0001 }] */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final okio.Sink getSink() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.h     // Catch: java.lang.Throwable -> L23
            if (r0 != 0) goto Le
            boolean r0 = r2.isLocallyInitiated()     // Catch: java.lang.Throwable -> L23
            if (r0 == 0) goto Lc
            goto Le
        Lc:
            r0 = 0
            goto Lf
        Le:
            r0 = 1
        Lf:
            if (r0 == 0) goto L17
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L23
            monitor-exit(r2)
            okhttp3.internal.http2.Http2Stream$FramingSink r0 = r2.j
            return r0
        L17:
            java.lang.String r0 = "reply before requesting the sink"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L23
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L23
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L23
            throw r1     // Catch: java.lang.Throwable -> L23
        L23:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.getSink():okio.Sink");
    }

    @NotNull
    public final FramingSink getSink$okhttp() {
        return this.j;
    }

    @NotNull
    public final Source getSource() {
        return this.i;
    }

    @NotNull
    public final FramingSource getSource$okhttp() {
        return this.i;
    }

    public final long getWriteBytesMaximum() {
        return this.f;
    }

    public final long getWriteBytesTotal() {
        return this.e;
    }

    @NotNull
    public final StreamTimeout getWriteTimeout$okhttp() {
        return this.l;
    }

    public final boolean isLocallyInitiated() {
        return this.b.getClient$okhttp() == ((this.f14284a & 1) == 1);
    }

    public final synchronized boolean isOpen() {
        if (this.m != null) {
            return false;
        }
        if ((this.i.getFinished$okhttp() || this.i.getClosed$okhttp()) && (this.j.getFinished() || this.j.getClosed())) {
            if (this.h) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public final Timeout readTimeout() {
        return this.k;
    }

    public final void receiveData(@NotNull BufferedSource source, int i) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
        }
        this.i.receive$okhttp(source, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0051 A[Catch: all -> 0x006c, TryCatch #0 {, blocks: (B:10:0x0038, B:14:0x0040, B:17:0x0051, B:18:0x0058, B:15:0x0048), top: B:26:0x0038 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void receiveHeaders(@org.jetbrains.annotations.NotNull okhttp3.Headers r3, boolean r4) {
        /*
            r2 = this;
            java.lang.String r0 = "headers"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            boolean r0 = okhttp3.internal.Util.assertionsEnabled
            if (r0 == 0) goto L37
            boolean r0 = java.lang.Thread.holdsLock(r2)
            if (r0 != 0) goto L10
            goto L37
        L10:
            java.lang.AssertionError r3 = new java.lang.AssertionError
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "Thread "
            r4.append(r0)
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            java.lang.String r0 = r0.getName()
            r4.append(r0)
            java.lang.String r0 = " MUST NOT hold lock on "
            r4.append(r0)
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L37:
            monitor-enter(r2)
            boolean r0 = r2.h     // Catch: java.lang.Throwable -> L6c
            r1 = 1
            if (r0 == 0) goto L48
            if (r4 != 0) goto L40
            goto L48
        L40:
            okhttp3.internal.http2.Http2Stream$FramingSource r0 = r2.getSource$okhttp()     // Catch: java.lang.Throwable -> L6c
            r0.setTrailers(r3)     // Catch: java.lang.Throwable -> L6c
            goto L4f
        L48:
            r2.h = r1     // Catch: java.lang.Throwable -> L6c
            java.util.ArrayDeque<okhttp3.Headers> r0 = r2.g     // Catch: java.lang.Throwable -> L6c
            r0.add(r3)     // Catch: java.lang.Throwable -> L6c
        L4f:
            if (r4 == 0) goto L58
            okhttp3.internal.http2.Http2Stream$FramingSource r3 = r2.getSource$okhttp()     // Catch: java.lang.Throwable -> L6c
            r3.setFinished$okhttp(r1)     // Catch: java.lang.Throwable -> L6c
        L58:
            boolean r3 = r2.isOpen()     // Catch: java.lang.Throwable -> L6c
            r2.notifyAll()     // Catch: java.lang.Throwable -> L6c
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L6c
            monitor-exit(r2)
            if (r3 != 0) goto L6b
            okhttp3.internal.http2.Http2Connection r3 = r2.b
            int r4 = r2.f14284a
            r3.removeStream$okhttp(r4)
        L6b:
            return
        L6c:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.receiveHeaders(okhttp3.Headers, boolean):void");
    }

    public final synchronized void receiveRstStream(@NotNull ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        if (this.m == null) {
            this.m = errorCode;
            notifyAll();
        }
    }

    public final void setErrorCode$okhttp(@Nullable ErrorCode errorCode) {
        this.m = errorCode;
    }

    public final void setErrorException$okhttp(@Nullable IOException iOException) {
        this.n = iOException;
    }

    public final void setReadBytesAcknowledged$okhttp(long j) {
        this.d = j;
    }

    public final void setReadBytesTotal$okhttp(long j) {
        this.c = j;
    }

    public final void setWriteBytesMaximum$okhttp(long j) {
        this.f = j;
    }

    public final void setWriteBytesTotal$okhttp(long j) {
        this.e = j;
    }

    @NotNull
    public final synchronized Headers takeHeaders() throws IOException {
        Headers removeFirst;
        this.k.enter();
        while (this.g.isEmpty() && this.m == null) {
            waitForIo$okhttp();
        }
        this.k.exitAndThrowIfTimedOut();
        if (!this.g.isEmpty()) {
            removeFirst = this.g.removeFirst();
            Intrinsics.checkNotNullExpressionValue(removeFirst, "headersQueue.removeFirst()");
        } else {
            IOException iOException = this.n;
            if (iOException == null) {
                ErrorCode errorCode = this.m;
                Intrinsics.checkNotNull(errorCode);
                throw new StreamResetException(errorCode);
            }
            throw iOException;
        }
        return removeFirst;
    }

    @NotNull
    public final synchronized Headers trailers() throws IOException {
        Headers trailers;
        if (this.i.getFinished$okhttp() && this.i.getReceiveBuffer().exhausted() && this.i.getReadBuffer().exhausted()) {
            trailers = this.i.getTrailers();
            if (trailers == null) {
                trailers = Util.EMPTY_HEADERS;
            }
        } else if (this.m != null) {
            IOException iOException = this.n;
            if (iOException == null) {
                ErrorCode errorCode = this.m;
                Intrinsics.checkNotNull(errorCode);
                throw new StreamResetException(errorCode);
            }
            throw iOException;
        } else {
            throw new IllegalStateException("too early; can't read the trailers yet");
        }
        return trailers;
    }

    public final void waitForIo$okhttp() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public final void writeHeaders(@NotNull List<Header> responseHeaders, boolean z, boolean z2) throws IOException {
        boolean z3;
        Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
        }
        synchronized (this) {
            this.h = true;
            if (z) {
                getSink$okhttp().setFinished(true);
            }
            Unit unit = Unit.INSTANCE;
        }
        if (!z2) {
            synchronized (this.b) {
                z3 = getConnection().getWriteBytesTotal() >= getConnection().getWriteBytesMaximum();
            }
            z2 = z3;
        }
        this.b.writeHeaders$okhttp(this.f14284a, z, responseHeaders);
        if (z2) {
            this.b.flush();
        }
    }

    @NotNull
    public final Timeout writeTimeout() {
        return this.l;
    }

    /* loaded from: classes12.dex */
    public final class FramingSink implements Sink {
        public boolean h;
        @NotNull
        public final Buffer i;
        @Nullable
        public Headers j;
        public boolean k;

        public FramingSink(Http2Stream this$0, boolean z) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Http2Stream.this = this$0;
            this.h = z;
            this.i = new Buffer();
        }

        public final void a(boolean z) throws IOException {
            long min;
            boolean z2;
            Http2Stream http2Stream = Http2Stream.this;
            synchronized (http2Stream) {
                http2Stream.getWriteTimeout$okhttp().enter();
                while (http2Stream.getWriteBytesTotal() >= http2Stream.getWriteBytesMaximum() && !getFinished() && !getClosed() && http2Stream.getErrorCode$okhttp() == null) {
                    http2Stream.waitForIo$okhttp();
                }
                http2Stream.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
                http2Stream.checkOutNotClosed$okhttp();
                min = Math.min(http2Stream.getWriteBytesMaximum() - http2Stream.getWriteBytesTotal(), this.i.size());
                http2Stream.setWriteBytesTotal$okhttp(http2Stream.getWriteBytesTotal() + min);
                z2 = z && min == this.i.size();
                Unit unit = Unit.INSTANCE;
            }
            Http2Stream.this.getWriteTimeout$okhttp().enter();
            try {
                Http2Stream.this.getConnection().writeData(Http2Stream.this.getId(), z2, this.i, min);
            } finally {
                Http2Stream.this.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
            }
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            Http2Stream http2Stream = Http2Stream.this;
            if (Util.assertionsEnabled && Thread.holdsLock(http2Stream)) {
                throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + http2Stream);
            }
            Http2Stream http2Stream2 = Http2Stream.this;
            synchronized (http2Stream2) {
                if (getClosed()) {
                    return;
                }
                boolean z = http2Stream2.getErrorCode$okhttp() == null;
                Unit unit = Unit.INSTANCE;
                if (!Http2Stream.this.getSink$okhttp().h) {
                    boolean z2 = this.i.size() > 0;
                    if (this.j != null) {
                        while (this.i.size() > 0) {
                            a(false);
                        }
                        Http2Connection connection = Http2Stream.this.getConnection();
                        int id = Http2Stream.this.getId();
                        Headers headers = this.j;
                        Intrinsics.checkNotNull(headers);
                        connection.writeHeaders$okhttp(id, z, Util.toHeaderList(headers));
                    } else if (z2) {
                        while (this.i.size() > 0) {
                            a(true);
                        }
                    } else if (z) {
                        Http2Stream.this.getConnection().writeData(Http2Stream.this.getId(), true, null, 0L);
                    }
                }
                synchronized (Http2Stream.this) {
                    setClosed(true);
                    Unit unit2 = Unit.INSTANCE;
                }
                Http2Stream.this.getConnection().flush();
                Http2Stream.this.cancelStreamIfNecessary$okhttp();
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            Http2Stream http2Stream = Http2Stream.this;
            if (Util.assertionsEnabled && Thread.holdsLock(http2Stream)) {
                throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + http2Stream);
            }
            Http2Stream http2Stream2 = Http2Stream.this;
            synchronized (http2Stream2) {
                http2Stream2.checkOutNotClosed$okhttp();
                Unit unit = Unit.INSTANCE;
            }
            while (this.i.size() > 0) {
                a(false);
                Http2Stream.this.getConnection().flush();
            }
        }

        public final boolean getClosed() {
            return this.k;
        }

        public final boolean getFinished() {
            return this.h;
        }

        @Nullable
        public final Headers getTrailers() {
            return this.j;
        }

        public final void setClosed(boolean z) {
            this.k = z;
        }

        public final void setFinished(boolean z) {
            this.h = z;
        }

        public final void setTrailers(@Nullable Headers headers) {
            this.j = headers;
        }

        @Override // okio.Sink
        @NotNull
        public Timeout timeout() {
            return Http2Stream.this.getWriteTimeout$okhttp();
        }

        @Override // okio.Sink
        public void write(@NotNull Buffer source, long j) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            Http2Stream http2Stream = Http2Stream.this;
            if (Util.assertionsEnabled && Thread.holdsLock(http2Stream)) {
                throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + http2Stream);
            }
            this.i.write(source, j);
            while (this.i.size() >= 16384) {
                a(false);
            }
        }

        public /* synthetic */ FramingSink(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(Http2Stream.this, (i & 1) != 0 ? false : z);
        }
    }
}
