package com.coveiot.coveaccess.hrv;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.hrv.model.HrvData;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.HrvDataBean;
import com.coveiot.coveaccess.model.server.SGetHrvDataRes;
import com.coveiot.coveaccess.model.server.SSaveHrvDataReq;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class HrvApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ActivityRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6611a;

        public a(CoveApiListener coveApiListener) {
            this.f6611a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityRes> call, Throwable th) {
            this.f6611a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityRes> call, Response<ActivityRes> response) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        this.f6611a.onSuccess(new SaveHrvDataRes(response.code()));
                        return;
                    } else {
                        this.f6611a.onError(new CoveApiErrorModel(response.body().getMessage(), response.code()));
                        return;
                    }
                }
                int code = response.code();
                this.f6611a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6611a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetHrvDataRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6612a;

        public b(CoveApiListener coveApiListener) {
            this.f6612a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetHrvDataRes> call, Throwable th) {
            this.f6612a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetHrvDataRes> call, Response<SGetHrvDataRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                ArrayList arrayList = new ArrayList();
                GetHrvDataRes getHrvDataRes = new GetHrvDataRes(response.code());
                if (response.body().getData() != null && !CoveUtil.isEmpty(response.body().getData().getFitnessData())) {
                    for (HrvDataBean hrvDataBean : response.body().getData().getFitnessData()) {
                        arrayList.add(HrvConvertionAdapter.getHrvDataFrom(hrvDataBean));
                    }
                    getHrvDataRes.setmHrvDataList(arrayList);
                    this.f6612a.onSuccess(getHrvDataRes);
                    return;
                }
                this.f6612a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            this.f6612a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<SaveHrvDataRes, CoveApiErrorModel> coveApiListener, SSaveHrvDataReq sSaveHrvDataReq, Map<String, String> map) {
        Call<ActivityRes> postHrvDataToServer = CoveApi.getService().postHrvDataToServer(map, sSaveHrvDataReq);
        if (CoveApi.getInstance().isEgApp()) {
            postHrvDataToServer = CoveApi.getService().postEgHrvDataToServer(map, sSaveHrvDataReq);
        }
        postHrvDataToServer.enqueue(new a(coveApiListener));
    }

    public static void b(String str, String str2, boolean z, CoveApiListener<GetHrvDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getHrvDataFromServer(map, str, str2, "HRV", z).enqueue(new b(coveApiListener));
    }

    public static void getHrvDataFromServer(String str, String str2, boolean z, CoveApiListener<GetHrvDataRes, CoveApiErrorModel> coveApiListener) {
        b(str, str2, z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveHrvHistoryData(SaveHrvDataReq saveHrvDataReq, CoveApiListener<SaveHrvDataRes, CoveApiErrorModel> coveApiListener) {
        SSaveHrvDataReq sSaveHrvDataReq = new SSaveHrvDataReq();
        ArrayList arrayList = new ArrayList();
        if (!CoveUtil.isEmpty(saveHrvDataReq.getmHrvDataList())) {
            for (HrvData hrvData : saveHrvDataReq.getmHrvDataList()) {
                arrayList.add(HrvConvertionAdapter.getHrvDataBeanFrom(hrvData));
            }
        }
        sSaveHrvDataReq.setFitnessData(arrayList);
        a(coveApiListener, sSaveHrvDataReq, CoveApi.getCustomHeaders());
    }

    public static void getHrvDataFromServer(HashMap<String, String> hashMap, String str, String str2, boolean z, CoveApiListener<GetHrvDataRes, CoveApiErrorModel> coveApiListener) {
        b(str, str2, z, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveHrvHistoryData(HashMap<String, String> hashMap, SaveHrvDataReq saveHrvDataReq, CoveApiListener<SaveHrvDataRes, CoveApiErrorModel> coveApiListener) {
        SSaveHrvDataReq sSaveHrvDataReq = new SSaveHrvDataReq();
        ArrayList arrayList = new ArrayList();
        if (!CoveUtil.isEmpty(saveHrvDataReq.getmHrvDataList())) {
            for (HrvData hrvData : saveHrvDataReq.getmHrvDataList()) {
                arrayList.add(HrvConvertionAdapter.getHrvDataBeanFrom(hrvData));
            }
        }
        sSaveHrvDataReq.setFitnessData(arrayList);
        a(coveApiListener, sSaveHrvDataReq, CoveUtil.getRevisedHeaders(hashMap));
    }
}
