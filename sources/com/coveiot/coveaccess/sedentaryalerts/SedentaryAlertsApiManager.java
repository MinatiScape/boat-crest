package com.coveiot.coveaccess.sedentaryalerts;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sedentaryalerts.model.GetSedentaryAlertsDataRes;
import com.coveiot.coveaccess.sedentaryalerts.model.SGetSedentaryAlertsDataRes;
import com.coveiot.coveaccess.sedentaryalerts.model.SSaveSedentaryAlertsDataReq;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class SedentaryAlertsApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ActivityRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6698a;

        public a(CoveApiListener coveApiListener) {
            this.f6698a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityRes> call, Throwable th) {
            this.f6698a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityRes> call, Response<ActivityRes> response) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        this.f6698a.onSuccess(new SaveSedentaryAlertsDataRes(response.code()));
                        return;
                    } else {
                        this.f6698a.onError(new CoveApiErrorModel(response.body().getMessage(), response.code()));
                        return;
                    }
                }
                int code = response.code();
                this.f6698a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6698a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetSedentaryAlertsDataRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6699a;

        public b(CoveApiListener coveApiListener) {
            this.f6699a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetSedentaryAlertsDataRes> call, Throwable th) {
            this.f6699a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetSedentaryAlertsDataRes> call, Response<SGetSedentaryAlertsDataRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                GetSedentaryAlertsDataRes getSedentaryAlertsDataRes = new GetSedentaryAlertsDataRes(response.code());
                if (response.body().getData() != null && !CoveUtil.isEmpty(response.body().getData().getSedentaryAlertsDataBeans())) {
                    getSedentaryAlertsDataRes.setSedentaryAlertsDataBeanList(response.body().getData().getSedentaryAlertsDataBeans());
                    this.f6699a.onSuccess(getSedentaryAlertsDataRes);
                    return;
                } else if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6699a.onSuccess(null);
                    return;
                } else {
                    this.f6699a.onError(new CoveApiErrorModel(response.body().getMessage()));
                    return;
                }
            }
            this.f6699a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<SaveSedentaryAlertsDataRes, CoveApiErrorModel> coveApiListener, SSaveSedentaryAlertsDataReq sSaveSedentaryAlertsDataReq, Map<String, String> map) {
        CoveApi.getService().postSedentaryAlertsDataToServer(map, sSaveSedentaryAlertsDataReq).enqueue(new a(coveApiListener));
    }

    public static void b(String str, String str2, boolean z, CoveApiListener<GetSedentaryAlertsDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getSedentaryAlertsDataFromServer(map, str, str2, "SED_ALERTS", z).enqueue(new b(coveApiListener));
    }

    public static void getSedentaryAlertsDataFromServer(String str, String str2, boolean z, CoveApiListener<GetSedentaryAlertsDataRes, CoveApiErrorModel> coveApiListener) {
        b(str, str2, z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveSedentaryAlertsData(SSaveSedentaryAlertsDataReq sSaveSedentaryAlertsDataReq, CoveApiListener<SaveSedentaryAlertsDataRes, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, sSaveSedentaryAlertsDataReq, CoveApi.getCustomHeaders());
    }
}
