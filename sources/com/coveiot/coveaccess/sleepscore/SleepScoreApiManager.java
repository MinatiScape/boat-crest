package com.coveiot.coveaccess.sleepscore;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveApiService;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class SleepScoreApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SleepScoreApiRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6708a;

        public a(CoveApiListener coveApiListener) {
            this.f6708a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SleepScoreApiRes> call, Throwable th) {
            this.f6708a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SleepScoreApiRes> call, Response<SleepScoreApiRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6708a.onSuccess(response.body());
            } else {
                this.f6708a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    public static void a(@NonNull SleepScoreApiReq sleepScoreApiReq, CoveApiListener<SleepScoreApiRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApiService service = CoveApi.getService();
        service.getSleepScore(map, CoveApi.getInstance().getGateWayUrl() + "v1/algosrv/sleep/score", sleepScoreApiReq).enqueue(new a(coveApiListener));
    }

    public static void sendDataForSleepQualityCal(HashMap<String, String> hashMap, @NonNull SleepScoreApiReq sleepScoreApiReq, CoveApiListener<SleepScoreApiRes, CoveApiErrorModel> coveApiListener) {
        a(sleepScoreApiReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendDataForSleepQualityCal(@NonNull SleepScoreApiReq sleepScoreApiReq, CoveApiListener<SleepScoreApiRes, CoveApiErrorModel> coveApiListener) {
        a(sleepScoreApiReq, coveApiListener, CoveApi.getCustomHeaders());
    }
}
