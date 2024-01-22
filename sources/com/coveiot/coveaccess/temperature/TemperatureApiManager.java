package com.coveiot.coveaccess.temperature;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetTemperatureDataRes;
import com.coveiot.coveaccess.model.server.SSaveTemperatureDataReq;
import com.coveiot.coveaccess.model.server.TemperatureDataBean;
import com.coveiot.coveaccess.temperature.model.TemperatureData;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class TemperatureApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ActivityRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6794a;

        public a(CoveApiListener coveApiListener) {
            this.f6794a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityRes> call, Throwable th) {
            this.f6794a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityRes> call, Response<ActivityRes> response) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        this.f6794a.onSuccess(new SaveTemperatureDataRes(response.code()));
                        return;
                    } else {
                        this.f6794a.onError(new CoveApiErrorModel(response.body().getMessage(), response.code()));
                        return;
                    }
                }
                int code = response.code();
                this.f6794a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6794a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetTemperatureDataRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6795a;

        public b(CoveApiListener coveApiListener) {
            this.f6795a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetTemperatureDataRes> call, Throwable th) {
            this.f6795a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetTemperatureDataRes> call, Response<SGetTemperatureDataRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                GetTemperatureDataRes getTemperatureDataRes = new GetTemperatureDataRes(response.code());
                ArrayList arrayList = new ArrayList();
                if (response.body().getData() != null && !CoveUtil.isEmpty(response.body().getData().getFitnessData())) {
                    for (TemperatureDataBean temperatureDataBean : response.body().getData().getFitnessData()) {
                        arrayList.add(TemperatureUtils.getServerTemperatureDatafrom(temperatureDataBean));
                    }
                    getTemperatureDataRes.setTemperatureDataList(arrayList);
                    this.f6795a.onSuccess(getTemperatureDataRes);
                    return;
                } else if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6795a.onSuccess(null);
                    return;
                } else {
                    this.f6795a.onError(new CoveApiErrorModel(response.body().getMessage()));
                    return;
                }
            }
            this.f6795a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    private static void getTemperatureDataFromServerL(String str, String str2, boolean z, CoveApiListener<GetTemperatureDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getTemperatureDataFromServer(map, str, str2, "BODY_TEMPERATURE", z).enqueue(new b(coveApiListener));
    }

    public static void getTemperatureFromServer(String str, String str2, boolean z, CoveApiListener<GetTemperatureDataRes, CoveApiErrorModel> coveApiListener) {
        getTemperatureDataFromServerL(str, str2, z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveTemperatureHistoryData(SaveTemperatureDataReq saveTemperatureDataReq, CoveApiListener<SaveTemperatureDataRes, CoveApiErrorModel> coveApiListener) {
        SSaveTemperatureDataReq sSaveTemperatureDataReq = new SSaveTemperatureDataReq();
        ArrayList arrayList = new ArrayList();
        if (!CoveUtil.isEmpty(saveTemperatureDataReq.getTemperatureDataList())) {
            for (TemperatureData temperatureData : saveTemperatureDataReq.getTemperatureDataList()) {
                arrayList.add(TemperatureUtils.getServerTemperatureDataBeanfrom(temperatureData));
            }
        }
        sSaveTemperatureDataReq.setFitnessData(arrayList);
        saveTemperatureHistoryDataL(coveApiListener, sSaveTemperatureDataReq, CoveApi.getCustomHeaders());
    }

    private static void saveTemperatureHistoryDataL(CoveApiListener<SaveTemperatureDataRes, CoveApiErrorModel> coveApiListener, SSaveTemperatureDataReq sSaveTemperatureDataReq, Map<String, String> map) {
        Call<ActivityRes> postTemperatureDataToServer = CoveApi.getService().postTemperatureDataToServer(map, sSaveTemperatureDataReq);
        if (CoveApi.getInstance().isEgApp()) {
            postTemperatureDataToServer = CoveApi.getService().postEgTemperatureDataToServer(map, sSaveTemperatureDataReq);
        }
        postTemperatureDataToServer.enqueue(new a(coveApiListener));
    }

    public static void getTemperatureFromServer(HashMap<String, String> hashMap, String str, String str2, boolean z, CoveApiListener<GetTemperatureDataRes, CoveApiErrorModel> coveApiListener) {
        getTemperatureDataFromServerL(str, str2, z, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveTemperatureHistoryData(HashMap<String, String> hashMap, SaveTemperatureDataReq saveTemperatureDataReq, CoveApiListener<SaveTemperatureDataRes, CoveApiErrorModel> coveApiListener) {
        SSaveTemperatureDataReq sSaveTemperatureDataReq = new SSaveTemperatureDataReq();
        ArrayList arrayList = new ArrayList();
        if (!CoveUtil.isEmpty(saveTemperatureDataReq.getTemperatureDataList())) {
            for (TemperatureData temperatureData : saveTemperatureDataReq.getTemperatureDataList()) {
                arrayList.add(TemperatureUtils.getServerTemperatureDataBeanfrom(temperatureData));
            }
        }
        sSaveTemperatureDataReq.setFitnessData(arrayList);
        saveTemperatureHistoryDataL(coveApiListener, sSaveTemperatureDataReq, CoveUtil.getRevisedHeaders(hashMap));
    }
}
