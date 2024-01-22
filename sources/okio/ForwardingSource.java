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
public abstract class ForwardingSource implements Source {
    @NotNull
    public final Source h;

    public ForwardingSource(@NotNull Source delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.h = delegate;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "delegate", imports = {}))
    @JvmName(name = "-deprecated_delegate")
    @NotNull
    /* renamed from: -deprecated_delegate  reason: not valid java name */
    public final Source m938deprecated_delegate() {
        return this.h;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h.close();
    }

    @JvmName(name = "delegate")
    @NotNull
    public final Source delegate() {
        return this.h;
    }

    @Override // okio.Source
    public long read(@NotNull Buffer sink, long j) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return this.h.read(sink, j);
    }

    @Override // okio.Source
    @NotNull
    public Timeout timeout() {
        return this.h.timeout();
    }

    @NotNull
    public String toString() {
        return getClass().getSimpleName() + HexStringBuilder.COMMENT_BEGIN_CHAR + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }
}
