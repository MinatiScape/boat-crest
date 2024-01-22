package com.coveiot.coveaccess.bp;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.bp.model.BpData;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.BpDataBean;
import com.coveiot.coveaccess.model.server.SGetBpHeartRateDataRes;
import com.coveiot.coveaccess.model.server.SSaveBpDataReq;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class BpApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ActivityRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6436a;

        public a(CoveApiListener coveApiListener) {
            this.f6436a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityRes> call, Throwable th) {
            this.f6436a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityRes> call, Response<ActivityRes> response) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        this.f6436a.onSuccess(new SaveBpDataRes(response.code()));
                        return;
                    } else {
                        this.f6436a.onError(new CoveApiErrorModel(response.body().getMessage(), response.code()));
                        return;
                    }
                }
                int code = response.code();
                this.f6436a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6436a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetBpHeartRateDataRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6437a;

        public b(CoveApiListener coveApiListener) {
            this.f6437a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetBpHeartRateDataRes> call, Throwable th) {
            this.f6437a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetBpHeartRateDataRes> call, Response<SGetBpHeartRateDataRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                GetBpRateDataRes getBpRateDataRes = new GetBpRateDataRes(response.code());
                ArrayList arrayList = new ArrayList();
                if (response.body().getData() != null && !CoveUtil.isEmpty(response.body().getData().getFitnessData())) {
                    for (BpDataBean bpDataBean : response.body().getData().getFitnessData()) {
                        arrayList.add(BpHelper.getBpDataFrom(bpDataBean));
                    }
                    getBpRateDataRes.setBpDataList(arrayList);
                    this.f6437a.onSuccess(getBpRateDataRes);
                    return;
                } else if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6437a.onSuccess(null);
                    return;
                } else {
                    this.f6437a.onError(new CoveApiErrorModel(response.body().getMessage()));
                    return;
                }
            }
            this.f6437a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<SaveBpDataRes, CoveApiErrorModel> coveApiListener, SSaveBpDataReq sSaveBpDataReq, Map<String, String> map) {
        Call<ActivityRes> postBpDataToServer = CoveApi.getService().postBpDataToServer(map, sSaveBpDataReq);
        if (CoveApi.getInstance().isEgApp()) {
            postBpDataToServer = CoveApi.getService().postEgBpDataToServer(map, sSaveBpDataReq);
        }
        postBpDataToServer.enqueue(new a(coveApiListener));
    }

    public static void b(String str, String str2, boolean z, CoveApiListener<GetBpRateDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getBpDataFromServer(map, str, str2, EcgStyleReportUtil.UserInfoKey.BP, z).enqueue(new b(coveApiListener));
    }

    public static void getBpDataFromServer(String str, String str2, boolean z, CoveApiListener<GetBpRateDataRes, CoveApiErrorModel> coveApiListener) {
        b(str, str2, z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveBpHistoryData(SaveBpDataReq saveBpDataReq, CoveApiListener<SaveBpDataRes, CoveApiErrorModel> coveApiListener) {
        SSaveBpDataReq sSaveBpDataReq = new SSaveBpDataReq();
        ArrayList arrayList = new ArrayList();
        if (!CoveUtil.isEmpty(saveBpDataReq.getmBpDataList())) {
            for (BpData bpData : saveBpDataReq.getmBpDataList()) {
                arrayList.add(BpHelper.getBpDataBeanFrom(bpData));
            }
        }
        sSaveBpDataReq.setFitnessData(arrayList);
        a(coveApiListener, sSaveBpDataReq, CoveApi.getCustomHeaders());
    }

    public static void getBpDataFromServer(HashMap<String, String> hashMap, String str, String str2, boolean z, CoveApiListener<GetBpRateDataRes, CoveApiErrorModel> coveApiListener) {
        b(str, str2, z, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveBpHistoryData(HashMap<String, String> hashMap, SaveBpDataReq saveBpDataReq, CoveApiListener<SaveBpDataRes, CoveApiErrorModel> coveApiListener) {
        SSaveBpDataReq sSaveBpDataReq = new SSaveBpDataReq();
        ArrayList arrayList = new ArrayList();
        if (!CoveUtil.isEmpty(saveBpDataReq.getmBpDataList())) {
            for (BpData bpData : saveBpDataReq.getmBpDataList()) {
                arrayList.add(BpHelper.getBpDataBeanFrom(bpData));
            }
        }
        sSaveBpDataReq.setFitnessData(arrayList);
        a(coveApiListener, sSaveBpDataReq, CoveUtil.getRevisedHeaders(hashMap));
    }
}
