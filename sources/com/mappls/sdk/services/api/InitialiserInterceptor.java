package com.mappls.sdk.services.api;

import androidx.annotation.Keep;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.whoami.MapplsLicensing;
import com.mappls.sdk.services.api.whoami.model.LicensingHeader;
import com.mappls.sdk.services.api.whoami.model.LicensingOutputParams;
import com.mappls.sdk.services.api.whoami.model.LicensingParams;
import com.mappls.sdk.services.api.whoami.model.LicensingResponse;
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
public class InitialiserInterceptor implements Interceptor {
    private OkHttpClient httpClient;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        String token;
        Request request = chain.request();
        if (MapplsApiConfiguration.getInstance().isLoginRequired() == null) {
            synchronized (this.httpClient) {
                if (MapplsApiConfiguration.getInstance().isLoginRequired() == null) {
                    try {
                        retrofit2.Response<LicensingResponse> executeCall = MapplsLicensing.builder().deviceId(MapplsAccountManager.getInstance().getClusterId()).build().executeCall();
                        if (executeCall.code() == 200 && executeCall.body() != null) {
                            MapplsApiConfiguration.getInstance().isLoginRequired = executeCall.body().isUserLoginRequired();
                            if (MapplsApiConfiguration.getInstance().isLoginRequired().booleanValue() && (token = MapplsApiConfiguration.getInstance().iTokenRepo.getToken()) != null) {
                                MapplsAccountManager.getInstance().setRefreshToken(token, null);
                            }
                            if (executeCall.body().getLicensingHeader() != null) {
                                LicensingHeader licensingHeader = executeCall.body().getLicensingHeader();
                                MapplsApiConfiguration.getInstance().xMsSeh = licensingHeader.getxMsSeh();
                                MapplsApiConfiguration.getInstance().xDh = licensingHeader.getxDh();
                            }
                            if (executeCall.body().getLicensingParams() != null) {
                                LicensingParams licensingParams = executeCall.body().getLicensingParams();
                                MapplsApiConfiguration.getInstance().clusterId = licensingParams.getClusterDevice();
                                MapplsApiConfiguration.getInstance().deviceFingerPrint = licensingParams.getDeviceFingerprint();
                                MapplsApiConfiguration.getInstance().vin = licensingParams.getVin();
                                MapplsApiConfiguration.getInstance().userId = licensingParams.getUserId();
                            }
                            if (executeCall.body().getLicensingOutputParams() != null) {
                                LicensingOutputParams licensingOutputParams = executeCall.body().getLicensingOutputParams();
                                if (licensingOutputParams.getDeviceRegion() != null && licensingOutputParams.getDeviceRegion().size() > 0) {
                                    MapplsAccountManager.getInstance().setRegion(licensingOutputParams.getDeviceRegion().get(0));
                                }
                            }
                            LoggerUtils.e("InitialiserInterceptor", String.format(Locale.US, "Initialising Api Success: Code = %d", Integer.valueOf(executeCall.code())));
                        } else if (executeCall.code() == 7) {
                            return new Response.Builder().request(request).protocol(executeCall.raw().protocol()).code(7).body(ResponseBody.create(MediaType.parse("text"), "")).message("Authentication Failed").header(Constants.KEY_MESSAGE, "Authentication Failed").build();
                        } else {
                            LoggerUtils.e("InitialiserInterceptor", String.format(Locale.US, "Initialising Api Failure: Code = %d Message = %s", Integer.valueOf(executeCall.code()), executeCall.message()));
                            return new Response.Builder().request(request).protocol(executeCall.raw().protocol()).code(8).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK not initialised").message("SDK not initialised").build();
                        }
                    } catch (SocketTimeoutException e) {
                        e = e;
                        LoggerUtils.e("InitialiserInterceptor", e.getMessage(), e);
                        return new Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(8).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK not initialised").message("SDK not initialised").build();
                    } catch (UnknownHostException e2) {
                        e = e2;
                        LoggerUtils.e("InitialiserInterceptor", e.getMessage(), e);
                        return new Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(8).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK not initialised").message("SDK not initialised").build();
                    } catch (SSLHandshakeException e3) {
                        e = e3;
                        LoggerUtils.e("InitialiserInterceptor", e.getMessage(), e);
                        return new Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(8).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK not initialised").message("SDK not initialised").build();
                    } catch (ConnectTimeoutException e4) {
                        e = e4;
                        LoggerUtils.e("InitialiserInterceptor", e.getMessage(), e);
                        return new Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(8).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK not initialised").message("SDK not initialised").build();
                    }
                }
            }
        }
        return chain.proceed(request);
    }

    public void setHttpClient(OkHttpClient okHttpClient) {
        this.httpClient = okHttpClient;
    }
}
