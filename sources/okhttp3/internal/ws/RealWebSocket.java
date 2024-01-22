package okhttp3.internal.ws;

import androidx.core.view.PointerIconCompat;
import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.collections.e;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.m;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.ws.RealWebSocket;
import okhttp3.internal.ws.WebSocketReader;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    public static final long DEFAULT_MINIMUM_DEFLATE_SIZE = 1024;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Request f14304a;
    @NotNull
    public final WebSocketListener b;
    @NotNull
    public final Random c;
    public final long d;
    @Nullable
    public WebSocketExtensions e;
    public long f;
    @NotNull
    public final String g;
    @Nullable
    public Call h;
    @Nullable
    public Task i;
    @Nullable
    public WebSocketReader j;
    @Nullable
    public WebSocketWriter k;
    @NotNull
    public TaskQueue l;
    @Nullable
    public String m;
    @Nullable
    public Streams n;
    @NotNull
    public final ArrayDeque<ByteString> o;
    @NotNull
    public final ArrayDeque<Object> p;
    public long q;
    public boolean r;
    public int s;
    @Nullable
    public String t;
    public boolean u;
    public int v;
    public int w;
    public int x;
    public boolean y;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final List<Protocol> z = e.listOf(Protocol.HTTP_1_1);

    /* loaded from: classes12.dex */
    public static final class Close {

        /* renamed from: a  reason: collision with root package name */
        public final int f14305a;
        @Nullable
        public final ByteString b;
        public final long c;

        public Close(int i, @Nullable ByteString byteString, long j) {
            this.f14305a = i;
            this.b = byteString;
            this.c = j;
        }

        public final long getCancelAfterCloseMillis() {
            return this.c;
        }

        public final int getCode() {
            return this.f14305a;
        }

        @Nullable
        public final ByteString getReason() {
            return this.b;
        }
    }

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes12.dex */
    public static final class Message {

        /* renamed from: a  reason: collision with root package name */
        public final int f14306a;
        @NotNull
        public final ByteString b;

        public Message(int i, @NotNull ByteString data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.f14306a = i;
            this.b = data;
        }

        @NotNull
        public final ByteString getData() {
            return this.b;
        }

        public final int getFormatOpcode() {
            return this.f14306a;
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class Streams implements Closeable {
        public final boolean h;
        @NotNull
        public final BufferedSource i;
        @NotNull
        public final BufferedSink j;

        public Streams(boolean z, @NotNull BufferedSource source, @NotNull BufferedSink sink) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(sink, "sink");
            this.h = z;
            this.i = source;
            this.j = sink;
        }

        public final boolean getClient() {
            return this.h;
        }

        @NotNull
        public final BufferedSink getSink() {
            return this.j;
        }

        @NotNull
        public final BufferedSource getSource() {
            return this.i;
        }
    }

    /* loaded from: classes12.dex */
    public final class a extends Task {
        public final /* synthetic */ RealWebSocket e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(RealWebSocket this$0) {
            super(Intrinsics.stringPlus(this$0.m, " writer"), false, 2, null);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.e = this$0;
        }

        @Override // okhttp3.internal.concurrent.Task
        public long runOnce() {
            try {
                return this.e.writeOneFrame$okhttp() ? 0L : -1L;
            } catch (IOException e) {
                this.e.failWebSocket(e, null);
                return -1L;
            }
        }
    }

    public RealWebSocket(@NotNull TaskRunner taskRunner, @NotNull Request originalRequest, @NotNull WebSocketListener listener, @NotNull Random random, long j, @Nullable WebSocketExtensions webSocketExtensions, long j2) {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        Intrinsics.checkNotNullParameter(originalRequest, "originalRequest");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(random, "random");
        this.f14304a = originalRequest;
        this.b = listener;
        this.c = random;
        this.d = j;
        this.e = webSocketExtensions;
        this.f = j2;
        this.l = taskRunner.newQueue();
        this.o = new ArrayDeque<>();
        this.p = new ArrayDeque<>();
        this.s = -1;
        if (Intrinsics.areEqual("GET", originalRequest.method())) {
            ByteString.Companion companion = ByteString.Companion;
            byte[] bArr = new byte[16];
            random.nextBytes(bArr);
            Unit unit = Unit.INSTANCE;
            this.g = ByteString.Companion.of$default(companion, bArr, 0, 0, 3, null).base64();
            return;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Request must be GET: ", originalRequest.method()).toString());
    }

    public final boolean a(WebSocketExtensions webSocketExtensions) {
        if (!webSocketExtensions.unknownValues && webSocketExtensions.clientMaxWindowBits == null) {
            return webSocketExtensions.serverMaxWindowBits == null || new IntRange(8, 15).contains(webSocketExtensions.serverMaxWindowBits.intValue());
        }
        return false;
    }

    public final void awaitTermination(long j, @NotNull TimeUnit timeUnit) throws InterruptedException {
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        this.l.idleLatch().await(j, timeUnit);
    }

    public final void b() {
        if (Util.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST hold lock on " + this);
        }
        Task task = this.i;
        if (task != null) {
            TaskQueue.schedule$default(this.l, task, 0L, 2, null);
        }
    }

    public final synchronized boolean c(ByteString byteString, int i) {
        if (!this.u && !this.r) {
            if (this.q + byteString.size() > 16777216) {
                close(1001, null);
                return false;
            }
            this.q += byteString.size();
            this.p.add(new Message(i, byteString));
            b();
            return true;
        }
        return false;
    }

    @Override // okhttp3.WebSocket
    public void cancel() {
        Call call = this.h;
        Intrinsics.checkNotNull(call);
        call.cancel();
    }

    public final void checkUpgradeSuccess$okhttp(@NotNull Response response, @Nullable Exchange exchange) throws IOException {
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.code() == 101) {
            String header$default = Response.header$default(response, HttpHeaders.CONNECTION, null, 2, null);
            if (m.equals(HttpHeaders.UPGRADE, header$default, true)) {
                String header$default2 = Response.header$default(response, HttpHeaders.UPGRADE, null, 2, null);
                if (m.equals("websocket", header$default2, true)) {
                    String header$default3 = Response.header$default(response, HttpHeaders.SEC_WEBSOCKET_ACCEPT, null, 2, null);
                    String base64 = ByteString.Companion.encodeUtf8(Intrinsics.stringPlus(this.g, WebSocketProtocol.ACCEPT_MAGIC)).sha1().base64();
                    if (Intrinsics.areEqual(base64, header$default3)) {
                        if (exchange == null) {
                            throw new ProtocolException("Web Socket exchange missing: bad interceptor?");
                        }
                        return;
                    }
                    throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + base64 + "' but was '" + ((Object) header$default3) + '\'');
                }
                throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + ((Object) header$default2) + '\'');
            }
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + ((Object) header$default) + '\'');
        }
        throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + ' ' + response.message() + '\'');
    }

    @Override // okhttp3.WebSocket
    public boolean close(int i, @Nullable String str) {
        return close(i, str, Constants.ONE_MIN_IN_MILLIS);
    }

    public final void connect(@NotNull OkHttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        if (this.f14304a.header(HttpHeaders.SEC_WEBSOCKET_EXTENSIONS) != null) {
            failWebSocket(new ProtocolException("Request header not permitted: 'Sec-WebSocket-Extensions'"), null);
            return;
        }
        OkHttpClient build = client.newBuilder().eventListener(EventListener.NONE).protocols(z).build();
        final Request build2 = this.f14304a.newBuilder().header(HttpHeaders.UPGRADE, "websocket").header(HttpHeaders.CONNECTION, HttpHeaders.UPGRADE).header(HttpHeaders.SEC_WEBSOCKET_KEY, this.g).header(HttpHeaders.SEC_WEBSOCKET_VERSION, BleConst.CMD_MCUReset).header(HttpHeaders.SEC_WEBSOCKET_EXTENSIONS, "permessage-deflate").build();
        RealCall realCall = new RealCall(build, build2, true);
        this.h = realCall;
        Intrinsics.checkNotNull(realCall);
        realCall.enqueue(new Callback() { // from class: okhttp3.internal.ws.RealWebSocket$connect$1
            @Override // okhttp3.Callback
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                RealWebSocket.this.failWebSocket(e, null);
            }

            @Override // okhttp3.Callback
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                boolean a2;
                ArrayDeque arrayDeque;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                Exchange exchange = response.exchange();
                try {
                    RealWebSocket.this.checkUpgradeSuccess$okhttp(response, exchange);
                    Intrinsics.checkNotNull(exchange);
                    RealWebSocket.Streams newWebSocketStreams = exchange.newWebSocketStreams();
                    WebSocketExtensions parse = WebSocketExtensions.Companion.parse(response.headers());
                    RealWebSocket.this.e = parse;
                    a2 = RealWebSocket.this.a(parse);
                    if (!a2) {
                        RealWebSocket realWebSocket = RealWebSocket.this;
                        synchronized (realWebSocket) {
                            arrayDeque = realWebSocket.p;
                            arrayDeque.clear();
                            realWebSocket.close(PointerIconCompat.TYPE_ALIAS, "unexpected Sec-WebSocket-Extensions in response header");
                        }
                    }
                    try {
                        RealWebSocket.this.initReaderAndWriter(Util.okHttpName + " WebSocket " + build2.url().redact(), newWebSocketStreams);
                        RealWebSocket.this.getListener$okhttp().onOpen(RealWebSocket.this, response);
                        RealWebSocket.this.loopReader();
                    } catch (Exception e) {
                        RealWebSocket.this.failWebSocket(e, null);
                    }
                } catch (IOException e2) {
                    if (exchange != null) {
                        exchange.webSocketUpgradeFailed();
                    }
                    RealWebSocket.this.failWebSocket(e2, response);
                    Util.closeQuietly(response);
                }
            }
        });
    }

    public final void failWebSocket(@NotNull Exception e, @Nullable Response response) {
        Intrinsics.checkNotNullParameter(e, "e");
        synchronized (this) {
            if (this.u) {
                return;
            }
            this.u = true;
            Streams streams = this.n;
            this.n = null;
            WebSocketReader webSocketReader = this.j;
            this.j = null;
            WebSocketWriter webSocketWriter = this.k;
            this.k = null;
            this.l.shutdown();
            Unit unit = Unit.INSTANCE;
            try {
                this.b.onFailure(this, e, response);
            } finally {
                if (streams != null) {
                    Util.closeQuietly(streams);
                }
                if (webSocketReader != null) {
                    Util.closeQuietly(webSocketReader);
                }
                if (webSocketWriter != null) {
                    Util.closeQuietly(webSocketWriter);
                }
            }
        }
    }

    @NotNull
    public final WebSocketListener getListener$okhttp() {
        return this.b;
    }

    public final void initReaderAndWriter(@NotNull String name, @NotNull Streams streams) throws IOException {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(streams, "streams");
        WebSocketExtensions webSocketExtensions = this.e;
        Intrinsics.checkNotNull(webSocketExtensions);
        synchronized (this) {
            this.m = name;
            this.n = streams;
            this.k = new WebSocketWriter(streams.getClient(), streams.getSink(), this.c, webSocketExtensions.perMessageDeflate, webSocketExtensions.noContextTakeover(streams.getClient()), this.f);
            this.i = new a(this);
            long j = this.d;
            if (j != 0) {
                final long nanos = TimeUnit.MILLISECONDS.toNanos(j);
                TaskQueue taskQueue = this.l;
                final String stringPlus = Intrinsics.stringPlus(name, " ping");
                taskQueue.schedule(new Task(stringPlus) { // from class: okhttp3.internal.ws.RealWebSocket$initReaderAndWriter$lambda-3$$inlined$schedule$1
                    @Override // okhttp3.internal.concurrent.Task
                    public long runOnce() {
                        this.writePingFrame$okhttp();
                        return nanos;
                    }
                }, nanos);
            }
            if (!this.p.isEmpty()) {
                b();
            }
            Unit unit = Unit.INSTANCE;
        }
        this.j = new WebSocketReader(streams.getClient(), streams.getSource(), this, webSocketExtensions.perMessageDeflate, webSocketExtensions.noContextTakeover(!streams.getClient()));
    }

    public final void loopReader() throws IOException {
        while (this.s == -1) {
            WebSocketReader webSocketReader = this.j;
            Intrinsics.checkNotNull(webSocketReader);
            webSocketReader.processNextFrame();
        }
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadClose(int i, @NotNull String reason) {
        Streams streams;
        WebSocketReader webSocketReader;
        WebSocketWriter webSocketWriter;
        Intrinsics.checkNotNullParameter(reason, "reason");
        boolean z2 = true;
        if (i != -1) {
            synchronized (this) {
                if (this.s != -1) {
                    z2 = false;
                }
                if (z2) {
                    this.s = i;
                    this.t = reason;
                    streams = null;
                    if (this.r && this.p.isEmpty()) {
                        Streams streams2 = this.n;
                        this.n = null;
                        webSocketReader = this.j;
                        this.j = null;
                        webSocketWriter = this.k;
                        this.k = null;
                        this.l.shutdown();
                        streams = streams2;
                    } else {
                        webSocketReader = null;
                        webSocketWriter = null;
                    }
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("already closed".toString());
                }
            }
            try {
                this.b.onClosing(this, i, reason);
                if (streams != null) {
                    this.b.onClosed(this, i, reason);
                }
                if (webSocketWriter == null) {
                    return;
                }
                return;
            } finally {
                if (streams != null) {
                    Util.closeQuietly(streams);
                }
                if (webSocketReader != null) {
                    Util.closeQuietly(webSocketReader);
                }
                if (webSocketWriter != null) {
                    Util.closeQuietly(webSocketWriter);
                }
            }
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(@NotNull String text) throws IOException {
        Intrinsics.checkNotNullParameter(text, "text");
        this.b.onMessage(this, text);
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPing(@NotNull ByteString payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (!this.u && (!this.r || !this.p.isEmpty())) {
            this.o.add(payload);
            b();
            this.w++;
        }
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPong(@NotNull ByteString payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        this.x++;
        this.y = false;
    }

    public final synchronized boolean pong(@NotNull ByteString payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (!this.u && (!this.r || !this.p.isEmpty())) {
            this.o.add(payload);
            b();
            return true;
        }
        return false;
    }

    public final boolean processNextFrame() throws IOException {
        try {
            WebSocketReader webSocketReader = this.j;
            Intrinsics.checkNotNull(webSocketReader);
            webSocketReader.processNextFrame();
            return this.s == -1;
        } catch (Exception e) {
            failWebSocket(e, null);
            return false;
        }
    }

    @Override // okhttp3.WebSocket
    public synchronized long queueSize() {
        return this.q;
    }

    public final synchronized int receivedPingCount() {
        return this.w;
    }

    public final synchronized int receivedPongCount() {
        return this.x;
    }

    @Override // okhttp3.WebSocket
    @NotNull
    public Request request() {
        return this.f14304a;
    }

    @Override // okhttp3.WebSocket
    public boolean send(@NotNull String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return c(ByteString.Companion.encodeUtf8(text), 1);
    }

    public final synchronized int sentPingCount() {
        return this.v;
    }

    public final void tearDown() throws InterruptedException {
        this.l.shutdown();
        this.l.idleLatch().await(10L, TimeUnit.SECONDS);
    }

    public final boolean writeOneFrame$okhttp() throws IOException {
        Streams streams;
        String str;
        WebSocketReader webSocketReader;
        Closeable closeable;
        synchronized (this) {
            if (this.u) {
                return false;
            }
            WebSocketWriter webSocketWriter = this.k;
            ByteString poll = this.o.poll();
            int i = -1;
            Message message = null;
            if (poll == null) {
                Object poll2 = this.p.poll();
                if (poll2 instanceof Close) {
                    int i2 = this.s;
                    str = this.t;
                    if (i2 != -1) {
                        Streams streams2 = this.n;
                        this.n = null;
                        webSocketReader = this.j;
                        this.j = null;
                        closeable = this.k;
                        this.k = null;
                        this.l.shutdown();
                        message = poll2;
                        i = i2;
                        streams = streams2;
                    } else {
                        long cancelAfterCloseMillis = ((Close) poll2).getCancelAfterCloseMillis();
                        TaskQueue taskQueue = this.l;
                        final String stringPlus = Intrinsics.stringPlus(this.m, " cancel");
                        taskQueue.schedule(new Task(stringPlus, true) { // from class: okhttp3.internal.ws.RealWebSocket$writeOneFrame$lambda-8$$inlined$execute$default$1
                            @Override // okhttp3.internal.concurrent.Task
                            public long runOnce() {
                                this.cancel();
                                return -1L;
                            }
                        }, TimeUnit.MILLISECONDS.toNanos(cancelAfterCloseMillis));
                        i = i2;
                        streams = null;
                        webSocketReader = null;
                    }
                } else if (poll2 == null) {
                    return false;
                } else {
                    streams = null;
                    str = null;
                    webSocketReader = null;
                }
                closeable = webSocketReader;
                message = poll2;
            } else {
                streams = null;
                str = null;
                webSocketReader = null;
                closeable = null;
            }
            Unit unit = Unit.INSTANCE;
            try {
                if (poll != null) {
                    Intrinsics.checkNotNull(webSocketWriter);
                    webSocketWriter.writePong(poll);
                } else if (message instanceof Message) {
                    Message message2 = message;
                    Intrinsics.checkNotNull(webSocketWriter);
                    webSocketWriter.writeMessageFrame(message2.getFormatOpcode(), message2.getData());
                    synchronized (this) {
                        this.q -= message2.getData().size();
                    }
                } else if (message instanceof Close) {
                    Close close = (Close) message;
                    Intrinsics.checkNotNull(webSocketWriter);
                    webSocketWriter.writeClose(close.getCode(), close.getReason());
                    if (streams != null) {
                        WebSocketListener webSocketListener = this.b;
                        Intrinsics.checkNotNull(str);
                        webSocketListener.onClosed(this, i, str);
                    }
                } else {
                    throw new AssertionError();
                }
                return true;
            } finally {
                if (streams != null) {
                    Util.closeQuietly(streams);
                }
                if (webSocketReader != null) {
                    Util.closeQuietly(webSocketReader);
                }
                if (closeable != null) {
                    Util.closeQuietly(closeable);
                }
            }
        }
    }

    public final void writePingFrame$okhttp() {
        synchronized (this) {
            if (this.u) {
                return;
            }
            WebSocketWriter webSocketWriter = this.k;
            if (webSocketWriter == null) {
                return;
            }
            int i = this.y ? this.v : -1;
            this.v++;
            this.y = true;
            Unit unit = Unit.INSTANCE;
            if (i != -1) {
                failWebSocket(new SocketTimeoutException("sent ping but didn't receive pong within " + this.d + "ms (after " + (i - 1) + " successful ping/pongs)"), null);
                return;
            }
            try {
                webSocketWriter.writePing(ByteString.EMPTY);
            } catch (IOException e) {
                failWebSocket(e, null);
            }
        }
    }

    public final synchronized boolean close(int i, @Nullable String str, long j) {
        WebSocketProtocol.INSTANCE.validateCloseCode(i);
        ByteString byteString = null;
        if (str != null) {
            byteString = ByteString.Companion.encodeUtf8(str);
            if (!(((long) byteString.size()) <= 123)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("reason.size() > 123: ", str).toString());
            }
        }
        if (!this.u && !this.r) {
            this.r = true;
            this.p.add(new Close(i, byteString, j));
            b();
            return true;
        }
        return false;
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(@NotNull ByteString bytes) throws IOException {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        this.b.onMessage(this, bytes);
    }

    @Override // okhttp3.WebSocket
    public boolean send(@NotNull ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return c(bytes, 2);
    }
}
