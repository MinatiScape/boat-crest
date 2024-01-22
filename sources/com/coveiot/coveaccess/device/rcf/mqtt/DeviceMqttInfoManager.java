package com.coveiot.coveaccess.device.rcf.mqtt;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.rcf.mqtt.model.GetIotMqttCredInfoResponse;
import com.coveiot.coveaccess.device.rcf.mqtt.model.GetIotMqttInfoResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetIotMqttCredInfoResponse;
import com.coveiot.coveaccess.model.server.SGetIotMqttInfoResponse;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class DeviceMqttInfoManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SGetIotMqttInfoResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6471a;

        public a(CoveApiListener coveApiListener) {
            this.f6471a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetIotMqttInfoResponse> call, Throwable th) {
            this.f6471a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetIotMqttInfoResponse> call, Response<SGetIotMqttInfoResponse> response) {
            if (response.isSuccessful() && response.body() != null && response.body().data != null && response.body().data.s2tCmd != null) {
                GetIotMqttInfoResponse getIotMqttInfoResponse = new GetIotMqttInfoResponse(Integer.valueOf(response.code()));
                getIotMqttInfoResponse.clientId = response.body().data.clientId;
                getIotMqttInfoResponse.host = response.body().data.host;
                getIotMqttInfoResponse.keepAlive = response.body().data.keepAlive;
                getIotMqttInfoResponse.port = response.body().data.port;
                getIotMqttInfoResponse.message = response.message();
                getIotMqttInfoResponse.s2tCmd = response.body().data.s2tCmd;
                getIotMqttInfoResponse.setEntitySports(response.body().data.entitySports);
                this.f6471a.onSuccess(getIotMqttInfoResponse);
                return;
            }
            int code = response.code();
            this.f6471a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetIotMqttCredInfoResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6472a;

        public b(CoveApiListener coveApiListener) {
            this.f6472a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetIotMqttCredInfoResponse> call, Throwable th) {
            this.f6472a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetIotMqttCredInfoResponse> call, Response<SGetIotMqttCredInfoResponse> response) {
            if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                GetIotMqttCredInfoResponse getIotMqttCredInfoResponse = new GetIotMqttCredInfoResponse(Integer.valueOf(response.code()));
                getIotMqttCredInfoResponse.message = response.body().message;
                getIotMqttCredInfoResponse.caCertPem = response.body().data.caCertPem;
                getIotMqttCredInfoResponse.certPem = response.body().data.certPem;
                getIotMqttCredInfoResponse.pvtKeyPem = response.body().data.pvtKeyPem;
                getIotMqttCredInfoResponse.keystoreP12 = response.body().data.keystoreP12;
                this.f6472a.onSuccess(getIotMqttCredInfoResponse);
                return;
            }
            int code = response.code();
            this.f6472a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(@NonNull CoveApiListener<GetIotMqttCredInfoResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map, String str) {
        CoveApi.getService().getIotMqttCredInfoFromServer(map, str).enqueue(new b(coveApiListener));
    }

    public static void b(@NonNull CoveApiListener<GetIotMqttInfoResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map, String str) {
        CoveApi.getService().getIotMqttInfoFromServer(map, str).enqueue(new a(coveApiListener));
    }

    public static void getIotMqttCredInfo(String str, HashMap<String, String> hashMap, @NonNull CoveApiListener<GetIotMqttCredInfoResponse, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str);
    }

    public static void getIotMqttInfo(String str, HashMap<String, String> hashMap, @NonNull CoveApiListener<GetIotMqttInfoResponse, CoveApiErrorModel> coveApiListener) {
        b(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str);
    }

    public static void getIotMqttCredInfo(String str, @NonNull CoveApiListener<GetIotMqttCredInfoResponse, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders(), str);
    }

    public static void getIotMqttInfo(String str, @NonNull CoveApiListener<GetIotMqttInfoResponse, CoveApiErrorModel> coveApiListener) {
        b(coveApiListener, CoveApi.getCustomHeaders(), str);
    }
}
