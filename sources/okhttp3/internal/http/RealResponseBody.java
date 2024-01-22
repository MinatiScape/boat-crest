package okhttp3.internal.http;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class RealResponseBody extends ResponseBody {
    @Nullable
    public final String i;
    public final long j;
    @NotNull
    public final BufferedSource k;

    public RealResponseBody(@Nullable String str, long j, @NotNull BufferedSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.i = str;
        this.j = j;
        this.k = source;
    }

    @Override // okhttp3.ResponseBody
    public long contentLength() {
        return this.j;
    }

    @Override // okhttp3.ResponseBody
    @Nullable
    public MediaType contentType() {
        String str = this.i;
        if (str == null) {
            return null;
        }
        return MediaType.Companion.parse(str);
    }

    @Override // okhttp3.ResponseBody
    @NotNull
    public BufferedSource source() {
        return this.k;
    }
}
