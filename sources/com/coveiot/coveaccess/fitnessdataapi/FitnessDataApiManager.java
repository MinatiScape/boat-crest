package com.coveiot.coveaccess.fitnessdataapi;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetSleepData;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class FitnessDataApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<JsonObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6588a;

        public a(CoveApiListener coveApiListener) {
            this.f6588a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JsonObject> call, Throwable th) {
            this.f6588a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful()) {
                SaveSleepDataResponse saveSleepDataResponse = new SaveSleepDataResponse(response.code());
                saveSleepDataResponse.setMessage(response.body().get(Constants.KEY_MESSAGE).getAsString());
                saveSleepDataResponse.setStatus(response.body().get(NotificationCompat.CATEGORY_STATUS).getAsString());
                this.f6588a.onSuccess(saveSleepDataResponse);
                return;
            }
            int code = response.code();
            this.f6588a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetSleepData> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6589a;

        public b(CoveApiListener coveApiListener) {
            this.f6589a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetSleepData> call, Throwable th) {
            this.f6589a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetSleepData> call, Response<SGetSleepData> response) {
            if (response.isSuccessful()) {
                this.f6589a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6589a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(SaveSleepDataRequest saveSleepDataRequest, CoveApiListener<SaveSleepDataResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        Call<JsonObject> saveSleepDataV2 = CoveApi.getService().saveSleepDataV2(map, saveSleepDataRequest.f6590a);
        if (CoveApi.getInstance().isEgApp()) {
            saveSleepDataV2 = CoveApi.getService().saveEgSleepDataV2(map, saveSleepDataRequest.f6590a);
        }
        saveSleepDataV2.enqueue(new a(coveApiListener));
    }

    public static void b(String str, String str2, boolean z, CoveApiListener<SGetSleepData, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getSleepDataV2For(map, str, str2, "SLEEP", z).enqueue(new b(coveApiListener));
    }

    public static void getSleepDataFromServer(String str, String str2, boolean z, CoveApiListener<SGetSleepData, CoveApiErrorModel> coveApiListener) {
        b(str, str2, z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveSleepData(SaveSleepDataRequest saveSleepDataRequest, CoveApiListener<SaveSleepDataResponse, CoveApiErrorModel> coveApiListener) {
        a(saveSleepDataRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getSleepDataFromServer(HashMap<String, String> hashMap, String str, String str2, boolean z, CoveApiListener<SGetSleepData, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        b(str, str2, z, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveSleepData(HashMap<String, String> hashMap, SaveSleepDataRequest saveSleepDataRequest, CoveApiListener<SaveSleepDataResponse, CoveApiErrorModel> coveApiListener) {
        a(saveSleepDataRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
