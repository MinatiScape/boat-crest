package com.coveiot.coveaccess.iotheartbeat;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SSaveHeartBeatData;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class IotHeartBeatApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<JsonObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6622a;

        public a(CoveApiListener coveApiListener) {
            this.f6622a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JsonObject> call, Throwable th) {
            this.f6622a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful()) {
                SaveHeartBeatDataRes saveHeartBeatDataRes = new SaveHeartBeatDataRes(response.code());
                saveHeartBeatDataRes.setMessage(response.body().get(Constants.KEY_MESSAGE).getAsString());
                saveHeartBeatDataRes.setStatus(response.body().get(NotificationCompat.CATEGORY_STATUS).getAsString());
                this.f6622a.onSuccess(saveHeartBeatDataRes);
                return;
            }
            int code = response.code();
            this.f6622a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(SSaveHeartBeatData sSaveHeartBeatData, CoveApiListener<SaveHeartBeatDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().saveIotHeartBeatData(map, sSaveHeartBeatData).enqueue(new a(coveApiListener));
    }

    public static void saveHeartBeatData(SSaveHeartBeatData sSaveHeartBeatData, CoveApiListener<SaveHeartBeatDataRes, CoveApiErrorModel> coveApiListener) {
        a(sSaveHeartBeatData, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveHeartBeatData(HashMap<String, String> hashMap, SSaveHeartBeatData sSaveHeartBeatData, CoveApiListener<SaveHeartBeatDataRes, CoveApiErrorModel> coveApiListener) {
        a(sSaveHeartBeatData, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
