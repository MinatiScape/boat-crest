package com.mappls.sdk.services.api;

import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import com.google.gson.GsonBuilder;
import com.mappls.android.lms.MapplsLMSManager;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.auth.MapplsAuthentication;
import com.mappls.sdk.services.api.auth.model.AtlasAuthToken;
import com.mappls.sdk.services.log.LoggerUtils;
import com.mappls.sdk.services.utils.ApiCallHelper;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.http.conn.ConnectTimeoutException;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Keep
/* loaded from: classes11.dex */
public abstract class MapplsService<T, S> {
    public static final int MAX_URL_SIZE = 8192;
    public static OkHttpClient atlasOkHttpClient;
    public static OkHttpClient loginAtlasOkHttpClient;
    public static OkHttpClient loginOkHttpClient;
    public static OkHttpClient okHttpClient;
    public static OkHttpClient plainOkHttpClient;
    private Call<T> call;
    private Call.Factory callFactory;
    private final boolean enableDebug = false;
    public HostnameVerifier hostnameVerifier = new a(this);
    private Retrofit retrofit;
    private S service;
    private final Class<S> serviceType;

    /* loaded from: classes11.dex */
    public class a implements HostnameVerifier {
        public a(MapplsService mapplsService) {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            try {
                Certificate[] peerCertificates = sSLSession.getPeerCertificates();
                if (peerCertificates == null || peerCertificates.length == 0 || !str.equalsIgnoreCase(sSLSession.getPeerHost())) {
                    return false;
                }
                try {
                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    trustManagerFactory.init((KeyStore) null);
                    TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                    if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                        X509TrustManager x509TrustManager = (X509TrustManager) trustManagers[0];
                        int length = peerCertificates.length;
                        for (int i = 0; i < length; i++) {
                            x509TrustManager.checkServerTrusted(new X509Certificate[]{(X509Certificate) peerCertificates[i]}, "RSA");
                        }
                        return true;
                    }
                    throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
                } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
                    e.printStackTrace();
                    return false;
                }
            } catch (SSLPeerUnverifiedException e2) {
                e2.printStackTrace();
                return false;
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class b implements Interceptor {

        /* renamed from: a  reason: collision with root package name */
        public OkHttpClient f13147a;

        public final void a(Request.Builder builder, String str) {
            builder.header("Authorization", String.format("bearer %s", str));
        }

        public void b(OkHttpClient okHttpClient) {
            this.f13147a = okHttpClient;
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder newBuilder = request.newBuilder();
            newBuilder.header(HttpHeaders.ACCEPT, "application/json");
            if (MapplsAccountManager.getInstance().getAccessToken() == null) {
                synchronized (this.f13147a) {
                    if (MapplsAccountManager.getInstance().getAccessToken() == null) {
                        try {
                            retrofit2.Response<AtlasAuthToken> executeCall = MapplsAuthentication.builder().build().executeCall();
                            if (executeCall.code() == 200 && executeCall.body() != null) {
                                if (MapplsLMSManager.isInitialised()) {
                                    if (executeCall.body().projectCode != null) {
                                        MapplsLMSManager.getInstance().setProjectCode(executeCall.body().projectCode);
                                    }
                                    if (executeCall.body().clientId != null) {
                                        MapplsLMSManager.getInstance().setClientId(executeCall.body().clientId);
                                    }
                                }
                                LoggerUtils.e("OAuthInterceptor", String.format(Locale.US, "Authentication API Success: Code = %d,  Message = %s", Integer.valueOf(executeCall.code()), executeCall.body().accessToken));
                                MapplsAccountManager.getInstance().setAccessToken(executeCall.body().accessToken);
                            } else {
                                LoggerUtils.e("OAuthInterceptor", String.format(Locale.US, "Authentication API: Failure Code = %d,  Message = %s", Integer.valueOf(executeCall.code()), executeCall.message()));
                                return new Response.Builder().request(request).code(7).body(ResponseBody.create(MediaType.parse("text"), "")).protocol(Protocol.HTTP_1_0).message("Authentication Failed").header(Constants.KEY_MESSAGE, "Authentication Failed").build();
                            }
                        } catch (SocketTimeoutException e) {
                            e = e;
                            LoggerUtils.e("OAuthInterceptor", e.getMessage(), e);
                            return new Response.Builder().request(request).code(7).body(ResponseBody.create(MediaType.parse("text"), "")).protocol(Protocol.HTTP_1_0).message("Authentication Failed").header(Constants.KEY_MESSAGE, "Authentication Failed").build();
                        } catch (UnknownHostException e2) {
                            e = e2;
                            LoggerUtils.e("OAuthInterceptor", e.getMessage(), e);
                            return new Response.Builder().request(request).code(7).body(ResponseBody.create(MediaType.parse("text"), "")).protocol(Protocol.HTTP_1_0).message("Authentication Failed").header(Constants.KEY_MESSAGE, "Authentication Failed").build();
                        } catch (SSLHandshakeException e3) {
                            e = e3;
                            LoggerUtils.e("OAuthInterceptor", e.getMessage(), e);
                            return new Response.Builder().request(request).code(7).body(ResponseBody.create(MediaType.parse("text"), "")).protocol(Protocol.HTTP_1_0).message("Authentication Failed").header(Constants.KEY_MESSAGE, "Authentication Failed").build();
                        } catch (ConnectTimeoutException e4) {
                            e = e4;
                            LoggerUtils.e("OAuthInterceptor", e.getMessage(), e);
                            return new Response.Builder().request(request).code(7).body(ResponseBody.create(MediaType.parse("text"), "")).protocol(Protocol.HTTP_1_0).message("Authentication Failed").header(Constants.KEY_MESSAGE, "Authentication Failed").build();
                        }
                    }
                    a(newBuilder, MapplsAccountManager.getInstance().getAccessToken());
                    return chain.proceed(newBuilder.build());
                }
            }
            String accessToken = MapplsAccountManager.getInstance().getAccessToken();
            a(newBuilder, accessToken);
            Request build = newBuilder.build();
            Response proceed = chain.proceed(build);
            if (proceed.code() == 401) {
                synchronized (this.f13147a) {
                    String accessToken2 = MapplsAccountManager.getInstance().getAccessToken();
                    if (accessToken2 == null || accessToken2.equalsIgnoreCase(accessToken)) {
                        retrofit2.Response<AtlasAuthToken> executeCall2 = MapplsAuthentication.builder().build().executeCall();
                        if (executeCall2.code() == 200 && executeCall2.body() != null) {
                            if (MapplsLMSManager.isInitialised()) {
                                if (executeCall2.body().projectCode != null) {
                                    MapplsLMSManager.getInstance().setProjectCode(executeCall2.body().projectCode);
                                }
                                if (executeCall2.body().clientId != null) {
                                    MapplsLMSManager.getInstance().setClientId(executeCall2.body().clientId);
                                }
                            }
                            MapplsAccountManager.getInstance().setAccessToken(executeCall2.body().accessToken);
                        } else {
                            LoggerUtils.e("OAuthInterceptor", String.format(Locale.US, "Authentication API: Code = %d,  Message = %s", Integer.valueOf(executeCall2.code()), executeCall2.message()));
                            return new Response.Builder().request(build).code(7).body(ResponseBody.create(MediaType.parse("text"), "")).protocol(Protocol.HTTP_1_0).message("Authentication Failed").header(Constants.KEY_MESSAGE, "Authentication Failed").build();
                        }
                    }
                    if (MapplsAccountManager.getInstance().getAccessToken() != null) {
                        proceed.close();
                        a(newBuilder, MapplsAccountManager.getInstance().getAccessToken());
                        return chain.proceed(newBuilder.build());
                    }
                }
            }
            return proceed;
        }
    }

    /* loaded from: classes11.dex */
    public static class c implements Interceptor {
        public c() {
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request.newBuilder().headers(request.headers().newBuilder().removeAll(HttpHeaders.USER_AGENT).addUnsafeNonAscii(HttpHeaders.USER_AGENT, ApiCallHelper.getHeaderUserAgent()).build()).build());
        }

        public /* synthetic */ c(a aVar) {
            this();
        }
    }

    public MapplsService(Class<S> cls) {
        this.serviceType = cls;
    }

    public static void initProxy() {
        okHttpClient = null;
        atlasOkHttpClient = null;
        loginAtlasOkHttpClient = null;
        loginOkHttpClient = null;
    }

    public abstract String baseUrl();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void cancelCall() {
        getCall().cancel();
    }

    public retrofit2.Call<T> cloneCall() {
        return getCall().mo952clone();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void enqueueCall(Callback<T> callback) {
        getCall().enqueue(callback);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public retrofit2.Response<T> executeCall() throws IOException {
        return getCall().execute();
    }

    public synchronized OkHttpClient getAtlasOkHttpClient() {
        if (atlasOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            b bVar = new b();
            SDKConfigInterceptor sDKConfigInterceptor = new SDKConfigInterceptor();
            builder.addInterceptor(new c(null));
            builder.addInterceptor(sDKConfigInterceptor);
            builder.addInterceptor(bVar);
            builder.addInterceptor(new RegionInterceptor());
            if (MapplsApiConfiguration.getInstance().proxyHost != null) {
                builder.proxy(new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(MapplsApiConfiguration.getInstance().proxyHost, MapplsApiConfiguration.getInstance().proxyPort.intValue())));
            }
            if (isEnableDebug()) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(httpLoggingInterceptor);
            }
            builder.retryOnConnectionFailure(false);
            if (MapplsApiConfiguration.getInstance().getCertificateHash() != null) {
                CertificatePinner.Builder builder2 = new CertificatePinner.Builder();
                Iterator<String> it = MapplsApiConfiguration.getInstance().getCertificateHash().iterator();
                while (it.hasNext()) {
                    builder2.add("*.mappls.com", "sha256/" + it.next());
                }
                builder.certificatePinner(builder2.build());
            } else {
                builder.hostnameVerifier(this.hostnameVerifier);
            }
            OkHttpClient build = builder.build();
            atlasOkHttpClient = build;
            bVar.b(build);
            sDKConfigInterceptor.setOkHttpClient(atlasOkHttpClient);
        }
        return atlasOkHttpClient;
    }

    public retrofit2.Call<T> getCall() {
        if (this.call == null) {
            this.call = initializeCall();
        }
        return this.call;
    }

    public List<CallAdapter.Factory> getCallAdapterFactory() {
        return new ArrayList();
    }

    public Call.Factory getCallFactory() {
        return this.callFactory;
    }

    public GsonBuilder getGsonBuilder() {
        return new GsonBuilder();
    }

    public synchronized OkHttpClient getLoginAtlasOkHttpClient() {
        if (loginAtlasOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            LoginOAuthInterceptor loginOAuthInterceptor = new LoginOAuthInterceptor();
            InitialiserInterceptor initialiserInterceptor = new InitialiserInterceptor();
            PublicKeyInterceptor publicKeyInterceptor = new PublicKeyInterceptor();
            SDKConfigInterceptor sDKConfigInterceptor = new SDKConfigInterceptor();
            builder.addInterceptor(new c(null));
            builder.addInterceptor(new AnalyticsInterceptor());
            builder.addInterceptor(sDKConfigInterceptor);
            builder.addInterceptor(initialiserInterceptor);
            builder.addInterceptor(publicKeyInterceptor);
            builder.addInterceptor(loginOAuthInterceptor);
            builder.addInterceptor(new RegionInterceptor());
            builder.addInterceptor(new AddParametersInterceptor());
            if (MapplsApiConfiguration.getInstance().proxyHost != null) {
                builder.proxy(new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(MapplsApiConfiguration.getInstance().proxyHost, MapplsApiConfiguration.getInstance().proxyPort.intValue())));
            }
            if (isEnableDebug()) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(httpLoggingInterceptor);
            }
            builder.retryOnConnectionFailure(false);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            builder.callTimeout(20L, timeUnit);
            builder.connectTimeout(20L, timeUnit);
            builder.readTimeout(20L, timeUnit);
            builder.writeTimeout(20L, timeUnit);
            if (MapplsApiConfiguration.getInstance().getCertificateHash() != null) {
                CertificatePinner.Builder builder2 = new CertificatePinner.Builder();
                Iterator<String> it = MapplsApiConfiguration.getInstance().getCertificateHash().iterator();
                while (it.hasNext()) {
                    builder2.add("*.mappls.com", "sha256/" + it.next());
                }
                builder.certificatePinner(builder2.build());
            } else {
                builder.hostnameVerifier(this.hostnameVerifier);
            }
            OkHttpClient build = builder.build();
            loginAtlasOkHttpClient = build;
            initialiserInterceptor.setHttpClient(build);
            loginOAuthInterceptor.setHttpClient(loginAtlasOkHttpClient);
            publicKeyInterceptor.setClient(loginAtlasOkHttpClient);
            sDKConfigInterceptor.setOkHttpClient(loginAtlasOkHttpClient);
        }
        return loginAtlasOkHttpClient;
    }

    public synchronized OkHttpClient getLoginOkHttpClient() {
        if (loginOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            InitialiserInterceptor initialiserInterceptor = new InitialiserInterceptor();
            PublicKeyInterceptor publicKeyInterceptor = new PublicKeyInterceptor();
            SDKConfigInterceptor sDKConfigInterceptor = new SDKConfigInterceptor();
            builder.addInterceptor(new c(null));
            builder.addInterceptor(new AnalyticsInterceptor());
            builder.addInterceptor(sDKConfigInterceptor);
            builder.addInterceptor(initialiserInterceptor);
            builder.addInterceptor(publicKeyInterceptor);
            builder.addInterceptor(new RegionInterceptor());
            builder.addInterceptor(new AddParametersInterceptor());
            if (MapplsApiConfiguration.getInstance().proxyHost != null) {
                builder.proxy(new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(MapplsApiConfiguration.getInstance().proxyHost, MapplsApiConfiguration.getInstance().proxyPort.intValue())));
            }
            if (isEnableDebug()) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(httpLoggingInterceptor);
            }
            builder.retryOnConnectionFailure(false);
            if (MapplsApiConfiguration.getInstance().getCertificateHash() != null) {
                CertificatePinner.Builder builder2 = new CertificatePinner.Builder();
                Iterator<String> it = MapplsApiConfiguration.getInstance().getCertificateHash().iterator();
                while (it.hasNext()) {
                    builder2.add("*.mappls.com", "sha256/" + it.next());
                }
                builder.certificatePinner(builder2.build());
            } else {
                builder.hostnameVerifier(this.hostnameVerifier);
            }
            OkHttpClient build = builder.build();
            loginOkHttpClient = build;
            initialiserInterceptor.setHttpClient(build);
            publicKeyInterceptor.setClient(loginOkHttpClient);
            sDKConfigInterceptor.setOkHttpClient(loginOkHttpClient);
        }
        return loginOkHttpClient;
    }

    public S getLoginService(boolean z) {
        S s = this.service;
        if (s != null) {
            return s;
        }
        Retrofit.Builder addConverterFactory = new Retrofit.Builder().baseUrl(baseUrl()).addConverterFactory(GsonConverterFactory.create(getGsonBuilder().create()));
        for (CallAdapter.Factory factory : getCallAdapterFactory()) {
            addConverterFactory.addCallAdapterFactory(factory);
        }
        if (getCallFactory() != null) {
            addConverterFactory.callFactory(getCallFactory());
        } else {
            addConverterFactory.client(z ? getLoginAtlasOkHttpClient() : getLoginOkHttpClient());
        }
        Retrofit build = addConverterFactory.build();
        this.retrofit = build;
        S s2 = (S) build.create(this.serviceType);
        this.service = s2;
        return s2;
    }

    public synchronized OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            SDKConfigInterceptor sDKConfigInterceptor = new SDKConfigInterceptor();
            builder.addInterceptor(new c(null));
            builder.addInterceptor(new RegionInterceptor());
            builder.addInterceptor(sDKConfigInterceptor);
            if (MapplsApiConfiguration.getInstance().proxyHost != null) {
                builder.proxy(new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(MapplsApiConfiguration.getInstance().proxyHost, MapplsApiConfiguration.getInstance().proxyPort.intValue())));
            }
            if (isEnableDebug()) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(httpLoggingInterceptor);
            }
            builder.retryOnConnectionFailure(false);
            if (MapplsApiConfiguration.getInstance().getCertificateHash() != null) {
                CertificatePinner.Builder builder2 = new CertificatePinner.Builder();
                Iterator<String> it = MapplsApiConfiguration.getInstance().getCertificateHash().iterator();
                while (it.hasNext()) {
                    builder2.add("*.mappls.com", "sha256/" + it.next());
                }
                builder.certificatePinner(builder2.build());
            } else {
                builder.hostnameVerifier(this.hostnameVerifier);
            }
            OkHttpClient build = builder.build();
            okHttpClient = build;
            sDKConfigInterceptor.setOkHttpClient(build);
        }
        return okHttpClient;
    }

    public synchronized OkHttpClient getPlainOkHttpClient() {
        if (plainOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new c(null));
            if (MapplsApiConfiguration.getInstance().proxyHost != null) {
                builder.proxy(new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(MapplsApiConfiguration.getInstance().proxyHost, MapplsApiConfiguration.getInstance().proxyPort.intValue())));
            }
            builder.hostnameVerifier(this.hostnameVerifier);
            builder.retryOnConnectionFailure(false);
            if (isEnableDebug()) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(httpLoggingInterceptor);
            }
            plainOkHttpClient = builder.build();
        }
        return plainOkHttpClient;
    }

    public S getPlainService() {
        S s = this.service;
        if (s != null) {
            return s;
        }
        Retrofit.Builder addConverterFactory = new Retrofit.Builder().baseUrl(baseUrl()).addConverterFactory(GsonConverterFactory.create(getGsonBuilder().create()));
        addConverterFactory.client(getPlainOkHttpClient());
        Retrofit build = addConverterFactory.build();
        this.retrofit = build;
        S s2 = (S) build.create(this.serviceType);
        this.service = s2;
        return s2;
    }

    public S getService(boolean z) {
        S s = this.service;
        if (s != null) {
            return s;
        }
        Retrofit.Builder addConverterFactory = new Retrofit.Builder().baseUrl(baseUrl()).addConverterFactory(GsonConverterFactory.create(getGsonBuilder().create()));
        for (CallAdapter.Factory factory : getCallAdapterFactory()) {
            addConverterFactory.addCallAdapterFactory(factory);
        }
        if (MapplsApiConfiguration.getInstance().getCertificateHash() == null && SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).c() != null) {
            MapplsApiConfiguration.getInstance().setValidationData(SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).c());
            if (MapplsApiConfiguration.getInstance().getExpiry().longValue() * 1000 <= System.currentTimeMillis()) {
                MapplsApiConfiguration.getInstance().setValidationData(null);
                SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).d(null);
            }
        }
        if (getCallFactory() != null) {
            addConverterFactory.callFactory(getCallFactory());
        } else {
            addConverterFactory.client(z ? getAtlasOkHttpClient() : getOkHttpClient());
        }
        Retrofit build = addConverterFactory.build();
        this.retrofit = build;
        S s2 = (S) build.create(this.serviceType);
        this.service = s2;
        return s2;
    }

    public abstract retrofit2.Call<T> initializeCall();

    public boolean isEnableDebug() {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isExecuted() {
        return getCall().isExecuted();
    }

    public void setCallFactory(Call.Factory factory) {
        this.callFactory = factory;
    }
}
