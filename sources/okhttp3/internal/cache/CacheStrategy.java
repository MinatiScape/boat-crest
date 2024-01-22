package okhttp3.internal.cache;

import com.google.common.net.HttpHeaders;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class CacheStrategy {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Request f14250a;
    @Nullable
    public final Response b;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isCacheable(@NotNull Response response, @NotNull Request request) {
            Intrinsics.checkNotNullParameter(response, "response");
            Intrinsics.checkNotNullParameter(request, "request");
            int code = response.code();
            if (code != 200 && code != 410 && code != 414 && code != 501 && code != 203 && code != 204) {
                if (code != 307) {
                    if (code != 308 && code != 404 && code != 405) {
                        switch (code) {
                            case 300:
                            case 301:
                                break;
                            case 302:
                                break;
                            default:
                                return false;
                        }
                    }
                }
                if (Response.header$default(response, HttpHeaders.EXPIRES, null, 2, null) == null && response.cacheControl().maxAgeSeconds() == -1 && !response.cacheControl().isPublic() && !response.cacheControl().isPrivate()) {
                    return false;
                }
            }
            return (response.cacheControl().noStore() || request.cacheControl().noStore()) ? false : true;
        }
    }

    /* loaded from: classes12.dex */
    public static final class Factory {

        /* renamed from: a  reason: collision with root package name */
        public final long f14251a;
        @NotNull
        public final Request b;
        @Nullable
        public final Response c;
        @Nullable
        public Date d;
        @Nullable
        public String e;
        @Nullable
        public Date f;
        @Nullable
        public String g;
        @Nullable
        public Date h;
        public long i;
        public long j;
        @Nullable
        public String k;
        public int l;

        public Factory(long j, @NotNull Request request, @Nullable Response response) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.f14251a = j;
            this.b = request;
            this.c = response;
            this.l = -1;
            if (response != null) {
                this.i = response.sentRequestAtMillis();
                this.j = response.receivedResponseAtMillis();
                Headers headers = response.headers();
                int i = 0;
                int size = headers.size();
                while (i < size) {
                    int i2 = i + 1;
                    String name = headers.name(i);
                    String value = headers.value(i);
                    if (m.equals(name, HttpHeaders.DATE, true)) {
                        this.d = DatesKt.toHttpDateOrNull(value);
                        this.e = value;
                    } else if (m.equals(name, HttpHeaders.EXPIRES, true)) {
                        this.h = DatesKt.toHttpDateOrNull(value);
                    } else if (m.equals(name, HttpHeaders.LAST_MODIFIED, true)) {
                        this.f = DatesKt.toHttpDateOrNull(value);
                        this.g = value;
                    } else if (m.equals(name, HttpHeaders.ETAG, true)) {
                        this.k = value;
                    } else if (m.equals(name, "Age", true)) {
                        this.l = Util.toNonNegativeInt(value, -1);
                    }
                    i = i2;
                }
            }
        }

        public final long a() {
            Date date = this.d;
            long max = date != null ? Math.max(0L, this.j - date.getTime()) : 0L;
            int i = this.l;
            if (i != -1) {
                max = Math.max(max, TimeUnit.SECONDS.toMillis(i));
            }
            long j = this.j;
            return max + (j - this.i) + (this.f14251a - j);
        }

        public final CacheStrategy b() {
            if (this.c == null) {
                return new CacheStrategy(this.b, null);
            }
            if (this.b.isHttps() && this.c.handshake() == null) {
                return new CacheStrategy(this.b, null);
            }
            if (!CacheStrategy.Companion.isCacheable(this.c, this.b)) {
                return new CacheStrategy(this.b, null);
            }
            CacheControl cacheControl = this.b.cacheControl();
            if (!cacheControl.noCache() && !d(this.b)) {
                CacheControl cacheControl2 = this.c.cacheControl();
                long a2 = a();
                long c = c();
                if (cacheControl.maxAgeSeconds() != -1) {
                    c = Math.min(c, TimeUnit.SECONDS.toMillis(cacheControl.maxAgeSeconds()));
                }
                long j = 0;
                long millis = cacheControl.minFreshSeconds() != -1 ? TimeUnit.SECONDS.toMillis(cacheControl.minFreshSeconds()) : 0L;
                if (!cacheControl2.mustRevalidate() && cacheControl.maxStaleSeconds() != -1) {
                    j = TimeUnit.SECONDS.toMillis(cacheControl.maxStaleSeconds());
                }
                if (!cacheControl2.noCache()) {
                    long j2 = millis + a2;
                    if (j2 < j + c) {
                        Response.Builder newBuilder = this.c.newBuilder();
                        if (j2 >= c) {
                            newBuilder.addHeader(HttpHeaders.WARNING, "110 HttpURLConnection \"Response is stale\"");
                        }
                        if (a2 > 86400000 && e()) {
                            newBuilder.addHeader(HttpHeaders.WARNING, "113 HttpURLConnection \"Heuristic expiration\"");
                        }
                        return new CacheStrategy(null, newBuilder.build());
                    }
                }
                String str = this.k;
                String str2 = HttpHeaders.IF_MODIFIED_SINCE;
                if (str != null) {
                    str2 = HttpHeaders.IF_NONE_MATCH;
                } else if (this.f != null) {
                    str = this.g;
                } else if (this.d != null) {
                    str = this.e;
                } else {
                    return new CacheStrategy(this.b, null);
                }
                Headers.Builder newBuilder2 = this.b.headers().newBuilder();
                Intrinsics.checkNotNull(str);
                newBuilder2.addLenient$okhttp(str2, str);
                return new CacheStrategy(this.b.newBuilder().headers(newBuilder2.build()).build(), this.c);
            }
            return new CacheStrategy(this.b, null);
        }

        public final long c() {
            Long valueOf;
            Response response = this.c;
            Intrinsics.checkNotNull(response);
            CacheControl cacheControl = response.cacheControl();
            if (cacheControl.maxAgeSeconds() != -1) {
                return TimeUnit.SECONDS.toMillis(cacheControl.maxAgeSeconds());
            }
            Date date = this.h;
            if (date != null) {
                Date date2 = this.d;
                valueOf = date2 != null ? Long.valueOf(date2.getTime()) : null;
                long time = date.getTime() - (valueOf == null ? this.j : valueOf.longValue());
                if (time > 0) {
                    return time;
                }
                return 0L;
            } else if (this.f == null || this.c.request().url().query() != null) {
                return 0L;
            } else {
                Date date3 = this.d;
                valueOf = date3 != null ? Long.valueOf(date3.getTime()) : null;
                long longValue = valueOf == null ? this.i : valueOf.longValue();
                Date date4 = this.f;
                Intrinsics.checkNotNull(date4);
                long time2 = longValue - date4.getTime();
                if (time2 > 0) {
                    return time2 / 10;
                }
                return 0L;
            }
        }

        @NotNull
        public final CacheStrategy compute() {
            CacheStrategy b = b();
            return (b.getNetworkRequest() == null || !this.b.cacheControl().onlyIfCached()) ? b : new CacheStrategy(null, null);
        }

        public final boolean d(Request request) {
            return (request.header(HttpHeaders.IF_MODIFIED_SINCE) == null && request.header(HttpHeaders.IF_NONE_MATCH) == null) ? false : true;
        }

        public final boolean e() {
            Response response = this.c;
            Intrinsics.checkNotNull(response);
            return response.cacheControl().maxAgeSeconds() == -1 && this.h == null;
        }

        @NotNull
        public final Request getRequest$okhttp() {
            return this.b;
        }
    }

    public CacheStrategy(@Nullable Request request, @Nullable Response response) {
        this.f14250a = request;
        this.b = response;
    }

    @Nullable
    public final Response getCacheResponse() {
        return this.b;
    }

    @Nullable
    public final Request getNetworkRequest() {
        return this.f14250a;
    }
}
