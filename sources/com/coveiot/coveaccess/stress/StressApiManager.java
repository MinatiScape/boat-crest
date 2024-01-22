package com.coveiot.coveaccess.stress;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetStressDataRes;
import com.coveiot.coveaccess.model.server.SSavePeriodicStressDataReq;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class StressApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ActivityRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6756a;

        public a(CoveApiListener coveApiListener) {
            this.f6756a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityRes> call, Throwable th) {
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityRes> call, Response<ActivityRes> response) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        this.f6756a.onSuccess(new SaveStressRes(response.code()));
                        return;
                    } else {
                        this.f6756a.onError(new CoveApiErrorModel(response.body().getMessage(), response.code()));
                        return;
                    }
                }
                int code = response.code();
                this.f6756a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6756a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetStressDataRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6757a;

        public b(CoveApiListener coveApiListener) {
            this.f6757a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetStressDataRes> call, Throwable th) {
            this.f6757a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetStressDataRes> call, Response<SGetStressDataRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                GetStressDataRes getStressDataRes = new GetStressDataRes(response.code());
                if (response.body().getData() != null && !CoveUtil.isEmpty(response.body().getData().getFitnessData())) {
                    getStressDataRes.setStressDataList(response.body().getData().getFitnessData());
                    this.f6757a.onSuccess(getStressDataRes);
                    return;
                } else if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6757a.onSuccess(null);
                    return;
                } else {
                    this.f6757a.onError(new CoveApiErrorModel(response.body().getMessage()));
                    return;
                }
            }
            this.f6757a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(SSavePeriodicStressDataReq sSavePeriodicStressDataReq, CoveApiListener<SaveStressRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().postStressDataToServer(map, sSavePeriodicStressDataReq).enqueue(new a(coveApiListener));
    }

    public static void b(String str, String str2, boolean z, CoveApiListener<GetStressDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getStressDataFromServer(map, str, str2, "STRESS", z).enqueue(new b(coveApiListener));
    }

    public static void getStressDataFromServer(String str, String str2, boolean z, CoveApiListener<GetStressDataRes, CoveApiErrorModel> coveApiListener) {
        b(str, str2, z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveStressPeriodicData(SSavePeriodicStressDataReq sSavePeriodicStressDataReq, CoveApiListener<SaveStressRes, CoveApiErrorModel> coveApiListener) {
        a(sSavePeriodicStressDataReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getStressDataFromServer(HashMap<String, String> hashMap, String str, String str2, boolean z, CoveApiListener<GetStressDataRes, CoveApiErrorModel> coveApiListener) {
        b(str, str2, z, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveStressPeriodicData(HashMap<String, String> hashMap, SSavePeriodicStressDataReq sSavePeriodicStressDataReq, CoveApiListener<SaveStressRes, CoveApiErrorModel> coveApiListener) {
        a(sSavePeriodicStressDataReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
