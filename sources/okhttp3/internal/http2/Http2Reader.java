package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.h;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Hpack;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class Http2Reader implements Closeable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final Logger l;
    @NotNull
    public final BufferedSource h;
    public final boolean i;
    @NotNull
    public final ContinuationSource j;
    @NotNull
    public final Hpack.Reader k;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Logger getLogger() {
            return Http2Reader.l;
        }

        public final int lengthWithoutPadding(int i, int i2, int i3) throws IOException {
            if ((i2 & 8) != 0) {
                i--;
            }
            if (i3 <= i) {
                return i - i3;
            }
            throw new IOException("PROTOCOL_ERROR padding " + i3 + " > remaining length " + i);
        }
    }

    /* loaded from: classes12.dex */
    public static final class ContinuationSource implements Source {
        @NotNull
        public final BufferedSource h;
        public int i;
        public int j;
        public int k;
        public int l;
        public int m;

        public ContinuationSource(@NotNull BufferedSource source) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.h = source;
        }

        public final void a() throws IOException {
            int i = this.k;
            int readMedium = Util.readMedium(this.h);
            this.l = readMedium;
            this.i = readMedium;
            int and = Util.and(this.h.readByte(), 255);
            this.j = Util.and(this.h.readByte(), 255);
            Companion companion = Http2Reader.Companion;
            if (companion.getLogger().isLoggable(Level.FINE)) {
                companion.getLogger().fine(Http2.INSTANCE.frameLog(true, this.k, this.i, and, this.j));
            }
            int readInt = this.h.readInt() & Integer.MAX_VALUE;
            this.k = readInt;
            if (and == 9) {
                if (readInt != i) {
                    throw new IOException("TYPE_CONTINUATION streamId changed");
                }
                return;
            }
            throw new IOException(and + " != TYPE_CONTINUATION");
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public final int getFlags() {
            return this.j;
        }

        public final int getLeft() {
            return this.l;
        }

        public final int getLength() {
            return this.i;
        }

        public final int getPadding() {
            return this.m;
        }

        public final int getStreamId() {
            return this.k;
        }

        @Override // okio.Source
        public long read(@NotNull Buffer sink, long j) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            while (true) {
                int i = this.l;
                if (i == 0) {
                    this.h.skip(this.m);
                    this.m = 0;
                    if ((this.j & 4) != 0) {
                        return -1L;
                    }
                    a();
                } else {
                    long read = this.h.read(sink, Math.min(j, i));
                    if (read == -1) {
                        return -1L;
                    }
                    this.l -= (int) read;
                    return read;
                }
            }
        }

        public final void setFlags(int i) {
            this.j = i;
        }

        public final void setLeft(int i) {
            this.l = i;
        }

        public final void setLength(int i) {
            this.i = i;
        }

        public final void setPadding(int i) {
            this.m = i;
        }

        public final void setStreamId(int i) {
            this.k = i;
        }

        @Override // okio.Source
        @NotNull
        public Timeout timeout() {
            return this.h.timeout();
        }
    }

    /* loaded from: classes12.dex */
    public interface Handler {
        void ackSettings();

        void alternateService(int i, @NotNull String str, @NotNull ByteString byteString, @NotNull String str2, int i2, long j);

        void data(boolean z, int i, @NotNull BufferedSource bufferedSource, int i2) throws IOException;

        void goAway(int i, @NotNull ErrorCode errorCode, @NotNull ByteString byteString);

        void headers(boolean z, int i, int i2, @NotNull List<Header> list);

        void ping(boolean z, int i, int i2);

        void priority(int i, int i2, int i3, boolean z);

        void pushPromise(int i, int i2, @NotNull List<Header> list) throws IOException;

        void rstStream(int i, @NotNull ErrorCode errorCode);

        void settings(boolean z, @NotNull Settings settings);

        void windowUpdate(int i, long j);
    }

    static {
        Logger logger = Logger.getLogger(Http2.class.getName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(Http2::class.java.name)");
        l = logger;
    }

    public Http2Reader(@NotNull BufferedSource source, boolean z) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.h = source;
        this.i = z;
        ContinuationSource continuationSource = new ContinuationSource(source);
        this.j = continuationSource;
        this.k = new Hpack.Reader(continuationSource, 4096, 0, 4, null);
    }

    public final void a(Handler handler, int i, int i2, int i3) throws IOException {
        if (i3 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_DATA streamId == 0");
        }
        boolean z = (i2 & 1) != 0;
        if (!((i2 & 32) != 0)) {
            int and = (i2 & 8) != 0 ? Util.and(this.h.readByte(), 255) : 0;
            handler.data(z, i3, this.h, Companion.lengthWithoutPadding(i, i2, and));
            this.h.skip(and);
            return;
        }
        throw new IOException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA");
    }

    public final void b(Handler handler, int i, int i2, int i3) throws IOException {
        if (i < 8) {
            throw new IOException(Intrinsics.stringPlus("TYPE_GOAWAY length < 8: ", Integer.valueOf(i)));
        }
        if (i3 == 0) {
            int readInt = this.h.readInt();
            int readInt2 = this.h.readInt();
            int i4 = i - 8;
            ErrorCode fromHttp2 = ErrorCode.Companion.fromHttp2(readInt2);
            if (fromHttp2 != null) {
                ByteString byteString = ByteString.EMPTY;
                if (i4 > 0) {
                    byteString = this.h.readByteString(i4);
                }
                handler.goAway(readInt, fromHttp2, byteString);
                return;
            }
            throw new IOException(Intrinsics.stringPlus("TYPE_GOAWAY unexpected error code: ", Integer.valueOf(readInt2)));
        }
        throw new IOException("TYPE_GOAWAY streamId != 0");
    }

    public final List<Header> c(int i, int i2, int i3, int i4) throws IOException {
        this.j.setLeft(i);
        ContinuationSource continuationSource = this.j;
        continuationSource.setLength(continuationSource.getLeft());
        this.j.setPadding(i2);
        this.j.setFlags(i3);
        this.j.setStreamId(i4);
        this.k.readHeaders();
        return this.k.getAndResetHeaderList();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h.close();
    }

    public final void d(Handler handler, int i, int i2, int i3) throws IOException {
        if (i3 != 0) {
            boolean z = (i2 & 1) != 0;
            int and = (i2 & 8) != 0 ? Util.and(this.h.readByte(), 255) : 0;
            if ((i2 & 32) != 0) {
                f(handler, i3);
                i -= 5;
            }
            handler.headers(z, i3, -1, c(Companion.lengthWithoutPadding(i, i2, and), and, i2, i3));
            return;
        }
        throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
    }

    public final void e(Handler handler, int i, int i2, int i3) throws IOException {
        if (i != 8) {
            throw new IOException(Intrinsics.stringPlus("TYPE_PING length != 8: ", Integer.valueOf(i)));
        }
        if (i3 == 0) {
            handler.ping((i2 & 1) != 0, this.h.readInt(), this.h.readInt());
            return;
        }
        throw new IOException("TYPE_PING streamId != 0");
    }

    public final void f(Handler handler, int i) throws IOException {
        int readInt = this.h.readInt();
        handler.priority(i, readInt & Integer.MAX_VALUE, Util.and(this.h.readByte(), 255) + 1, (Integer.MIN_VALUE & readInt) != 0);
    }

    public final void g(Handler handler, int i, int i2, int i3) throws IOException {
        if (i == 5) {
            if (i3 != 0) {
                f(handler, i3);
                return;
            }
            throw new IOException("TYPE_PRIORITY streamId == 0");
        }
        throw new IOException("TYPE_PRIORITY length: " + i + " != 5");
    }

    public final void h(Handler handler, int i, int i2, int i3) throws IOException {
        if (i3 != 0) {
            int and = (i2 & 8) != 0 ? Util.and(this.h.readByte(), 255) : 0;
            handler.pushPromise(i3, this.h.readInt() & Integer.MAX_VALUE, c(Companion.lengthWithoutPadding(i - 4, i2, and), and, i2, i3));
            return;
        }
        throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
    }

    public final void i(Handler handler, int i, int i2, int i3) throws IOException {
        if (i != 4) {
            throw new IOException("TYPE_RST_STREAM length: " + i + " != 4");
        } else if (i3 != 0) {
            int readInt = this.h.readInt();
            ErrorCode fromHttp2 = ErrorCode.Companion.fromHttp2(readInt);
            if (fromHttp2 != null) {
                handler.rstStream(i3, fromHttp2);
                return;
            }
            throw new IOException(Intrinsics.stringPlus("TYPE_RST_STREAM unexpected error code: ", Integer.valueOf(readInt)));
        } else {
            throw new IOException("TYPE_RST_STREAM streamId == 0");
        }
    }

    public final void j(Handler handler, int i, int i2, int i3) throws IOException {
        int readInt;
        if (i3 != 0) {
            throw new IOException("TYPE_SETTINGS streamId != 0");
        }
        if ((i2 & 1) != 0) {
            if (i == 0) {
                handler.ackSettings();
                return;
            }
            throw new IOException("FRAME_SIZE_ERROR ack frame should be empty!");
        } else if (i % 6 == 0) {
            Settings settings = new Settings();
            IntProgression step = h.step(h.until(0, i), 6);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                while (true) {
                    int i4 = first + step2;
                    int and = Util.and(this.h.readShort(), 65535);
                    readInt = this.h.readInt();
                    if (and != 2) {
                        if (and == 3) {
                            and = 4;
                        } else if (and == 4) {
                            and = 7;
                            if (readInt < 0) {
                                throw new IOException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1");
                            }
                        } else if (and == 5 && (readInt < 16384 || readInt > 16777215)) {
                            break;
                        }
                    } else if (readInt != 0 && readInt != 1) {
                        throw new IOException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1");
                    }
                    settings.set(and, readInt);
                    if (first == last) {
                        break;
                    }
                    first = i4;
                }
                throw new IOException(Intrinsics.stringPlus("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: ", Integer.valueOf(readInt)));
            }
            handler.settings(false, settings);
        } else {
            throw new IOException(Intrinsics.stringPlus("TYPE_SETTINGS length % 6 != 0: ", Integer.valueOf(i)));
        }
    }

    public final void k(Handler handler, int i, int i2, int i3) throws IOException {
        if (i == 4) {
            long and = Util.and(this.h.readInt(), 2147483647L);
            if (and != 0) {
                handler.windowUpdate(i3, and);
                return;
            }
            throw new IOException("windowSizeIncrement was 0");
        }
        throw new IOException(Intrinsics.stringPlus("TYPE_WINDOW_UPDATE length !=4: ", Integer.valueOf(i)));
    }

    public final boolean nextFrame(boolean z, @NotNull Handler handler) throws IOException {
        Intrinsics.checkNotNullParameter(handler, "handler");
        try {
            this.h.require(9L);
            int readMedium = Util.readMedium(this.h);
            if (readMedium <= 16384) {
                int and = Util.and(this.h.readByte(), 255);
                int and2 = Util.and(this.h.readByte(), 255);
                int readInt = this.h.readInt() & Integer.MAX_VALUE;
                Logger logger = l;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(Http2.INSTANCE.frameLog(true, readInt, readMedium, and, and2));
                }
                if (z && and != 4) {
                    throw new IOException(Intrinsics.stringPlus("Expected a SETTINGS frame but was ", Http2.INSTANCE.formattedType$okhttp(and)));
                }
                switch (and) {
                    case 0:
                        a(handler, readMedium, and2, readInt);
                        return true;
                    case 1:
                        d(handler, readMedium, and2, readInt);
                        return true;
                    case 2:
                        g(handler, readMedium, and2, readInt);
                        return true;
                    case 3:
                        i(handler, readMedium, and2, readInt);
                        return true;
                    case 4:
                        j(handler, readMedium, and2, readInt);
                        return true;
                    case 5:
                        h(handler, readMedium, and2, readInt);
                        return true;
                    case 6:
                        e(handler, readMedium, and2, readInt);
                        return true;
                    case 7:
                        b(handler, readMedium, and2, readInt);
                        return true;
                    case 8:
                        k(handler, readMedium, and2, readInt);
                        return true;
                    default:
                        this.h.skip(readMedium);
                        return true;
                }
            }
            throw new IOException(Intrinsics.stringPlus("FRAME_SIZE_ERROR: ", Integer.valueOf(readMedium)));
        } catch (EOFException unused) {
            return false;
        }
    }

    public final void readConnectionPreface(@NotNull Handler handler) throws IOException {
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (this.i) {
            if (!nextFrame(true, handler)) {
                throw new IOException("Required SETTINGS preface not received");
            }
            return;
        }
        BufferedSource bufferedSource = this.h;
        ByteString byteString = Http2.CONNECTION_PREFACE;
        ByteString readByteString = bufferedSource.readByteString(byteString.size());
        Logger logger = l;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Util.format(Intrinsics.stringPlus("<< CONNECTION ", readByteString.hex()), new Object[0]));
        }
        if (!Intrinsics.areEqual(byteString, readByteString)) {
            throw new IOException(Intrinsics.stringPlus("Expected a connection header but was ", readByteString.utf8()));
        }
    }
}
