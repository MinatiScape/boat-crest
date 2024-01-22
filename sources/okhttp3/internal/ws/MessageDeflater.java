package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.Deflater;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.DeflaterSink;
import okio.Sink;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class MessageDeflater implements Closeable {
    public final boolean h;
    @NotNull
    public final Buffer i;
    @NotNull
    public final Deflater j;
    @NotNull
    public final DeflaterSink k;

    public MessageDeflater(boolean z) {
        this.h = z;
        Buffer buffer = new Buffer();
        this.i = buffer;
        Deflater deflater = new Deflater(-1, true);
        this.j = deflater;
        this.k = new DeflaterSink((Sink) buffer, deflater);
    }

    public final boolean a(Buffer buffer, ByteString byteString) {
        return buffer.rangeEquals(buffer.size() - byteString.size(), byteString);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.k.close();
    }

    public final void deflate(@NotNull Buffer buffer) throws IOException {
        ByteString byteString;
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (this.i.size() == 0) {
            if (this.h) {
                this.j.reset();
            }
            this.k.write(buffer, buffer.size());
            this.k.flush();
            Buffer buffer2 = this.i;
            byteString = MessageDeflaterKt.f14303a;
            if (a(buffer2, byteString)) {
                long size = this.i.size() - 4;
                Buffer.UnsafeCursor readAndWriteUnsafe$default = Buffer.readAndWriteUnsafe$default(this.i, null, 1, null);
                try {
                    readAndWriteUnsafe$default.resizeBuffer(size);
                    CloseableKt.closeFinally(readAndWriteUnsafe$default, null);
                } finally {
                }
            } else {
                this.i.writeByte(0);
            }
            Buffer buffer3 = this.i;
            buffer.write(buffer3, buffer3.size());
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }
}
