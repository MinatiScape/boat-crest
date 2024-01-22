package com.coveiot.coveaccess.fitnesscomputeddataapi;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.ApiException;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class FitnessComputedDataApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<JsonObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6570a;

        public a(CoveApiListener coveApiListener) {
            this.f6570a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JsonObject> call, Throwable th) {
            this.f6570a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful()) {
                SaveFitnessComputedDataResponse saveFitnessComputedDataResponse = new SaveFitnessComputedDataResponse(response.code());
                saveFitnessComputedDataResponse.setMessage(response.body().get(Constants.KEY_MESSAGE).getAsString());
                saveFitnessComputedDataResponse.setStatus(response.body().get(NotificationCompat.CATEGORY_STATUS).getAsString());
                this.f6570a.onSuccess(saveFitnessComputedDataResponse);
                return;
            }
            int code = response.code();
            this.f6570a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<GetFitnessComputedDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6571a;

        public b(CoveApiListener coveApiListener) {
            this.f6571a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GetFitnessComputedDataResponse> call, Throwable th) {
            this.f6571a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GetFitnessComputedDataResponse> call, Response<GetFitnessComputedDataResponse> response) {
            if (response.isSuccessful()) {
                this.f6571a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6571a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(SaveFitnessComputedDataRequest saveFitnessComputedDataRequest, CoveApiListener<SaveFitnessComputedDataResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().saveFitnessComputedData(map, saveFitnessComputedDataRequest).enqueue(new a(coveApiListener));
    }

    public static void b(String str, CoveApiListener<GetFitnessComputedDataResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (!CoveUtil.isEmpty(str)) {
            CoveApi.getService().getFitnessComputedDataFromSever(map, str).enqueue(new b(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void getFitnessComputedDataFromServer(String str, CoveApiListener<GetFitnessComputedDataResponse, CoveApiErrorModel> coveApiListener) {
        b(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveFitnesComputedData(SaveFitnessComputedDataRequest saveFitnessComputedDataRequest, CoveApiListener<SaveFitnessComputedDataResponse, CoveApiErrorModel> coveApiListener) {
        a(saveFitnessComputedDataRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getFitnessComputedDataFromServer(HashMap<String, String> hashMap, String str, CoveApiListener<GetFitnessComputedDataResponse, CoveApiErrorModel> coveApiListener) {
        b(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveFitnesComputedData(HashMap<String, String> hashMap, SaveFitnessComputedDataRequest saveFitnessComputedDataRequest, CoveApiListener<SaveFitnessComputedDataResponse, CoveApiErrorModel> coveApiListener) {
        a(saveFitnessComputedDataRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
