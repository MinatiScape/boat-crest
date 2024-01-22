package okhttp3.internal.cache;

import java.io.IOException;
import okio.Sink;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface CacheRequest {
    void abort();

    @NotNull
    Sink body() throws IOException;
}
