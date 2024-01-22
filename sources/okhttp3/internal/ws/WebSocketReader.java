package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class WebSocketReader implements Closeable {
    public final boolean h;
    @NotNull
    public final BufferedSource i;
    @NotNull
    public final FrameCallback j;
    public final boolean k;
    public final boolean l;
    public boolean m;
    public int n;
    public long o;
    public boolean p;
    public boolean q;
    public boolean r;
    @NotNull
    public final Buffer s;
    @NotNull
    public final Buffer t;
    @Nullable
    public MessageInflater u;
    @Nullable
    public final byte[] v;
    @Nullable
    public final Buffer.UnsafeCursor w;

    /* loaded from: classes12.dex */
    public interface FrameCallback {
        void onReadClose(int i, @NotNull String str);

        void onReadMessage(@NotNull String str) throws IOException;

        void onReadMessage(@NotNull ByteString byteString) throws IOException;

        void onReadPing(@NotNull ByteString byteString);

        void onReadPong(@NotNull ByteString byteString);
    }

    public WebSocketReader(boolean z, @NotNull BufferedSource source, @NotNull FrameCallback frameCallback, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(frameCallback, "frameCallback");
        this.h = z;
        this.i = source;
        this.j = frameCallback;
        this.k = z2;
        this.l = z3;
        this.s = new Buffer();
        this.t = new Buffer();
        this.v = z ? null : new byte[4];
        this.w = z ? null : new Buffer.UnsafeCursor();
    }

    public final void a() throws IOException {
        String str;
        long j = this.o;
        if (j > 0) {
            this.i.readFully(this.s, j);
            if (!this.h) {
                Buffer buffer = this.s;
                Buffer.UnsafeCursor unsafeCursor = this.w;
                Intrinsics.checkNotNull(unsafeCursor);
                buffer.readAndWriteUnsafe(unsafeCursor);
                this.w.seek(0L);
                WebSocketProtocol webSocketProtocol = WebSocketProtocol.INSTANCE;
                Buffer.UnsafeCursor unsafeCursor2 = this.w;
                byte[] bArr = this.v;
                Intrinsics.checkNotNull(bArr);
                webSocketProtocol.toggleMask(unsafeCursor2, bArr);
                this.w.close();
            }
        }
        switch (this.n) {
            case 8:
                short s = 1005;
                long size = this.s.size();
                if (size != 1) {
                    if (size != 0) {
                        s = this.s.readShort();
                        str = this.s.readUtf8();
                        String closeCodeExceptionMessage = WebSocketProtocol.INSTANCE.closeCodeExceptionMessage(s);
                        if (closeCodeExceptionMessage != null) {
                            throw new ProtocolException(closeCodeExceptionMessage);
                        }
                    } else {
                        str = "";
                    }
                    this.j.onReadClose(s, str);
                    this.m = true;
                    return;
                }
                throw new ProtocolException("Malformed close payload length of 1.");
            case 9:
                this.j.onReadPing(this.s.readByteString());
                return;
            case 10:
                this.j.onReadPong(this.s.readByteString());
                return;
            default:
                throw new ProtocolException(Intrinsics.stringPlus("Unknown control opcode: ", Util.toHexString(this.n)));
        }
    }

    public final void b() throws IOException, ProtocolException {
        boolean z;
        if (!this.m) {
            long timeoutNanos = this.i.timeout().timeoutNanos();
            this.i.timeout().clearTimeout();
            try {
                int and = Util.and(this.i.readByte(), 255);
                this.i.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                int i = and & 15;
                this.n = i;
                boolean z2 = (and & 128) != 0;
                this.p = z2;
                boolean z3 = (and & 8) != 0;
                this.q = z3;
                if (z3 && !z2) {
                    throw new ProtocolException("Control frames must be final.");
                }
                boolean z4 = (and & 64) != 0;
                if (i == 1 || i == 2) {
                    if (!z4) {
                        z = false;
                    } else if (!this.k) {
                        throw new ProtocolException("Unexpected rsv1 flag");
                    } else {
                        z = true;
                    }
                    this.r = z;
                } else if (z4) {
                    throw new ProtocolException("Unexpected rsv1 flag");
                }
                if ((and & 32) != 0) {
                    throw new ProtocolException("Unexpected rsv2 flag");
                }
                if (!((and & 16) != 0)) {
                    int and2 = Util.and(this.i.readByte(), 255);
                    boolean z5 = (and2 & 128) != 0;
                    if (z5 == this.h) {
                        throw new ProtocolException(this.h ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
                    }
                    long j = and2 & 127;
                    this.o = j;
                    if (j == 126) {
                        this.o = Util.and(this.i.readShort(), 65535);
                    } else if (j == 127) {
                        long readLong = this.i.readLong();
                        this.o = readLong;
                        if (readLong < 0) {
                            throw new ProtocolException("Frame length 0x" + Util.toHexString(this.o) + " > 0x7FFFFFFFFFFFFFFF");
                        }
                    }
                    if (this.q && this.o > 125) {
                        throw new ProtocolException("Control frame must be less than 125B.");
                    }
                    if (z5) {
                        BufferedSource bufferedSource = this.i;
                        byte[] bArr = this.v;
                        Intrinsics.checkNotNull(bArr);
                        bufferedSource.readFully(bArr);
                        return;
                    }
                    return;
                }
                throw new ProtocolException("Unexpected rsv3 flag");
            } catch (Throwable th) {
                this.i.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                throw th;
            }
        }
        throw new IOException("closed");
    }

    public final void c() throws IOException {
        while (!this.m) {
            long j = this.o;
            if (j > 0) {
                this.i.readFully(this.t, j);
                if (!this.h) {
                    Buffer buffer = this.t;
                    Buffer.UnsafeCursor unsafeCursor = this.w;
                    Intrinsics.checkNotNull(unsafeCursor);
                    buffer.readAndWriteUnsafe(unsafeCursor);
                    this.w.seek(this.t.size() - this.o);
                    WebSocketProtocol webSocketProtocol = WebSocketProtocol.INSTANCE;
                    Buffer.UnsafeCursor unsafeCursor2 = this.w;
                    byte[] bArr = this.v;
                    Intrinsics.checkNotNull(bArr);
                    webSocketProtocol.toggleMask(unsafeCursor2, bArr);
                    this.w.close();
                }
            }
            if (this.p) {
                return;
            }
            e();
            if (this.n != 0) {
                throw new ProtocolException(Intrinsics.stringPlus("Expected continuation opcode. Got: ", Util.toHexString(this.n)));
            }
        }
        throw new IOException("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        MessageInflater messageInflater = this.u;
        if (messageInflater == null) {
            return;
        }
        messageInflater.close();
    }

    public final void d() throws IOException {
        int i = this.n;
        if (i != 1 && i != 2) {
            throw new ProtocolException(Intrinsics.stringPlus("Unknown opcode: ", Util.toHexString(i)));
        }
        c();
        if (this.r) {
            MessageInflater messageInflater = this.u;
            if (messageInflater == null) {
                messageInflater = new MessageInflater(this.l);
                this.u = messageInflater;
            }
            messageInflater.inflate(this.t);
        }
        if (i == 1) {
            this.j.onReadMessage(this.t.readUtf8());
        } else {
            this.j.onReadMessage(this.t.readByteString());
        }
    }

    public final void e() throws IOException {
        while (!this.m) {
            b();
            if (!this.q) {
                return;
            }
            a();
        }
    }

    @NotNull
    public final BufferedSource getSource() {
        return this.i;
    }

    public final void processNextFrame() throws IOException {
        b();
        if (this.q) {
            a();
        } else {
            d();
        }
    }
}
