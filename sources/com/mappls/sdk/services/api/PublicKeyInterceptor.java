package com.mappls.sdk.services.api;

import android.provider.Settings;
import androidx.annotation.Keep;
import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.publickey.MapplsPublicKey;
import com.mappls.sdk.services.api.publickey.model.PublicKeyResponse;
import com.mappls.sdk.services.hmac.interfaces.GetEncapsulatedHeader;
import com.mappls.sdk.services.log.LoggerUtils;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
@Keep
/* loaded from: classes11.dex */
public class PublicKeyInterceptor implements Interceptor {
    private OkHttpClient okHttpClient;

    public OkHttpClient getOkHttpClient() {
        return this.okHttpClient;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (MapplsApiConfiguration.getInstance().isLoginRequired() == null) {
            return new Response.Builder().request(request).protocol(Protocol.HTTP_1_0).code(8).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK not initialised").message("SDK not initialised").build();
        }
        Request.Builder newBuilder = request.newBuilder();
        newBuilder.header(HttpHeaders.ACCEPT, "application/json");
        String string = Settings.Secure.getString(MapplsUtils.getSDKContext().getContentResolver(), "android_id");
        if (MapplsApiConfiguration.getInstance().xMsSeh != null) {
            if (MapplsApiConfiguration.getInstance().getPublicKey() == null && System.currentTimeMillis() / 1000 > MapplsApiConfiguration.getInstance().getPublicKeyExpirationTime()) {
                synchronized (this.okHttpClient) {
                    if (MapplsApiConfiguration.getInstance().isLoginRequired() == null) {
                        return new Response.Builder().request(request).protocol(Protocol.HTTP_1_0).code(8).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK not initialised").message("SDK not initialised").build();
                    } else if (MapplsApiConfiguration.getInstance().getPublicKey() == null && System.currentTimeMillis() / 1000 > MapplsApiConfiguration.getInstance().getPublicKeyExpirationTime()) {
                        retrofit2.Response<PublicKeyResponse> executeCall = MapplsPublicKey.builder().build().executeCall();
                        if (executeCall.code() == 200 && executeCall.body() != null) {
                            MapplsApiConfiguration.getInstance().setPublicKey(executeCall.body().getSecretKey());
                            MapplsApiConfiguration.getInstance().setPublicKeyExpirationTime(executeCall.body().getExpiresAfter().longValue());
                            GetEncapsulatedHeader getEncapsulatedHeader = GetEncapsulatedHeader.getInstance();
                            HashMap hashMap = new HashMap();
                            hashMap.put("d", string);
                            hashMap.put("hsa", "SHA256");
                            Map<String, String> encapsulatedHeader = getEncapsulatedHeader.getEncapsulatedHeader(hashMap, MapplsApiConfiguration.getInstance().getPublicKey());
                            if (encapsulatedHeader != null) {
                                for (Map.Entry<String, String> entry : encapsulatedHeader.entrySet()) {
                                    newBuilder.header(entry.getKey(), entry.getValue());
                                }
                            }
                            if (MapplsApiConfiguration.getInstance().isLoginRequired() == null) {
                                return new Response.Builder().request(request).protocol(Protocol.HTTP_1_0).code(8).body(ResponseBody.create(MediaType.parse("text"), "")).addHeader(Constants.KEY_MESSAGE, "SDK not initialised").message("SDK not initialised").build();
                            } else if (MapplsApiConfiguration.getInstance().isLoginRequired().booleanValue()) {
                                MapplsAccountManager.getInstance().setAccessToken(null);
                            }
                        } else if (MapplsApiConfiguration.getInstance().xMsSeh != null && MapplsApiConfiguration.getInstance().xMsSeh.booleanValue()) {
                            return new Response.Builder().request(request).code(9).body(ResponseBody.create(MediaType.parse("text"), "")).protocol(Protocol.HTTP_1_0).message("Something went wrong - 100").header(Constants.KEY_MESSAGE, "Something went wrong - 100").build();
                        } else {
                            LoggerUtils.e("PublicKeyGen", String.format(Locale.US, "Code = %d Message = %s", Integer.valueOf(executeCall.code()), executeCall.message()));
                        }
                    } else {
                        GetEncapsulatedHeader getEncapsulatedHeader2 = GetEncapsulatedHeader.getInstance();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("d", string);
                        hashMap2.put("hsa", "SHA256");
                        for (Map.Entry<String, String> entry2 : getEncapsulatedHeader2.getEncapsulatedHeader(hashMap2, MapplsApiConfiguration.getInstance().getPublicKey()).entrySet()) {
                            newBuilder.header(entry2.getKey(), entry2.getValue());
                        }
                    }
                }
            } else {
                GetEncapsulatedHeader getEncapsulatedHeader3 = GetEncapsulatedHeader.getInstance();
                HashMap hashMap3 = new HashMap();
                hashMap3.put("d", string);
                hashMap3.put("hsa", "SHA256");
                for (Map.Entry<String, String> entry3 : getEncapsulatedHeader3.getEncapsulatedHeader(hashMap3, MapplsApiConfiguration.getInstance().getPublicKey()).entrySet()) {
                    newBuilder.header(entry3.getKey(), entry3.getValue());
                }
            }
        }
        if (MapplsApiConfiguration.getInstance().xDh != null) {
            newBuilder.header("x-dh", string);
        }
        return chain.proceed(newBuilder.build());
    }

    public void setClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }
}
