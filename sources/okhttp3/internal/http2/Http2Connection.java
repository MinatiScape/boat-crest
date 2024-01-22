package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskQueue$execute$1;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.Http2Reader;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Http2Connection implements Closeable {
    public static final int AWAIT_PING = 3;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int DEGRADED_PING = 2;
    public static final int DEGRADED_PONG_TIMEOUT_NS = 1000000000;
    public static final int INTERVAL_PING = 1;
    @NotNull
    public static final Settings K;
    public static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    @NotNull
    public final Settings A;
    @NotNull
    public Settings B;
    public long C;
    public long D;
    public long E;
    public long F;
    @NotNull
    public final Socket G;
    @NotNull
    public final Http2Writer H;
    @NotNull
    public final ReaderRunnable I;
    @NotNull
    public final Set<Integer> J;
    public final boolean h;
    @NotNull
    public final Listener i;
    @NotNull
    public final Map<Integer, Http2Stream> j;
    @NotNull
    public final String k;
    public int l;
    public int m;
    public boolean n;
    @NotNull
    public final TaskRunner o;
    @NotNull
    public final TaskQueue p;
    @NotNull
    public final TaskQueue q;
    @NotNull
    public final TaskQueue r;
    @NotNull
    public final PushObserver s;
    public long t;
    public long u;
    public long v;
    public long w;
    public long x;
    public long y;
    public long z;

    /* loaded from: classes12.dex */
    public static final class Builder {

        /* renamed from: a */
        public boolean f14282a;
        @NotNull
        public final TaskRunner b;
        @NotNull
        public Listener c;
        public String connectionName;
        @NotNull
        public PushObserver d;
        public int e;
        public BufferedSink sink;
        public Socket socket;
        public BufferedSource source;

        public Builder(boolean z, @NotNull TaskRunner taskRunner) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            this.f14282a = z;
            this.b = taskRunner;
            this.c = Listener.REFUSE_INCOMING_STREAMS;
            this.d = PushObserver.CANCEL;
        }

        public static /* synthetic */ Builder socket$default(Builder builder, Socket socket, String str, BufferedSource bufferedSource, BufferedSink bufferedSink, int i, Object obj) throws IOException {
            if ((i & 2) != 0) {
                str = Util.peerName(socket);
            }
            if ((i & 4) != 0) {
                bufferedSource = Okio.buffer(Okio.source(socket));
            }
            if ((i & 8) != 0) {
                bufferedSink = Okio.buffer(Okio.sink(socket));
            }
            return builder.socket(socket, str, bufferedSource, bufferedSink);
        }

        @NotNull
        public final Http2Connection build() {
            return new Http2Connection(this);
        }

        public final boolean getClient$okhttp() {
            return this.f14282a;
        }

        @NotNull
        public final String getConnectionName$okhttp() {
            String str = this.connectionName;
            if (str != null) {
                return str;
            }
            Intrinsics.throwUninitializedPropertyAccessException("connectionName");
            return null;
        }

        @NotNull
        public final Listener getListener$okhttp() {
            return this.c;
        }

        public final int getPingIntervalMillis$okhttp() {
            return this.e;
        }

        @NotNull
        public final PushObserver getPushObserver$okhttp() {
            return this.d;
        }

        @NotNull
        public final BufferedSink getSink$okhttp() {
            BufferedSink bufferedSink = this.sink;
            if (bufferedSink != null) {
                return bufferedSink;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sink");
            return null;
        }

        @NotNull
        public final Socket getSocket$okhttp() {
            Socket socket = this.socket;
            if (socket != null) {
                return socket;
            }
            Intrinsics.throwUninitializedPropertyAccessException("socket");
            return null;
        }

        @NotNull
        public final BufferedSource getSource$okhttp() {
            BufferedSource bufferedSource = this.source;
            if (bufferedSource != null) {
                return bufferedSource;
            }
            Intrinsics.throwUninitializedPropertyAccessException("source");
            return null;
        }

        @NotNull
        public final TaskRunner getTaskRunner$okhttp() {
            return this.b;
        }

        @NotNull
        public final Builder listener(@NotNull Listener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            setListener$okhttp(listener);
            return this;
        }

        @NotNull
        public final Builder pingIntervalMillis(int i) {
            setPingIntervalMillis$okhttp(i);
            return this;
        }

        @NotNull
        public final Builder pushObserver(@NotNull PushObserver pushObserver) {
            Intrinsics.checkNotNullParameter(pushObserver, "pushObserver");
            setPushObserver$okhttp(pushObserver);
            return this;
        }

        public final void setClient$okhttp(boolean z) {
            this.f14282a = z;
        }

        public final void setConnectionName$okhttp(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.connectionName = str;
        }

        public final void setListener$okhttp(@NotNull Listener listener) {
            Intrinsics.checkNotNullParameter(listener, "<set-?>");
            this.c = listener;
        }

        public final void setPingIntervalMillis$okhttp(int i) {
            this.e = i;
        }

        public final void setPushObserver$okhttp(@NotNull PushObserver pushObserver) {
            Intrinsics.checkNotNullParameter(pushObserver, "<set-?>");
            this.d = pushObserver;
        }

        public final void setSink$okhttp(@NotNull BufferedSink bufferedSink) {
            Intrinsics.checkNotNullParameter(bufferedSink, "<set-?>");
            this.sink = bufferedSink;
        }

        public final void setSocket$okhttp(@NotNull Socket socket) {
            Intrinsics.checkNotNullParameter(socket, "<set-?>");
            this.socket = socket;
        }

        public final void setSource$okhttp(@NotNull BufferedSource bufferedSource) {
            Intrinsics.checkNotNullParameter(bufferedSource, "<set-?>");
            this.source = bufferedSource;
        }

        @JvmOverloads
        @NotNull
        public final Builder socket(@NotNull Socket socket) throws IOException {
            Intrinsics.checkNotNullParameter(socket, "socket");
            return socket$default(this, socket, null, null, null, 14, null);
        }

        @JvmOverloads
        @NotNull
        public final Builder socket(@NotNull Socket socket, @NotNull String peerName) throws IOException {
            Intrinsics.checkNotNullParameter(socket, "socket");
            Intrinsics.checkNotNullParameter(peerName, "peerName");
            return socket$default(this, socket, peerName, null, null, 12, null);
        }

        @JvmOverloads
        @NotNull
        public final Builder socket(@NotNull Socket socket, @NotNull String peerName, @NotNull BufferedSource source) throws IOException {
            Intrinsics.checkNotNullParameter(socket, "socket");
            Intrinsics.checkNotNullParameter(peerName, "peerName");
            Intrinsics.checkNotNullParameter(source, "source");
            return socket$default(this, socket, peerName, source, null, 8, null);
        }

        @JvmOverloads
        @NotNull
        public final Builder socket(@NotNull Socket socket, @NotNull String peerName, @NotNull BufferedSource source, @NotNull BufferedSink sink) throws IOException {
            String stringPlus;
            Intrinsics.checkNotNullParameter(socket, "socket");
            Intrinsics.checkNotNullParameter(peerName, "peerName");
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(sink, "sink");
            setSocket$okhttp(socket);
            if (getClient$okhttp()) {
                stringPlus = Util.okHttpName + ' ' + peerName;
            } else {
                stringPlus = Intrinsics.stringPlus("MockWebServer ", peerName);
            }
            setConnectionName$okhttp(stringPlus);
            setSource$okhttp(source);
            setSink$okhttp(sink);
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Settings getDEFAULT_SETTINGS() {
            return Http2Connection.K;
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class Listener {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @JvmField
        @NotNull
        public static final Listener REFUSE_INCOMING_STREAMS = new Listener() { // from class: okhttp3.internal.http2.Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1
            @Override // okhttp3.internal.http2.Http2Connection.Listener
            public void onStream(@NotNull Http2Stream stream) throws IOException {
                Intrinsics.checkNotNullParameter(stream, "stream");
                stream.close(ErrorCode.REFUSED_STREAM, null);
            }
        };

        /* loaded from: classes12.dex */
        public static final class Companion {
            public Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public void onSettings(@NotNull Http2Connection connection, @NotNull Settings settings) {
            Intrinsics.checkNotNullParameter(connection, "connection");
            Intrinsics.checkNotNullParameter(settings, "settings");
        }

        public abstract void onStream(@NotNull Http2Stream http2Stream) throws IOException;
    }

    /* loaded from: classes12.dex */
    public final class ReaderRunnable implements Http2Reader.Handler, Function0<Unit> {
        @NotNull
        public final Http2Reader h;
        public final /* synthetic */ Http2Connection i;

        public ReaderRunnable(@NotNull Http2Connection this$0, Http2Reader reader) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(reader, "reader");
            this.i = this$0;
            this.h = reader;
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void ackSettings() {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void alternateService(int i, @NotNull String origin, @NotNull ByteString protocol, @NotNull String host, int i2, long j) {
            Intrinsics.checkNotNullParameter(origin, "origin");
            Intrinsics.checkNotNullParameter(protocol, "protocol");
            Intrinsics.checkNotNullParameter(host, "host");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void applyAndAckSettings(boolean z, @NotNull Settings settings) {
            T t;
            long initialWindowSize;
            int i;
            Http2Stream[] http2StreamArr;
            Intrinsics.checkNotNullParameter(settings, "settings");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Http2Writer writer = this.i.getWriter();
            final Http2Connection http2Connection = this.i;
            synchronized (writer) {
                synchronized (http2Connection) {
                    Settings peerSettings = http2Connection.getPeerSettings();
                    if (z) {
                        t = settings;
                    } else {
                        Settings settings2 = new Settings();
                        settings2.merge(peerSettings);
                        settings2.merge(settings);
                        t = settings2;
                    }
                    objectRef.element = t;
                    initialWindowSize = ((Settings) t).getInitialWindowSize() - peerSettings.getInitialWindowSize();
                    i = 0;
                    if (initialWindowSize != 0 && !http2Connection.getStreams$okhttp().isEmpty()) {
                        Object[] array = http2Connection.getStreams$okhttp().values().toArray(new Http2Stream[0]);
                        if (array == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                        }
                        http2StreamArr = (Http2Stream[]) array;
                        http2Connection.setPeerSettings((Settings) objectRef.element);
                        http2Connection.r.schedule(new Task(Intrinsics.stringPlus(http2Connection.getConnectionName$okhttp(), " onSettings"), true) { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$applyAndAckSettings$lambda-7$lambda-6$$inlined$execute$default$1
                            @Override // okhttp3.internal.concurrent.Task
                            public long runOnce() {
                                http2Connection.getListener$okhttp().onSettings(http2Connection, (Settings) objectRef.element);
                                return -1L;
                            }
                        }, 0L);
                        Unit unit = Unit.INSTANCE;
                    }
                    http2StreamArr = null;
                    http2Connection.setPeerSettings((Settings) objectRef.element);
                    http2Connection.r.schedule(new Task(Intrinsics.stringPlus(http2Connection.getConnectionName$okhttp(), " onSettings"), true) { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$applyAndAckSettings$lambda-7$lambda-6$$inlined$execute$default$1
                        @Override // okhttp3.internal.concurrent.Task
                        public long runOnce() {
                            http2Connection.getListener$okhttp().onSettings(http2Connection, (Settings) objectRef.element);
                            return -1L;
                        }
                    }, 0L);
                    Unit unit2 = Unit.INSTANCE;
                }
                try {
                    http2Connection.getWriter().applyAndAckSettings((Settings) objectRef.element);
                } catch (IOException e) {
                    http2Connection.a(e);
                }
                Unit unit3 = Unit.INSTANCE;
            }
            if (http2StreamArr != null) {
                int length = http2StreamArr.length;
                while (i < length) {
                    Http2Stream http2Stream = http2StreamArr[i];
                    i++;
                    synchronized (http2Stream) {
                        http2Stream.addBytesToWriteWindow(initialWindowSize);
                        Unit unit4 = Unit.INSTANCE;
                    }
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void data(boolean z, int i, @NotNull BufferedSource source, int i2) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            if (this.i.pushedStream$okhttp(i)) {
                this.i.pushDataLater$okhttp(i, source, i2, z);
                return;
            }
            Http2Stream stream = this.i.getStream(i);
            if (stream == null) {
                this.i.writeSynResetLater$okhttp(i, ErrorCode.PROTOCOL_ERROR);
                long j = i2;
                this.i.updateConnectionFlowControl$okhttp(j);
                source.skip(j);
                return;
            }
            stream.receiveData(source, i2);
            if (z) {
                stream.receiveHeaders(Util.EMPTY_HEADERS, true);
            }
        }

        @NotNull
        public final Http2Reader getReader$okhttp() {
            return this.h;
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void goAway(int i, @NotNull ErrorCode errorCode, @NotNull ByteString debugData) {
            int i2;
            Object[] array;
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            Intrinsics.checkNotNullParameter(debugData, "debugData");
            debugData.size();
            Http2Connection http2Connection = this.i;
            synchronized (http2Connection) {
                i2 = 0;
                array = http2Connection.getStreams$okhttp().values().toArray(new Http2Stream[0]);
                if (array != null) {
                    http2Connection.n = true;
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
            }
            Http2Stream[] http2StreamArr = (Http2Stream[]) array;
            int length = http2StreamArr.length;
            while (i2 < length) {
                Http2Stream http2Stream = http2StreamArr[i2];
                i2++;
                if (http2Stream.getId() > i && http2Stream.isLocallyInitiated()) {
                    http2Stream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    this.i.removeStream$okhttp(http2Stream.getId());
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void headers(boolean z, int i, int i2, @NotNull List<Header> headerBlock) {
            Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
            if (this.i.pushedStream$okhttp(i)) {
                this.i.pushHeadersLater$okhttp(i, headerBlock, z);
                return;
            }
            final Http2Connection http2Connection = this.i;
            synchronized (http2Connection) {
                Http2Stream stream = http2Connection.getStream(i);
                if (stream == null) {
                    if (http2Connection.n) {
                        return;
                    }
                    if (i <= http2Connection.getLastGoodStreamId$okhttp()) {
                        return;
                    }
                    if (i % 2 == http2Connection.getNextStreamId$okhttp() % 2) {
                        return;
                    }
                    final Http2Stream http2Stream = new Http2Stream(i, http2Connection, false, z, Util.toHeaders(headerBlock));
                    http2Connection.setLastGoodStreamId$okhttp(i);
                    http2Connection.getStreams$okhttp().put(Integer.valueOf(i), http2Stream);
                    TaskQueue newQueue = http2Connection.o.newQueue();
                    newQueue.schedule(new Task(http2Connection.getConnectionName$okhttp() + '[' + i + "] onStream", true) { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$headers$lambda-2$$inlined$execute$default$1
                        @Override // okhttp3.internal.concurrent.Task
                        public long runOnce() {
                            try {
                                http2Connection.getListener$okhttp().onStream(http2Stream);
                                return -1L;
                            } catch (IOException e) {
                                Platform.Companion.get().log(Intrinsics.stringPlus("Http2Connection.Listener failure for ", http2Connection.getConnectionName$okhttp()), 4, e);
                                try {
                                    http2Stream.close(ErrorCode.PROTOCOL_ERROR, e);
                                    return -1L;
                                } catch (IOException unused) {
                                    return -1L;
                                }
                            }
                        }
                    }, 0L);
                    return;
                }
                Unit unit = Unit.INSTANCE;
                stream.receiveHeaders(Util.toHeaders(headerBlock), z);
            }
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void ping(boolean z, final int i, final int i2) {
            if (z) {
                Http2Connection http2Connection = this.i;
                synchronized (http2Connection) {
                    if (i == 1) {
                        http2Connection.u++;
                    } else if (i == 2) {
                        http2Connection.w++;
                    } else {
                        if (i == 3) {
                            http2Connection.y++;
                            http2Connection.notifyAll();
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                }
                return;
            }
            TaskQueue taskQueue = this.i.p;
            String stringPlus = Intrinsics.stringPlus(this.i.getConnectionName$okhttp(), " ping");
            final Http2Connection http2Connection2 = this.i;
            taskQueue.schedule(new Task(stringPlus, true) { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$ping$$inlined$execute$default$1
                @Override // okhttp3.internal.concurrent.Task
                public long runOnce() {
                    http2Connection2.writePing(true, i, i2);
                    return -1L;
                }
            }, 0L);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void priority(int i, int i2, int i3, boolean z) {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void pushPromise(int i, int i2, @NotNull List<Header> requestHeaders) {
            Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
            this.i.pushRequestLater$okhttp(i2, requestHeaders);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void rstStream(int i, @NotNull ErrorCode errorCode) {
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            if (this.i.pushedStream$okhttp(i)) {
                this.i.pushResetLater$okhttp(i, errorCode);
                return;
            }
            Http2Stream removeStream$okhttp = this.i.removeStream$okhttp(i);
            if (removeStream$okhttp == null) {
                return;
            }
            removeStream$okhttp.receiveRstStream(errorCode);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void settings(final boolean z, @NotNull final Settings settings) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            this.i.p.schedule(new Task(Intrinsics.stringPlus(this.i.getConnectionName$okhttp(), " applyAndAckSettings"), true) { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$settings$$inlined$execute$default$1
                @Override // okhttp3.internal.concurrent.Task
                public long runOnce() {
                    this.applyAndAckSettings(z, settings);
                    return -1L;
                }
            }, 0L);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void windowUpdate(int i, long j) {
            if (i == 0) {
                Http2Connection http2Connection = this.i;
                synchronized (http2Connection) {
                    http2Connection.F = http2Connection.getWriteBytesMaximum() + j;
                    http2Connection.notifyAll();
                    Unit unit = Unit.INSTANCE;
                }
                return;
            }
            Http2Stream stream = this.i.getStream(i);
            if (stream != null) {
                synchronized (stream) {
                    stream.addBytesToWriteWindow(j);
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [okhttp3.internal.http2.ErrorCode] */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v5, types: [java.io.Closeable, okhttp3.internal.http2.Http2Reader] */
        /* renamed from: invoke */
        public void invoke2() {
            ErrorCode errorCode;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            IOException e = null;
            try {
                try {
                    this.h.readConnectionPreface(this);
                    while (this.h.nextFrame(false, this)) {
                    }
                    ErrorCode errorCode3 = ErrorCode.NO_ERROR;
                    try {
                        this.i.close$okhttp(errorCode3, ErrorCode.CANCEL, null);
                        errorCode = errorCode3;
                    } catch (IOException e2) {
                        e = e2;
                        ErrorCode errorCode4 = ErrorCode.PROTOCOL_ERROR;
                        Http2Connection http2Connection = this.i;
                        http2Connection.close$okhttp(errorCode4, errorCode4, e);
                        errorCode = http2Connection;
                        errorCode2 = this.h;
                        Util.closeQuietly((Closeable) errorCode2);
                    }
                } catch (Throwable th) {
                    th = th;
                    this.i.close$okhttp(errorCode, errorCode2, e);
                    Util.closeQuietly(this.h);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            } catch (Throwable th2) {
                th = th2;
                errorCode = errorCode2;
                this.i.close$okhttp(errorCode, errorCode2, e);
                Util.closeQuietly(this.h);
                throw th;
            }
            errorCode2 = this.h;
            Util.closeQuietly((Closeable) errorCode2);
        }
    }

    static {
        Settings settings = new Settings();
        settings.set(7, 65535);
        settings.set(5, 16384);
        K = settings;
    }

    public Http2Connection(@NotNull Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        boolean client$okhttp = builder.getClient$okhttp();
        this.h = client$okhttp;
        this.i = builder.getListener$okhttp();
        this.j = new LinkedHashMap();
        String connectionName$okhttp = builder.getConnectionName$okhttp();
        this.k = connectionName$okhttp;
        this.m = builder.getClient$okhttp() ? 3 : 2;
        TaskRunner taskRunner$okhttp = builder.getTaskRunner$okhttp();
        this.o = taskRunner$okhttp;
        TaskQueue newQueue = taskRunner$okhttp.newQueue();
        this.p = newQueue;
        this.q = taskRunner$okhttp.newQueue();
        this.r = taskRunner$okhttp.newQueue();
        this.s = builder.getPushObserver$okhttp();
        Settings settings = new Settings();
        if (builder.getClient$okhttp()) {
            settings.set(7, 16777216);
        }
        this.A = settings;
        Settings settings2 = K;
        this.B = settings2;
        this.F = settings2.getInitialWindowSize();
        this.G = builder.getSocket$okhttp();
        this.H = new Http2Writer(builder.getSink$okhttp(), client$okhttp);
        this.I = new ReaderRunnable(this, new Http2Reader(builder.getSource$okhttp(), client$okhttp));
        this.J = new LinkedHashSet();
        if (builder.getPingIntervalMillis$okhttp() != 0) {
            final long nanos = TimeUnit.MILLISECONDS.toNanos(builder.getPingIntervalMillis$okhttp());
            newQueue.schedule(new Task(Intrinsics.stringPlus(connectionName$okhttp, " ping")) { // from class: okhttp3.internal.http2.Http2Connection$special$$inlined$schedule$1
                @Override // okhttp3.internal.concurrent.Task
                public long runOnce() {
                    long j;
                    long j2;
                    boolean z;
                    synchronized (this) {
                        long j3 = this.u;
                        j = this.t;
                        if (j3 < j) {
                            z = true;
                        } else {
                            j2 = this.t;
                            this.t = j2 + 1;
                            z = false;
                        }
                    }
                    if (z) {
                        this.a(null);
                        return -1L;
                    }
                    this.writePing(false, 1, 0);
                    return nanos;
                }
            }, nanos);
        }
    }

    public static /* synthetic */ void start$default(Http2Connection http2Connection, boolean z, TaskRunner taskRunner, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            taskRunner = TaskRunner.INSTANCE;
        }
        http2Connection.start(z, taskRunner);
    }

    public final void a(IOException iOException) {
        ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
        close$okhttp(errorCode, errorCode, iOException);
    }

    public final synchronized void awaitPong() throws InterruptedException {
        while (this.y < this.x) {
            wait();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x0055 A[Catch: all -> 0x0096, TryCatch #1 {, blocks: (B:48:0x0006, B:67:0x0062, B:69:0x0065, B:70:0x006d, B:72:0x0074, B:77:0x0084, B:78:0x008f, B:49:0x0007, B:51:0x0010, B:52:0x0015, B:54:0x0019, B:56:0x0033, B:58:0x003f, B:63:0x004f, B:65:0x0055, B:66:0x0060, B:79:0x0090, B:80:0x0095), top: B:87:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final okhttp3.internal.http2.Http2Stream b(int r11, java.util.List<okhttp3.internal.http2.Header> r12, boolean r13) throws java.io.IOException {
        /*
            r10 = this;
            r6 = r13 ^ 1
            r4 = 0
            okhttp3.internal.http2.Http2Writer r7 = r10.H
            monitor-enter(r7)
            monitor-enter(r10)     // Catch: java.lang.Throwable -> L99
            int r0 = r10.getNextStreamId$okhttp()     // Catch: java.lang.Throwable -> L96
            r1 = 1073741823(0x3fffffff, float:1.9999999)
            if (r0 <= r1) goto L15
            okhttp3.internal.http2.ErrorCode r0 = okhttp3.internal.http2.ErrorCode.REFUSED_STREAM     // Catch: java.lang.Throwable -> L96
            r10.shutdown(r0)     // Catch: java.lang.Throwable -> L96
        L15:
            boolean r0 = r10.n     // Catch: java.lang.Throwable -> L96
            if (r0 != 0) goto L90
            int r8 = r10.getNextStreamId$okhttp()     // Catch: java.lang.Throwable -> L96
            int r0 = r10.getNextStreamId$okhttp()     // Catch: java.lang.Throwable -> L96
            int r0 = r0 + 2
            r10.setNextStreamId$okhttp(r0)     // Catch: java.lang.Throwable -> L96
            okhttp3.internal.http2.Http2Stream r9 = new okhttp3.internal.http2.Http2Stream     // Catch: java.lang.Throwable -> L96
            r5 = 0
            r0 = r9
            r1 = r8
            r2 = r10
            r3 = r6
            r0.<init>(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L96
            r0 = 1
            if (r13 == 0) goto L4e
            long r1 = r10.getWriteBytesTotal()     // Catch: java.lang.Throwable -> L96
            long r3 = r10.getWriteBytesMaximum()     // Catch: java.lang.Throwable -> L96
            int r13 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r13 >= 0) goto L4e
            long r1 = r9.getWriteBytesTotal()     // Catch: java.lang.Throwable -> L96
            long r3 = r9.getWriteBytesMaximum()     // Catch: java.lang.Throwable -> L96
            int r13 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r13 < 0) goto L4c
            goto L4e
        L4c:
            r13 = 0
            goto L4f
        L4e:
            r13 = r0
        L4f:
            boolean r1 = r9.isOpen()     // Catch: java.lang.Throwable -> L96
            if (r1 == 0) goto L60
            java.util.Map r1 = r10.getStreams$okhttp()     // Catch: java.lang.Throwable -> L96
            java.lang.Integer r2 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L96
            r1.put(r2, r9)     // Catch: java.lang.Throwable -> L96
        L60:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L96
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L99
            if (r11 != 0) goto L6d
            okhttp3.internal.http2.Http2Writer r11 = r10.getWriter()     // Catch: java.lang.Throwable -> L99
            r11.headers(r6, r8, r12)     // Catch: java.lang.Throwable -> L99
            goto L7b
        L6d:
            boolean r1 = r10.getClient$okhttp()     // Catch: java.lang.Throwable -> L99
            r0 = r0 ^ r1
            if (r0 == 0) goto L84
            okhttp3.internal.http2.Http2Writer r0 = r10.getWriter()     // Catch: java.lang.Throwable -> L99
            r0.pushPromise(r11, r8, r12)     // Catch: java.lang.Throwable -> L99
        L7b:
            monitor-exit(r7)
            if (r13 == 0) goto L83
            okhttp3.internal.http2.Http2Writer r11 = r10.H
            r11.flush()
        L83:
            return r9
        L84:
            java.lang.String r11 = "client streams shouldn't have associated stream IDs"
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> L99
            java.lang.String r11 = r11.toString()     // Catch: java.lang.Throwable -> L99
            r12.<init>(r11)     // Catch: java.lang.Throwable -> L99
            throw r12     // Catch: java.lang.Throwable -> L99
        L90:
            okhttp3.internal.http2.ConnectionShutdownException r11 = new okhttp3.internal.http2.ConnectionShutdownException     // Catch: java.lang.Throwable -> L96
            r11.<init>()     // Catch: java.lang.Throwable -> L96
            throw r11     // Catch: java.lang.Throwable -> L96
        L96:
            r11 = move-exception
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L99
            throw r11     // Catch: java.lang.Throwable -> L99
        L99:
            r11 = move-exception
            monitor-exit(r7)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.b(int, java.util.List, boolean):okhttp3.internal.http2.Http2Stream");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        close$okhttp(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
    }

    public final void close$okhttp(@NotNull ErrorCode connectionCode, @NotNull ErrorCode streamCode, @Nullable IOException iOException) {
        int i;
        Intrinsics.checkNotNullParameter(connectionCode, "connectionCode");
        Intrinsics.checkNotNullParameter(streamCode, "streamCode");
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
        }
        try {
            shutdown(connectionCode);
        } catch (IOException unused) {
        }
        Object[] objArr = null;
        synchronized (this) {
            if (!getStreams$okhttp().isEmpty()) {
                objArr = getStreams$okhttp().values().toArray(new Http2Stream[0]);
                if (objArr != null) {
                    getStreams$okhttp().clear();
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        Http2Stream[] http2StreamArr = (Http2Stream[]) objArr;
        if (http2StreamArr != null) {
            for (Http2Stream http2Stream : http2StreamArr) {
                try {
                    http2Stream.close(streamCode, iOException);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            getWriter().close();
        } catch (IOException unused3) {
        }
        try {
            getSocket$okhttp().close();
        } catch (IOException unused4) {
        }
        this.p.shutdown();
        this.q.shutdown();
        this.r.shutdown();
    }

    public final void flush() throws IOException {
        this.H.flush();
    }

    public final boolean getClient$okhttp() {
        return this.h;
    }

    @NotNull
    public final String getConnectionName$okhttp() {
        return this.k;
    }

    public final int getLastGoodStreamId$okhttp() {
        return this.l;
    }

    @NotNull
    public final Listener getListener$okhttp() {
        return this.i;
    }

    public final int getNextStreamId$okhttp() {
        return this.m;
    }

    @NotNull
    public final Settings getOkHttpSettings() {
        return this.A;
    }

    @NotNull
    public final Settings getPeerSettings() {
        return this.B;
    }

    public final long getReadBytesAcknowledged() {
        return this.D;
    }

    public final long getReadBytesTotal() {
        return this.C;
    }

    @NotNull
    public final ReaderRunnable getReaderRunnable() {
        return this.I;
    }

    @NotNull
    public final Socket getSocket$okhttp() {
        return this.G;
    }

    @Nullable
    public final synchronized Http2Stream getStream(int i) {
        return this.j.get(Integer.valueOf(i));
    }

    @NotNull
    public final Map<Integer, Http2Stream> getStreams$okhttp() {
        return this.j;
    }

    public final long getWriteBytesMaximum() {
        return this.F;
    }

    public final long getWriteBytesTotal() {
        return this.E;
    }

    @NotNull
    public final Http2Writer getWriter() {
        return this.H;
    }

    public final synchronized boolean isHealthy(long j) {
        if (this.n) {
            return false;
        }
        if (this.w < this.v) {
            if (j >= this.z) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public final Http2Stream newStream(@NotNull List<Header> requestHeaders, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        return b(0, requestHeaders, z);
    }

    public final synchronized int openStreamCount() {
        return this.j.size();
    }

    public final void pushDataLater$okhttp(final int i, @NotNull BufferedSource source, final int i2, final boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        final Buffer buffer = new Buffer();
        long j = i2;
        source.require(j);
        source.read(buffer, j);
        this.q.schedule(new Task(this.k + '[' + i + "] onData", true) { // from class: okhttp3.internal.http2.Http2Connection$pushDataLater$$inlined$execute$default$1
            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                PushObserver pushObserver;
                Set set;
                try {
                    pushObserver = this.s;
                    boolean onData = pushObserver.onData(i, buffer, i2, z);
                    if (onData) {
                        this.getWriter().rstStream(i, ErrorCode.CANCEL);
                    }
                    if (onData || z) {
                        synchronized (this) {
                            set = this.J;
                            set.remove(Integer.valueOf(i));
                        }
                        return -1L;
                    }
                    return -1L;
                } catch (IOException unused) {
                    return -1L;
                }
            }
        }, 0L);
    }

    public final void pushHeadersLater$okhttp(final int i, @NotNull final List<Header> requestHeaders, final boolean z) {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        TaskQueue taskQueue = this.q;
        taskQueue.schedule(new Task(this.k + '[' + i + "] onHeaders", true) { // from class: okhttp3.internal.http2.Http2Connection$pushHeadersLater$$inlined$execute$default$1
            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                PushObserver pushObserver;
                Set set;
                pushObserver = this.s;
                boolean onHeaders = pushObserver.onHeaders(i, requestHeaders, z);
                if (onHeaders) {
                    try {
                        this.getWriter().rstStream(i, ErrorCode.CANCEL);
                    } catch (IOException unused) {
                        return -1L;
                    }
                }
                if (onHeaders || z) {
                    synchronized (this) {
                        set = this.J;
                        set.remove(Integer.valueOf(i));
                    }
                    return -1L;
                }
                return -1L;
            }
        }, 0L);
    }

    public final void pushRequestLater$okhttp(final int i, @NotNull final List<Header> requestHeaders) {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        synchronized (this) {
            if (this.J.contains(Integer.valueOf(i))) {
                writeSynResetLater$okhttp(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.J.add(Integer.valueOf(i));
            TaskQueue taskQueue = this.q;
            taskQueue.schedule(new Task(this.k + '[' + i + "] onRequest", true) { // from class: okhttp3.internal.http2.Http2Connection$pushRequestLater$$inlined$execute$default$1
                @Override // okhttp3.internal.concurrent.Task
                public long runOnce() {
                    PushObserver pushObserver;
                    Set set;
                    pushObserver = this.s;
                    if (pushObserver.onRequest(i, requestHeaders)) {
                        try {
                            this.getWriter().rstStream(i, ErrorCode.CANCEL);
                            synchronized (this) {
                                set = this.J;
                                set.remove(Integer.valueOf(i));
                            }
                            return -1L;
                        } catch (IOException unused) {
                            return -1L;
                        }
                    }
                    return -1L;
                }
            }, 0L);
        }
    }

    public final void pushResetLater$okhttp(final int i, @NotNull final ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        TaskQueue taskQueue = this.q;
        taskQueue.schedule(new Task(this.k + '[' + i + "] onReset", true) { // from class: okhttp3.internal.http2.Http2Connection$pushResetLater$$inlined$execute$default$1
            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                PushObserver pushObserver;
                Set set;
                pushObserver = this.s;
                pushObserver.onReset(i, errorCode);
                synchronized (this) {
                    set = this.J;
                    set.remove(Integer.valueOf(i));
                    Unit unit = Unit.INSTANCE;
                }
                return -1L;
            }
        }, 0L);
    }

    @NotNull
    public final Http2Stream pushStream(int i, @NotNull List<Header> requestHeaders, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        if (!this.h) {
            return b(i, requestHeaders, z);
        }
        throw new IllegalStateException("Client cannot push requests.".toString());
    }

    public final boolean pushedStream$okhttp(int i) {
        return i != 0 && (i & 1) == 0;
    }

    @Nullable
    public final synchronized Http2Stream removeStream$okhttp(int i) {
        Http2Stream remove;
        remove = this.j.remove(Integer.valueOf(i));
        notifyAll();
        return remove;
    }

    public final void sendDegradedPingLater$okhttp() {
        synchronized (this) {
            long j = this.w;
            long j2 = this.v;
            if (j < j2) {
                return;
            }
            this.v = j2 + 1;
            this.z = System.nanoTime() + 1000000000;
            Unit unit = Unit.INSTANCE;
            this.p.schedule(new Task(Intrinsics.stringPlus(this.k, " ping"), true) { // from class: okhttp3.internal.http2.Http2Connection$sendDegradedPingLater$$inlined$execute$default$1
                @Override // okhttp3.internal.concurrent.Task
                public long runOnce() {
                    this.writePing(false, 2, 0);
                    return -1L;
                }
            }, 0L);
        }
    }

    public final void setLastGoodStreamId$okhttp(int i) {
        this.l = i;
    }

    public final void setNextStreamId$okhttp(int i) {
        this.m = i;
    }

    public final void setPeerSettings(@NotNull Settings settings) {
        Intrinsics.checkNotNullParameter(settings, "<set-?>");
        this.B = settings;
    }

    public final void setSettings(@NotNull Settings settings) throws IOException {
        Intrinsics.checkNotNullParameter(settings, "settings");
        synchronized (this.H) {
            synchronized (this) {
                if (!this.n) {
                    getOkHttpSettings().merge(settings);
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new ConnectionShutdownException();
                }
            }
            getWriter().settings(settings);
        }
    }

    public final void shutdown(@NotNull ErrorCode statusCode) throws IOException {
        Intrinsics.checkNotNullParameter(statusCode, "statusCode");
        synchronized (this.H) {
            Ref.IntRef intRef = new Ref.IntRef();
            synchronized (this) {
                if (this.n) {
                    return;
                }
                this.n = true;
                intRef.element = getLastGoodStreamId$okhttp();
                Unit unit = Unit.INSTANCE;
                getWriter().goAway(intRef.element, statusCode, Util.EMPTY_BYTE_ARRAY);
            }
        }
    }

    @JvmOverloads
    public final void start() throws IOException {
        start$default(this, false, null, 3, null);
    }

    @JvmOverloads
    public final void start(boolean z) throws IOException {
        start$default(this, z, null, 2, null);
    }

    @JvmOverloads
    public final void start(boolean z, @NotNull TaskRunner taskRunner) throws IOException {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        if (z) {
            this.H.connectionPreface();
            this.H.settings(this.A);
            int initialWindowSize = this.A.getInitialWindowSize();
            if (initialWindowSize != 65535) {
                this.H.windowUpdate(0, initialWindowSize - 65535);
            }
        }
        taskRunner.newQueue().schedule(new TaskQueue$execute$1(this.k, true, this.I), 0L);
    }

    public final synchronized void updateConnectionFlowControl$okhttp(long j) {
        long j2 = this.C + j;
        this.C = j2;
        long j3 = j2 - this.D;
        if (j3 >= this.A.getInitialWindowSize() / 2) {
            writeWindowUpdateLater$okhttp(0, j3);
            this.D += j3;
        }
    }

    public final void writeData(int i, boolean z, @Nullable Buffer buffer, long j) throws IOException {
        int min;
        long j2;
        if (j == 0) {
            this.H.data(z, i, buffer, 0);
            return;
        }
        while (j > 0) {
            synchronized (this) {
                while (getWriteBytesTotal() >= getWriteBytesMaximum()) {
                    try {
                        if (getStreams$okhttp().containsKey(Integer.valueOf(i))) {
                            wait();
                        } else {
                            throw new IOException("stream closed");
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                }
                min = Math.min((int) Math.min(j, getWriteBytesMaximum() - getWriteBytesTotal()), getWriter().maxDataLength());
                j2 = min;
                this.E = getWriteBytesTotal() + j2;
                Unit unit = Unit.INSTANCE;
            }
            j -= j2;
            this.H.data(z && j == 0, i, buffer, min);
        }
    }

    public final void writeHeaders$okhttp(int i, boolean z, @NotNull List<Header> alternating) throws IOException {
        Intrinsics.checkNotNullParameter(alternating, "alternating");
        this.H.headers(z, i, alternating);
    }

    public final void writePing(boolean z, int i, int i2) {
        try {
            this.H.ping(z, i, i2);
        } catch (IOException e) {
            a(e);
        }
    }

    public final void writePingAndAwaitPong() throws InterruptedException {
        writePing();
        awaitPong();
    }

    public final void writeSynReset$okhttp(int i, @NotNull ErrorCode statusCode) throws IOException {
        Intrinsics.checkNotNullParameter(statusCode, "statusCode");
        this.H.rstStream(i, statusCode);
    }

    public final void writeSynResetLater$okhttp(final int i, @NotNull final ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        TaskQueue taskQueue = this.p;
        taskQueue.schedule(new Task(this.k + '[' + i + "] writeSynReset", true) { // from class: okhttp3.internal.http2.Http2Connection$writeSynResetLater$$inlined$execute$default$1
            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                try {
                    this.writeSynReset$okhttp(i, errorCode);
                    return -1L;
                } catch (IOException e) {
                    this.a(e);
                    return -1L;
                }
            }
        }, 0L);
    }

    public final void writeWindowUpdateLater$okhttp(final int i, final long j) {
        TaskQueue taskQueue = this.p;
        taskQueue.schedule(new Task(this.k + '[' + i + "] windowUpdate", true) { // from class: okhttp3.internal.http2.Http2Connection$writeWindowUpdateLater$$inlined$execute$default$1
            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                try {
                    this.getWriter().windowUpdate(i, j);
                    return -1L;
                } catch (IOException e) {
                    this.a(e);
                    return -1L;
                }
            }
        }, 0L);
    }

    public final void writePing() throws InterruptedException {
        synchronized (this) {
            this.x++;
        }
        writePing(false, 3, 1330343787);
    }
}
