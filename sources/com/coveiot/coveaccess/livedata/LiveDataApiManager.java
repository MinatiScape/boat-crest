package com.coveiot.coveaccess.livedata;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class LiveDataApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6634a;

        public a(CoveApiListener coveApiListener) {
            this.f6634a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6634a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.code() == 200) {
                this.f6634a.onSuccess(new SaveLiveHealthDataRes(response.code()));
            }
        }
    }

    public static void a(SaveLiveHealthDataReq saveLiveHealthDataReq, CoveApiListener<SaveLiveHealthDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        Call<JSONObject> saveLiveHealthData = CoveApi.getService().saveLiveHealthData(map, saveLiveHealthDataReq.getLiveHealthDataModel());
        if (CoveApi.getInstance().isEgApp()) {
            saveLiveHealthData = CoveApi.getService().saveEgLiveHealthData(map, saveLiveHealthDataReq.getLiveHealthDataModel());
        }
        saveLiveHealthData.enqueue(new a(coveApiListener));
    }

    public static void saveLiveHealthData(SaveLiveHealthDataReq saveLiveHealthDataReq, CoveApiListener<SaveLiveHealthDataRes, CoveApiErrorModel> coveApiListener) {
        a(saveLiveHealthDataReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveLiveHealthData(HashMap<String, String> hashMap, SaveLiveHealthDataReq saveLiveHealthDataReq, CoveApiListener<SaveLiveHealthDataRes, CoveApiErrorModel> coveApiListener) {
        a(saveLiveHealthDataReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
