package com.coveiot.coveaccess;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.constants.CoveApiHeaderConstants;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.model.server.AuthErrorModel;
import com.coveiot.coveaccess.model.server.AuthErrorType;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.Authenticator;
import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/* loaded from: classes8.dex */
public final class CoveApi {
    public static String GATEWAY_API_KEY = "uvfOkrshtW7ei6b4whG5L6vUBlsYisBO8Mt5dF1u";
    public static String GATEWAY_BASE_URL = "https://aiml.us.cove.kahaapi.com/";
    public static String TAPPY_API_URL = "https://gateway.pay.cove.kahaapi.com/tpysrv/";
    public static String TAPPY_PROXY_API_URL = "https://gateway.pay.cove.kahaapi.com/tpysrv/prx/";

    /* renamed from: a  reason: collision with root package name */
    public static final String f6257a = "CoveApi";
    public static String b = "https://prod.cove.kahaapi.com/";
    public static final HashMap<String, String> c = new HashMap<>();
    public static CoveApi d = null;
    public static CoveApiService e = null;
    public static String f = "NA";
    public static String g = "NA";
    public static String h = "NA";
    public static String i = "NA";
    public static final List<String> immutableHeaders = Arrays.asList(CoveApiHeaderConstants.HTTP_HEADER_SESSION_ID, CoveApiHeaderConstants.HTTP_HEADER_API_KEY);
    public static Context j;

    /* loaded from: classes8.dex */
    public class CertificateExpiryInterceptor implements Interceptor {
        public CertificateExpiryInterceptor(CoveApi coveApi) {
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Response proceed = chain.proceed(chain.request());
            try {
                Certificate[] certificateArr = (Certificate[]) proceed.handshake().peerCertificates().toArray(new Certificate[0]);
                if (certificateArr.length > 0 && (certificateArr[0] instanceof X509Certificate)) {
                    if (new Date().after(((X509Certificate) certificateArr[0]).getNotAfter())) {
                        LocalBroadcastManager.getInstance(CoveApi.j).sendBroadcast(new Intent(ApiConsts.SSL_CERTIFICATE_EXPIRY_BROADCAST_INTENT_ACTION));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return proceed;
        }
    }

    /* loaded from: classes8.dex */
    public class CoveApiLoggerHelper implements HttpLoggingInterceptor.Logger {
        public CoveApiLoggerHelper(CoveApi coveApi) {
        }

        @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
        public void log(String str) {
            LogHelper.d(CoveApi.f6257a, str);
        }
    }

    /* loaded from: classes8.dex */
    public class b implements Interceptor {
        public b(CoveApi coveApi) {
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException, SSLPeerUnverifiedException {
            Request request = chain.request();
            if (request.headers() != null) {
                try {
                    Response proceed = chain.proceed(request);
                    if (proceed.code() == 401) {
                        BufferedSource source = proceed.body().source();
                        source.request(Long.MAX_VALUE);
                        Buffer buffer = source.buffer();
                        Charset forName = Charset.forName("UTF-8");
                        MediaType contentType = proceed.body().contentType();
                        if (contentType != null) {
                            try {
                                forName = contentType.charset(forName);
                            } catch (UnsupportedCharsetException e) {
                                e.printStackTrace();
                            }
                        }
                        if (proceed.body().contentLength() != 0) {
                            AuthErrorModel authErrorModel = (AuthErrorModel) new Gson().fromJson(buffer.clone().readString(forName), (Class<Object>) AuthErrorModel.class);
                            if (authErrorModel != null && authErrorModel.getCode().equalsIgnoreCase(AuthErrorType.ERROR_AUTH_200498.toString()) && CoveApi.j != null) {
                                CoveApi.j.sendBroadcast(new Intent("session_expiry_brodcast"));
                            }
                        }
                    }
                    return proceed;
                } catch (SSLPeerUnverifiedException e2) {
                    e2.printStackTrace();
                    LocalBroadcastManager.getInstance(CoveApi.j).sendBroadcast(new Intent(ApiConsts.SSL_CERTIFICATE_EXPIRY_BROADCAST_INTENT_ACTION));
                    throw e2;
                } catch (Exception e3) {
                    throw e3;
                }
            }
            throw new RuntimeException("Need to declare @HeaderMap property for Url " + request.url().toString());
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Authenticator {

        /* renamed from: a  reason: collision with root package name */
        public static final Object f6258a = new Object();

        public c() {
        }

        @Override // okhttp3.Authenticator
        @Nullable
        public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
            if (!AppUtils.isEmpty(PreferenceManager.getInstance().getRefreshToken())) {
                synchronized (f6258a) {
                    if (!response.request().url().toString().contains("eg/auth/token")) {
                        String b = com.coveiot.coveaccess.a.a(CoveApi.j).b();
                        if (!AppUtils.isEmpty(b)) {
                            HashMap hashMap = CoveApi.c;
                            hashMap.put("Authorization", "Bearer " + b);
                            Request.Builder newBuilder = response.request().newBuilder();
                            return newBuilder.header("Authorization", "Bearer " + b).build();
                        }
                    } else {
                        LogHelper.d("Auth request body", response.request().body().toString());
                        CoveApi.j.sendBroadcast(new Intent("session_expiry_brodcast"));
                        PreferenceManager.getInstance().clearLoginDetails();
                    }
                    return null;
                }
            }
            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset forName = Charset.forName("UTF-8");
            MediaType contentType = response.body().contentType();
            if (contentType != null) {
                try {
                    forName = contentType.charset(forName);
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                }
            }
            if (response.body().contentLength() != 0) {
                AuthErrorModel authErrorModel = (AuthErrorModel) new Gson().fromJson(buffer.clone().readString(forName), (Class<Object>) AuthErrorModel.class);
                if (authErrorModel == null || authErrorModel.getCode() == null || !authErrorModel.getCode().equalsIgnoreCase(AuthErrorType.ERROR_AUTH_200498.toString()) || CoveApi.j == null) {
                    return null;
                }
                CoveApi.j.sendBroadcast(new Intent("session_expiry_brodcast"));
                PreferenceManager.getInstance().clearLoginDetails();
                return null;
            }
            return null;
        }
    }

    public CoveApi(@Nullable SSLConfig sSLConfig) {
        e = (CoveApiService) new Retrofit.Builder().baseUrl(b).addConverterFactory(GsonConverterFactory.create()).client(b(sSLConfig)).build().create(CoveApiService.class);
    }

    public static void c(Context context) {
        HashMap<String, String> hashMap = c;
        hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_API_KEY, f);
        if (!i.equalsIgnoreCase("NA")) {
            hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_TAG_ID, i);
        }
        hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_CLOVE_API_VERSION, "1");
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            StringBuilder sb = new StringBuilder();
            sb.append(h);
            sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            sb.append(str);
            sb.append(" (android/");
            sb.append(Build.VERSION.RELEASE);
            sb.append(";");
            sb.append(Build.MANUFACTURER.replace(";", HexStringBuilder.DEFAULT_SEPARATOR));
            sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            String str2 = Build.MODEL;
            sb.append(str2.replace(";", HexStringBuilder.DEFAULT_SEPARATOR));
            sb.append(")");
            hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_USER_AGENT, sb.toString());
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_DEVICE_AGENT, g + str2 + ";" + string);
            hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_TIMEZONE, CoveUtil.getCurrentTimezone());
            hashMap.put("Content-Language", PreferenceManager.getInstance().getLanguage());
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            throw new SetupException(ErrorConstants.SETUP_ERR_GENERIC);
        }
    }

    public static void clearInstance() {
        d = null;
    }

    public static void f(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null && !bundle.isEmpty()) {
                f = CoveUtil.getMetaData(bundle, CoveApiConstants.COVE_API_METADATA_KEY);
                g = CoveUtil.getMetaData(bundle, CoveApiConstants.COVE_API_METADATA_CLIENT_ID);
                h = CoveUtil.getMetaData(bundle, CoveApiConstants.COVE_API_METADATA_APP_NAME);
                GATEWAY_BASE_URL = getMetaData(bundle, CoveApiConstants.COVE_API_METADATA_GATEWAY_URL);
                GATEWAY_API_KEY = getMetaData(bundle, CoveApiConstants.COVE_API_METADATA_GATEWAY_KEY);
                try {
                    i = CoveUtil.getMetaData(bundle, CoveApiConstants.COVE_API_METADATA_TAG_ID);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            throw new SetupException(ErrorConstants.SETUP_ERR_MISSING_DATA);
        } catch (PackageManager.NameNotFoundException | NullPointerException e3) {
            e3.printStackTrace();
            throw new SetupException(ErrorConstants.SETUP_ERR_GENERIC);
        }
    }

    public static String getClientId() {
        return g;
    }

    public static HashMap<String, String> getCustomHeaders() {
        String authToken = PreferenceManager.getInstance().getAuthToken();
        if (!CoveUtil.isEmpty(authToken)) {
            c.put(CoveApiHeaderConstants.HTTP_HEADER_AUTH_TOKEN, authToken);
        }
        String sessionId = PreferenceManager.getInstance().getSessionId();
        if (!CoveUtil.isEmpty(sessionId)) {
            c.put(CoveApiHeaderConstants.HTTP_HEADER_SESSION_ID, sessionId);
        }
        String deviceAgent = PreferenceManager.getInstance().getDeviceAgent();
        if (!CoveUtil.isEmpty(deviceAgent)) {
            c.put(CoveApiHeaderConstants.HTTP_HEADER_DEVICE_AGENT, deviceAgent);
        }
        if (PreferenceManager.getInstance().getUserId().intValue() != 0) {
            c.put(CoveApiHeaderConstants.HTTP_HEADER_USER_ID, String.valueOf(PreferenceManager.getInstance().getUserId()));
        }
        String language = PreferenceManager.getInstance().getLanguage();
        HashMap<String, String> hashMap = c;
        hashMap.put("Content-Language", language);
        if (!AppUtils.isEmpty(GATEWAY_API_KEY)) {
            hashMap.put(CoveApiHeaderConstants.HTTP_HEADER_KEY, GATEWAY_API_KEY);
        }
        String accessToken = PreferenceManager.getInstance().getAccessToken();
        if (!AppUtils.isEmpty(accessToken)) {
            hashMap.put("Authorization", "Bearer " + accessToken);
        }
        return hashMap;
    }

    public static synchronized CoveApi getInstance() {
        CoveApi coveApi;
        synchronized (CoveApi.class) {
            if (d != null) {
                if (!f.equals("NA")) {
                    coveApi = d;
                } else {
                    throw new SetupException(ErrorConstants.SETUP_ERR_SETUP_NOT_COMPLETE);
                }
            } else {
                throw new SetupException(ErrorConstants.SETUP_ERR_NOT_INIT);
            }
        }
        return coveApi;
    }

    public static String getMetaData(Bundle bundle, String str) {
        if (bundle.containsKey(str)) {
            String string = bundle.getString(str);
            if (string.trim() != null && string.trim().length() > 0) {
                return string;
            }
        }
        return null;
    }

    public static CoveApiService getService() {
        if (d != null && e != null) {
            if (!f.equals("NA")) {
                return e;
            }
            throw new SetupException(ErrorConstants.SETUP_ERR_SETUP_NOT_COMPLETE);
        }
        throw new SetupException(ErrorConstants.SETUP_ERR_NOT_INIT);
    }

    public static void h() {
        if (GATEWAY_API_KEY != null && GATEWAY_BASE_URL != null) {
            b = "https://prod.cove.kahaapi.com/";
        } else if (h.equalsIgnoreCase("armtrackr")) {
            b = BuildConfig.ARM_BUILD_URL;
        } else if (h.equalsIgnoreCase("coveav")) {
            b = BuildConfig.AV_BUILD_URL;
        } else if (!h.equalsIgnoreCase("coveoa") && !h.equalsIgnoreCase("coveblueridge") && !h.equalsIgnoreCase("covelifeus") && !h.equalsIgnoreCase("covemonmouth")) {
            b = "https://prod.cove.kahaapi.com/";
            GATEWAY_BASE_URL = BuildConfig.GEATEWAY_BUILD_URL_SINGAPORE;
            GATEWAY_API_KEY = BuildConfig.GEATEWAY_API_KEY_SINGAPORE;
        } else {
            b = BuildConfig.OA_BUILD_URL;
            GATEWAY_BASE_URL = BuildConfig.GEATEWAY_BUILD_URL_US;
            GATEWAY_API_KEY = BuildConfig.GEATEWAY_API_KEY_US;
        }
    }

    public static synchronized void initCoveApi(Context context, @Nullable SSLConfig sSLConfig) {
        synchronized (CoveApi.class) {
            if (d == null) {
                f(context);
                j = context;
                h();
                d = new CoveApi(sSLConfig);
                c(context);
                PreferenceManager.initPreferenceMgr(context);
            }
        }
    }

    public final OkHttpClient b(@Nullable SSLConfig sSLConfig) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new CoveApiLoggerHelper(this));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        if (sSLConfig != null && d(sSLConfig.getRsaKey())) {
            CertificatePinner build = new CertificatePinner.Builder().add(b.replaceAll("https://", "").replaceAll(MqttTopic.TOPIC_LEVEL_SEPARATOR, ""), sSLConfig.getRsaKey()).build();
            OkHttpClient.Builder addInterceptor = new OkHttpClient.Builder().authenticator(new c()).addInterceptor(httpLoggingInterceptor).addInterceptor(new b()).addInterceptor(new CertificateExpiryInterceptor(this));
            TimeUnit timeUnit = TimeUnit.SECONDS;
            return addInterceptor.connectTimeout(30L, timeUnit).readTimeout(30L, timeUnit).writeTimeout(30L, timeUnit).certificatePinner(build).build();
        }
        OkHttpClient.Builder addInterceptor2 = new OkHttpClient.Builder().authenticator(new c()).addInterceptor(httpLoggingInterceptor);
        TimeUnit timeUnit2 = TimeUnit.SECONDS;
        return addInterceptor2.connectTimeout(30L, timeUnit2).readTimeout(30L, timeUnit2).writeTimeout(30L, timeUnit2).build();
    }

    public final boolean d(String[] strArr) {
        for (String str : strArr) {
            if (!str.startsWith("sha256") && !str.startsWith("sha1")) {
                return false;
            }
        }
        return true;
    }

    public String getBaseUrl() {
        return b;
    }

    public String getGateWayUrl() {
        return GATEWAY_BASE_URL;
    }

    public String getTappyApiUrl() {
        return TAPPY_API_URL;
    }

    public String getTappyProxyApiUrl() {
        return TAPPY_PROXY_API_URL;
    }

    public boolean isEgApp() {
        return h.equalsIgnoreCase("ega");
    }
}
