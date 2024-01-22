package okio;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.IOException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public abstract class ForwardingSink implements Sink {
    @NotNull
    public final Sink h;

    public ForwardingSink(@NotNull Sink delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.h = delegate;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "delegate", imports = {}))
    @JvmName(name = "-deprecated_delegate")
    @NotNull
    /* renamed from: -deprecated_delegate  reason: not valid java name */
    public final Sink m937deprecated_delegate() {
        return this.h;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h.close();
    }

    @JvmName(name = "delegate")
    @NotNull
    public final Sink delegate() {
        return this.h;
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        this.h.flush();
    }

    @Override // okio.Sink
    @NotNull
    public Timeout timeout() {
        return this.h.timeout();
    }

    @NotNull
    public String toString() {
        return getClass().getSimpleName() + HexStringBuilder.COMMENT_BEGIN_CHAR + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // okio.Sink
    public void write(@NotNull Buffer source, long j) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        this.h.write(source, j);
    }
}
