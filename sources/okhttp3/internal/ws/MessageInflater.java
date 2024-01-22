package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.Inflater;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.InflaterSource;
import okio.Source;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class MessageInflater implements Closeable {
    public final boolean h;
    @NotNull
    public final Buffer i;
    @NotNull
    public final Inflater j;
    @NotNull
    public final InflaterSource k;

    public MessageInflater(boolean z) {
        this.h = z;
        Buffer buffer = new Buffer();
        this.i = buffer;
        Inflater inflater = new Inflater(true);
        this.j = inflater;
        this.k = new InflaterSource((Source) buffer, inflater);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.k.close();
    }

    public final void inflate(@NotNull Buffer buffer) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (this.i.size() == 0) {
            if (this.h) {
                this.j.reset();
            }
            this.i.writeAll(buffer);
            this.i.writeInt(65535);
            long bytesRead = this.j.getBytesRead() + this.i.size();
            do {
                this.k.readOrInflate(buffer, Long.MAX_VALUE);
            } while (this.j.getBytesRead() < bytesRead);
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }
}
