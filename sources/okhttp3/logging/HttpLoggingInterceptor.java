package okhttp3.logging;

import com.facebook.stetho.inspector.network.DecompressionHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.collections.a0;
import kotlin.collections.i;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.m;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class HttpLoggingInterceptor implements Interceptor {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Logger f14308a;
    @NotNull
    public volatile Set<String> b;
    @NotNull
    public volatile Level c;

    /* loaded from: classes12.dex */
    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    /* loaded from: classes12.dex */
    public interface Logger {
        @NotNull
        public static final Companion Companion = Companion.f14309a;
        @JvmField
        @NotNull
        public static final Logger DEFAULT = new Companion.a();

        /* loaded from: classes12.dex */
        public static final class Companion {

            /* renamed from: a  reason: collision with root package name */
            public static final /* synthetic */ Companion f14309a = new Companion();

            /* loaded from: classes12.dex */
            public static final class a implements Logger {
                @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
                public void log(@NotNull String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    Platform.log$default(Platform.Companion.get(), message, 0, null, 6, null);
                }
            }
        }

        void log(@NotNull String str);
    }

    @JvmOverloads
    public HttpLoggingInterceptor() {
        this(null, 1, null);
    }

    @JvmOverloads
    public HttpLoggingInterceptor(@NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.f14308a = logger;
        this.b = a0.emptySet();
        this.c = Level.NONE;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = FirebaseAnalytics.Param.LEVEL, imports = {}))
    @JvmName(name = "-deprecated_level")
    @NotNull
    /* renamed from: -deprecated_level  reason: not valid java name */
    public final Level m921deprecated_level() {
        return this.c;
    }

    public final boolean a(Headers headers) {
        String str = headers.get(HttpHeaders.CONTENT_ENCODING);
        return (str == null || m.equals(str, "identity", true) || m.equals(str, DecompressionHelper.GZIP_ENCODING, true)) ? false : true;
    }

    public final void b(Headers headers, int i) {
        String value = this.b.contains(headers.name(i)) ? "██" : headers.value(i);
        Logger logger = this.f14308a;
        logger.log(headers.name(i) + ": " + value);
    }

    @NotNull
    public final Level getLevel() {
        return this.c;
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        String str;
        char c;
        String sb;
        Charset charset;
        Long l;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Level level = this.c;
        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }
        boolean z = level == Level.BODY;
        boolean z2 = z || level == Level.HEADERS;
        RequestBody body = request.body();
        Connection connection = chain.connection();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("--> ");
        sb2.append(request.method());
        sb2.append(' ');
        sb2.append(request.url());
        sb2.append(connection != null ? Intrinsics.stringPlus(HexStringBuilder.DEFAULT_SEPARATOR, connection.protocol()) : "");
        String sb3 = sb2.toString();
        if (!z2 && body != 0) {
            sb3 = sb3 + " (" + body.contentLength() + "-byte body)";
        }
        this.f14308a.log(sb3);
        if (z2) {
            Headers headers = request.headers();
            if (body != null) {
                MediaType contentType = body.contentType();
                if (contentType != null && headers.get("Content-Type") == null) {
                    this.f14308a.log(Intrinsics.stringPlus("Content-Type: ", contentType));
                }
                if (body.contentLength() != -1 && headers.get("Content-Length") == null) {
                    this.f14308a.log(Intrinsics.stringPlus("Content-Length: ", Long.valueOf(body.contentLength())));
                }
            }
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                b(headers, i);
            }
            if (z && body != null) {
                if (a(request.headers())) {
                    this.f14308a.log("--> END " + request.method() + " (encoded body omitted)");
                } else if (body.isDuplex()) {
                    this.f14308a.log("--> END " + request.method() + " (duplex request body omitted)");
                } else if (body.isOneShot()) {
                    this.f14308a.log("--> END " + request.method() + " (one-shot body omitted)");
                } else {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    MediaType contentType2 = body.contentType();
                    Charset UTF_8 = contentType2 == null ? null : contentType2.charset(StandardCharsets.UTF_8);
                    if (UTF_8 == null) {
                        UTF_8 = StandardCharsets.UTF_8;
                        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
                    }
                    this.f14308a.log("");
                    if (Utf8Kt.isProbablyUtf8(buffer)) {
                        this.f14308a.log(buffer.readString(UTF_8));
                        this.f14308a.log("--> END " + request.method() + " (" + body.contentLength() + "-byte body)");
                    } else {
                        this.f14308a.log("--> END " + request.method() + " (binary " + body.contentLength() + "-byte body omitted)");
                    }
                }
            } else {
                this.f14308a.log(Intrinsics.stringPlus("--> END ", request.method()));
            }
        }
        long nanoTime = System.nanoTime();
        try {
            Response proceed = chain.proceed(request);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
            ResponseBody body2 = proceed.body();
            Intrinsics.checkNotNull(body2);
            long contentLength = body2.contentLength();
            String str2 = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
            Logger logger = this.f14308a;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("<-- ");
            sb4.append(proceed.code());
            if (proceed.message().length() == 0) {
                str = "-byte body omitted)";
                sb = "";
                c = ' ';
            } else {
                String message = proceed.message();
                StringBuilder sb5 = new StringBuilder();
                str = "-byte body omitted)";
                c = ' ';
                sb5.append(' ');
                sb5.append(message);
                sb = sb5.toString();
            }
            sb4.append(sb);
            sb4.append(c);
            sb4.append(proceed.request().url());
            sb4.append(" (");
            sb4.append(millis);
            sb4.append("ms");
            sb4.append(z2 ? "" : ", " + str2 + " body");
            sb4.append(HexStringBuilder.COMMENT_END_CHAR);
            logger.log(sb4.toString());
            if (z2) {
                Headers headers2 = proceed.headers();
                int size2 = headers2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    b(headers2, i2);
                }
                if (z && okhttp3.internal.http.HttpHeaders.promisesBody(proceed)) {
                    if (a(proceed.headers())) {
                        this.f14308a.log("<-- END HTTP (encoded body omitted)");
                    } else {
                        BufferedSource source = body2.source();
                        source.request(Long.MAX_VALUE);
                        Buffer buffer2 = source.getBuffer();
                        if (m.equals(DecompressionHelper.GZIP_ENCODING, headers2.get(HttpHeaders.CONTENT_ENCODING), true)) {
                            l = Long.valueOf(buffer2.size());
                            GzipSource gzipSource = new GzipSource(buffer2.clone());
                            try {
                                buffer2 = new Buffer();
                                buffer2.writeAll(gzipSource);
                                charset = null;
                                CloseableKt.closeFinally(gzipSource, null);
                            } finally {
                            }
                        } else {
                            charset = null;
                            l = null;
                        }
                        MediaType contentType3 = body2.contentType();
                        Charset UTF_82 = contentType3 == null ? charset : contentType3.charset(StandardCharsets.UTF_8);
                        if (UTF_82 == null) {
                            UTF_82 = StandardCharsets.UTF_8;
                            Intrinsics.checkNotNullExpressionValue(UTF_82, "UTF_8");
                        }
                        if (!Utf8Kt.isProbablyUtf8(buffer2)) {
                            this.f14308a.log("");
                            this.f14308a.log("<-- END HTTP (binary " + buffer2.size() + str);
                            return proceed;
                        }
                        if (contentLength != 0) {
                            this.f14308a.log("");
                            this.f14308a.log(buffer2.clone().readString(UTF_82));
                        }
                        if (l != null) {
                            this.f14308a.log("<-- END HTTP (" + buffer2.size() + "-byte, " + l + "-gzipped-byte body)");
                        } else {
                            this.f14308a.log("<-- END HTTP (" + buffer2.size() + "-byte body)");
                        }
                    }
                } else {
                    this.f14308a.log("<-- END HTTP");
                }
            }
            return proceed;
        } catch (Exception e) {
            this.f14308a.log(Intrinsics.stringPlus("<-- HTTP FAILED: ", e));
            throw e;
        }
    }

    @JvmName(name = FirebaseAnalytics.Param.LEVEL)
    public final void level(@NotNull Level level) {
        Intrinsics.checkNotNullParameter(level, "<set-?>");
        this.c = level;
    }

    public final void redactHeader(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        TreeSet treeSet = new TreeSet(m.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        i.addAll(treeSet, this.b);
        treeSet.add(name);
        this.b = treeSet;
    }

    @NotNull
    public final HttpLoggingInterceptor setLevel(@NotNull Level level) {
        Intrinsics.checkNotNullParameter(level, "level");
        level(level);
        return this;
    }

    public /* synthetic */ HttpLoggingInterceptor(Logger logger, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Logger.DEFAULT : logger);
    }
}
