package com.coveiot.coveaccess.breachalert;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveBreachAlertApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<JsonObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6442a;

        public a(CoveApiListener coveApiListener) {
            this.f6442a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JsonObject> call, Throwable th) {
            this.f6442a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            int code = response.code();
            SaveLocationBreachInfoResponse saveLocationBreachInfoResponse = new SaveLocationBreachInfoResponse(code);
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    saveLocationBreachInfoResponse.setStatus(response.body().get(NotificationCompat.CATEGORY_STATUS).getAsString());
                    saveLocationBreachInfoResponse.setMessage(response.body().get(Constants.KEY_MESSAGE).getAsString());
                    this.f6442a.onSuccess(saveLocationBreachInfoResponse);
                    return;
                }
                this.f6442a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            this.f6442a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(SaveLocationBreachInfoRequest saveLocationBreachInfoRequest, CoveApiListener<SaveLocationBreachInfoResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().saveLocationBreaches(map, saveLocationBreachInfoRequest.f6443a).enqueue(new a(coveApiListener));
    }

    public static void saveLocationBreachInfo(SaveLocationBreachInfoRequest saveLocationBreachInfoRequest, CoveApiListener<SaveLocationBreachInfoResponse, CoveApiErrorModel> coveApiListener) {
        a(saveLocationBreachInfoRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveLocationBreachInfo(HashMap<String, String> hashMap, SaveLocationBreachInfoRequest saveLocationBreachInfoRequest, CoveApiListener<SaveLocationBreachInfoResponse, CoveApiErrorModel> coveApiListener) {
        a(saveLocationBreachInfoRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
