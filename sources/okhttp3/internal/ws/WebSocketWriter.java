package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.Random;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class WebSocketWriter implements Closeable {
    public final boolean h;
    @NotNull
    public final BufferedSink i;
    @NotNull
    public final Random j;
    public final boolean k;
    public final boolean l;
    public final long m;
    @NotNull
    public final Buffer n;
    @NotNull
    public final Buffer o;
    public boolean p;
    @Nullable
    public MessageDeflater q;
    @Nullable
    public final byte[] r;
    @Nullable
    public final Buffer.UnsafeCursor s;

    public WebSocketWriter(boolean z, @NotNull BufferedSink sink, @NotNull Random random, boolean z2, boolean z3, long j) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(random, "random");
        this.h = z;
        this.i = sink;
        this.j = random;
        this.k = z2;
        this.l = z3;
        this.m = j;
        this.n = new Buffer();
        this.o = sink.getBuffer();
        this.r = z ? new byte[4] : null;
        this.s = z ? new Buffer.UnsafeCursor() : null;
    }

    public final void a(int i, ByteString byteString) throws IOException {
        if (!this.p) {
            int size = byteString.size();
            if (((long) size) <= 125) {
                this.o.writeByte(i | 128);
                if (this.h) {
                    this.o.writeByte(size | 128);
                    Random random = this.j;
                    byte[] bArr = this.r;
                    Intrinsics.checkNotNull(bArr);
                    random.nextBytes(bArr);
                    this.o.write(this.r);
                    if (size > 0) {
                        long size2 = this.o.size();
                        this.o.write(byteString);
                        Buffer buffer = this.o;
                        Buffer.UnsafeCursor unsafeCursor = this.s;
                        Intrinsics.checkNotNull(unsafeCursor);
                        buffer.readAndWriteUnsafe(unsafeCursor);
                        this.s.seek(size2);
                        WebSocketProtocol.INSTANCE.toggleMask(this.s, this.r);
                        this.s.close();
                    }
                } else {
                    this.o.writeByte(size);
                    this.o.write(byteString);
                }
                this.i.flush();
                return;
            }
            throw new IllegalArgumentException("Payload size must be less than or equal to 125".toString());
        }
        throw new IOException("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        MessageDeflater messageDeflater = this.q;
        if (messageDeflater == null) {
            return;
        }
        messageDeflater.close();
    }

    @NotNull
    public final Random getRandom() {
        return this.j;
    }

    @NotNull
    public final BufferedSink getSink() {
        return this.i;
    }

    public final void writeClose(int i, @Nullable ByteString byteString) throws IOException {
        ByteString byteString2 = ByteString.EMPTY;
        if (i != 0 || byteString != null) {
            if (i != 0) {
                WebSocketProtocol.INSTANCE.validateCloseCode(i);
            }
            Buffer buffer = new Buffer();
            buffer.writeShort(i);
            if (byteString != null) {
                buffer.write(byteString);
            }
            byteString2 = buffer.readByteString();
        }
        try {
            a(8, byteString2);
        } finally {
            this.p = true;
        }
    }

    public final void writeMessageFrame(int i, @NotNull ByteString data) throws IOException {
        Intrinsics.checkNotNullParameter(data, "data");
        if (!this.p) {
            this.n.write(data);
            int i2 = i | 128;
            if (this.k && data.size() >= this.m) {
                MessageDeflater messageDeflater = this.q;
                if (messageDeflater == null) {
                    messageDeflater = new MessageDeflater(this.l);
                    this.q = messageDeflater;
                }
                messageDeflater.deflate(this.n);
                i2 |= 64;
            }
            long size = this.n.size();
            this.o.writeByte(i2);
            int i3 = this.h ? 128 : 0;
            if (size <= 125) {
                this.o.writeByte(((int) size) | i3);
            } else if (size <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                this.o.writeByte(i3 | 126);
                this.o.writeShort((int) size);
            } else {
                this.o.writeByte(i3 | 127);
                this.o.writeLong(size);
            }
            if (this.h) {
                Random random = this.j;
                byte[] bArr = this.r;
                Intrinsics.checkNotNull(bArr);
                random.nextBytes(bArr);
                this.o.write(this.r);
                if (size > 0) {
                    Buffer buffer = this.n;
                    Buffer.UnsafeCursor unsafeCursor = this.s;
                    Intrinsics.checkNotNull(unsafeCursor);
                    buffer.readAndWriteUnsafe(unsafeCursor);
                    this.s.seek(0L);
                    WebSocketProtocol.INSTANCE.toggleMask(this.s, this.r);
                    this.s.close();
                }
            }
            this.o.write(this.n, size);
            this.i.emit();
            return;
        }
        throw new IOException("closed");
    }

    public final void writePing(@NotNull ByteString payload) throws IOException {
        Intrinsics.checkNotNullParameter(payload, "payload");
        a(9, payload);
    }

    public final void writePong(@NotNull ByteString payload) throws IOException {
        Intrinsics.checkNotNullParameter(payload, "payload");
        a(10, payload);
    }
}
