package com.coveiot.coveaccess.energyscore;

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
public class EnergyScoreApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<EnergyScoreApiRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6510a;

        public a(CoveApiListener coveApiListener) {
            this.f6510a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<EnergyScoreApiRes> call, Throwable th) {
            this.f6510a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<EnergyScoreApiRes> call, Response<EnergyScoreApiRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6510a.onSuccess(response.body());
            } else {
                this.f6510a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    public static void a(@NonNull EnergyScoreApiReq energyScoreApiReq, CoveApiListener<EnergyScoreApiRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApiService service = CoveApi.getService();
        service.getEnergyScore(map, CoveApi.getInstance().getGateWayUrl() + "v1/algosrv/energy/score", energyScoreApiReq).enqueue(new a(coveApiListener));
    }

    public static void sendDataForEnergyScoreCal(HashMap<String, String> hashMap, @NonNull EnergyScoreApiReq energyScoreApiReq, CoveApiListener<EnergyScoreApiRes, CoveApiErrorModel> coveApiListener) {
        a(energyScoreApiReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendDataForEnergyScoreCal(@NonNull EnergyScoreApiReq energyScoreApiReq, CoveApiListener<EnergyScoreApiRes, CoveApiErrorModel> coveApiListener) {
        a(energyScoreApiReq, coveApiListener, CoveApi.getCustomHeaders());
    }
}
