package com.mappls.sdk.services.api;

import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.services.api.sdkconfig.MapplsSDKConfig;
import com.mappls.sdk.services.api.sdkconfig.model.SDKConfigResponse;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
/* loaded from: classes11.dex */
public class SDKConfigInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    public OkHttpClient f13148a;

    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (MapplsApiConfiguration.getInstance().getExpiry() == null) {
            synchronized (this.f13148a) {
                if (MapplsApiConfiguration.getInstance().getExpiry() == null) {
                    if (SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).c() == null) {
                        retrofit2.Response<SDKConfigResponse> executeCall = MapplsSDKConfig.builder().configUrl("https://sdkconfig.mappls.com/SSL/config_prod_1.0.0.json").build().executeCall();
                        if (executeCall.code() == 200 && executeCall.body() != null) {
                            SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).d(executeCall.body().getData());
                            MapplsApiConfiguration.getInstance().setValidationData(executeCall.body().getData());
                            if (MapplsApiConfiguration.getInstance().getExpiry().longValue() * 1000 <= System.currentTimeMillis()) {
                                SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).d(null);
                                MapplsApiConfiguration.getInstance().setValidationData(null);
                                return new Response.Builder().request(request).protocol(Protocol.HTTP_1_0).code(10).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK is not Configured").message("SDK is not Configured").build();
                            }
                        } else {
                            return new Response.Builder().request(request).protocol(Protocol.HTTP_1_0).code(10).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK is not Configured").message("SDK is not Configured").build();
                        }
                    } else {
                        MapplsApiConfiguration.getInstance().setValidationData(SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).c());
                        if (MapplsApiConfiguration.getInstance().getExpiry().longValue() * 1000 <= System.currentTimeMillis()) {
                            SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).d(null);
                            MapplsApiConfiguration.getInstance().setValidationData(null);
                        }
                    }
                }
            }
        }
        if (MapplsApiConfiguration.getInstance().getExpiry() == null || MapplsApiConfiguration.getInstance().getExpiry().longValue() * 1000 <= System.currentTimeMillis()) {
            synchronized (this.f13148a) {
                if (MapplsApiConfiguration.getInstance().getExpiry() == null || MapplsApiConfiguration.getInstance().getExpiry().longValue() * 1000 <= System.currentTimeMillis()) {
                    retrofit2.Response<SDKConfigResponse> executeCall2 = MapplsSDKConfig.builder().configUrl("https://sdkconfig.mappls.com/SSL/config_prod_1.0.0.json").build().executeCall();
                    if (executeCall2.code() == 200 && executeCall2.body() != null) {
                        SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).d(executeCall2.body().getData());
                        MapplsApiConfiguration.getInstance().setValidationData(executeCall2.body().getData());
                        if (MapplsApiConfiguration.getInstance().getExpiry().longValue() * 1000 <= System.currentTimeMillis()) {
                            SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).d(null);
                            MapplsApiConfiguration.getInstance().setValidationData(null);
                            return new Response.Builder().request(request).protocol(Protocol.HTTP_1_0).code(10).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK is not Configured").message("SDK is not Configured").build();
                        }
                    } else {
                        return new Response.Builder().request(request).protocol(Protocol.HTTP_1_0).code(10).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK is not configured").message("SDK is not configured").build();
                    }
                }
            }
        }
        String httpUrl = request.url().toString();
        if (httpUrl.contains(com.mappls.sdk.services.utils.Constants.ADVANCE_MAP_BASE_URL)) {
            httpUrl = httpUrl.replace(com.mappls.sdk.services.utils.Constants.ADVANCE_MAP_BASE_URL, MapplsApiConfiguration.getInstance().getUrlData().getApisUrl());
        } else if (httpUrl.contains(com.mappls.sdk.services.utils.Constants.ANCHOR_BASE_URL)) {
            httpUrl = httpUrl.replace(com.mappls.sdk.services.utils.Constants.ANCHOR_BASE_URL, MapplsApiConfiguration.getInstance().getUrlData().getAnchorUrl());
        } else if (httpUrl.contains(com.mappls.sdk.services.utils.Constants.OUTPOST_BASE_URL)) {
            httpUrl = httpUrl.replace(com.mappls.sdk.services.utils.Constants.OUTPOST_BASE_URL, MapplsApiConfiguration.getInstance().getUrlData().getOutpostUrl());
        } else if (httpUrl.contains(com.mappls.sdk.services.utils.Constants.ATLAS_BASE_URL)) {
            httpUrl = httpUrl.replace(com.mappls.sdk.services.utils.Constants.ATLAS_BASE_URL, MapplsApiConfiguration.getInstance().getUrlData().getAtlasUrl());
        } else if (httpUrl.contains(com.mappls.sdk.services.utils.Constants.EXPLORE_BASE_URL)) {
            httpUrl = httpUrl.replace(com.mappls.sdk.services.utils.Constants.EXPLORE_BASE_URL, MapplsApiConfiguration.getInstance().getUrlData().getExploreUrl());
        } else if (httpUrl.contains(com.mappls.sdk.services.utils.Constants.TRAFFIC_BASE_URL)) {
            httpUrl = httpUrl.replace(com.mappls.sdk.services.utils.Constants.TRAFFIC_BASE_URL, MapplsApiConfiguration.getInstance().getUrlData().getTrafficUrl());
        } else if (httpUrl.contains(com.mappls.sdk.services.utils.Constants.MGIS_BASE_URL)) {
            httpUrl = httpUrl.replace(com.mappls.sdk.services.utils.Constants.MGIS_BASE_URL, MapplsApiConfiguration.getInstance().getUrlData().getMgisUrl());
        } else if (httpUrl.contains(com.mappls.sdk.services.utils.Constants.MGIS_APIS_BASE_URL)) {
            httpUrl = httpUrl.replace(com.mappls.sdk.services.utils.Constants.MGIS_APIS_BASE_URL, MapplsApiConfiguration.getInstance().getUrlData().getMgisApiUrl());
        } else if (httpUrl.contains(com.mappls.sdk.services.utils.Constants.INTOUCH_BASE_URL)) {
            httpUrl = httpUrl.replace(com.mappls.sdk.services.utils.Constants.INTOUCH_BASE_URL, MapplsApiConfiguration.getInstance().getUrlData().getIntouchUrl());
        }
        return chain.proceed(request.newBuilder().url(httpUrl).build());
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.f13148a = okHttpClient;
    }
}
