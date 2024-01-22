package com.coveiot.coveaccess.spo2;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetSpo2DataRes;
import com.coveiot.coveaccess.model.server.SSaveSpo2DataReq;
import com.coveiot.coveaccess.model.server.Spo2DataBean;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class Spo2ApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ActivityRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6736a;

        public a(CoveApiListener coveApiListener) {
            this.f6736a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityRes> call, Throwable th) {
            this.f6736a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityRes> call, Response<ActivityRes> response) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        this.f6736a.onSuccess(new SaveSpo2DataRes(response.code()));
                        return;
                    } else {
                        this.f6736a.onError(new CoveApiErrorModel(response.body().getMessage(), response.code()));
                        return;
                    }
                }
                int code = response.code();
                this.f6736a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6736a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetSpo2DataRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6737a;

        public b(CoveApiListener coveApiListener) {
            this.f6737a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetSpo2DataRes> call, Throwable th) {
            this.f6737a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetSpo2DataRes> call, Response<SGetSpo2DataRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                GetSpo2DataRes getSpo2DataRes = new GetSpo2DataRes(response.code());
                ArrayList arrayList = new ArrayList();
                if (response.body().getData() != null && !CoveUtil.isEmpty(response.body().getData().getFitnessData())) {
                    for (Spo2DataBean spo2DataBean : response.body().getData().getFitnessData()) {
                        arrayList.add(Spo2Utils.getServerSpo2Datafrom(spo2DataBean));
                    }
                    getSpo2DataRes.setSpo2DataList(arrayList);
                    this.f6737a.onSuccess(getSpo2DataRes);
                    return;
                } else if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6737a.onSuccess(null);
                    return;
                } else {
                    this.f6737a.onError(new CoveApiErrorModel(response.body().getMessage()));
                    return;
                }
            }
            this.f6737a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(String str, String str2, boolean z, CoveApiListener<GetSpo2DataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getSpo2DataFromServer(map, str, str2, "SPO2", z).enqueue(new b(coveApiListener));
    }

    public static void b(CoveApiListener<SaveSpo2DataRes, CoveApiErrorModel> coveApiListener, SSaveSpo2DataReq sSaveSpo2DataReq, Map<String, String> map) {
        Call<ActivityRes> postSpo2DataToServer = CoveApi.getService().postSpo2DataToServer(map, sSaveSpo2DataReq);
        if (CoveApi.getInstance().isEgApp()) {
            postSpo2DataToServer = CoveApi.getService().postSpo2DataToServer(map, sSaveSpo2DataReq);
        }
        postSpo2DataToServer.enqueue(new a(coveApiListener));
    }

    public static void getSpo2FromServer(String str, String str2, boolean z, CoveApiListener<GetSpo2DataRes, CoveApiErrorModel> coveApiListener) {
        a(str, str2, z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveSpo2HistoryData(SaveSpo2DataReq saveSpo2DataReq, CoveApiListener<SaveSpo2DataRes, CoveApiErrorModel> coveApiListener) {
        SSaveSpo2DataReq sSaveSpo2DataReq = new SSaveSpo2DataReq();
        ArrayList arrayList = new ArrayList();
        if (!CoveUtil.isEmpty(saveSpo2DataReq.getSpo2DataList())) {
            for (Spo2Data spo2Data : saveSpo2DataReq.getSpo2DataList()) {
                arrayList.add(Spo2Utils.getServerSpo2DataBeanfrom(spo2Data));
            }
        }
        sSaveSpo2DataReq.setFitnessData(arrayList);
        b(coveApiListener, sSaveSpo2DataReq, CoveApi.getCustomHeaders());
    }

    public static void getSpo2FromServer(HashMap<String, String> hashMap, String str, String str2, boolean z, CoveApiListener<GetSpo2DataRes, CoveApiErrorModel> coveApiListener) {
        a(str, str2, z, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveSpo2HistoryData(HashMap<String, String> hashMap, SaveSpo2DataReq saveSpo2DataReq, CoveApiListener<SaveSpo2DataRes, CoveApiErrorModel> coveApiListener) {
        SSaveSpo2DataReq sSaveSpo2DataReq = new SSaveSpo2DataReq();
        ArrayList arrayList = new ArrayList();
        if (!CoveUtil.isEmpty(saveSpo2DataReq.getSpo2DataList())) {
            for (Spo2Data spo2Data : saveSpo2DataReq.getSpo2DataList()) {
                arrayList.add(Spo2Utils.getServerSpo2DataBeanfrom(spo2Data));
            }
        }
        sSaveSpo2DataReq.setFitnessData(arrayList);
        b(coveApiListener, sSaveSpo2DataReq, CoveUtil.getRevisedHeaders(hashMap));
    }
}
