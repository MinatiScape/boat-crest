package com.coveiot.coveaccess.heartrate;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.HeartRateDataBean;
import com.coveiot.coveaccess.model.server.SGetHeartRateDataRes;
import com.coveiot.coveaccess.model.server.SPostHeartRateAndBP;
import com.coveiot.coveaccess.model.server.SSaveHeartRateDataReq;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class HearRateApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ActivityRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6603a;

        public a(CoveApiListener coveApiListener) {
            this.f6603a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityRes> call, Throwable th) {
            this.f6603a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityRes> call, Response<ActivityRes> response) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        this.f6603a.onSuccess(new SaveHeartRateRes(response.code()));
                        return;
                    } else {
                        this.f6603a.onError(new CoveApiErrorModel(response.body().getMessage(), response.code()));
                        return;
                    }
                }
                int code = response.code();
                this.f6603a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6603a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetHeartRateDataRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6604a;

        public b(CoveApiListener coveApiListener) {
            this.f6604a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetHeartRateDataRes> call, Throwable th) {
            this.f6604a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetHeartRateDataRes> call, Response<SGetHeartRateDataRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                GetHeartRateDataRes getHeartRateDataRes = new GetHeartRateDataRes(response.code());
                ArrayList arrayList = new ArrayList();
                if (response.body().getData() != null && !CoveUtil.isEmpty(response.body().getData().getFitnessData())) {
                    for (HeartRateDataBean heartRateDataBean : response.body().getData().getFitnessData()) {
                        arrayList.add(HeartRateUtils.getServerHeartDataBeanfrom(heartRateDataBean));
                    }
                    getHeartRateDataRes.setHeartRateDataList(arrayList);
                    this.f6604a.onSuccess(getHeartRateDataRes);
                    return;
                } else if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6604a.onSuccess(null);
                    return;
                } else {
                    this.f6604a.onError(new CoveApiErrorModel(response.body().getMessage()));
                    return;
                }
            }
            this.f6604a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6605a;

        public c(CoveApiListener coveApiListener) {
            this.f6605a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6605a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            this.f6605a.onSuccess(new SaveHeartRateAndBpRes(response.code()));
        }
    }

    public static void a(SaveHeartRateReq saveHeartRateReq, CoveApiListener<SaveHeartRateRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SSaveHeartRateDataReq sSaveHeartRateDataReq = new SSaveHeartRateDataReq();
        ArrayList arrayList = new ArrayList();
        if (!CoveUtil.isEmpty(saveHeartRateReq.getHeartRateDataList())) {
            for (HeartRateData heartRateData : saveHeartRateReq.getHeartRateDataList()) {
                arrayList.add(HeartRateUtils.getServerHeartDataBeanfrom(heartRateData));
            }
        }
        sSaveHeartRateDataReq.setFitnessData(arrayList);
        Call<ActivityRes> postHeartRateDataToServer = CoveApi.getService().postHeartRateDataToServer(map, sSaveHeartRateDataReq);
        if (CoveApi.getInstance().isEgApp()) {
            postHeartRateDataToServer = CoveApi.getService().postEgHeartRateDataToServer(map, sSaveHeartRateDataReq);
        }
        postHeartRateDataToServer.enqueue(new a(coveApiListener));
    }

    public static void b(SPostHeartRateAndBP sPostHeartRateAndBP, CoveApiListener<SaveHeartRateAndBpRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().postHeartRateAndBp(map, sPostHeartRateAndBP).enqueue(new c(coveApiListener));
    }

    public static void c(String str, String str2, boolean z, CoveApiListener<GetHeartRateDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getHeartRateDataFromServer(map, str, str2, EcgStyleReportUtil.UserInfoKey.HR, z).enqueue(new b(coveApiListener));
    }

    public static void getHeartRateDataFromServer(String str, String str2, boolean z, CoveApiListener<GetHeartRateDataRes, CoveApiErrorModel> coveApiListener) {
        c(str, str2, z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveHeartRateAndBpData(SPostHeartRateAndBP sPostHeartRateAndBP, CoveApiListener<SaveHeartRateAndBpRes, CoveApiErrorModel> coveApiListener) {
        b(sPostHeartRateAndBP, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveHeartRateData(SaveHeartRateReq saveHeartRateReq, CoveApiListener<SaveHeartRateRes, CoveApiErrorModel> coveApiListener) {
        a(saveHeartRateReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getHeartRateDataFromServer(HashMap<String, String> hashMap, String str, String str2, boolean z, CoveApiListener<GetHeartRateDataRes, CoveApiErrorModel> coveApiListener) {
        c(str, str2, z, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveHeartRateAndBpData(HashMap<String, String> hashMap, SPostHeartRateAndBP sPostHeartRateAndBP, CoveApiListener<SaveHeartRateAndBpRes, CoveApiErrorModel> coveApiListener) {
        b(sPostHeartRateAndBP, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveHeartRateData(HashMap<String, String> hashMap, SaveHeartRateReq saveHeartRateReq, CoveApiListener<SaveHeartRateRes, CoveApiErrorModel> coveApiListener) {
        a(saveHeartRateReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
