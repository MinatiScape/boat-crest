package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Hpack;
import okio.Buffer;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Http2Writer implements Closeable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final Logger n = Logger.getLogger(Http2.class.getName());
    @NotNull
    public final BufferedSink h;
    public final boolean i;
    @NotNull
    public final Buffer j;
    public int k;
    public boolean l;
    @NotNull
    public final Hpack.Writer m;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Http2Writer(@NotNull BufferedSink sink, boolean z) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.h = sink;
        this.i = z;
        Buffer buffer = new Buffer();
        this.j = buffer;
        this.k = 16384;
        this.m = new Hpack.Writer(0, false, buffer, 3, null);
    }

    public final void a(int i, long j) throws IOException {
        while (j > 0) {
            long min = Math.min(this.k, j);
            j -= min;
            frameHeader(i, (int) min, 9, j == 0 ? 4 : 0);
            this.h.write(this.j, min);
        }
    }

    public final synchronized void applyAndAckSettings(@NotNull Settings peerSettings) throws IOException {
        Intrinsics.checkNotNullParameter(peerSettings, "peerSettings");
        if (!this.l) {
            this.k = peerSettings.getMaxFrameSize(this.k);
            if (peerSettings.getHeaderTableSize() != -1) {
                this.m.resizeHeaderTable(peerSettings.getHeaderTableSize());
            }
            frameHeader(0, 0, 4, 1);
            this.h.flush();
        } else {
            throw new IOException("closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.l = true;
        this.h.close();
    }

    public final synchronized void connectionPreface() throws IOException {
        if (!this.l) {
            if (this.i) {
                Logger logger = n;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(Util.format(Intrinsics.stringPlus(">> CONNECTION ", Http2.CONNECTION_PREFACE.hex()), new Object[0]));
                }
                this.h.write(Http2.CONNECTION_PREFACE);
                this.h.flush();
                return;
            }
            return;
        }
        throw new IOException("closed");
    }

    public final synchronized void data(boolean z, int i, @Nullable Buffer buffer, int i2) throws IOException {
        if (!this.l) {
            dataFrame(i, z ? 1 : 0, buffer, i2);
        } else {
            throw new IOException("closed");
        }
    }

    public final void dataFrame(int i, int i2, @Nullable Buffer buffer, int i3) throws IOException {
        frameHeader(i, i3, 0, i2);
        if (i3 > 0) {
            BufferedSink bufferedSink = this.h;
            Intrinsics.checkNotNull(buffer);
            bufferedSink.write(buffer, i3);
        }
    }

    public final synchronized void flush() throws IOException {
        if (!this.l) {
            this.h.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public final void frameHeader(int i, int i2, int i3, int i4) throws IOException {
        Logger logger = n;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Http2.INSTANCE.frameLog(false, i, i2, i3, i4));
        }
        if (!(i2 <= this.k)) {
            throw new IllegalArgumentException(("FRAME_SIZE_ERROR length > " + this.k + ": " + i2).toString());
        }
        if ((Integer.MIN_VALUE & i) == 0) {
            Util.writeMedium(this.h, i2);
            this.h.writeByte(i3 & 255);
            this.h.writeByte(i4 & 255);
            this.h.writeInt(i & Integer.MAX_VALUE);
            return;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("reserved bit set: ", Integer.valueOf(i)).toString());
    }

    @NotNull
    public final Hpack.Writer getHpackWriter() {
        return this.m;
    }

    public final synchronized void goAway(int i, @NotNull ErrorCode errorCode, @NotNull byte[] debugData) throws IOException {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        Intrinsics.checkNotNullParameter(debugData, "debugData");
        if (!this.l) {
            if (errorCode.getHttpCode() != -1) {
                frameHeader(0, debugData.length + 8, 7, 0);
                this.h.writeInt(i);
                this.h.writeInt(errorCode.getHttpCode());
                if (!(debugData.length == 0)) {
                    this.h.write(debugData);
                }
                this.h.flush();
            } else {
                throw new IllegalArgumentException("errorCode.httpCode == -1".toString());
            }
        } else {
            throw new IOException("closed");
        }
    }

    public final synchronized void headers(boolean z, int i, @NotNull List<Header> headerBlock) throws IOException {
        Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
        if (!this.l) {
            this.m.writeHeaders(headerBlock);
            long size = this.j.size();
            long min = Math.min(this.k, size);
            int i2 = (size > min ? 1 : (size == min ? 0 : -1));
            int i3 = i2 == 0 ? 4 : 0;
            if (z) {
                i3 |= 1;
            }
            frameHeader(i, (int) min, 1, i3);
            this.h.write(this.j, min);
            if (i2 > 0) {
                a(i, size - min);
            }
        } else {
            throw new IOException("closed");
        }
    }

    public final int maxDataLength() {
        return this.k;
    }

    public final synchronized void ping(boolean z, int i, int i2) throws IOException {
        if (!this.l) {
            frameHeader(0, 8, 6, z ? 1 : 0);
            this.h.writeInt(i);
            this.h.writeInt(i2);
            this.h.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public final synchronized void pushPromise(int i, int i2, @NotNull List<Header> requestHeaders) throws IOException {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        if (!this.l) {
            this.m.writeHeaders(requestHeaders);
            long size = this.j.size();
            int min = (int) Math.min(this.k - 4, size);
            int i3 = min + 4;
            long j = min;
            int i4 = (size > j ? 1 : (size == j ? 0 : -1));
            frameHeader(i, i3, 5, i4 == 0 ? 4 : 0);
            this.h.writeInt(i2 & Integer.MAX_VALUE);
            this.h.write(this.j, j);
            if (i4 > 0) {
                a(i, size - j);
            }
        } else {
            throw new IOException("closed");
        }
    }

    public final synchronized void rstStream(int i, @NotNull ErrorCode errorCode) throws IOException {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        if (!this.l) {
            if (errorCode.getHttpCode() != -1) {
                frameHeader(i, 4, 3, 0);
                this.h.writeInt(errorCode.getHttpCode());
                this.h.flush();
            } else {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        } else {
            throw new IOException("closed");
        }
    }

    public final synchronized void settings(@NotNull Settings settings) throws IOException {
        Intrinsics.checkNotNullParameter(settings, "settings");
        if (!this.l) {
            int i = 0;
            frameHeader(0, settings.size() * 6, 4, 0);
            while (i < 10) {
                int i2 = i + 1;
                if (settings.isSet(i)) {
                    this.h.writeShort(i != 4 ? i != 7 ? i : 4 : 3);
                    this.h.writeInt(settings.get(i));
                }
                i = i2;
            }
            this.h.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public final synchronized void windowUpdate(int i, long j) throws IOException {
        if (this.l) {
            throw new IOException("closed");
        }
        if (j != 0 && j <= 2147483647L) {
            frameHeader(i, 4, 8, 0);
            this.h.writeInt((int) j);
            this.h.flush();
        } else {
            throw new IllegalArgumentException(Intrinsics.stringPlus("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: ", Long.valueOf(j)).toString());
        }
    }
}
