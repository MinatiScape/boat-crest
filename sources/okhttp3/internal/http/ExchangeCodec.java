package okhttp3.internal.http;

import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.RealConnection;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public interface ExchangeCodec {
    @NotNull
    public static final Companion Companion = Companion.f14272a;
    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ Companion f14272a = new Companion();
    }

    void cancel();

    @NotNull
    Sink createRequestBody(@NotNull Request request, long j) throws IOException;

    void finishRequest() throws IOException;

    void flushRequest() throws IOException;

    @NotNull
    RealConnection getConnection();

    @NotNull
    Source openResponseBodySource(@NotNull Response response) throws IOException;

    @Nullable
    Response.Builder readResponseHeaders(boolean z) throws IOException;

    long reportedContentLength(@NotNull Response response) throws IOException;

    @NotNull
    Headers trailers() throws IOException;

    void writeRequestHeaders(@NotNull Request request) throws IOException;
}
