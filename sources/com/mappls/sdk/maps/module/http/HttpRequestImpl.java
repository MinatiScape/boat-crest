package com.mappls.sdk.maps.module.http;

import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.common.net.HttpHeaders;
import com.mappls.sdk.maps.MapplsMapConfiguration;
import com.mappls.sdk.maps.constants.MapplsConstants;
import com.mappls.sdk.maps.http.HttpLogger;
import com.mappls.sdk.maps.http.HttpRequest;
import com.mappls.sdk.maps.http.HttpResponder;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.utils.ApiCallHelper;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
/* loaded from: classes11.dex */
public class HttpRequestImpl implements HttpRequest {
    public static final String b = ApiCallHelper.getHeaderUserAgent();
    public static b c = new b();
    public static com.mappls.sdk.maps.module.http.a d = new com.mappls.sdk.maps.module.http.a();
    @VisibleForTesting
    public static final OkHttpClient e;
    @VisibleForTesting
    public static OkHttpClient f;

    /* renamed from: a  reason: collision with root package name */
    public Call f12787a;

    /* loaded from: classes11.dex */
    public static class a implements Callback {

        /* renamed from: a  reason: collision with root package name */
        public HttpResponder f12788a;

        public a(HttpResponder httpResponder) {
            this.f12788a = httpResponder;
        }

        public final int b(Exception exc) {
            if ((exc instanceof NoRouteToHostException) || (exc instanceof UnknownHostException) || (exc instanceof SocketException) || (exc instanceof ProtocolException) || (exc instanceof SSLException)) {
                return 0;
            }
            return exc instanceof InterruptedIOException ? 1 : 2;
        }

        public final void c(@Nullable Call call, Exception exc) {
            String message = exc.getMessage() != null ? exc.getMessage() : "Error processing the request";
            int b = b(exc);
            if (HttpLogger.logEnabled && call != null && call.request() != null) {
                HttpLogger.logFailure(b, message, call.request().url().toString());
            }
            this.f12788a.handleFailure(b, message);
        }

        @Override // okhttp3.Callback
        public void onFailure(@NonNull Call call, @NonNull IOException iOException) {
            c(call, iOException);
        }

        @Override // okhttp3.Callback
        public void onResponse(@NonNull Call call, @NonNull Response response) {
            if (response.isSuccessful()) {
                HttpLogger.log(2, String.format("[HTTP] Request was successful (code = %s).", Integer.valueOf(response.code())));
            } else {
                HttpLogger.log(3, String.format("[HTTP] Request with response = %s: %s", Integer.valueOf(response.code()), !TextUtils.isEmpty(response.message()) ? response.message() : "No additional information"));
            }
            ResponseBody body = response.body();
            try {
                if (body == null) {
                    HttpLogger.log(6, "[HTTP] Received empty response body");
                    return;
                }
                try {
                    byte[] bytes = body.bytes();
                    response.close();
                    this.f12788a.onResponse(response.code(), response.header(HttpHeaders.ETAG), response.header(HttpHeaders.LAST_MODIFIED), response.header(HttpHeaders.CACHE_CONTROL), response.header(HttpHeaders.EXPIRES), response.header(HttpHeaders.RETRY_AFTER), response.header("x-rate-limit-reset"), bytes);
                } catch (IOException e) {
                    onFailure(call, e);
                    response.close();
                }
            } catch (Throwable th) {
                response.close();
                throw th;
            }
        }
    }

    static {
        OkHttpClient build = new OkHttpClient.Builder().dispatcher(a()).addInterceptor(c).addInterceptor(d).build();
        e = build;
        f = build;
    }

    public HttpRequestImpl() {
        if (c.a() == null) {
            c.b(f);
        }
        if (d.a() == null) {
            d.c(f);
        }
    }

    @NonNull
    public static Dispatcher a() {
        Dispatcher dispatcher = new Dispatcher();
        if (Build.VERSION.SDK_INT >= 21) {
            dispatcher.setMaxRequestsPerHost(20);
        } else {
            dispatcher.setMaxRequestsPerHost(10);
        }
        return dispatcher;
    }

    public static void enableLog(boolean z) {
        HttpLogger.logEnabled = z;
    }

    public static void enablePrintRequestUrlOnFailure(boolean z) {
        HttpLogger.logRequestUrl = z;
    }

    public static void setOkHttpClient(@Nullable OkHttpClient okHttpClient) {
        if (okHttpClient != null) {
            f = okHttpClient;
        } else {
            f = e;
        }
    }

    public static void setProxy(String str, int i) {
        f.newBuilder().proxy(new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(str, i)));
    }

    @Override // com.mappls.sdk.maps.http.HttpRequest
    public void cancelRequest() {
        Call call = this.f12787a;
        if (call != null) {
            HttpLogger.log(3, String.format("[HTTP] This request was cancelled (%s). This is expected for tiles that were being prefetched but are no longer needed for the map to render.", call.request().url()));
            this.f12787a.cancel();
        }
    }

    @Override // com.mappls.sdk.maps.http.HttpRequest
    public void executeRequest(HttpResponder httpResponder, long j, @NonNull String str, @NonNull String str2, @NonNull String str3, boolean z) {
        String str4 = "map_tile";
        a aVar = new a(httpResponder);
        try {
            String replace = str.replace("mmi_h", "mt1");
            if (MapplsAccountManager.getInstance().getMapSDKKey() != null) {
                replace = replace.replace("v_mmi", MapplsAccountManager.getInstance().getMapSDKKey());
            }
            if (!replace.startsWith("http")) {
                replace = "https://" + replace;
            }
            if (!MapplsMapConfiguration.getInstance().isAllowOtherUrls() && !replace.toLowerCase().contains("mapmyindia") && !replace.toLowerCase().contains(DirectionsCriteria.PROFILE_DEFAULT_USER)) {
                HttpLogger.log(6, String.format("[HTTP] Invalid resourceUrl %s", replace));
            } else if (HttpUrl.parse(replace) == null) {
                HttpLogger.log(6, String.format("[HTTP] Unable to parse resourceUrl %s", replace));
            } else {
                Request.Builder builder = new Request.Builder();
                if (!MapplsMapConfiguration.getInstance().isUsingRasterStyle() && ((replace.contains("/vector_tile/") || replace.contains("/map_tile/") || replace.contains("/vectorTiles/")) && MapplsMapManager.getInstance().getRawPublicKey() != null)) {
                    if (!replace.contains("map_tile")) {
                        str4 = "vector_tile";
                    }
                    String replace2 = replace.replace(str4, "vectorTiles");
                    String substring = replace2.substring(replace2.lastIndexOf("vectorTiles/") + 12, replace2.length());
                    replace = replace2.replace(substring, "pbf");
                    String str5 = new String(Base64.encode(c.b().a(d.a().b(), substring), 2));
                    builder.header("Content-Type", "text/plain");
                    builder.header("TILE", str5);
                }
                builder.url(replace).tag(replace.toLowerCase(MapplsConstants.MAPPLS_LOCALE)).addHeader(HttpHeaders.USER_AGENT, b);
                if (str2.length() > 0) {
                    builder.addHeader(HttpHeaders.IF_NONE_MATCH, str2);
                } else if (str3.length() > 0) {
                    builder.addHeader(HttpHeaders.IF_MODIFIED_SINCE, str3);
                }
                Call newCall = f.newCall(builder.build());
                this.f12787a = newCall;
                newCall.enqueue(aVar);
            }
        } catch (Exception e2) {
            aVar.c(this.f12787a, e2);
        }
    }
}
