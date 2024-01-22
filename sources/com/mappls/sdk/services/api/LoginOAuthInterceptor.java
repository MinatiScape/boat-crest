package com.mappls.sdk.services.api;

import androidx.annotation.Keep;
import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import com.mappls.android.lms.MapplsLMSManager;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.auth.MapplsAuthentication;
import com.mappls.sdk.services.api.auth.model.AtlasAuthToken;
import com.mappls.sdk.services.log.LoggerUtils;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Locale;
import javax.net.ssl.SSLHandshakeException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.http.conn.ConnectTimeoutException;
@Keep
/* loaded from: classes11.dex */
public class LoginOAuthInterceptor implements Interceptor {
    private OkHttpClient httpClient;

    private void setAuthHeader(Request.Builder builder, String str) {
        builder.header("Authorization", String.format("bearer %s", str));
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (MapplsApiConfiguration.getInstance().isLoginRequired() == null) {
            return new Response.Builder().request(request).protocol(Protocol.HTTP_1_0).code(8).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK not initialised").message("SDK not initialised").build();
        }
        Request.Builder newBuilder = request.newBuilder();
        newBuilder.header(HttpHeaders.ACCEPT, "application/json");
        if (MapplsApiConfiguration.getInstance().isLoginRequired() != null && MapplsApiConfiguration.getInstance().isLoginRequired().booleanValue()) {
            if (MapplsAccountManager.getInstance().getRefreshToken() == null) {
                return new Response.Builder().request(request).code(101).body(ResponseBody.create(MediaType.parse("text"), "")).protocol(Protocol.HTTP_1_0).message("Whitelisting Required").header(Constants.KEY_MESSAGE, "User Whitelisting Required").build();
            }
            if (MapplsAccountManager.getInstance().getAccessToken() == null) {
                synchronized (this.httpClient) {
                    if (MapplsAccountManager.getInstance().getAccessToken() == null) {
                        retrofit2.Response<AtlasAuthToken> executeCall = MapplsAuthentication.builder().refreshToken(MapplsAccountManager.getInstance().getRefreshToken()).grantType("refresh_token").build().executeCall();
                        if (executeCall.code() == 200 && executeCall.body() != null) {
                            if (MapplsLMSManager.isInitialised()) {
                                if (executeCall.body().projectCode != null) {
                                    MapplsLMSManager.getInstance().setProjectCode(executeCall.body().projectCode);
                                }
                                if (executeCall.body().clientId != null) {
                                    MapplsLMSManager.getInstance().setClientId(executeCall.body().clientId);
                                }
                                MapplsLMSManager.getInstance().setUserId(executeCall.body().userId);
                            }
                            MapplsAccountManager.getInstance().setRefreshToken(executeCall.body().refreshToken, executeCall.body().accessToken);
                            MapplsAccountManager.getInstance().setUserId(executeCall.body().userId);
                            MapplsApiConfiguration.getInstance().iTokenRepo.setToken(executeCall.body().refreshToken);
                        } else if (executeCall.code() == 401) {
                            MapplsApiConfiguration.getInstance().iTokenRepo.clearToken();
                            LoggerUtils.e("LoginOAuthInterceptor", String.format(Locale.US, "Refresh Token API API: Code = %d,  Message = %s", Integer.valueOf(executeCall.code()), executeCall.message()));
                            return new Response.Builder().request(request).body(executeCall.errorBody()).protocol(Protocol.HTTP_1_0).code(102).header(Constants.KEY_MESSAGE, "Session Expired").message("Session Expired").build();
                        } else {
                            LoggerUtils.e("LoginOAuthInterceptor", String.format(Locale.US, "Refresh Token API API: Code = %d,  Message = %s", Integer.valueOf(executeCall.code()), executeCall.message()));
                            return new Response.Builder().request(request).code(7).body(ResponseBody.create(MediaType.parse("text"), "")).protocol(Protocol.HTTP_1_0).message("Authentication Failed").header(Constants.KEY_MESSAGE, "Authentication Failed").build();
                        }
                    }
                    setAuthHeader(newBuilder, MapplsAccountManager.getInstance().getAccessToken());
                    return chain.proceed(newBuilder.build());
                }
            }
            String accessToken = MapplsAccountManager.getInstance().getAccessToken();
            setAuthHeader(newBuilder, accessToken);
            Request build = newBuilder.build();
            Response proceed = chain.proceed(build);
            if (proceed.code() == 401) {
                synchronized (this.httpClient) {
                    String accessToken2 = MapplsAccountManager.getInstance().getAccessToken();
                    if (accessToken2 == null || accessToken2.equalsIgnoreCase(accessToken)) {
                        retrofit2.Response<AtlasAuthToken> executeCall2 = MapplsAuthentication.builder().refreshToken(MapplsAccountManager.getInstance().getRefreshToken()).grantType("refresh_token").build().executeCall();
                        if (executeCall2.code() == 200 && executeCall2.body() != null) {
                            if (MapplsLMSManager.isInitialised()) {
                                if (executeCall2.body().projectCode != null) {
                                    MapplsLMSManager.getInstance().setProjectCode(executeCall2.body().projectCode);
                                }
                                if (executeCall2.body().clientId != null) {
                                    MapplsLMSManager.getInstance().setClientId(executeCall2.body().clientId);
                                }
                                MapplsLMSManager.getInstance().setUserId(executeCall2.body().userId);
                            }
                            MapplsAccountManager.getInstance().setRefreshToken(executeCall2.body().refreshToken, executeCall2.body().accessToken);
                            MapplsAccountManager.getInstance().setUserId(executeCall2.body().userId);
                            MapplsApiConfiguration.getInstance().iTokenRepo.setToken(executeCall2.body().refreshToken);
                        } else {
                            if (executeCall2.code() != 401 && executeCall2.code() != 400) {
                                LoggerUtils.e("LoginOAuthInterceptor", String.format(Locale.US, "Refresh Token API API: Code = %d,  Message = %s", Integer.valueOf(executeCall2.code()), executeCall2.message()));
                                return new Response.Builder().request(build).code(7).body(ResponseBody.create(MediaType.parse("text"), "")).protocol(Protocol.HTTP_1_0).message("Authentication Failed").header(Constants.KEY_MESSAGE, "Authentication Failed").build();
                            }
                            MapplsApiConfiguration.getInstance().iTokenRepo.clearToken();
                            LoggerUtils.e("LoginOAuthInterceptor", String.format(Locale.US, "Refresh Token API API: Code = %d,  Message = %s", Integer.valueOf(executeCall2.code()), executeCall2.message()));
                            return proceed.newBuilder().code(102).header(Constants.KEY_MESSAGE, "Session Expired").message("Session Expired").build();
                        }
                    }
                    if (MapplsAccountManager.getInstance().getAccessToken() != null) {
                        proceed.close();
                        setAuthHeader(newBuilder, MapplsAccountManager.getInstance().getAccessToken());
                        return chain.proceed(newBuilder.build());
                    }
                }
            }
            return proceed;
        } else if (MapplsAccountManager.getInstance().getAccessToken() == null) {
            synchronized (this.httpClient) {
                if (MapplsApiConfiguration.getInstance().isLoginRequired() == null) {
                    return new Response.Builder().request(request).protocol(Protocol.HTTP_1_0).code(8).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK not initialised").message("SDK not initialised").build();
                }
                if (MapplsAccountManager.getInstance().getAccessToken() == null) {
                    try {
                        retrofit2.Response<AtlasAuthToken> executeCall3 = MapplsAuthentication.builder().build().executeCall();
                        if (executeCall3.code() == 200 && executeCall3.body() != null) {
                            if (MapplsLMSManager.isInitialised()) {
                                if (executeCall3.body().projectCode != null) {
                                    MapplsLMSManager.getInstance().setProjectCode(executeCall3.body().projectCode);
                                }
                                if (executeCall3.body().clientId != null) {
                                    MapplsLMSManager.getInstance().setClientId(executeCall3.body().clientId);
                                }
                            }
                            LoggerUtils.e("LoginOAuthInterceptor", String.format(Locale.US, "Authentication API Success: Code = %d,  Message = %s", Integer.valueOf(executeCall3.code()), executeCall3.body().accessToken));
                            MapplsAccountManager.getInstance().setAccessToken(executeCall3.body().accessToken);
                        } else {
                            LoggerUtils.e("LoginOAuthInterceptor", String.format(Locale.US, "Authentication API Failure: Code = %d,  Message = %s", Integer.valueOf(executeCall3.code()), executeCall3.message()));
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
                setAuthHeader(newBuilder, MapplsAccountManager.getInstance().getAccessToken());
                return chain.proceed(newBuilder.build());
            }
        } else {
            String accessToken3 = MapplsAccountManager.getInstance().getAccessToken();
            setAuthHeader(newBuilder, accessToken3);
            Request build2 = newBuilder.build();
            Response proceed2 = chain.proceed(build2);
            if (proceed2.code() == 401) {
                synchronized (this.httpClient) {
                    String accessToken4 = MapplsAccountManager.getInstance().getAccessToken();
                    if (accessToken4 == null || accessToken4.equalsIgnoreCase(accessToken3)) {
                        retrofit2.Response<AtlasAuthToken> executeCall4 = MapplsAuthentication.builder().build().executeCall();
                        if (executeCall4.code() == 200 && executeCall4.body() != null) {
                            if (MapplsLMSManager.isInitialised()) {
                                if (executeCall4.body().projectCode != null) {
                                    MapplsLMSManager.getInstance().setProjectCode(executeCall4.body().projectCode);
                                }
                                if (executeCall4.body().clientId != null) {
                                    MapplsLMSManager.getInstance().setClientId(executeCall4.body().clientId);
                                }
                            }
                            MapplsAccountManager.getInstance().setAccessToken(executeCall4.body().accessToken);
                        } else {
                            LoggerUtils.e("LoginOAuthInterceptor", String.format(Locale.US, "Authentication API: Code = %d,  Message = %s", Integer.valueOf(executeCall4.code()), executeCall4.message()));
                            return new Response.Builder().request(build2).code(7).body(ResponseBody.create(MediaType.parse("text"), "")).protocol(Protocol.HTTP_1_0).message("Authentication Failed").header(Constants.KEY_MESSAGE, "Authentication Failed").build();
                        }
                    }
                    if (MapplsAccountManager.getInstance().getAccessToken() != null) {
                        proceed2.close();
                        setAuthHeader(newBuilder, MapplsAccountManager.getInstance().getAccessToken());
                        return chain.proceed(newBuilder.build());
                    }
                }
            }
            return proceed2;
        }
    }

    public void setHttpClient(OkHttpClient okHttpClient) {
        this.httpClient = okHttpClient;
    }
}
